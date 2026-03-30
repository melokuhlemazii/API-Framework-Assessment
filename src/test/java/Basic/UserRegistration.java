package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserRegistration {
    static String baseURL = "https://ndosiautomation.co.za";
    static String registeredEmail;
    static String authToken;
    static String userId;

    @Test
    public void adminLoginTest() {
        // Implement admin login test and set authToken
        String apiPath = "/APIDEV/login";
        String payload = "{\n" +
                "  \"email\": \"spare@admin.com\",\n" +
                "  \"password\": \"@12345678\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath(apiPath)
                .header("Content-Type", "application/json")
                .body(payload)
                .log().all()
                .post().prettyPeek();

        int actualStatusCode = response.getStatusCode();
        assert actualStatusCode == 200 : "Status code should be 200";
        authToken = response.jsonPath().getString("data.token");
    }
}
