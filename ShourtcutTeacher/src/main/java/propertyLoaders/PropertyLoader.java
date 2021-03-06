package propertyLoaders;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

class PropertyLoader {
	private final Properties prop;
	
	PropertyLoader(Configuration applicationMode) {
		this.prop = new Properties();
		try {
			prop.load(new FileInputStream(applicationMode.getPropertyFileName()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	String get(String value) {
		return prop.getProperty(value);
	}
	
	Map<String, String> getAll(){
		Map<String, String> map = new HashMap<>();
		for (Entry<Object, Object> entry : prop.entrySet())
		    map.put((String) entry.getKey(), (String) entry.getValue());
		
		return map;
	}

}
