package actionsKeyword;

import org.testng.Assert;

import base.common.BaseClass;
import utililties.PropertyManager;

public class TestExecutor{

	private static TestExecutor testExecutor=null;
	
	private TestExecutor() {}
	
	public static TestExecutor getInstance() {
		if(testExecutor==null) {
			testExecutor=new TestExecutor();
		}
		return testExecutor;
	}

	public void executeAction(TestSteps steps) {
		switch (steps.getAction()) {
		case "Click":{
			BaseClass.click(steps.getLocator());
			break;
		}case "EnterValue":{
			BaseClass.type( steps.getLocator(),steps.getValue());
			break;
		}case "WaitForElement":{
			BaseClass.waitForElement(Integer.parseInt(steps.getValue()));
			break;
		}case "WaitForPageToBeLoad":{
			BaseClass.waitForPageToBeLoad(Integer.parseInt(steps.getValue()));
			break;	
		}case "JSClick":{
			BaseClass.jsClick(steps.getLocator());
			break;	
		}case "ScrollToElement":{
			BaseClass.scrollToElement(steps.getLocator());
			break;
		}case "VerifyAttributeValue":{
			String attriValue[]=steps.getValue().split("|");
			String status=BaseClass.getElementAttribute(steps.getLocator(),attriValue[0]);
			Assert.assertEquals(status, attriValue[1],"Expected: "+attriValue[1]+" Actual: "+status);
			break;
		}case "VerifyTextValue":{
			String textValue=BaseClass.getElementText(steps.getLocator());
			Assert.assertEquals(textValue, steps.getValue(),"Expected: "+steps.getValue()+" Actual: "+textValue);
			break;
		}case "VerifyVisibility":{
			boolean status=BaseClass.isElementDisplayedOrEnabledOrSelected(steps.getLocator(), "DISPLAYED");
			Assert.assertEquals(status,Boolean.parseBoolean(steps.getValue()));
			break;
		}default:
			Assert.assertTrue(false, steps.getAction() + "is not defined.Please define your action in the TestExecutor Class.");
		}
	}
	
	public String generateLocator(String locatorIdentifier, String paramValues){
		String xpath = PropertyManager.getAnyProperty("locators", locatorIdentifier);
		String locatorValue = xpath;
		if (paramValues.contains("~")) {
			String[] parameters = paramValues.split("~");
			int j = parameters.length;
			// logger_method().info(j);
			for (int i = 0; i < j; i++) {
				String replacement = parameters[i];
				locatorValue = xpath.replace("$" + i + "$", replacement);
				xpath = locatorValue;
			}
		} else {
			locatorValue = xpath.replace("$0$", paramValues);
			xpath = locatorValue;
		}
		System.out.println("LOCATOR_VALUE: "+locatorValue);
		return locatorValue;
	}

}
