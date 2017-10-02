package google.gist.tests;

import com.jayway.restassured.response.Response;
import google.gist.model.Gist;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetTest extends BaseTest {

    @Test(description = "Checks status code for getting a user's gists")
    public void checkGetGistsStatusCodeTest() {
        Response response = getGivenAuth().get("/gists").andReturn();
        int actualStatusCode = response.statusCode();

        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test(description = "Get list a user's gists")
    public void checkGistsExistTest() {
        Response response = getGivenAuth().get("/gists").andReturn();
        Gist[] gists = response.as(Gist[].class);

        Assert.assertTrue(gists.length > 0);
    }
}
