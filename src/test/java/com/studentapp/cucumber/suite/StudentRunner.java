package com.studentapp.cucumber.suite;

import com.studentapp.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {"src/test/resources/features/"}
        ,
        glue = {"com.studentapp.cucumber.steps"}
)
public class StudentRunner extends TestBase {
}
