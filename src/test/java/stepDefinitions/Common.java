package stepDefinitions;

import actionsKeyword.TestExecutor;
import actionsKeyword.TestSteps;
import base.instancesFactory.FactoryRegistry;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Common {
	
	TestExecutor testExecutor=FactoryRegistry.getTestExecutor();
	TestSteps steps=FactoryRegistry.getTestSteps();
	
	/*Example:
	 * Then I "Click" on "Button" with values "Login"
	   Then I "WaitUntillElementDisappear" on "Button" with values "Login"
	   Then I "WaitUntillElementAppear" on "Button" with values "Login"
	 */
	@Then("I {string} on {string} with values {string}")
	public void iOnWithValues(String action,String locatorIdentifier,String param){
		steps.setAction(action);
		steps.setLocator(testExecutor.generateLocator(locatorIdentifier, param));
		testExecutor.executeAction(steps);
	}
	
	/*Example:
	 * Then I "WaitForPageLoadState" "load"
	 */
	@Given("I {string} {string}")
	public void i(String action, String value){
		steps.setAction(action);
		steps.setValue(value);
		testExecutor.executeAction(steps);
	}
	
	/*Example:
	 * When I "EnterValue" "Shabbir" for "TextField" with values "Username"
	 * Then I "WaitUntill" "VISIBLE" for "TextField" with values "Username"
	 */
	@When("I {string} {string} for {string} with values {string}")
	public void iForWithValues(String action,String value,String locatorIdentifier,String param){
		steps.setAction(action);
		steps.setValue(value);
		steps.setLocator(testExecutor.generateLocator(locatorIdentifier, param));
		testExecutor.executeAction(steps);
	    
	}

	/*Example:
	 * Then I "VerifyVisibility" is "true" for "TagWithText" with values "span~<OrderConfirmationText>"
	 * Then I "VerifySelected" is "true" for "TagWithText" with values "span~<OrderConfirmationText>"
	 */
	@Then("I {string} is {string} for {string} with values {string}")
	public void iIsForWithValues(String action,String value,String locatorIdentifier,String param){
		steps.setAction(action);
		steps.setValue(value);
		steps.setLocator(testExecutor.generateLocator(locatorIdentifier, param));
		testExecutor.executeAction(steps);
	}
	
	/*Example:
	* Then I "WaitForElement" "5" seconds
	*/
	@Then("I {string} {string} seconds")
	public void iSeconds(String action, String value){
		steps.setAction(action);
		steps.setValue(value);
		testExecutor.executeAction(steps);
	    
	}

}
