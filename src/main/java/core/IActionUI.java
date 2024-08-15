package core;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface IActionUI {

	public void initialize(String browserName, boolean isHeadless);

	public void closeCurrentTabWindow();
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

	public String takeScreenshot(String screenshotPath);
	public String takeScreenshot(String locatorValue,String screenshotPath);

	public void jsClick(String locatorValue);

	public WebElement findElement(String locatorValue);

	public List<WebElement> findElements(String locatorValue);

	public void waitUntill(final String locatorValue, final String conditionName);

	public void waitForPageLoad(int timeInSeconds);

	public void scrollToElement(String locatorValue,String scrollType);

	public boolean waitUntillElementAppear(String locatorValue);

	public boolean waitUntillElementDisappear(String locatorValue);
	public void navigateTo(String direction);
	public void performWindowTabSwitch(int windowTabIndex);
	public void createNewWindowTabSwitch(String type);
	public void hoverElement(String locatorValue);
	public void rightClickElement(String locatorValue);
	public void doubleClickElement(String locatorValue);
	public  String getTitle();

}
