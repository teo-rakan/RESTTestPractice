package jsonplaceholder.typicode.com.tests;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import jsonplaceholder.typicode.com.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RestAssuredUsersTest {

    private final String baseUrl = "http://jsonplaceholder.typicode.com/users";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(baseUrl).build();

    @Test
    public void checkStatusCodeTest() {
        Response response = given().spec(requestSpecification).get().andReturn();
        int actualStatusCode = response.statusCode();

        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeaderTest() {
        Response response = given().spec(requestSpecification).get().andReturn();
        String actualContentType = response.header("Content-Type");

        Assert.assertEquals(actualContentType, "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBodyTest() {
        Response response = given().spec(requestSpecification).get().andReturn();
        User[] users = response.as(User[].class);

        Assert.assertEquals(users.length, 10);
    }
}
