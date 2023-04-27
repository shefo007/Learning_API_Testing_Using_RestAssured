package Session_3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class LoggingDemo {

    @Test
    void testLogs() {
        baseURI = "https://google.com";

        given()

        .when()
                .get(baseURI)
        .then()
                .log().all();
    }

    @Test
    void testLogsBodyOnly() {
        baseURI = "https://google.com";

        given()

        .when()
                .get(baseURI)
        .then()
                .log().body();
    }

    @Test
    void testLogsHeadersOnly() {
        baseURI = "https://google.com";

        given()

        .when()
                .get(baseURI)
        .then()
                .log().headers();
    }

    @Test
    void testLogsCookiesOnly() {
        baseURI = "https://google.com";

        given()

        .when()
                .get(baseURI)
        .then()
                .log().cookies();
    }
}
