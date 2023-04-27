package Session_5;

import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.FileStore;
import java.nio.file.Files;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class FileUploadAndDownload {

    @Test(priority = 1)
    void singleFileUpload() {

        File myFile = new File("resources/Test1.txt");

        given()
                .multiPart("file", myFile)
                .contentType("multipart/form-data")
        .when()
                .post("http://localhost:8080/uploadFile")
        .then()
                .statusCode(200)
                .body("fileName", equalTo("Test1.txt"))
                .log().all();

    }

    @Test
    void multipleFilesUpload() {

        File myFile1 = new File("resources/Test1.txt");
        File myFile2 = new File("resources/Test2.txt");

        given()
                .multiPart("files", myFile1)
                .multiPart("files", myFile2)
                .contentType("multipart/form-data")
        .when()
                .post("http://localhost:8080/uploadMultipleFiles")
        .then()
                .statusCode(200)
                .body("[0].fileName", equalTo("Test1.txt"))
                .body("[1].fileName", equalTo("Test2.txt"))
                .log().all();
    }

    @Test(priority = 2)
    void fileDownload() {

        given()

        .when()
                .get("http://localhost:8080/downloadFile/Test1.txt")
        .then()
                .statusCode(200)
                .log().all();
    }
}
