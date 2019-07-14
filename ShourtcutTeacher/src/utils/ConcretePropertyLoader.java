package utils;

import java.util.Map;

import main.ApplicationMode;

public class ConcretePropertyLoader{

	private final PropertyLoader propertyLoader;
	private String PATH_TO_IMAGES = "conf.PATH_TO_IMAGES";
	private String SUFFIX = "conf.SUFFIX";
	private String EXTENSION = "conf.EXTENSION";
	
	public ConcretePropertyLoader(ApplicationMode applicationMode) {
		this.propertyLoader = new PropertyLoader(applicationMode); 
	}

	public String getPathToImages() {
		return propertyLoader.get(PATH_TO_IMAGES);
	}
	
	public String getSuffix() {
		return propertyLoader.get(SUFFIX);
	}
	
	public String getExtension() {
		return propertyLoader.get(EXTENSION);
	}
	
	public String getShortcutDscription(String keys) {
		return propertyLoader.get(keys);
	}
	
	public Map<String, String> getAllFromPropertyFile(){
		return propertyLoader.getAll();
	}
	
}
