package google.gist.tests;

import com.jayway.restassured.response.Response;
import google.gist.model.Gist;
import google.gist.model.GistFile;
import org.testng.Assert;
import org.testng.annotations.Test;

// Create - Read - Update - Delete gist cycle
public class CRUDGistTest extends BaseTest {

    private String gistId;
    private final String fileName = "test_file.txt";

    @Test(description = "Create a gist")
    public void createGistTest() {
        Response response  = getGivenAuth().body(getGist()).with().contentType("application/json").post("/gists").andReturn();
        Assert.assertEquals(response.statusCode(), 201);

        //save to update and remove this gist later
        gistId = response.as(Gist.class).getId();
    }

    @Test(dependsOnMethods = "createGistTest", description = "Get a single gist")
    public void checkGistTest() {
        Response response = getGivenAuth().get("/gists/" + gistId).andReturn();
        Gist gist = response.as(Gist.class);
        Assert.assertTrue(gist.getFiles().containsKey(fileName));
    }

    @Test(dependsOnMethods = "checkGistTest", description = "Edit a gist")
    public void updateGistTest() {
        Gist gist = getGist();
        String oldDescription = gist.getDescription();
        String newDescription = "Updated description";
        String actualDescription;
        Response response;

        gist.setDescription(newDescription);
        response = getGivenAuth().body(gist).with().contentType("application/json").patch("/gists/" + gistId).andReturn();
        actualDescription = response.as(Gist.class).getDescription();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotEquals(actualDescription, oldDescription);
        Assert.assertEquals(actualDescription, newDescription);
    }

    @Test(dependsOnMethods = "updateGistTest", description = "Delete a gist")
    public void removeGistTest() {
        Response response = getGivenAuth().delete("/gists/" + gistId).andReturn();
        Assert.assertEquals(response.statusCode(), 204);
    }

    private Gist getGist() {
        Gist gist = new Gist();
        gist.setDescription("The description for this gist");
        gist.setPublic(true);
        gist.setFile(fileName, new GistFile("String file content"));
        return gist;
    }
}
