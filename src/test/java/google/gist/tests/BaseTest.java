package google.gist.tests;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import google.gist.utils.PropertyManager;

import static com.jayway.restassured.RestAssured.given;

public class BaseTest {

    private final String token = PropertyManager.get("git.token");
    private final String baseUrl = "https://api.github.com/gists";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(baseUrl).build();

//    @BeforeTest
//    public void initTest() {
//        RestAssured.baseURI = "https://api.github.com/gists";
//    }

    RequestSpecification getGivenAuth() {
        return given().spec(requestSpecification).auth().oauth2(token);
    }
}
