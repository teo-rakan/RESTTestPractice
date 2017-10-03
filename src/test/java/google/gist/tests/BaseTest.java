package google.gist.tests;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import google.gist.utils.PropertyManager;

import static com.jayway.restassured.RestAssured.given;

class BaseTest {

    private final String token = PropertyManager.get("git.token");
    private final String baseUrl = "https://api.github.com/gists";
    private final RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(baseUrl).build();

    RequestSpecification getGivenAuth() {
        return given().spec(requestSpecification).auth().oauth2(token);
    }
}
