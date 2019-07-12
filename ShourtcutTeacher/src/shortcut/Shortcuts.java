package shortcut;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import utils.KeyMap;
import utils.PropertyLoader;

public class Shortcuts {
	private final Function<PropertyLoader,List<ReadShortcut>> shortcutsProducer;

	public Shortcuts(Function<PropertyLoader,List<ReadShortcut>> shortcutsProducer) {
		this.shortcutsProducer = shortcutsProducer;
	}
	
	public List<Shortcut> getShortcuts(PropertyLoader propertyLoader){
		List<ReadShortcut> readShortcut = shortcutsProducer.apply(propertyLoader);
		return convertReadShortcut(readShortcut);
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
