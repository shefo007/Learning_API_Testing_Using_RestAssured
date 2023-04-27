package Session_6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.testng.annotations.Test;



// POJO ----> Serialization -----> JSON Object
// JSON Object -----> Deserialization ----> POJO

public class SerializationDeserialization {

    // convert javaObject ---> JsonObject (Serialization)
    @Test(priority = 1)
    void convertPOJO2Json() throws JsonProcessingException {

        Student student = new Student(); // POJO Class

        student.setName("Ahmed Ali");
        student.setLocation("Egypt");
        student.setPhone("012345678");

        String[] courses = {"Java", "RestAssured API Testing"};
        student.setCourses(courses);

        ObjectMapper objectMapper = new ObjectMapper();


        String jsonData = objectMapper.writerWithDefaultPrettyPrinter()
                                        .writeValueAsString(student);

        System.out.println(jsonData);
    }


    // convert JsonObject ---> javaObject (Deserialization)
    @Test(priority = 2)
    void convertJson2POJO() throws JsonProcessingException {

        String jsonData = "{\n" +
                "  \"name\" : \"Ahmed Ali\",\n" +
                "  \"location\" : \"Egypt\",\n" +
                "  \"phone\" : \"012345678\",\n" +
                "  \"courses\" : [ \"Java\", \"RestAssured API Testing\" ]\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();

        Student student = objectMapper.readValue(jsonData, Student.class);

        System.out.println("Name: " + student.getName());
        System.out.println("Location: " + student.getLocation());
        System.out.println("Phone: " + student.getPhone());
        System.out.println("Courses: " + student.getCourses()[0]
                +  ", " + student.getCourses()[1]);
    }

}
