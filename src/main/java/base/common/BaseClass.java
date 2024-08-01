package base.common;

import config.ConfigProp;
import core.actionInterface.IActionUI;
import core.toolFactory.ToolFactory;

public class BaseClass {

	public static IActionUI globalUIDriver = null;

	public static void appStart() {
		globalUIDriver = ToolFactory.uiToolInstance(ConfigProp.UI_TOOL_NAME);
		globalUIDriver.initialize(ConfigProp.BROWSER_NAME, ConfigProp.IS_HEADLESS_MODE);
		globalUIDriver.openURL(ConfigProp.APP_URL);
	}

	public static void closeBrowser() {
		globalUIDriver.closeBrowser();
	}

	public static void click(String locatorValue) {
		globalUIDriver.clickElement(locatorValue);
	}

	public static void type(String locatorValue, String textToEnter) {
		globalUIDriver.enterTextOnElement(locatorValue, textToEnter);
	}

	public static void waitForPageToBeLoad(int timeInSeconds) {
		globalUIDriver.waitForPageLoad(timeInSeconds);
	}

	public static boolean isElementDisplayedOrEnabledOrSelected(String locatorValue, String state) {
		return globalUIDriver.isElementDisplayedOrEnabledOrSelected(locatorValue, state);
	}

	public static void scrollToElement(String locatorValue) {
		globalUIDriver.scrollToElement(locatorValue,"NORMAL");
	}

	
	public static void waitUntill(String locatorValue, String conditionName) {
		globalUIDriver.waitUntill(locatorValue, conditionName);
	}

	public static String getElementAttribute(String locatorValue, String attributeName) {
		return globalUIDriver.getAttributeValue(locatorValue, attributeName);
	}

	public static void waitForElement(int timeInSeconds) {
		globalUIDriver.waitForElement(timeInSeconds);
	}

	public static void jsClick(String locatorValue) {
		globalUIDriver.jsClick(locatorValue);
	}

	public static String getElementText(String locatorValue) {
		return globalUIDriver.getText(locatorValue);
	}

}
