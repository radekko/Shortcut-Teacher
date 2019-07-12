package shortcut;

public class ShortcutsFactory {
	
	public static Shortcuts getShortcuts(ReadingShortcutsMode readingMode) {
		switch(readingMode) {
		case FROM_FILE_NAMES:
			return new Shortcuts(new ShortcutReaderFromFilesName());
//		READ_FROM_PROPERTY_FILE:
		default:
			return new Shortcuts(new ShortcutReaderFromProperties());
		}
	}

}
