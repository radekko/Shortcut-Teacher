package shortcut;

import java.util.List;

public class Shortcut {
	private final String keysAsString;
	private final List<Integer> keys;
	private final String description;
	
	public Shortcut(String keysAsString, List<Integer> keys, String description) {
		this.keysAsString = keysAsString;
		this.keys = keys;
		this.description = description;
	}

	public String getKeysAsString() {
		return keysAsString;
	}

	public List<Integer> getKeys() {
		return keys;
	}

	public String getDescription() {
		return description;
	}
}