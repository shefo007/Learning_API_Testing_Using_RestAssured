package Session_8;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class UpdateUser {

    /*
    curl -i -H "Accept:application/json"
         -H "Content-Type:application/json"
         -H "Authorization: Bearer 171f5fa12aeec5453239f89c6bf6e85dc990941543e4082476f637b7545ed651"
         -X PATCH "https://gorest.co.in/public/v2/users/661"
         -d '{"name":"Allasani Peddana",
              "email":"allasani.peddana@15ce.com",
              "status":"active"}'
     */

    @Test
    void test_UpdateUser(ITestContext context) {
        baseURI = "https://gorest.co.in";
        String accessToken = "171f5fa12aeec5453239f89c6bf6e85dc990941543e4082476f637b7545ed651";

        //to get id from createUser test and run all tests in testng_session_8.xml
//        int id = (Integer) context.getAttribute("id");

        // to run test individual in testng2_session_8.xml
        int id = (Integer) context.getSuite().getAttribute("id");

        Faker faker = new Faker();

        String name = faker.name().fullName();
        String gender = "Female";
        String email = faker.internet().emailAddress();
        String status = "inactive";

        JSONObject data = new JSONObject();

        data.put("name", name);
        data.put("gender", gender);
        data.put("email", email);
        data.put("status", status);


        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);


        given()
                .headers(headers)
                .pathParam("id", id)
                .body(data.toString())
        .when()
                .put("/public/v2/users/{id}")
        .then()
                .statusCode(200)
                .log().all();
    }
}
