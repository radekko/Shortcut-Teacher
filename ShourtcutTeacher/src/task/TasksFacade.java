package task;

import java.util.List;
import java.util.function.Function;

import main.ApplicationConfig;
import propertyLoaders.ConfigurationPropertyLoader;
import shortcut.ReadShortcut;
import shortcut.ReadingShortcutsMode;
import shortcut.ShortcutReaderFromFilesName;
import shortcut.ShortcutReaderFromProperties;
import shortcut.Shortcuts;

public class TasksFacade {
	private final Tasks tasks;
	
	public TasksFacade(ApplicationConfig applicationConfig) {
		ConfigurationPropertyLoader loader = new ConfigurationPropertyLoader(applicationConfig.getApplicationMode());
		
		Function<ConfigurationPropertyLoader,List<ReadShortcut>> readingShortcutsStrategy = setReadingStrategy(applicationConfig);
		List<ReadShortcut> readShortcuts = readingShortcutsStrategy.apply(loader);
		
		Shortcuts shortcuts = new Shortcuts(readShortcuts);
		
		this.tasks = new Tasks(shortcuts,loader.getTaskInfo());
	}
	
	public Tasks getTasks() {
		return tasks;
	}

	private Function<ConfigurationPropertyLoader, List<ReadShortcut>> setReadingStrategy(ApplicationConfig applicationConfig) {
		return applicationConfig.getReadingMode().equals(ReadingShortcutsMode.FROM_FILE_NAMES) ?
				new ShortcutReaderFromFilesName() : new ShortcutReaderFromProperties();
	}
	
}
