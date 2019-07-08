package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
	
	public Map<String, String> getAll(){
		Map<String, String> map = new HashMap<>();
		for (Entry<Object, Object> entry : prop.entrySet())
		    map.put((String) entry.getKey(), (String) entry.getValue());
		
		return map;
	}

}
