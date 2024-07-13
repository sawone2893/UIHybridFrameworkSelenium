package actions;

public class TestSteps {

	private static TestSteps testSteps=null;
	private String action;
	private String locator;
	private String value;
	
	private TestSteps(){}
	public static TestSteps getInstance() {
		if(testSteps==null) {
			testSteps=new TestSteps();
		}
		return testSteps;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
