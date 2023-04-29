package endPoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.TestData;

import java.util.List;
import java.util.Map;

public class SingleUser {

    @Before
    public void setup(){
        String baseURL = TestData.getProperty("baseURL");
        RestAssured.baseURI = baseURL;
    }

    @Test

    public void testSuccessfulRequest() {
        RestAssured.basePath = "api/users/2";

        RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testUserNameIDAndAvatarURL() {
        RestAssured.basePath = "api/users/2";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get();
        String userName = response.jsonPath().get("data.first_name");
        int userId = response.jsonPath().get("data.id");
        String avatarURL = response.jsonPath().get("data.avatar");
        Assert.assertEquals(false, (userName == null || userName.isEmpty()));
        Assert.assertTrue(userId > 0);
        Assert.assertEquals(false, (avatarURL == null || avatarURL.isEmpty()));
    }


    @Test
    public void testSupportURL() {
        RestAssured.basePath = "api/users/2";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get();
        String actualURL = response.jsonPath().get("support.url");
        String expectedUrl = TestData.getProperty("expected.support.url");
        ;
        Assert.assertEquals(expectedUrl, actualURL);
//        System.out.println(expectedUrl);
//        System.out.println(actualURL);
    }

    @Test
    public void testAllUsersData() {
        RestAssured.basePath = "api/users/2";
        Response singleUserResponse = RestAssured.given().accept(ContentType.JSON)
                .when().get();
        String userName = singleUserResponse.jsonPath().get("data.first_name");
        Assert.assertEquals(false, (userName == null || userName.isEmpty()));

        // Retrieve data for all 12 users
        Response allUsersResponse = RestAssured.given().accept(ContentType.JSON)
                .queryParam("per_page", 12)
                .when().get("https://reqres.in/api/users");
        List<Map<String, Object>> usersData = allUsersResponse.jsonPath().getList("data");
        Assert.assertEquals(12, usersData.size());

    }

    @Test
    public void testInvalidUserID() {
        RestAssured.basePath = "api/users/999";
        RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(404);

    }
}

