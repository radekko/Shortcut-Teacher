package task;

import main.ApplicationConfig;
import propertyLoaders.ConfigurationPropertyLoader;
import shortcut.Shortcuts;

public class TasksFactory {
	private final ApplicationConfig applicationConfig;
	
	public TasksFactory(ApplicationConfig applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	public Tasks getTasks() {
		ConfigurationPropertyLoader loader = new ConfigurationPropertyLoader(applicationConfig.getApplicationMode());
		Shortcuts shortcuts = new Shortcuts(loader, applicationConfig.getReadingMode());
		return new Tasks(shortcuts,loader.getTaskInfo());
	}

}
