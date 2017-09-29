package google.gist.tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class SimpleGetTest {

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://api.github.com";
    }

    @Test
    public void checkStatusCodeTest() {
        Response response = given().get("/gists/public").andReturn();
        int actualStatusCode = response.statusCode();

        Assert.assertEquals(actualStatusCode, 200);
    }
}
