package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src\\test\\java\\cucumber",glue = "ashok.selenium.stepDefinitions",
monochrome=true, tags = "@Regression", plugin = {"html:target/ucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
