package shortcut;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import utils.KeyMap;

public class Shortcut {
	private final String keysAsString;
	private final Set<Integer> keys;
	private final String description;

	public Shortcut(String keysAsString, String description) {
		this.keysAsString = keysAsString;
		this.description = description;
		this.keys = convertStringToKeys(keysAsString);
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
	
	private Set<Integer> convertStringToKeys(String keysAsString) {
		String[] extractedKeys = getKeysAsTab(keysAsString);
		
		return Arrays.stream(extractedKeys)
					 .map(this::convertSingleKey)
					 .collect(Collectors.toSet());
	}
	
	private String[] getKeysAsTab(String keysAsString) {
		String[] parts = keysAsString.split("\\+");
		rejectSecondShortcut(parts);
		return parts;
	}
	
	private String[] rejectSecondShortcut(String[] parts) {
		String last = parts[parts.length-1];
		last = last.split("\\(")[0];
		parts[parts.length-1] = last;
		
		return parts;
	}
	
	private int convertSingleKey(String keyName) {
		return KeyMap.getInstance().getKey(keyName);
	}
}