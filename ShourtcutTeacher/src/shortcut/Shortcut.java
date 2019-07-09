package shortcut;

import java.util.Set;

public class Shortcut {
	private final String keysAsString;
	private final Set<Integer> keys;
	private final String description;
	
	public Shortcut(String keysAsString, Set<Integer> keys, String description) {
		this.keysAsString = keysAsString;
		this.keys = keys;
		this.description = description;
	}

	public String getKeysAsString() {
		return keysAsString;
	}

	public Set<Integer> getKeys() {
		return keys;
	}

	public String getDescription() {
		return description;
	}
}