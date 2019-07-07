package main;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShortcutsInfoCreator {

	private static final String PATH_TO_IMAGES = "images/";
	private static final String EXTENSION = ".jpg";
	private static final String SUFFIX = "_2";

	private final List<ShortcutInfo> shortcutsInfo;
	private Iterator<ShortcutInfo> iterator;

	public ShortcutsInfoCreator() {
		this.shortcutsInfo = createPossibleShortcutsFromFilesName();
		this.iterator = prepareIterator();
	}

	public Optional<ShortcutInfo> getNextShortcut() {
		if(isLackOfTasks())
			return Optional.empty();
		
		if (iterator.hasNext())
			return Optional.of(iterator.next());
		
		iterator = prepareIterator();
		return getNextShortcut();
	}

	private boolean isLackOfTasks() {
		return shortcutsInfo.isEmpty();
	}

	private List<ShortcutInfo> createPossibleShortcutsFromFilesName() {
		List<String> keys = readKeysFromFilename();
		return keys.stream().map(this::convertKeysToShortcutInfo).collect(Collectors.toList());
	}

	private ShortcutInfo convertKeysToShortcutInfo(String keys) {
		return new ShortcutInfo(keys);
	}

	private List<String> readKeysFromFilename() {
		File[] files = new File(PATH_TO_IMAGES).listFiles();

		return Arrays.stream(files)
					 .filter(this::fileIsNotHidden)
					 .filter(File::isFile)
					 .map(File::getName)
					 .filter(this::rejectDuplicate)
					 .map(this::removeExtension)
					 .collect(Collectors.toList());
	}
	
	private boolean fileIsNotHidden(File f) {
		return !f.isHidden();
	}

	private boolean rejectDuplicate(String s) {
		if (s.contains(SUFFIX))
			return false;
		return true;
	}

	private String removeExtension(String s) {
		return s.replace(EXTENSION, "");
	}
	
	private Iterator<ShortcutInfo> prepareIterator() {
		Collections.shuffle(shortcutsInfo);
		return shortcutsInfo.iterator();
	}

}
