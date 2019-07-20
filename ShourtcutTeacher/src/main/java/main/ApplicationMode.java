package main;

import propertyLoaders.Configuration;

public enum ApplicationMode implements Configuration{
	
	ECLIPSE("eclipse.properties"), FIREFOX("firefox.properties");
	
	private final String configurationFileName;
	
	private ApplicationMode(String propertyFileName) {
		this.configurationFileName = propertyFileName;
	}

	public String getPropertyFileName() {
		return configurationFileName;
	}

}
