package utililties;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class LoggerHelper {
	
	public static Logger logger;
	public Logger logGenerator() {
		String name = Thread.currentThread().getStackTrace()[2].getMethodName();
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		context.setConfigLocation(new File("log4j2.xml").toURI());
		logger = LogManager.getLogger(name);
		return logger;
	}
}
