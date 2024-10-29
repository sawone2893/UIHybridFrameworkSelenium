package config;

import io.github.shabryn2893.utils.PropertyFileManager;

public class ConfigProp {
	
	private ConfigProp() {}

	public static final String PROJECT_PATH=System.getProperty("user.dir");
	public static final String DOWNLOAD_PATH = PROJECT_PATH+"/resources/downloads";
	public static final String LOCATORS_PATH=PROJECT_PATH+"/resources/locators/locators";
	public static final String ENV_CONFIG_PATH = PROJECT_PATH+"/resources/envConfig/qaEnvConfig.properties";
	public static final String REPORT_CONFIG_PATH = PROJECT_PATH+"/resources/reportConfig/";
	public static final String REPORT_PATH = PROJECT_PATH+"/reports/";
	public static final String SCREENSHOT_PATH = PROJECT_PATH+"/reports/screenshots/";
	
	public static final String TOOL_NAME = "SELENIUM";
	public static final String BROWSER_TYPE = "CHROME";
	public static final boolean IS_HEADLESS_MODE =false;
	public static final boolean LOG_MODE =true;
	public static final String APP_URL = PropertyFileManager.getProperty(ENV_CONFIG_PATH, "APPURL");
	public static final String USER_NAME = PropertyFileManager.getProperty(ENV_CONFIG_PATH, "USERNAME");
	public static final String PASSWORD = PropertyFileManager.getProperty(ENV_CONFIG_PATH, "PASSWORD");
	public static final int MAX_WAIT_TIME = 120;

}
