package shortcut;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import utils.KeyMap;
import utils.PropertyLoader;

public class Shortcut {
	private final String keysAsString;
	private final Set<Integer> keys;
	private final String description;
	
	Shortcut(String keysAsString) {
		this.keysAsString = keysAsString;
		this.keys = convertStringToKeys(keysAsString);
		this.description = PropertyLoader.getLoader().get(keysAsString);
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
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Shortcut)) {
			return false;
		}
		Shortcut info = (Shortcut) o;
		return keysAsString == info.keysAsString && description == info.description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(keysAsString, keys, description);
	}
}