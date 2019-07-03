package main;

public class TaskFactory {

	private static final String PATH_TO_IMAGES = "image/";
	private static final String EXTENSION = ".jpg";
	private static final String SUFFIX = "_2";
	private final ShortcutsCreator shortcutsCreator;

	public TaskFactory(ShortcutsCreator shortcutsCreator) {
		this.shortcutsCreator = shortcutsCreator;
	}

	public Task getNextTask() {
		ShortcutInfo shortcut = shortcutsCreator.getNextShortcut();
		return createTaskToCurrentShortcut(shortcut);
	}
	
	private Task createTaskToCurrentShortcut(ShortcutInfo shortcut) {
		String pathToImageBefore = PATH_TO_IMAGES + shortcut.getKeysAsString() + EXTENSION;
		String pathToImageAfter = PATH_TO_IMAGES + shortcut.getKeysAsString() + SUFFIX + EXTENSION;
		return new Task(pathToImageBefore, pathToImageAfter, shortcut);
	}

}