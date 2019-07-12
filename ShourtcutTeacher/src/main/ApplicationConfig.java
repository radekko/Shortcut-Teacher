package main;

import shortcut.ReadingShortcutsMode;

public enum ApplicationConfig {
	FIREFOX_READ_FROM_FILE_NAMES(ApplicationMode.FIREFOX, ReadingShortcutsMode.FROM_FILE_NAMES),
	ECLIPSE_READ_FROM_FILE_NAMES(ApplicationMode.ECLIPSE, ReadingShortcutsMode.FROM_FILE_NAMES),
	ECLIPSE_READ_FROM_PROPERTIES(ApplicationMode.ECLIPSE, ReadingShortcutsMode.FROM_PROPERTY_FILE),
	FIREFOX_READ_FROM_PROPERTIES(ApplicationMode.FIREFOX, ReadingShortcutsMode.FROM_PROPERTY_FILE);
	
	private final ApplicationMode applicationMode;
	private final ReadingShortcutsMode readingMode;
	
	private ApplicationConfig(ApplicationMode applicationMode, ReadingShortcutsMode readingMode) {
		this.applicationMode = applicationMode;
		this.readingMode = readingMode;
	}

	public ApplicationMode getApplicationMode() {
		return applicationMode;
	}

	public ReadingShortcutsMode getReadingMode() {
		return readingMode;
	}
	
}
