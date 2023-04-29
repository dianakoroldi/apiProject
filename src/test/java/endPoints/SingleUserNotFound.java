package endPoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.TestData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SingleUserNotFound {

    @Before
    public void setup() {
        String baseURL = TestData.getProperty("baseURL");
        RestAssured.baseURI = baseURL;

    }

    @Test
    public void testingSingleUserNotFound() throws IOException {
        RestAssured.basePath= "/api/users/23";
       String expectedResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedResponse.json")));


        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(404)
                .and().body(Matchers.equalTo(expectedResponse))
                .extract().response();

    }
    @Test
    public void testingSingleUserNotFoundSecondWay() throws IOException {
        RestAssured.basePath= "/api/users/23";

        String expectedResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedResponse.json")));
        Response response = RestAssured.given().accept(ContentType.JSON)
         .when().get();

        int actualStatusCode = response.getStatusCode();
        String actualResponse = response.getBody().asString().trim();
// validation
        Assert.assertEquals(404, actualStatusCode);
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    }

