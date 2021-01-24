package com.studentapp.cucumber.steps;


import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class StudentSteps {
    @Steps
    StudentSerenitySteps steps;
    static String email = null;

    @When("User sends a Get request to the list endpoint, they must get back a valid status {}")
    public void verifyStatusCode200ForListEndpoint(int arg1) {
        SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
                .statusCode(arg1);
    }

    @When("I create a new student by providing the information firstName {} lastName {} email {} programme {} courses {}")
    public void createStudent(String firstName, String lastName, String _email, String programme, String course) {
        List<String> courses = new ArrayList<>();
        courses.add(course);
        email = TestUtils.getRandomVuale() + _email;

        steps.createStudent(firstName, lastName, email, programme, courses)
                .assertThat()
                .statusCode(201);

    }

    @Then("I verify that the student with {} is created")
    public void verifyStudent(String emailId) {
        HashMap<String, Object> actualValue = steps.getStudentInfoByEmailId(email);
        assertThat(actualValue, hasValue(email));
    }
}
