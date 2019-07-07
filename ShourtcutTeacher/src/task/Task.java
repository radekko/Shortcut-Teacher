package task;
import javax.swing.ImageIcon;

import shortcut.Shortcut;

public class Task {
	private static final String PATH_TO_IMAGES = "images/";
	private static final String EXTENSION = ".jpg";
	private static final String SUFFIX = "_2";
	
	private final ImageIcon imageBefore;
	private final ImageIcon imageAfter;
	private final Shortcut shortcut;

	Task(Shortcut shortcut) {
		this.shortcut = shortcut;
		this.imageBefore = new ImageIcon(createPathToImageBefore());
		this.imageAfter = new ImageIcon(createPathToImageAfter());
	}
	
	public Shortcut getShortcut() {
		return shortcut;
	}

	public ImageIcon getImageBefore() {
		return imageBefore;
	}

	public ImageIcon getImageAfter() {
		return imageAfter;
	}
	
	private String createPathToImageBefore() {
		return PATH_TO_IMAGES + shortcut.getKeysAsString() + EXTENSION;
	}

	private String createPathToImageAfter() {
		return PATH_TO_IMAGES + shortcut.getKeysAsString() + SUFFIX + EXTENSION;
	}
}
