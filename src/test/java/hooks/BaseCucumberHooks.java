package hooks;

import base.driverFactory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BaseCucumberHooks {

	@Before
	public void beforeScenario(Scenario scenario) {
		System.out.println("Scenario Started: " + scenario.getName());
		BaseClass.appStart();
	}

	@After
	public void afterScenario(Scenario scenario) {
		System.out.println("Scenario Ended: " + scenario.getName());
		BaseClass.closeBrowser();
	}

	@Before
	public void beforeStep(Scenario scenario) {
	}

	@After
	public void afterSteps(Scenario scenario) {
		if (scenario.isFailed()) {
			/*
			 * byte []snap=BaseClass.snapAsBytes(); scenario.attach(snap,
			 * "image/png",scenario.getName());
			 */
		}
	}

}
