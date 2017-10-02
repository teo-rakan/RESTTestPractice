package google.gist.tests;

import com.jayway.restassured.response.Response;
import google.gist.model.Gist;
import google.gist.model.GistFile;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class CRUDGistTest extends BaseTest {

    private String newGistId;

    private Gist getDefaultGist() {
        Gist gist = new Gist();
        gist.setDescription("The description for this gist");
        gist.setPublic(true);
        gist.setFile("test_file1.txt", new GistFile("String file content"));
        return gist;
    }

    @Test
    public void createGistTest() {
        Gist gist = getDefaultGist();
        Response response  = given().auth().oauth2(token).body(gist).with().contentType("application/json").post("/gists").andReturn();

        Assert.assertEquals(response.statusCode(), 201);

        //save to update and remove this gist later
        newGistId = response.as(Gist.class).getId();
    }

    @Test(dependsOnMethods = "createGistTest")
    public void updateGistTest() {
        Gist gist = getDefaultGist();
        String oldDescription = gist.getDescription();
        String newDescription = "Updated description";
        String actualDescription;
        Response response;

        gist.setDescription(newDescription);
        response = given().auth().oauth2(token).body(gist).with().contentType("application/json").patch("/gists/" + newGistId).andReturn();
        actualDescription = response.as(Gist.class).getDescription();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotEquals(actualDescription, oldDescription);
        Assert.assertEquals(actualDescription, newDescription);
    }

    @Test(dependsOnMethods = "createGistTest")
    public void starGistTest() {
        Response response  = given().auth().oauth2(token).put("/gists/" + newGistId + "/star").andReturn();

        Assert.assertEquals(response.statusCode(), 204);
    }

    @Test(dependsOnMethods = "starGistTest")
    public void unstarGistTest() {
        Response response  = given().auth().oauth2(token).delete("/gists/" + newGistId + "/star").andReturn();

        Assert.assertEquals(response.statusCode(), 204);
    }

    @Test(dependsOnMethods = "updateGistTest")
    public void removeGistTest() {
        Response response = given().auth().oauth2(token).delete("/gists/" + newGistId).andReturn();

        Assert.assertEquals(response.statusCode(), 204);
    }
}
