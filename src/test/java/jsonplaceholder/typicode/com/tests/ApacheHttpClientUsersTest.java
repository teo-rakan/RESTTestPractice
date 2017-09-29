package jsonplaceholder.typicode.com.tests;

import com.google.gson.Gson;
import jsonplaceholder.typicode.com.model.User;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApacheHttpClientUsersTest {

    private final String URL = "http://jsonplaceholder.typicode.com/users";

    @Test
    public void checkStatusCodeTest() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(new HttpGet(URL));
        int actualStatusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeaderTest() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(new HttpGet(URL));
        Header actualContentType = response.getFirstHeader("Content-Type");

        Assert.assertEquals(actualContentType.getValue(), "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBodyTest() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(new HttpGet(URL));
        String json = EntityUtils.toString(response.getEntity(), "UTF-8");
        User[] users = new Gson().fromJson(json, User[].class);

        Assert.assertEquals(users.length, 10);
    }
}
