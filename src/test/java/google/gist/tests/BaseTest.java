package google.gist.tests;


import com.jayway.restassured.RestAssured;
import google.gist.utils.PropertyManager;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected final String token = PropertyManager.get("git.token");

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://api.github.com";
    }
}
