package Session_5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ParsingXMLResponse {

    @BeforeMethod
    void setUp() {
        baseURI = "http://restapi.adequateshop.com/api/Traveler?page=2";
    }

    @Test
    void testXMLResponse() {

        // Approach 1
        /*given()

        .when()
                .get(baseURI)
        .then()
                .statusCode(200)
                .header("Content-Type", "application/xml; charset=utf-8")
                .body("TravelerinformationResponse.page", equalTo("2"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].id",
                        equalTo("11144"));*/

        // Approach 2
        Response response = given()
                .when()
                .get(baseURI);

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.header("Content-Type"),
                "application/xml; charset=utf-8");

        int pageNum = response.xmlPath().getInt("TravelerinformationResponse.page");
        Assert.assertEquals(pageNum,2);

        int id = response.xmlPath().getInt("TravelerinformationResponse.travelers.Travelerinformation[0].id");
        Assert.assertEquals(id, 11144);



    }

    @Test
    void testXMLResponseBody() {
        Response response = given()
                .when()
                .get(baseURI);

        XmlPath xmlObj = new XmlPath(response.asString());

        List<String> travellers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");

        Assert.assertEquals(travellers.size(), 10);

        // verify traveller name is present in response
        List<String> travellersNames = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

        System.out.println(travellersNames);

        boolean status = false;
        for (String name : travellersNames) {
            if (name.equals("mirza")) {
                status = true;
                break;
            }
        }

        Assert.assertTrue(status);
    }


}
