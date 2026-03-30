package Tests;

import RequestBuilder.ApiRequestBuilder;
import Utilities.DatabaseConnection;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserRegistrationTest {

    @BeforeClass
    public void setup() throws Exception {
        DatabaseConnection.dbConnection();
    }

    @Test
    public void adminLoginTest() {

        ApiRequestBuilder.loginUserResponse(DatabaseConnection.getEmail, DatabaseConnection.getPassword)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }
}
