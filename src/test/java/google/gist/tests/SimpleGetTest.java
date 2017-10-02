package google.gist.tests;

import com.jayway.restassured.response.Response;
import google.gist.model.Gist;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetTest extends BaseTest {

    private final String gistId = "989aaca6beb2d3796f07ea4040598c04";

    @Test(description = "Checks status code for getting a user's gists")
    public void checkGetGistsStatusCodeTest() {
        Response response = getGivenAuth().get("/gists").andReturn();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "List a user's gists")
    public void listUsersGistsTest() {
        Response response = getGivenAuth().get("/gists").andReturn();
        Gist[] gists = response.as(Gist[].class);
        Assert.assertTrue(gists.length > 0);
    }

    @Test(description = "List all public gists")
    public void listAllPublicGistsTest() {
        Response response = getGivenAuth().get("/gists/public").andReturn();
        Gist[] gists = response.as(Gist[].class);
        Assert.assertTrue(gists.length > 0);
    }

    @Test(description = "Get a specific revision of a gist")
    public void getSpecificGistRevisionTest() {
        String revisionSha = "5a69ffef97451a6c54da9de0a4676a2ba08d0d43";
        Response response = getGivenAuth().get("/gists/" + gistId + "/" + revisionSha).andReturn();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "List gist commits")
    public void listGistCommits() {
        Response response = getGivenAuth().get("/gists/" + gistId + "/commits").andReturn();
        Assert.assertEquals(response.statusCode(), 200);
    }
}
