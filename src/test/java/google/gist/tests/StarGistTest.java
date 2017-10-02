package google.gist.tests;

import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

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

    @Test(dependsOnMethods = "checkGistStaredTest", description = "Unstar a gist")
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
