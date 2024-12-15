package runner;

import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@Test
@CucumberOptions
        (tags = "",
                features = {"src/test/resources/features"},
        glue = {"stepDefinitions"},
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
                )

public class CucumberRunnerTests extends AbstractTestNGCucumberTests{

}
