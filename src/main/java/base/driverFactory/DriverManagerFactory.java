package base.driverFactory;

public class DriverManagerFactory {
	public static DriverManager getManager(String browsertype) {
		DriverManager driverManager;
		switch (browsertype.toUpperCase()) {
		case "CHROME":
			driverManager = new ChromeDriverManager();
			break;
		case "FIREFOX":
			driverManager = new FirefoxDriverManager();
			break;
		case "EDGE":
			driverManager = new EdgeDriverManager();
			break;
		default:
			driverManager = new SafariDriverManager();
			break;
		}
		return driverManager;
	}

}
