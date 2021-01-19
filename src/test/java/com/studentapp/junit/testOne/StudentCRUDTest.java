package com.studentapp.junit.testOne;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)// chay cac test theo thu tu
public class StudentCRUDTest extends TestBase {
    @Steps
    StudentSerenitySteps studentSteps;
    static String firstName = "nguyen" + TestUtils.getRandomVuale();
    static String lastName = "cao" + TestUtils.getRandomVuale();
    static String email = TestUtils.getRandomVuale() + "cuong@gmail.com";
    static String programme = "Automation";
    static int idStudent;

    @Title("This test create a new student")
    @Test
    public void test01() {
        List<String> courses = new ArrayList<>();
        courses.add("Python");
        courses.add("Java");
        studentSteps.createStudent(firstName, lastName, email, programme, courses)
                .statusCode(201)
                .spec(ReusableSpecifications.getGenericResponse());

    }

    @Title("Verify that student added in to the application")
    @Test
    public void test02() {
        HashMap<String, Object> value = studentSteps.getStudentInfoByFirstname(firstName);
        System.out.println("The is value: " + value);
        assertThat(value, hasValue(firstName));
        idStudent = (int) value.get("id");

//cach khac de ghet all danh sach va lay ra id cua ban ghi moi them vao
//        JsonPath jsonPath = SerenityRest.rest().given()
//                .when()
//                .get("/list").jsonPath();
//        List<StudentClass> classList = jsonPath.getList("",StudentClass.class);
//        for (StudentClass s: classList
//             ) {
//            if (s.getFirstName().equalsIgnoreCase(firstName)){
//                idStudent = s.getId();
//                break;
//            }
//        }
//        System.out.println("The is value: " + classList.toString());
    }

    @Title("Update Student")
    @Test
    public void test03() {
        List<String> courses = new ArrayList<>();
        courses.add("Python");
        courses.add("Java");
        firstName += "_Update";
        studentSteps.updateStudent(idStudent, firstName, lastName, email, programme, courses);
        HashMap<String, Object> value = studentSteps.getStudentInfoByFirstname(firstName);
        System.out.println("The is value: " + value);
        assertThat(value, hasValue(firstName));
    }

    @Title("Delete Student and verify if student is deleted")
    @Test
    public void test04() {

        studentSteps.deleteStudent(idStudent);
        studentSteps.getStudentById(idStudent)
                .statusCode(404);
    }
}
