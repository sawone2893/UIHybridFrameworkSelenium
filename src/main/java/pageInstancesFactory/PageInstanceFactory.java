package pageInstancesFactory;

import actionsKeyword.TestExecutor;
import actionsKeyword.TestSteps;

public class PageInstanceFactory {
	public static TestExecutor getTestExecutor(){return TestExecutor.getInstance();}
	public static TestSteps getTestSteps(){return TestSteps.getInstance();}
}
