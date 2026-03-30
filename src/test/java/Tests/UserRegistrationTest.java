package Tests;

import RequestBuilder.ApiRequestBuilder;
import org.testng.annotations.Test;

public class UserRegistrationTest {

    @Test
    public void adminLoginTest() {

        ApiRequestBuilder.loginUserResponse("spare@admin.com", "@12345678")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
