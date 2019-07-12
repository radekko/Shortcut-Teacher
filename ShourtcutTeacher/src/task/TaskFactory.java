package task;

import main.ApplicationConfig;
import shortcut.Shortcuts;
import shortcut.ShortcutsFactory;

public class TaskFactory {

	public static ITasks getTasks(ApplicationConfig applicationConfig) {
		Shortcuts shortcuts = ShortcutsFactory.getShortcuts(applicationConfig.getReadingMode());
		return new Tasks(shortcuts,applicationConfig.getApplicationMode());
	}
}
