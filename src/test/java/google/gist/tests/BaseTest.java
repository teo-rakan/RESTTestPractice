package google.gist.tests;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import google.gist.utils.PropertyManager;
import org.testng.annotations.BeforeTest;

import static com.jayway.restassured.RestAssured.given;

public class BaseTest {

    private final String token = PropertyManager.get("git.token");

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://api.github.com/gists";
    }

    RequestSpecification getGivenAuth() {
        return given().auth().oauth2(token);
    }
}
