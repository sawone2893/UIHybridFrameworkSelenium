package stepDefinitions;

import java.util.List;
import java.util.Map;

import actionsKeyword.TestExecutor;
import actionsKeyword.TestSteps;
import config.ConfigProp;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageInstancesFactory.PageInstanceFactory;
import utililties.ExtentReportManager;

public class Common {

	TestExecutor testExecutor = PageInstanceFactory.getTestExecutor();
	TestSteps steps = PageInstanceFactory.getTestSteps();
	boolean logStatus=ConfigProp.LOG_MODE;

	/*
	 * Example: Then I "Click" on "Button" with values "Login" Then I
	 * "WaitUntillElementDisappear" on "Button" with values "Login" Then I
	 * "WaitUntillElementAppear" on "Button" with values "Login"
	 */
	@Then("I {string} on {string} with values {string}")
	public void iOnWithValues(String action, String locatorIdentifier, String param) {
		if(logStatus) {ExtentReportManager.getTest().info("I "+action+" on "+locatorIdentifier+" with values "+param);}
		steps.setAction(action);
		steps.setLocator(testExecutor.generateLocator(locatorIdentifier, param));
		testExecutor.executeAction(steps);
	}

	/*
	 * Example: Then I "WaitForPageLoadState" "load"
	 */
	@Given("I {string} {string}")
	public void i(String action, String value) {
		if(logStatus) {ExtentReportManager.getTest().info("I "+action+" "+value);}
		steps.setAction(action);
		steps.setValue(value);
		testExecutor.executeAction(steps);
	}

	/*
	 * Example: When I "EnterValue" "Shabbir" for "TextField" with values "Username"
	 * Then I "WaitUntill" "VISIBLE" for "TextField" with values "Username"
	 */
	@When("I {string} {string} for {string} with values {string}")
	public void iForWithValues(String action, String value, String locatorIdentifier, String param) {
		if(logStatus) {ExtentReportManager.getTest().info("I "+action+" "+value+" for "+locatorIdentifier+" with values "+param);}
		steps.setAction(action);
		steps.setValue(value);
		steps.setLocator(testExecutor.generateLocator(locatorIdentifier, param));
		testExecutor.executeAction(steps);

	}

	/*
	 * Example: Then I "VerifyVisibility" is "true" for "TagWithText" with values
	 * "span~<OrderConfirmationText>" Then I "VerifySelected" is "true" for
	 * "TagWithText" with values "span~<OrderConfirmationText>"
	 */
	@Then("I {string} is {string} for {string} with values {string}")
	public void iIsForWithValues(String action, String value, String locatorIdentifier, String param) {
		if(logStatus) {ExtentReportManager.getTest().info("I "+action+" is "+value+" for "+locatorIdentifier+"with values"+param);}
		steps.setAction(action);
		steps.setValue(value);
		steps.setLocator(testExecutor.generateLocator(locatorIdentifier, param));
		testExecutor.executeAction(steps);
	}

	/*
	 * Example: Then I "WaitForElement" "5" seconds
	 */
	@Then("I {string} {string} seconds")
	public void iSeconds(String action, String value) {
		if(logStatus) {ExtentReportManager.getTest().info("I "+action+" "+value+" seconds");}
		steps.setAction(action);
		steps.setValue(value);
		testExecutor.executeAction(steps);

	}

	/*
	 * Example: Then I "VerifyVisibility" is "true" for "TagWithText" using
	 * following dataset" 
	 * |Apple| 
	 * |Mango| 
	 * |Orange|
	 */
	@Then("I {string} is {string} for {string} using following dataset")
	public void iIsForUsingFollowingDataset(String action, String value, String locatorIdentifier,
			DataTable dataTable) {
		if(logStatus) {ExtentReportManager.getTest().info("I "+action+" is "+value+" for "+locatorIdentifier+" using following dataset");}
		List<String> data = dataTable.asList();
		for (int i = 0; i < data.size(); i++) {
			if(logStatus) {ExtentReportManager.getTest().info("|"+data.get(i)+"|");}
			steps.setAction(action);
			steps.setValue(value);
			steps.setLocator(testExecutor.generateLocator(locatorIdentifier, data.get(i)));
			testExecutor.executeAction(steps);
		}

	}

	/*
	 * Example: Then I perform following actions using dataset
	 * |action          |value|locatorIdentifier|params|
	 * |VerifyVisibility|true |TagWithText      |Apple |
	 * |VerifySelected  |true |TagWithText      |Mango |
	 */
	@When("I perform following actions using dataset")
	public void iPerformFollowingActionsUsingDataset(DataTable dataTable) {
		List<Map<String, String>> dataRows = dataTable.asMaps();
		Map<String, String> row = null;
		if(logStatus) {ExtentReportManager.getTest().info("I perform following actions using dataset");}
		for (int i = 0; i < dataRows.size(); i++) {
			row = dataRows.get(i);
			if(logStatus) {ExtentReportManager.getTest().info("|"+row.get("action")+"| "+row.get("value")+" | "+row.get("locatorIdentifier")+" | "+row.get("params")+" |");}
			steps.setAction(row.get("action"));
			steps.setValue(row.get("value"));
			steps.setLocator(testExecutor.generateLocator(row.get("locatorIdentifier"), row.get("params")));
			testExecutor.executeAction(steps);
		}

	}

	/*
	 * Example: Then I "VerifyVisibility" is "true" using following dataset
	 * |locatorIdentifier|params|
	 * |Button           |Orange|
	 * |TagWithText      |Apple|
	 */
	@Then("I {string} is {string} using following dataset")
	public void iIsUsingDataset(String action, String value, DataTable dataTable) {
		List<Map<String, String>> dataRows = dataTable.asMaps();
		Map<String, String> row = null;
		if(logStatus) {ExtentReportManager.getTest().info("I "+action+"is "+value+"using following dataset");}
		for (int i = 0; i < dataRows.size(); i++) {
			row = dataRows.get(i);
			ExtentReportManager.getTest().info("| "+row.get("locatorIdentifier")+" | "+row.get("params")+" |");
			steps.setAction(action);
			steps.setValue(value);
			steps.setLocator(testExecutor.generateLocator(row.get("locatorIdentifier"), row.get("params")));
			testExecutor.executeAction(steps);
		}

	}

	/*
	 * Example: Then I perform click on multiple web element using following dataset
	 * locatorIdentifier|params| 
	 * |Button          |Orange| 
	 * |TagWithText      |Apple|
	 */
	@When("I perform click on multiple web element using following dataset")
	public void iPerformClickOnMultipleWebElementUsingFollowingDataset(DataTable dataTable) {
		List<Map<String, String>> dataRows = dataTable.asMaps();
		Map<String, String> row = null;
		if(logStatus) {ExtentReportManager.getTest().info("I perform click on multiple web element using following dataset");}
		for (int i = 0; i < dataRows.size(); i++) {
			row = dataRows.get(i);
			if(logStatus) {ExtentReportManager.getTest().info("| "+row.get("locatorIdentifier")+" | "+row.get("params")+" |");}
			steps.setAction("Click");
			steps.setLocator(testExecutor.generateLocator(row.get("locatorIdentifier"), row.get("params")));
			testExecutor.executeAction(steps);
		}

	}

}
