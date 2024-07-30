package base.instancesFactory;

import actions.TestExecutor;
import actions.TestSteps;

public class FactoryRegistry {
	public static TestExecutor getTestExecutor(){return TestExecutor.getInstance();}
	public static TestSteps getTestSteps(){return TestSteps.getInstance();}
}
