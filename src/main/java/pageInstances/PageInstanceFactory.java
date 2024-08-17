package pageInstances;

import testActionsKeyword.TestActionExecutor;
import testActionsKeyword.TestSteps;

public class PageInstanceFactory {
	public static TestActionExecutor getTestExecutor(){return TestActionExecutor.getInstance();}
	public static TestSteps getTestSteps(){return TestSteps.getInstance();}
}
