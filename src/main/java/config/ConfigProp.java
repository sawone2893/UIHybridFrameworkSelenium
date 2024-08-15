package config;

import utililties.PropertyManager;

public class ConfigProp {

	public static final String DOWNLOAD_PATH = "/resources/downloads";
	public static final String LOCATORS_PATH="/resources/locators/locators";
	public static final String ENV_CONFIG_PATH = "/resources/envConfig/qaEnvConfig";
	public static final String REPORT_CONFIG_PATH = "/resources/reportConfig/";
	public static final String REPORT_PATH = "/reports/";
	public static final String SCREENSHOT_PATH = "/reports/screenshots/";
	
	public static final String UI_TOOL_NAME = "Selenium";
	public static final String BROWSER_NAME = "chrome";
	public static final boolean IS_HEADLESS_MODE =false;
	public static final boolean LOG_MODE =true;
	public static final String APP_URL = PropertyManager.getAnyProperty(ENV_CONFIG_PATH, "APPURL");
	public static final String USER_NAME = PropertyManager.getAnyProperty(ENV_CONFIG_PATH, "USERNAME");
	public static final String PASSWORD = PropertyManager.getAnyProperty(ENV_CONFIG_PATH, "PASSWORD");
	public static final int MAX_WAIT_TIME = 20;

}
