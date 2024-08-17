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
	
	public static void waitUntillElementAppear(String locatorValue) {
		globalUIDriver.waitUntillElementAppear(locatorValue);
	}
	
	public static String snap(String screenshotPath) {
		return globalUIDriver.takeScreenshot(System.getProperty("user.dir")+screenshotPath);
	}
	
	public static String snap(String locatorValue,String screenshotPath) {
		return globalUIDriver.takeScreenshot(locatorValue,System.getProperty("user.dir")+screenshotPath);
	}
	
	public static String getTitle() {
		return globalUIDriver.getPageTitle();
	}

}
