package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src/test/java/features/"},
		dryRun=!true,
		monochrome=true,
		glue={"stepDefinitions","hooks"},
		//plugin = {"json:target/cucumber.json"},
		plugin = {"pretty"},
		tags="@sanity"
		)
public class TestRunner extends AbstractTestNGCucumberTests{

}
