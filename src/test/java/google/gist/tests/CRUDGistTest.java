package google.gist.tests;

import com.jayway.restassured.response.Response;
import google.gist.model.Gist;
import google.gist.model.GistFile;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CRUDGistTest extends BaseTest {

    private String newGistId;

    @Test
    public void createGistTest() {
        Response response  = getGivenAuth().body(getGist()).with().contentType("application/json").post("/gists").andReturn();
        Assert.assertEquals(response.statusCode(), 201);

        //save to update and remove this gist later
        newGistId = response.as(Gist.class).getId();
    }

    @Test(dependsOnMethods = "createGistTest")
    public void updateGistTest() {
        Gist gist = getGist();
        String oldDescription = gist.getDescription();
        String newDescription = "Updated description";
        String actualDescription;
        Response response;

        gist.setDescription(newDescription);
        response = getGivenAuth().body(gist).with().contentType("application/json").patch("/gists/" + newGistId).andReturn();
        actualDescription = response.as(Gist.class).getDescription();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotEquals(actualDescription, oldDescription);
        Assert.assertEquals(actualDescription, newDescription);
    }

    @Test(dependsOnMethods = "updateGistTest")
    public void removeGistTest() {
        Response response = getGivenAuth().delete("/gists/" + newGistId).andReturn();
        Assert.assertEquals(response.statusCode(), 204);
    }

    private Gist getGist() {
        Gist gist = new Gist();
        gist.setDescription("The description for this gist");
        gist.setPublic(true);
        gist.setFile("test_file1.txt", new GistFile("String file content"));
        return gist;
    }
}
