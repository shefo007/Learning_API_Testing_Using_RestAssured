package Session_6;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class schemaValidation {

    @Test
    void jsonSchemaValidation() {

        baseURI = "https://reqres.in/api/users?page=2";
        // store.json server running from terminal with this command "json-server store.json"
//        baseURI = "http://localhost:3000/Book";

        given()
                .contentType("application/json; charset=utf-8")
        .when()
                .get(baseURI)
        .then()
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath("reqres_schema.json"));
    }

    @Test
    void xmlSchemaValidation() {

        baseURI = "http://restapi.adequateshop.com/api/Traveler?page=2";

        given()
                .contentType("application/xml; charset=utf-8")
        .when()
                .get(baseURI)
        .then()
                .statusCode(200)
                .assertThat()
                .body(RestAssuredMatchers.matchesXsdInClasspath("xml_schema.xsd"));
    }





}
