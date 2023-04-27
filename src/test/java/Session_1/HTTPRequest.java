package Session_1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequest {

    int id;

    @Test(priority = 1)
    public void getUsers() {
        baseURI = "https://reqres.in";

        when().
                get("/api/users?page=2").
        then().
                statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }


    @Test(priority = 2)
    public void createUser() {
        baseURI = "https://reqres.in";

        HashMap<String, Object> data = new HashMap<>();

        data.put("name", "Sherif");
        data.put("job", "Trainer");

        id = given().
                contentType("application/json")
                .body(data)
        .when().
                post("/api/users")
                .jsonPath().getInt("id");
//        .then().
//                statusCode(201)
//                .log().all();
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    public void updateUser() {
        baseURI = "https://reqres.in";

        HashMap<String, Object> data = new HashMap<>();

        data.put("name", "Sherif Abdallah");
        data.put("job", "Software Tester");

        given().
                contentType("application/json")
                .body(data)
        .when().
                put("/api/users/"+id).
        then().
                statusCode(200)
                .log().all();
    }


    @Test(priority = 4)
    public void deleteUser() {
        baseURI = "https://reqres.in";

        given().

        when().
                delete("/api/users/"+id)
        .then().
                statusCode(204);

    }
}
