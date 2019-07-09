package shortcut;

import java.util.Optional;

public class ReadShortcut {
	private final String keysAsString;
	private Optional<String> description;
	
	public ReadShortcut(String keysAsString, String description) {
		this.keysAsString = keysAsString;
		this.description = Optional.of(description);
	}

	public ReadShortcut(String keysAsString) {
		this.keysAsString = keysAsString;
		this.description = Optional.empty();
	}

	public String getKeysAsString() {
		return keysAsString;
	}

	public Optional<String> getDescription() {
		return description;
	}
}
