package task;

import shortcut.Shortcut;

public class Task {
	private final String pathBefore;
	private final String pathAfter;
	private final Shortcut shortcut;
	
	public Task(Shortcut shortcut, TaskInfo taskInfo) {
		this.shortcut = shortcut;
		this.pathBefore = createPathToImageBefore(taskInfo);
		this.pathAfter = createPathToImageAfter(taskInfo);
	}
	
	public String getPathBefore() {
		return pathBefore;
	}

	public String getPathAfter() {
		return pathAfter;
	}

	public Shortcut getShortcut() {
		return shortcut;
	}

	private String createPathToImageBefore(TaskInfo taskInfo) {
		return taskInfo.getPathToImages() + shortcut.getKeysAsString() + taskInfo.getExtension();
	}

	private String createPathToImageAfter(TaskInfo taskInfo) {
		return taskInfo.getPathToImages() + shortcut.getKeysAsString() + taskInfo.getSuffix() + taskInfo.getExtension();
	}
	
}
