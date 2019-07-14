package task;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;

import main.ApplicationMode;
import shortcut.Shortcut;
import shortcut.Shortcuts;
import utils.ConcretePropertyLoader;

public class Tasks implements ITasks {
	private final List<Task> tasks;
	private Iterator<Task> iterator;
	private final ConcretePropertyLoader propertyLoader;

	public Tasks(Shortcuts shortcuts, ApplicationMode applicationMode) {
		this.propertyLoader = new ConcretePropertyLoader(applicationMode);
		this.tasks = createTasks(shortcuts.getShortcuts(propertyLoader));
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
		return propertyLoader.getPathToImages() + shortcut.getKeysAsString() + propertyLoader.getExtension();
	}

	private String createPathToImageAfter(Shortcut shortcut) {
		return propertyLoader.getPathToImages() + shortcut.getKeysAsString() + propertyLoader.getSuffix() + propertyLoader.getExtension();
	}

}