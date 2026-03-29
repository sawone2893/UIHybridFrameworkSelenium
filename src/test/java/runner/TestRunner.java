package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = { "src/test/java/features/" },
		dryRun = !true,
		monochrome = true,
		glue = { "com.stepDefinitions",
		"com.hooks" },
		plugin = { "pretty", "html:target/cucumber-reports.html",
				"json:target/cucumber.json" }, 
		tags = "@sanity"
		)
public class TestRunner extends AbstractTestNGCucumberTests {

}
