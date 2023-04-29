package endPoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.endPointS.ListResources.UnknownUserResource;
import utils.TestData;

import static utils.equalToClass.equalTo;

public class SingleResourceNotFound {
    @Before
    public void setup() {
        String baseURL = TestData.getProperty("baseURL");
        RestAssured.baseURI = baseURL;
    }
    @Test
    public void testUnknownResourceReturns404() {

        RestAssured.basePath = "api/unknown/23";

        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when()
                .get()
                .then()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body(equalTo("{}"))
                .extract()
                .response();
        }

    @Test
    public void testInvalidRequest() {
        RestAssured.basePath = "api/unknown/1h999";

        RestAssured.given()
                .get()
                .then()
                .assertThat()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body(equalTo("{}"));
    }
}
