package Tests;

import RequestBuilder.ApiRequestBuilder;
import Utilities.DatabaseConnection;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class UserRegistrationTest {

    static String registeredEmail;

    @BeforeClass
    public void setup() throws Exception {
        DatabaseConnection.dbConnection();
    }

    @Test
    public void userRegistration(){
        registeredEmail = Faker.instance().internet().emailAddress();
        ApiRequestBuilder.registerUserResponse("Ntuthuko","Mazii",registeredEmail,"Ntuthuko1031@", "1deae17a-c67a-4bb0-bdeb-df0fc9e2e526")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("success", equalTo(true));
    }

    @Test(dependsOnMethods = "userRegistration")
    public void adminLoginTest() {

        ApiRequestBuilder.loginUserResponse(DatabaseConnection.getEmail, DatabaseConnection.getPassword)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

    }

    @Test (dependsOnMethods = "adminLoginTest")
    public void approveUserRegistration(){
        ApiRequestBuilder.approveUserRegistrationResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test (dependsOnMethods = "approveUserRegistration")
    public void userLoginTest(){

        ApiRequestBuilder.loginUserResponse(registeredEmail, "Ntuthuko1031@")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }
}
