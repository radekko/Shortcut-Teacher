package shortcut;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import utils.KeyMap;

public class Shortcuts {
	private final List<ReadShortcut> readShortcuts;

	public Shortcuts(List<ReadShortcut> readShortcuts) {
		this.readShortcuts = readShortcuts;
	}
	
	public List<Shortcut> getShortcuts(){
		return convertReadShortcut(readShortcuts);
	}
	
	private List<Shortcut> convertReadShortcut(List<ReadShortcut> read){
		return read.stream().map(this::convertSingle).collect(Collectors.toList());
	}
	
	private Shortcut convertSingle(ReadShortcut readShortcut) {
		String keysAsString = readShortcut.getKeysAsString();
		List<Integer> keys = convertStringToKeys(keysAsString);
		String description = readShortcut.getDescription();
		return new Shortcut(keysAsString,keys,description);
	}
	
	private List<Integer> convertStringToKeys(String keysAsString) {
		String[] extractedKeys = getKeysAsTab(keysAsString);
		
		return Arrays.stream(extractedKeys)
					 .map(this::convertSingleKey)
					 .collect(Collectors.toList());
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
