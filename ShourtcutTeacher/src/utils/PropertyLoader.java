package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import main.ApplicationMode;

import java.util.Properties;

public class PropertyLoader {
	private static PropertyLoader ECLIPSE_LOADER = new PropertyLoader(ApplicationMode.ECLIPSE);
	private static PropertyLoader FIREFOX_LOADER = new PropertyLoader(ApplicationMode.FIREFOX);
	private final Properties prop;
	
	private PropertyLoader(ApplicationMode applicationMode) {
		this.prop = new Properties();
		try {
			prop.load(new FileInputStream(applicationMode.getPropertyFileName()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static PropertyLoader getLoader(ApplicationMode mode) {
		return (mode == ApplicationMode.ECLIPSE) ? ECLIPSE_LOADER : FIREFOX_LOADER;
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
