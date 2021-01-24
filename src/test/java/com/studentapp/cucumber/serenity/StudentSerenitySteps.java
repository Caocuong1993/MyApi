package com.studentapp.cucumber.serenity;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class StudentSerenitySteps {
    @Step("Create student with: firstName:{0}, lastName:{1}, email:{2}, programme:{3}, courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName,
                                             String email, String programme, List<String> courses) {
        StudentClass studentClass = new StudentClass();
        studentClass.setFirstName(firstName);
        studentClass.setLastName(lastName);
        studentClass.setEmail(email);
        studentClass.setProgramme(programme);
        studentClass.setCourses(courses);
// neu khong hieu ro ban chat cua class ReusableSpecifications va 2 method ben trong no
// thi thay .spec(ReusableSpecifications.getGenericRequest())= contentType(ContentType.JSON)
        return SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequest())
                .log()
                .all()
                .when()
                .body(studentClass)
                .post()
                .then();
    }

    @Step("Getting information of student {0}")
    public HashMap<String, Object> getStudentInfoByFirstname(String firstName) {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";
        return SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path(p1 + firstName + p2);
    }

    @Step("Update information student with: studentId:{0},firstName:{1}, lastName:{2}, email:{3}, programme:{4}, courses:{5}")
    public ValidatableResponse updateStudent(int studentId, String firstName, String lastName,
                                             String email, String programme, List<String> courses) {
        StudentClass studentClass = new StudentClass();
        studentClass.setFirstName(firstName);
        studentClass.setLastName(lastName);
        studentClass.setEmail(email);
        studentClass.setProgramme(programme);
        studentClass.setCourses(courses);
// neu khong hieu ro ban chat cua class ReusableSpecifications va 2 method ben trong no
// thi thay .spec(ReusableSpecifications.getGenericRequest())= contentType(ContentType.JSON)
        return SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequest())
                .log()
                .all()
                .when()
                .body(studentClass)
                .put("/" + studentId)
                .then();
    }

    @Step("Delete information of student {0}")
    public void deleteStudent(int studentId) {
        SerenityRest.rest().given()
                .when()
                .delete("/" + studentId);
    }

    @Step("Get student by ID {0}")
    public ValidatableResponse getStudentById(int studentId) {
        return SerenityRest.rest().given()
                .when()
                .get("/" + studentId)
                .then()
                .log()
                .all();
    }
    @Step
    public HashMap<String, Object> getStudentInfoByEmailId(String email) {

        String p1 = "findAll{it.email=='";
        String p2 = "'}.get(0)";
        return SerenityRest
                .rest().given().when().get("/list").then().extract()
                .path(p1 + email + p2);
    }

}
