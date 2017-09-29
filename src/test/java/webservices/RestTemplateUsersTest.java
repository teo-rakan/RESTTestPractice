package webservices;

import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestTemplateUsersTest {

    private final String URL = "http://jsonplaceholder.typicode.com/users";

    @Test
    public void checkStatusCodeTest() {
        ResponseEntity<User[]> response = new RestTemplate().getForEntity(URL, User[].class);
        int actualStatusCode = response.getStatusCode().value();

        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeaderTest() {
        ResponseEntity<User[]> response = new RestTemplate().getForEntity(URL, User[].class);
        String actualContentType = response.getHeaders().getFirst("Content-Type");

        Assert.assertEquals(actualContentType, "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBodyTest()  {
        User[] users = new RestTemplate().getForObject(URL, User[].class);

        Assert.assertEquals(users.length, 10);
    }
}
