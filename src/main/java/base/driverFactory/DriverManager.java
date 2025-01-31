package base.driverFactory;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
	
	protected WebDriver driver;
	
	public abstract void launchBrowser(boolean isHeadlessMode);
	
	public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }

    }

    public WebDriver getDriver(boolean isHeadlessMode) {
        if (null == driver) {
        	launchBrowser(isHeadlessMode);
        }
        return driver;
    }

}
