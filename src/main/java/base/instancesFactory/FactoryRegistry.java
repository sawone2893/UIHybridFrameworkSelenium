package base.instancesFactory;

import actionsKeyword.TestExecutor;
import actionsKeyword.TestSteps;

public class FactoryRegistry {
	public static TestExecutor getTestExecutor(){return TestExecutor.getInstance();}
	public static TestSteps getTestSteps(){return TestSteps.getInstance();}
}
