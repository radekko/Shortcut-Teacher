package main;

import java.io.FileNotFoundException;
import java.util.Optional;

public class TaskCreator {

	private static final String PATH_TO_IMAGES = "images/";
	private static final String EXTENSION = ".jpg";
	private static final String SUFFIX = "_2";
	private final ShortcutsInfoCreator shortcutsCreator;

	public TaskCreator(ShortcutsInfoCreator shortcutsCreator) {
		this.shortcutsCreator = shortcutsCreator;
	}

	public Task getNextTask() throws FileNotFoundException{
		Optional<ShortcutInfo> shortcut = shortcutsCreator.getNextShortcut();
		return shortcut.flatMap(this::createTask).orElseThrow(() -> new FileNotFoundException("Lack of tasks"));
	}
	
	private Optional<Task> createTask(ShortcutInfo shortcut){
		String pathToImageBefore = PATH_TO_IMAGES + shortcut.getKeysAsString() + EXTENSION;
		String pathToImageAfter = PATH_TO_IMAGES + shortcut.getKeysAsString() + SUFFIX + EXTENSION;
		return Optional.of(new Task(pathToImageBefore, pathToImageAfter, shortcut));
	}

}