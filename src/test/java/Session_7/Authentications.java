package Session_7;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Authentications {

    String baseURI = "https://postman-echo.com/basic-auth";

    @Test(priority = 1)
    void testBasicAuth() {

        given()
                .auth().basic("postman", "password")
        .when()
                .get(baseURI)
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority = 2)
    void testDigestAuth() {

        given()
                .auth().digest("postman", "password")
        .when()
                .get(baseURI)
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority = 3)
    void testPreemptiveAuth() {

        given()
                .auth().preemptive().basic( "postman", "password")
        .when()
                .get(baseURI)
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority = 4)
    void testBearerTokenAuth() {

        baseURI = "https://api.github.com/user/repos";
        String bearerToken = "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";

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/vnd.github+json");
        headers.put("Authorization", "Bearer " + bearerToken);

        given()
                .headers(headers)
        .when()
                .get(baseURI)
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void testOAuth_1() {

        given()
                .auth().oauth("",
                            "",
                            "",
                            "")
        .when()
                .get()
        .then()
                .statusCode(200);
    }

    @Test
    void testOAuth_2() {
        baseURI = "https://api.github.com/user/repos";
        String accessToken = "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";

        given()
                .auth().oauth2(accessToken)
        .when()
                .get(baseURI)
        .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    void testAPIKey() {

        String APIKey = "adfafa4a27a765ea6ee340e6911fffb2";
        baseURI = "https://api.openweathermap.org/data/2.5/weather?q=Cairo";

        given()
                .queryParam("appid",
                            APIKey)
        .when()
                .get(baseURI)
        .then()
                .statusCode(200)
                .log().all();

    }


}
