package google.gist.tests;

import com.jayway.restassured.response.Response;
import google.gist.model.Gist;
import org.testng.Assert;
import org.testng.annotations.Test;

import static google.gist.utils.DateUtil.getToday;

public class StarGistTest extends BaseTest {

    private final String gistId = "989aaca6beb2d3796f07ea4040598c04";
    private final String url = "/gists/" + gistId + "/star";

    @Test(description = "Star a gist")
    public void starGistTest() {
        Response response  = getGivenAuth().put(url).andReturn();
        Assert.assertEquals(response.statusCode(), 204);
    }

    @Test(dependsOnMethods = "starGistTest", description = "Check if a gist is starred")
    public void checkGistStaredTest() {
        Response response  = getGivenAuth().get(url).andReturn();
        Assert.assertEquals(response.statusCode(), 204);
    }

    @Test(dependsOnMethods = "checkGistStaredTest", description = "List starred gists")
    public void listStarredGistsTest() {
        Response response  = getGivenAuth().param("since", getToday()).get("/gists/starred").andReturn();
        Gist[] gists = response.as(Gist[].class);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(gists.length > 0);
    }

    @Test(dependsOnMethods = "listStarredGistsTest", description = "Unstar a gist")
    public void unstarGistTest() {
        Response response  = getGivenAuth().delete(url).andReturn();
        Assert.assertEquals(response.statusCode(), 204);
    }

    @Test(dependsOnMethods = "unstarGistTest", description = "Check if a gist is starred")
    public void checkGistUnstaredTest() {
        Response response  = getGivenAuth().get(url).andReturn();
        Assert.assertEquals(response.statusCode(), 404);
    }

}
