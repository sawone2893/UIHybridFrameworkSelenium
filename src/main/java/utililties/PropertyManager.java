package utililties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	
	public static String getAnyProperty(String propFileRelativeLocation,String configPropName) {
		String value = null;
		try {
			Properties property = new Properties();
			FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+propFileRelativeLocation+".properties"));
			property.load(fis);
			value = property.getProperty(configPropName);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
