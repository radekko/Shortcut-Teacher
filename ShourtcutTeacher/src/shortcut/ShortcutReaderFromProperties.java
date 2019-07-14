package shortcut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import utils.ConcretePropertyLoader;

public class ShortcutReaderFromProperties implements Function<ConcretePropertyLoader,List<ReadShortcut>>{

	@Override
	public List<ReadShortcut> apply(ConcretePropertyLoader propertyLoader) {
		Map<String, String> allShortcuts = propertyLoader.getAllFromPropertyFile();
		return convertMapToListOfShortcuts(allShortcuts);
	}
	
	private List<ReadShortcut> convertMapToListOfShortcuts(Map<String, String> allShortcuts){
		List<ReadShortcut> shortcuts = new ArrayList<>();
		for (Map.Entry<String, String> entry : allShortcuts.entrySet()) {
			String key = entry.getKey();
			if(checkIfKeyIsShortcut(key))
				shortcuts.add(new ReadShortcut(key, entry.getValue()));
		}
		
		return shortcuts;
	}

	private boolean checkIfKeyIsShortcut(String key) {
		if(key.startsWith("conf"))
			return false;
		
		return true;
	}

}
