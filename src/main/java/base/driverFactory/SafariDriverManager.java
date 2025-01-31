package base.driverFactory;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariDriverManager extends DriverManager{

	@Override
	public void launchBrowser(boolean isHeadlessMode) {
		SafariOptions options= new SafariOptions();
		options.setAcceptInsecureCerts(true);
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver=new SafariDriver(options);
		
	}

}
