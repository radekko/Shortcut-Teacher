package task;

import main.ApplicationConfig;
import propertyLoaders.ConfigurationPropertyLoader;
import shortcut.Shortcuts;

public class TasksFacade {
	private final Tasks tasks;
	
	public TasksFacade(ApplicationConfig applicationConfig) {
		ConfigurationPropertyLoader loader = new ConfigurationPropertyLoader(applicationConfig.getApplicationMode());
		
		Shortcuts shortcuts = new Shortcuts(loader, applicationConfig.getReadingMode());
		
		this.tasks = new Tasks(shortcuts,loader.getTaskInfo());
	}
	
	public Tasks getTasks() {
		return tasks;
	}

}
