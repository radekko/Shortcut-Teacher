package main;
import javax.swing.ImageIcon;

public class Task {

	private final ImageIcon imageBefore;
	private final ImageIcon imageAfter;
	private final ShortcutInfo shortcut;

	public Task(String pathToImageBefore, String pathToImageAfter, ShortcutInfo shortcut) {
		this.shortcut = shortcut;
		this.imageBefore = new ImageIcon(pathToImageBefore);
		this.imageAfter = new ImageIcon(pathToImageAfter);
	}
	
	public ShortcutInfo getShortcut() {
		return shortcut;
	}

	public ImageIcon getImageBefore() {
		return imageBefore;
	}

	public ImageIcon getImageAfter() {
		return imageAfter;
	}

}
