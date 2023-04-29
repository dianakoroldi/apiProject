package endPoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.endPointS.ListResources.UnknownUserResource;
import utils.TestData;

import java.util.ArrayList;
import java.util.List;

public class ListResources {
@Before
public  void setup() {
    String baseURL = TestData.getProperty("baseURL");
    RestAssured.baseURI = baseURL;
}
    @Test
    public void testResourceList() {
        RestAssured.basePath = "api/unknown";

        Response response = RestAssured.get()
                .then().statusCode(200)
                .extract().response();


        Assert.assertEquals(200, response.getStatusCode());

        List<UnknownUserResource> resources = new ArrayList<>(response.jsonPath().getList("data", UnknownUserResource.class));

        int totalPages = response.jsonPath().getInt("total_pages");
        if (totalPages > 1) {
            for (int i = 2; i <= totalPages; i++) {
                Response nextPageResponse = RestAssured.given().get("?page=" + i);
                List<UnknownUserResource> nextPageIds = nextPageResponse.jsonPath().getList("data", UnknownUserResource.class);
                for (UnknownUserResource id : nextPageIds) {
                    resources.add(id);
                }
            }
        }

        int sumOfIds = 0;
        for (UnknownUserResource resource : resources) {
            sumOfIds += resource.getId();
        }
        Assert.assertEquals(78, sumOfIds);

        double sumOfYear = 0.0;
        for (UnknownUserResource resource : resources) {
            sumOfYear += resource.getYear();
        }
        double avgOfYear = sumOfYear / resources.size();
        Assert.assertTrue(avgOfYear >= 2005 && avgOfYear <= 2006);
    }

    @Test
    public void testGetWithInvalidEndpoint() {
        RestAssured.basePath = "api/unknown/78";

        Response response = RestAssured.given().get()
                .then().statusCode(404)
                .extract().response();
        Assert.assertEquals(404, response.getStatusCode());
    }
}
