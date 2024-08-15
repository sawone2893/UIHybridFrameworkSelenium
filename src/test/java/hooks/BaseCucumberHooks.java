package hooks;

import base.BaseClass;
import config.ConfigProp;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utililties.ExtentReportManager;

public class BaseCucumberHooks {

	@Before
	public void beforeScenario(Scenario scenario) {
		ExtentReportManager.getInstance();
		ExtentReportManager.createTest(scenario.getName(), scenario.getId());
		System.out.println("Scenario Started: " + scenario.getName());
		BaseClass.appStart();
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			// Capture screenshot if test fails
			String screenshot = BaseClass.snap(ConfigProp.SCREENSHOT_PATH + scenario.getName());
			ExtentReportManager.getTest().fail("Test Failed").addScreenCaptureFromBase64String(new String(screenshot),
					scenario.getName());
		}
		ExtentReportManager.flush();
	}

	@After
	public void afterScenario(Scenario scenario) {
		System.out.println("Scenario Ended: " + scenario.getName());
		BaseClass.closeBrowser();
	}

}
