package endPoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.endPointS.ListOfUsers.User;
import utils.TestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ListOfUsers {

    @Before
    public void setup() {
        String baseURL = TestData.getProperty("baseURL");
        RestAssured.baseURI = baseURL;
        RestAssured.basePath = "api/users";
    }
@Test
    public void testSuccessfulRequest() {
        RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .contentType(ContentType.JSON);

    }
    @Test
    public void testPageNumber() {
        given().accept(ContentType.JSON)
                .queryParam("page", 2)
                .when().get()
                .then().body("page", Matchers.equalTo(2));
    }
    @Test
    public void testAllUsersData() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("page", 2)
                .when().get()
                .then().extract().response();

        List<User> users = response.jsonPath().getList("data", User.class);
        boolean invalidData = false;
        String avatarUrl = null;
        for (User user : users) {
            int id = user.getId();
            String firstName = user.getFirst_name();
            String lastName = user.getLast_name();
            String email = user.getEmail();
            avatarUrl = user.getAvatar();


            if (id <= 0) {
                System.out.println("Invalid user ID: " + id);
                invalidData = true;
            }

            if (firstName == null || firstName.isEmpty()) {
                System.out.println("User first name is null or empty");
                invalidData = true;
            }

            if (lastName == null || lastName.isEmpty()) {
                System.out.println("User last name is null or empty");
                invalidData = true;
            }

            if (avatarUrl == null || avatarUrl.isEmpty()) {
                System.out.println("User avatar URL is null or empty");
                invalidData = true;
            }
            System.out.println(id);
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(email);
            System.out.println(avatarUrl);
        }
    }

    @Test
    public void testUsersCountMatchesTotal(){

        Response countResponse = given().accept(ContentType.JSON)
                .when().get()
                .then().extract().response();

        int expectedTotal = countResponse.jsonPath().getInt("total");
        int actualTotal = 0;
        for (int page = 1; page <= 2; page++) {
            // Make the API request and extract the response
            Response response = given().accept(ContentType.JSON)
                    .queryParam("page", page)
                    .when().get()
                    .then().extract().response();

            List<User> currentPageUsers = response.jsonPath().getList("data", User.class);
            actualTotal += currentPageUsers.size();
        }


        Assert.assertEquals(expectedTotal, actualTotal);
//        System.out.println(expectedTotal);
//        System.out.println(actualTotal);
    }
    @Test
    public void storingUserInformationInMap() {
        Response response = given().accept(ContentType.JSON)
                .when().get()
                .then().extract().response();

        List<User> users = response.jsonPath().getList("data", User.class);

        Map<String, String> userInformationMap = new HashMap<>();
        for (User user : users) {
            String key = user.getFirst_name() + "." + user.getLast_name();
            String value = user.getEmail();
            userInformationMap.put(key, value);
        }
        System.out.println(userInformationMap);
        }


    @Test
    public void invalidEndpoint() {
        RestAssured.basePath = "api/users/abc123";

        RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(404)
                .contentType(ContentType.JSON);
    }
}

