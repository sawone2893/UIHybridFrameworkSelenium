package base.actionInterface;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface IActionUI {

	public void initialize(String browserName, String driverLocation, boolean isHeadless);

	public void closeBrowser();

	public void openURL(String url);

	public void refresh();

	public void clickElement(String locatorValue);

	public void enterTextOnElement(String locatorValue, String textToEnter);

	public void waitForElement(int seconds);

	public boolean isElementDisplayedOrEnabledOrSelected(String locatorValue, String state);

	public boolean isElementPresent(String locatorValue);

	public String getAttributeValue(String locatorValue, String attributeName);

	public String getText(String locatorValue);

	public String getURL();

	public void takeScreenshot(String screenshotName);

	public void jsClick(String locatorValue);

	public WebElement findElement(String locatorValue);

	public List<WebElement> findElements(String locatorValue);

	public void waitUntill(final String locatorValue, final String conditionName);

	// public byte [] takeScreenshotAsBytes();
	public void waitForPageLoad(int timeInSeconds);

	public void scrollToElement(String locatorValue);

}
