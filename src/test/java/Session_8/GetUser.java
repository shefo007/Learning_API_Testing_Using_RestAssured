package Session_8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class GetUser {

    /*
     * curl -i -H "Accept:application/json"
     * -H "Content-Type:application/json"
     * -H "Authorization: Bearer 171f5fa12aeec5453239f89c6bf6e85dc990941543e4082476f637b7545ed651"
     * -X GET "https://gorest.co.in/public/v2/users/{id}"
     * */

    @Test
    void test_getUser(ITestContext context) {
        baseURI = "https://gorest.co.in";
        String accessToken = "171f5fa12aeec5453239f89c6bf6e85dc990941543e4082476f637b7545ed651";

        //to get id from createUser test and run all tests in testng_session_8.xml
//        int id = (Integer) context.getAttribute("id");

        // to run test individual in testng2_session_8.xml
        int id = (Integer) context.getSuite().getAttribute("id");
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);

        given()
                .headers(headers)
                .pathParam("id", id)
        .when()
                .get("/public/v2/users/{id}")
        .then()
                .statusCode(200)
                .log().all();

    }
}
