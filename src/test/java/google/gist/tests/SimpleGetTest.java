package google.gist.tests;

import com.jayway.restassured.response.Response;
import google.gist.model.Gist;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class SimpleGetTest extends BaseTest {

    @Test
    public void checkGetGistsStatusCodeTest() {
        Response response = given().auth().oauth2(token).get("/gists").andReturn();
        int actualStatusCode = response.statusCode();

        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkGistsExistTest() {
        Response response = given().auth().oauth2(token).get("/gists").andReturn();
        Gist[] gists = response.as(Gist[].class);

        Assert.assertTrue(gists.length > 0);
    }

    @Test
    public void checkSingleGistTest() {
        String gistId = "989aaca6beb2d3796f07ea4040598c04";
        Response response = given().auth().oauth2(token).get("/gists/" + gistId).andReturn();
        Gist gist = response.as(Gist.class);

        Assert.assertTrue(gist.getId().equals(gistId));
    }
}
