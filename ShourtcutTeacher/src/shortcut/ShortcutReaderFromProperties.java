package shortcut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import utils.PropertyLoader;

public class ShortcutReaderFromProperties implements Function<PropertyLoader,List<ReadShortcut>>{

	@Override
	public List<ReadShortcut> apply(PropertyLoader propertyLoader) {
		Map<String, String> allShortcuts = propertyLoader.getAll();
		return convertMapToListOfShortcuts(allShortcuts);
	}
	
	private List<ReadShortcut> convertMapToListOfShortcuts(Map<String, String> allShortcuts){
		List<ReadShortcut> shortcuts = new ArrayList<>();
		for (Map.Entry<String, String> entry : allShortcuts.entrySet())
			shortcuts.add(new ReadShortcut(entry.getKey(), entry.getValue()));
		
		return shortcuts;
	}

}
