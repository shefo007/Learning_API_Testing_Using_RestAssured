package Session_8;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class CreateUser {

    /*
     * curl -i -H "Accept:application/json"
     * -H "Content-Type:application/json"
     * -H "Authorization: Bearer 171f5fa12aeec5453239f89c6bf6e85dc990941543e4082476f637b7545ed651"
     * -X GET "https://gorest.co.in/public/v2/users"
     * */


    @Test
    void test_CreateUser(ITestContext context) {

        baseURI = "https://gorest.co.in";
        String accessToken = "171f5fa12aeec5453239f89c6bf6e85dc990941543e4082476f637b7545ed651";

        Faker faker = new Faker();

        String name = faker.name().fullName();
        String gender = "male";
        String email = faker.internet().safeEmailAddress();
        String status = "active";

        JSONObject data = new JSONObject();

        data.put("name", name);
        data.put("gender", gender);
        data.put("email", email);
        data.put("status", status);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);


        Response response = given()
                .headers(headers)
                .body(data.toString())
        .when()
                .post("/public/v2/users");

        int userID = response.jsonPath().getInt("id");
        System.out.println(userID);

        //to run id with other tests in testng_Session_8.xml
//        context.setAttribute("id", userID);

        // to make id in the suite level for all tests to execute individually
        context.getSuite().setAttribute("id", userID);

        Assert.assertEquals(response.statusCode(), 201);

    }
}
