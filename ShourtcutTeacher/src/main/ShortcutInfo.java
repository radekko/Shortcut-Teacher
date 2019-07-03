package main;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ShortcutInfo {
	private final String keysAsString;
	private final String description;
	
	public ShortcutInfo(String keysAsString, String description) {
		this.keysAsString = keysAsString;
		this.description = description;
	}
	
	public String getKeysAsString() {
		return keysAsString;
	}	
	public Set<Integer> getKeys() {
		return convertStringToKeys(keysAsString);
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
