package Session_4;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ParsingJSONResponseData {

    Response response;
    String baseURI = "https://reqres.in/api/users?page=2";

    @Test
    void testJSONResponse() {

        response = given()
                .contentType("application/json")
        .when()
                .get(baseURI);

        Assert.assertEquals(response.statusCode(), 200);

        int userID = response.jsonPath().getInt("data[3].id");
        Assert.assertEquals(userID, 10);
    }


    @Test
    void testJsonResponseBodyData() {
        response = given()
                .contentType("application/json")
        .when()
                .get(baseURI);

        JSONObject JO = new JSONObject(response.asString());

        for (int i = 0; i < JO.getJSONArray("data").length(); i++) {
            String user = JO.getJSONArray("data").getJSONObject(i).getString("first_name");
            System.out.println(user);
        }




    }
}
