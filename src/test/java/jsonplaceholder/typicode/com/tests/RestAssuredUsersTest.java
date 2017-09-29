package jsonplaceholder.typicode.com.tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import jsonplaceholder.typicode.com.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RestAssuredUsersTest {

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCodeTest() {
        Response response = given().get("/users").andReturn();
        int actualStatusCode = response.statusCode();

        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeaderTest() {
        Response response = given().get("/users").andReturn();
        String actualContentType = response.header("Content-Type");

        Assert.assertEquals(actualContentType, "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBodyTest() {
        Response response = given().get("/users").andReturn();
        User[] users = response.as(User[].class);

        Assert.assertEquals(users.length, 10);
    }
}
