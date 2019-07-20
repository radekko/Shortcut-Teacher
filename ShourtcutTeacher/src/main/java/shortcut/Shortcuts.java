package shortcut;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import propertyLoaders.ConfigurationPropertyLoader;

public class Shortcuts {
	private final List<Shortcut> readShortcuts;
	private final Function<ConfigurationPropertyLoader,List<Shortcut>> readingShortcutsStrategy;

	public Shortcuts(ConfigurationPropertyLoader loader,ReadingShortcutsMode readingShortcutsMode) {
		this.readingShortcutsStrategy = setReadingStrategy(readingShortcutsMode);
		this.readShortcuts = readingShortcutsStrategy.apply(loader);
	}
	
	public List<Shortcut> getShortcuts(){
		return new ArrayList<Shortcut>(readShortcuts);
	}
	
	private Function<ConfigurationPropertyLoader, List<Shortcut>> setReadingStrategy(ReadingShortcutsMode readingShortcutsMode) {
		return readingShortcutsMode.equals(ReadingShortcutsMode.FROM_FILE_NAMES) ?
				new ShortcutReaderFromFilesName() : new ShortcutReaderFromProperties();
	}	
}
