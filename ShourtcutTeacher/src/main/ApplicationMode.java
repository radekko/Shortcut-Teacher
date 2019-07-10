package main;

public enum ApplicationMode {
	
	ECLIPSE("eclipse.properties"), FIREFOX("firefox.properties");
	
	private final String configurationFileName;
	
	private ApplicationMode(String propertyFileName) {
		this.configurationFileName = propertyFileName;
	}

	public String getPropertyFileName() {
		return configurationFileName;
	}

}
