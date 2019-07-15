package task;

import javax.swing.ImageIcon;

import shortcut.Shortcut;

public class Task {
	private final ImageIcon imageBefore;
	private final ImageIcon imageAfter;
	private final Shortcut shortcut;
	
	public Task(Shortcut shortcut, ImageIcon imageBefore, ImageIcon imageAfter) {
		this.shortcut = shortcut;
		this.imageBefore = imageBefore;
		this.imageAfter = imageAfter;
	}

	public ImageIcon getImageBefore() {
		return imageBefore;
	}

	public ImageIcon getImageAfter() {
		return imageAfter;
	}

	public Shortcut getShortcut() {
		return shortcut;
	}
}
