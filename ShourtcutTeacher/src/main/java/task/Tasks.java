package task;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import shortcut.Shortcut;
import shortcut.Shortcuts;

public class Tasks {
	private final List<Task> tasks;
	private Iterator<Task> iterator;
	private final TaskInfo taskInfo;

	public Tasks(Shortcuts shortcuts, TaskInfo taskInfo) {
		this.taskInfo = taskInfo;
		this.tasks = createTasks(shortcuts.getShortcuts());
		this.iterator = prepareIterator();
	}
	
	public Task getNextTask() {
		if(isLackOfTasks())
			throw new NoSuchElementException();
		
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
		return new Task(shortcut,taskInfo);
	}

}