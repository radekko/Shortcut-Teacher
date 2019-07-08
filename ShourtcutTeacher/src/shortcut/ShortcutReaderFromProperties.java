package shortcut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import utils.PropertyLoader;

public class ShortcutReaderFromProperties implements Supplier<List<Shortcut>>{

	@Override
	public List<Shortcut> get() {
		Map<String, String> allShortcuts = PropertyLoader.getLoader().getAll();
		return convertMapToListOfShortcuts(allShortcuts);
	}
	
	private List<Shortcut> convertMapToListOfShortcuts(Map<String, String> allShortcuts){
		List<Shortcut> shortcuts = new ArrayList<>();
		for (Map.Entry<String, String> entry : allShortcuts.entrySet())
			shortcuts.add(new Shortcut(entry.getKey(), entry.getValue()));
		
		return shortcuts;
	}
}
