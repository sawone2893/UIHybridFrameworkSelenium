package config;

import utililties.PropertyManager;

public class ConfigProp {

	public static final String UI_DRIVER_NAME = "Selenium";
	public static final String BROWSER_NAME = "chrome";
	public static final String CHROME_DRIVER_PATH = "./drivers/chromedriverV126.0.6478.127.exe";
	public static final String FIREFOX_DRIVER_PATH = "./drivers/geckodriver.exe";
	public static final String EDGE_DRIVER_PATH = "./drivers/msedgedriver";
	public static final String CONFIG_ENV = "qaEnvConfig";
	public static final String APP_URL = PropertyManager.getAnyProperty(CONFIG_ENV, "APPURL");
	public static final String USER_NAME = PropertyManager.getAnyProperty(CONFIG_ENV, "USERNAME");
	public static final String PASSWORD = PropertyManager.getAnyProperty(CONFIG_ENV, "PASSWORD");
	public static final int MAX_WAIT_TIME = 120;

}
