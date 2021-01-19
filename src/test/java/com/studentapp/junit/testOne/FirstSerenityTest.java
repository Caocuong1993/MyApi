package com.studentapp.junit.testOne;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost:8085/student";
    }

    @Test
    public void getAllStudent() {
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200);

    }

    @Test
    public void thisIsAFailing() {
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(500);
    }

    @Pending
    @Test
    public void thisIsAPending() {
    }

    @Ignore
    @Test
    public void thisIsAIgnore() {
    }

    @Test
    public void thisIsATestWithError() {
        System.out.println("This test error" + 5 / 0);
    }

    @Test
    public void thisIsATestWithFileDoesNotExist() throws FileNotFoundException {
        File file = new File("E://file.txt");
        FileReader fileReader = new FileReader(file);
    }

    @Manual
    @Test
    public void thisIsAManualTest() {
    }

    @Title("This test will get all information of student")
    @Test
    public void test001() {
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200);

    }
}
