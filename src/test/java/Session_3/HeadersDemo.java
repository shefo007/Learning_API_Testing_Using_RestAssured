package Session_3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HeadersDemo {

    Response response;

    @Test(priority = 1)
    void testHeaders() {

        baseURI = "https://google.com";

        given()

        .when()
                .get(baseURI)
        .then()
                .header("Content-Type","text/html; charset=ISO-8859-1")
                .header("Content-Encoding", "gzip")
                .header("Server", "gws");
    }

    @Test
    void getHeaders() {

        baseURI = "https://google.com";

        response = given()
                    .when()
                    .get(baseURI);

        // get single header info
        String header1 = response.getHeader("Content-Type");
        System.out.println("Content-Type = " + header1);

        // get all headers info
        Headers allHeaders = response.getHeaders();

        for (Header header : allHeaders) {
            System.out.println(header);
        }
    }
}
