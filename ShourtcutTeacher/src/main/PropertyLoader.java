package main;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

	private final String PROPERTY_FILE_NAME = "app.properties";
	private final Properties prop;
	
	private PropertyLoader() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(PROPERTY_FILE_NAME));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static PropertyLoader getLoader() {
		return new PropertyLoader();
	}

	public String get(String value) {
		return prop.getProperty(value);
	}

}
