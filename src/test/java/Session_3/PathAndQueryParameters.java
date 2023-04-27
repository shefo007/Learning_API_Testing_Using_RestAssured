package Session_3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {

    // URL: https://reqres.in/api/users?page=2&id=5

    @Test
    void testQueryAndPathParameters() {

        // full URL: https://reqres.in/api/users?page=2/id
        baseURI = "https://reqres.in";

        given()
                .pathParam("myPath", "users")
                .queryParam("page", 2)
                .queryParam("id", 5)
        .when()
                .get("/api/{myPath}")
        .then()
                .statusCode(200)
                .log().all();
    }
}
