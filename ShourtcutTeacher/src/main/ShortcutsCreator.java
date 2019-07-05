package main;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ShortcutsCreator {

	private static final String PATH_TO_IMAGES = "images/";
	private static final String EXTENSION = ".jpg";
	private static final String SUFFIX = "_2";

	private final Set<ShortcutInfo> shortcutsInfo;
	private Iterator<ShortcutInfo> iterator;
	private final PropertyLoader propertyLoader;

	public ShortcutsCreator(PropertyLoader propertyLoader) {
		this.propertyLoader = propertyLoader;
		this.shortcutsInfo = createPossibleShortcutsFromFilesName();
		this.iterator = shortcutsInfo.iterator();
	}

	public Optional<ShortcutInfo> getNextShortcut() {
		if(shortcutsInfo.isEmpty())
			return Optional.empty();
		
		if (iterator.hasNext())
			return Optional.of(iterator.next());

		iterator = shortcutsInfo.iterator();
		return getNextShortcut();
	}

	private Set<ShortcutInfo> createPossibleShortcutsFromFilesName() {
		Set<String> keys = readKeyShortcutsFromFilename();
		return keys.stream().map(this::convertKeysToShortcutInfo).collect(Collectors.toSet());
	}

	private ShortcutInfo convertKeysToShortcutInfo(String keys) {
		return new ShortcutInfo(keys, propertyLoader.get(keys));
	}

	private Set<String> readKeyShortcutsFromFilename() {
		File[] files = new File(PATH_TO_IMAGES).listFiles();

		return Arrays.stream(files)
					 .filter(this::fileIsNotHidden)
					 .filter(File::isFile)
					 .map(File::getName)
					 .filter(this::rejectDuplicate)
					 .map(this::removeExtension)
					 .collect(Collectors.toSet());
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

}
