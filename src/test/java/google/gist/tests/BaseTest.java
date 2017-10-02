package google.gist.tests;


import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected final String user = "teo-rakan";

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://api.github.com";
    }
}
