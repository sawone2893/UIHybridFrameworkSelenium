package pageInstances;

import testactionskeyword.TestActionExecutor;
import testactionskeyword.TestSteps;

public class PageInstanceFactory {
	public static TestActionExecutor getTestExecutor(){return TestActionExecutor.getInstance();}
	public static TestSteps getTestSteps(){return TestSteps.getInstance();}
}
