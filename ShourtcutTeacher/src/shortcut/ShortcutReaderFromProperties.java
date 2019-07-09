package shortcut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import utils.PropertyLoader;

public class ShortcutReaderFromProperties implements Supplier<List<ReadShortcut>>{

	@Override
	public List<ReadShortcut> get() {
		Map<String, String> allShortcuts = PropertyLoader.getLoader().getAll();
		return convertMapToListOfShortcuts(allShortcuts);
	}
	
	private List<ReadShortcut> convertMapToListOfShortcuts(Map<String, String> allShortcuts){
		List<ReadShortcut> shortcuts = new ArrayList<>();
		for (Map.Entry<String, String> entry : allShortcuts.entrySet())
			shortcuts.add(new ReadShortcut(entry.getKey(), entry.getValue()));
		
		return shortcuts;
	}
}
