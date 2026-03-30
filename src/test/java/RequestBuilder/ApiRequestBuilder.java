package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static Common.BaseURIs.baseURL;
import static PayloadBuilder.PayloadBuilder.loginUserPayload;

public class ApiRequestBuilder {
    static String authToken;

    public static Response loginUserResponse(String email, String password){

        String apiPath = "/APIDEV/login";
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath(apiPath)
                .header("Content-Type", "application/json")
                .body(loginUserPayload(email, password))
                .log().all()
                .post()
                .then().extract().response();
        authToken = response.jsonPath().getString("data.token");
        return response;
    }
}
