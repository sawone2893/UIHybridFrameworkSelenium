package core.modal;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

import base.driverFactory.DriverManager;
import base.driverFactory.DriverManagerFactory;
import config.ConfigProp;
import core.actionInterface.IActionUI;

public class UiActionsSelenium implements IActionUI {

	DriverManager driverManager;
	WebDriver driver;
	private static WebElement element = null;
	private int maxWaitTime = ConfigProp.MAX_WAIT_TIME;
	JavascriptExecutor jExecutor = null;

	@Override
	public void initialize(String browserType, boolean isHeadless) {
		driverManager = DriverManagerFactory.getManager(browserType);
		driver = driverManager.getDriver(isHeadless);
		jExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Override
	public void closeBrowser() {
		driverManager.quitDriver();
	}

	@Override
	public void openURL(String url) {
		System.out.println("Opening url:" + url);
		driver.get(url);
	}

	@Override
	public void refresh() {
		driver.navigate().refresh();
	}

	@Override
	public void clickElement(String locatorValue) {
		if (this.waitUntillElementAppear(locatorValue)) {
			element = findElement(locatorValue);
			waitUntill(locatorValue, "CLICKABLE");
			element.click();
		} else {
			Assert.assertTrue(false, "WebElement [" + locatorValue + "] is not clickable.");
		}
	}

	@Override
	public void enterTextOnElement(String locatorValue, String textToEnter) {
		if (this.waitUntillElementAppear(locatorValue)) {
			element = findElement(locatorValue);
			element.sendKeys(textToEnter);
		} else {
			Assert.assertTrue(false, "WebElement [" + locatorValue + "] is not enabled.");
		}

	}

	@Override
	public void waitUntill(final String locatorValue, final String conditionName) {
		try {
			element = findElement(locatorValue);
			Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
					switch (conditionName.toUpperCase()) {
					case "CLICKABLE": {
						wait.until(ExpectedConditions.elementToBeClickable(element));
						break;
					}
					case "INVISIBLE": {
						wait.until(ExpectedConditions.invisibilityOf(element));
						break;
					}
					case "VISIBLE": {
						wait.until(ExpectedConditions.visibilityOf(element));
						break;
					}
					case "SELECTED": {
						wait.until(ExpectedConditions.elementToBeSelected(element));
						break;
					}
					default:
						Assert.assertTrue(false, "Unsupported wait untill action: " + conditionName);
					}
					return true;
				}
			};
			setFluentWait(function);
		} catch (Exception ex1) {
			ex1.printStackTrace();
		}

	}

	public void setFluentWait(Function<WebDriver, Boolean> function) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.pollingEvery(Duration.ofMillis(500));
		fluentWait.withTimeout(Duration.ofSeconds(maxWaitTime));
		fluentWait.ignoring(NoSuchElementException.class);
		fluentWait.until(function);
	}

	public void setWebDriverWait(Function<WebDriver, Boolean> function, int time) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		wait.until(function);

	}

	public void waitForPageLoad(int time) {

		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.out.println("Current Window State  : "
						+ String.valueOf(jExecutor.executeScript("return document.readyState")));
				return String.valueOf(jExecutor.executeScript("return document.readyState")).equals("complete");
			}
		};

		setWebDriverWait(function, time);
	}

	@Override
	public void waitForElement(int seconds) {
		int time = seconds * 1000;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean isElementDisplayedOrEnabledOrSelected(String locatorValue, String stateType) {
		element = findElement(locatorValue);
		boolean status = false;
		switch (stateType.toUpperCase()) {
		case "DISPLAYED": {
			status = element.isDisplayed();
			break;
		}
		case "ENABLED": {
			status = element.isEnabled();
			break;
		}
		case "SELECTED": {
			status = element.isSelected();
			break;
		}
		default:
			Assert.assertTrue(false, "Unsupported state Type: " + stateType);
		}
		return status;
	}

	@Override
	public String getAttributeValue(String locatorValue, String attributeName) {
		String attributeValue = null;
		waitUntill(locatorValue, "VISIBLE");
		if (this.waitUntillElementAppear(locatorValue)) {
			element = findElement(locatorValue);
			attributeValue = element.getAttribute(attributeName);
		} else {
			Assert.assertTrue(false, "Unable to find attribute value as Web Element is not present in the DOM");
		}
		return attributeValue;
	}

	@Override
	public String getURL() {
		return driver.getCurrentUrl();

	}

	@Override
	public void takeScreenshot(String screenshotName) {
		try {
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File(System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void jsClick(String locatorValue) {
		if (this.waitUntillElementAppear(locatorValue)) {
			element = findElement(locatorValue);
			jExecutor.executeScript("arguments[0].click();", element);
		} else {
			Assert.assertTrue(false, "Unable to perform JSClick: Web Element is not present");
		}

	}

	public String getText(String locatorValue) {
		String textValue = null;
		if (this.waitUntillElementAppear(locatorValue)) {
			element = findElement(locatorValue);
			textValue = element.getText().trim();
		} else {
			Assert.assertTrue(false, "Unable to get Text: Web Element is not present");
		}
		return textValue;
	}

	public void scrollToElement(String locatorValue) {
		if (this.waitUntillElementAppear(locatorValue)) {
			element = findElement(locatorValue);
			jExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

		} else {
			Assert.assertTrue(false, "Unable to perform scroll: Web Element is not present");
		}

	}

	public boolean isElementPresent(String locatorValue) {
		boolean status = false;

		if (findElements(locatorValue).size() > 0 && isElementDisplayedOrEnabledOrSelected(locatorValue, "DISPLAYED")
				&& isElementDisplayedOrEnabledOrSelected(locatorValue, "ENABLED")) {
			status = true;
		}
		return status;

	}

	@Override
	public WebElement findElement(String locatorValue) {
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(locatorValue));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.assertTrue(false, "NoSuchElementException");
		} catch (WebDriverException e) {
			e.printStackTrace();
			Assert.assertTrue(false, "WebDriverException");
		}
		return element;

	}

	@Override
	public List<WebElement> findElements(String locatorValue) {
		List<WebElement> element = null;
		try {
			element = driver.findElements(By.xpath(locatorValue));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.assertTrue(false, "NoSuchElementException");
		} catch (WebDriverException e) {
			e.printStackTrace();
			Assert.assertTrue(false, "WebDriverException");
		}
		return element;

	}

	@Override
	public boolean waitUntillElementAppear(String locatorValue) {
		boolean status = true;
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		try {
			while (!(this.isElementPresent(locatorValue))) {
				System.out.println("Waiting for Element[" + locatorValue + "] to be appear...");
				this.waitForElement(1);
				endTime = System.currentTimeMillis();
				if (endTime - startTime > maxWaitTime * 1000) {
					break;
				}
			}

		} catch (Exception e) {
			status = false;
			System.out.println("Element:: " + locatorValue + " is not appear within the specified timeout");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean waitUntillElementDisappear(String locatorValue) {
		boolean status = true;
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		try {
			while ((this.isElementPresent(locatorValue))) {
				System.out.println("Waiting for Element[" + locatorValue + "] to be disappear...");
				this.waitForElement(1);
				endTime = System.currentTimeMillis();
				if (endTime - startTime > maxWaitTime * 1000) {
					break;
				}
			}

		} catch (Exception e) {
			status = false;
			System.out.println("Element:: " + locatorValue + " is not disappear within the specified timeout");
			e.printStackTrace();
		}
		return status;
	}

}
