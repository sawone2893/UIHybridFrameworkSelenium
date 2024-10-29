package utililties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.ConfigProp;

public class ExtentReportManager {

	// ThreadLocal to manage test instance per thread
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	// Private constructor to prevent instantiation
	private ExtentReportManager() {
		// Prevent instantiation
	}

	// Singleton Holder class for lazy initialization of ExtentReports
	private static class ExtentReportHolder {
		private static final ExtentReports INSTANCE = createInstance();
	}

	// Get the singleton instance of ExtentReports
	public static ExtentReports getInstance() {
		return ExtentReportHolder.INSTANCE;
	}

	// Initializes ExtentReports and configures the reporter
	private static ExtentReports createInstance() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(ConfigProp.REPORT_PATH + "executionReport.html");

		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("Automation Execution Report");
		sparkReporter.config().setReportName("Regression Report");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		return extent;
	}

	// Creates a new test instance and stores it in the ThreadLocal variable
	public static ExtentTest createTest(String testName, String description) {
		ExtentTest extentTest = getInstance().createTest(testName, description);
		test.set(extentTest);
		return extentTest;
	}

	// Retrieves the current thread's test instance
	public static ExtentTest getTest() {
		return test.get();
	}

	// Flushes the extent report, ensuring that resources are released
	public static void flush() {
		getInstance().flush(); // No need to check for null, getInstance() handles initialization
	}

	// Cleans up the ThreadLocal to avoid memory leaks
	public static void cleanUp() {
		test.remove();
	}
}
