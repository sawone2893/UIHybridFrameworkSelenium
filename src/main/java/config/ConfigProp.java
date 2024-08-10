package config;

import utililties.PropertyManager;

public class ConfigProp {

	public static final String UI_TOOL_NAME = "Selenium";
	public static final String BROWSER_NAME = "chrome";
	public static final boolean IS_HEADLESS_MODE =false;
	public static final String DOWNLOAD_PATH = "/downloads";
	public static final String LOCATORS_PATH="/src/main/java/locators/";
	public static final String RESOURCES_PATH = "/resources/";
	public static final String REPORTS_PATH = "/reports/";
	public static final String CONFIG_ENV = "qaEnvConfig";
	public static final String APP_URL = PropertyManager.getAnyProperty(RESOURCES_PATH+CONFIG_ENV, "APPURL");
	public static final String USER_NAME = PropertyManager.getAnyProperty(RESOURCES_PATH+CONFIG_ENV, "USERNAME");
	public static final String PASSWORD = PropertyManager.getAnyProperty(RESOURCES_PATH+CONFIG_ENV, "PASSWORD");
	public static final int MAX_WAIT_TIME = 120;

}
