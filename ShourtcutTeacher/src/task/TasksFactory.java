package task;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;

import shortcut.Shortcut;
import shortcut.ShortcutsFactory;

public class TasksFactory {
	private static final String PATH_TO_IMAGES = "images/";
	private static final String EXTENSION = ".jpg";
	private static final String SUFFIX = "_2";
	private final List<Task> tasks;
	private Iterator<Task> iterator;

	public TasksFactory(ShortcutsFactory shortcutsFactory) {
		this.tasks = createTasks(shortcutsFactory.getShortcuts());
		this.iterator = prepareIterator();
	}
	
	public Task getNextTask() throws FileNotFoundException{
		if(isLackOfTasks())
			throw new FileNotFoundException();
		
		if (iterator.hasNext())
			return iterator.next();
		
		iterator = prepareIterator();
		return getNextTask();
	}

	private boolean isLackOfTasks() {
		return tasks.isEmpty();
	}

	private Iterator<Task> prepareIterator() {
		Collections.shuffle(tasks);
		return tasks.iterator();
	}
	
	private List<Task> createTasks(List<Shortcut> list){
		return list.stream().map(this::createTask).collect(Collectors.toList());
	}
	
	private Task createTask(Shortcut shortcut) {
		return new Task(shortcut,
				 new ImageIcon(createPathToImageBefore(shortcut)),
				 new ImageIcon(createPathToImageAfter(shortcut)));
	}

	private String createPathToImageBefore(Shortcut shortcut) {
		return PATH_TO_IMAGES + shortcut.getKeysAsString() + EXTENSION;
	}

	private String createPathToImageAfter(Shortcut shortcut) {
		return PATH_TO_IMAGES + shortcut.getKeysAsString() + SUFFIX + EXTENSION;
	}

}