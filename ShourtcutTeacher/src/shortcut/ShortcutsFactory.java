package shortcut;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import utils.KeyMap;
import utils.PropertyLoader;

public class ShortcutsFactory {
	private final Function<PropertyLoader,List<ReadShortcut>> shortcutsProducer;

	public ShortcutsFactory(Function<PropertyLoader,List<ReadShortcut>> shortcutsProducer) {
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
		Set<Integer> keys = convertStringToKeys(keysAsString);
		String description = readShortcut.getDescription();
		return new Shortcut(keysAsString,keys,description);
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
