package Session_3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo {

    Response response;

    @Test
    void testCookies() {

        baseURI = "https://google.com";

        given()

        .when()
                .get(baseURI)
        .then()
                .cookie("AEC", "")
                .log().all();
    }

    @Test
    void getCookiesInfo() {

        baseURI = "https://google.com";

        response = given()
                    .when()
                    .get(baseURI);

        // get single cookie info, ex. AEC cookie
//        String AEC_Cookie = response.cookie("AEC");
//        System.out.println(AEC_Cookie);

        // get all cookies info
        Map<String, String> cookies_info = response.cookies();

        for (String key: cookies_info.keySet()) {
            System.out.println(key + " = " + cookies_info.get(key));
        }
    }
}
