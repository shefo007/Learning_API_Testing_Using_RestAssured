package Session_2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DiffWaysToCreatePostRequestBody {

    // 1) Post request body using HashMap
    @Test(priority = 1)
    public void testPostUsingHashMap() {
        baseURI = "https://reqres.in/";

        HashMap<String, Object> data = new HashMap<>();

        data.put("name", "Sherif");
        data.put("job", "Software Engineer");

        given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("/api/users")
        .then()
                .statusCode(201)
                .body("name", equalTo("Sherif"))
                .body("job", equalTo("Software Engineer"))
                .log().all();
    }

    // 2) Post request body using org.json
    @Test(priority = 2)
    public void testPostUsingJson() {
        baseURI = "https://reqres.in/";

        JSONObject data = new JSONObject();

        data.put("name", "Sherif");
        data.put("job", "Software Engineer");

        given()
                .contentType("application/json")
                .body(data.toString())
        .when()
                .post("/api/users")
        .then()
                .statusCode(201)
                .body("name", equalTo("Sherif"))
                .body("job", equalTo("Software Engineer"))
                .log().all();
    }

    // 3) Post request body using POJO Class
    @Test(priority = 3)
    public void testPostUsingPOJO() {
        baseURI = "https://reqres.in/";

        POJO_PostRequest data = new POJO_PostRequest();

        data.setName("Sherif");
        data.setJob("Software Engineer");

        given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("/api/users")
        .then()
                .statusCode(201)
                .body("name", equalTo("Sherif"))
                .body("job", equalTo("Software Engineer"))
                .log().all();
    }

    // 4) Post request body using external json file
    @Test(priority = 4)
    public void testPostUsingExternalJSONFile() {
        baseURI = "https://reqres.in/";

        File data = new File("src/test/java/Session_2/body.json");

//        FileReader fr = new FileReader(file);
//        JSONTokener jt = new JSONTokener(fr);
//        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("/api/users")
        .then()
                .statusCode(201)
                .body("name", equalTo("Sherif Abdallah"))
                .body("job", equalTo("Software Engineer"))
                .log().all();
    }
}
