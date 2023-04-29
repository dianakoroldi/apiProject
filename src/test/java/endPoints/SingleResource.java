package endPoints;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.endPointS.ListOfUsers.User;
import pojo.endPointS.SingleResource.singleResour;
import utils.TestData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.equalToClass.equalTo;

public class SingleResource {

    @Before
    public void setup(){
        String baseURL = TestData.getProperty("baseURL");
        RestAssured.baseURI = baseURL;
    }
    @Test
    public void testSuccessfulRequest() {
        RestAssured.basePath= "api/unknown/2";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id", Matchers.equalTo(2))
                .and().
                body("data.year", Matchers.equalTo(2001))
                .and().
                body("support.text", Matchers.containsString("contributions towards server costs"))
                .and().
                body("data.color", Matchers.equalTo("#C74375")).extract().response();
    }

    @Test
    public void testUnknownEndpoint() {
        RestAssured.basePath= "api/unknown";

        Response response = given().
                get().
                then().
                assertThat().
                statusCode(200).
                extract().response();

        int totalPages = JsonPath.from(response.getBody().asString()).getInt("total_pages");

        for (int page = 1; page <= totalPages; page++) {
            response = given().
                    queryParam("page", page).
                    when().
                    get("https://reqres.in/api/unknown").
                    then().
                    assertThat().
                    statusCode(200).
                    extract().response();

            List<Map<String, Object>> data = JsonPath.from(response.getBody().asString()).getList("data");

            for (Map<String, Object> resource : data) {
                for (String key : resource.keySet()) {
                    given().
                            get(String.format("https://reqres.in/api/unknown/%d", resource.get("id"))).
                            then().
                            assertThat().
                            statusCode(200).
                            body(String.format("data.%s", key), equalTo(resource.get(key)));

                }
            }
        }

    }
    //Negative Scenario
    @Test
    public void testInvalidRequest() {
        RestAssured.basePath= "api/unknown/100";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(404).extract().response();
    }
}


