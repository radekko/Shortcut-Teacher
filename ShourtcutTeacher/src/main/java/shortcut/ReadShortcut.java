package shortcut;

public class ReadShortcut {
	private final String keysAsString;
	private final String description;
	
	public ReadShortcut(String keysAsString, String description) {
		this.keysAsString = keysAsString;
		this.description = description;
	}

	public String getKeysAsString() {
		return keysAsString;
	}

	public String getDescription() {
		return description;
	}
	
}
