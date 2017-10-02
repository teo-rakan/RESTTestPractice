package google.gist.tests;

import com.jayway.restassured.response.Response;
import google.gist.model.Gist;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ForkGistTest extends BaseTest {

    private final String gistId = "fd247d02f8a44ce745db";
    private String forkedGistId;

    @Test(description = "Fork a gist")
    public void createForkTest() {
        Response response  = getGivenAuth().post("/gists/" + gistId + "/forks").andReturn();
        Gist forkedGist = response.as(Gist.class);
        Assert.assertEquals(response.statusCode(), 201);
        forkedGistId = forkedGist.getId();
    }

    @Test(dependsOnMethods = "createForkTest", description = "List gist forks")
    public void checkForkListTest() {
        Response response  = getGivenAuth().get("/gists/" + gistId + "/forks").andReturn();
        final Gist[] forkedGists = response.as(Gist[].class);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(Arrays.stream(forkedGists).anyMatch(m -> m.getId().equals(forkedGistId)));
    }

    @Test(dependsOnMethods = "checkForkListTest", description = "Delete a fork gist")
    public void removeForkGistTest() {
        Response response = getGivenAuth().delete("/gists/" + forkedGistId).andReturn();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
