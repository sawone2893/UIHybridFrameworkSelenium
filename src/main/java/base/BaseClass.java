package base;

import config.ConfigProp;
import core.IActionUI;
import core.ToolFactory;

public class BaseClass {

	public static IActionUI globalUIDriver = null;

	public static void appStart() {
		globalUIDriver = ToolFactory.getToolInstance(ConfigProp.TOOL_NAME);
		globalUIDriver.initializeDriver(ConfigProp.BROWSER_TYPE, ConfigProp.IS_HEADLESS_MODE);
		globalUIDriver.openURL(ConfigProp.APP_URL);
	}

	public static void closeBrowser() {
		globalUIDriver.closeBrowser();
	}

	public static void click(String locatorType,String locatorValue) {
		globalUIDriver.click(locatorType,locatorValue);
	}

	public static void type(String locatorType,String locatorValue, String textToEnter) {
		globalUIDriver.type(locatorType,locatorValue, textToEnter);
	}

	public static void waitForPageToBeLoad(int timeInSeconds) {
		globalUIDriver.waitForPageLoad(timeInSeconds);
	}

	public static boolean isElementDisplayedOrEnabledOrSelected(String locatorType,String locatorValue, String state) {
		return globalUIDriver.isElementDisplayedOrEnabledOrSelected(locatorType,locatorValue, state);
	}

	public static void scrollToElement(String locatorType,String locatorValue) {
		globalUIDriver.scrollToElement(locatorType,locatorValue,"NORMAL");
	}

	
	public static void waitUntill(String locatorType,String locatorValue, String conditionName) {
		globalUIDriver.waitUntill(locatorType,locatorValue, conditionName);
	}

	public static String getElementAttribute(String locatorType,String locatorValue, String attributeName) {
		return globalUIDriver.getAttributeValue(locatorType,locatorValue, attributeName);
	}

	public static void waitForElement(int timeInSeconds) {
		globalUIDriver.waitForElement(timeInSeconds);
	}

	public static void jsClick(String locatorType,String locatorValue) {
		globalUIDriver.jsClick(locatorType,locatorValue);
	}

	public static String getElementText(String locatorType,String locatorValue) {
		return globalUIDriver.getText(locatorType,locatorValue);
	}
	
	public static void waitUntillElementAppear(String locatorType,String locatorValue) {
		globalUIDriver.waitUntillElementAppear(locatorType,locatorValue);
	}
	
	public static String snap(String screenshotPath) {
		return globalUIDriver.takeScreenshot(System.getProperty("user.dir")+screenshotPath);
	}
	
	public static String snap(String locatorType,String locatorValue,String screenshotPath) {
		return globalUIDriver.takeScreenshot(locatorType,locatorValue,System.getProperty("user.dir")+screenshotPath);
	}
	
	public static String getTitle() {
		return globalUIDriver.getPageTitle();
	}

}
