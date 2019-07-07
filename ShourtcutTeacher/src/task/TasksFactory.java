package task;

import java.io.FileNotFoundException;
import java.util.Optional;

import shortcut.Shortcut;
import shortcut.ShortcutsFactory;

public class TasksFactory {
	
	private final ShortcutsFactory shortcutsFactory;

	public TasksFactory(ShortcutsFactory shortcutsFactory) {
		this.shortcutsFactory = shortcutsFactory;
	}

	public Task getNextTask() throws FileNotFoundException{
		Optional<Shortcut> shortcut = shortcutsFactory.getNextShortcut();
		return shortcut.flatMap(this::createTask).orElseThrow(() -> new FileNotFoundException("Lack of tasks"));
	}
	
	private Optional<Task> createTask(Shortcut shortcut){
		return Optional.of(new Task(shortcut));
	}

}