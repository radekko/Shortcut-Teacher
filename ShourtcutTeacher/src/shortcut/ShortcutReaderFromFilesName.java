package shortcut;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import utils.PropertyLoader;

public class ShortcutReaderFromFilesName implements Function<PropertyLoader,List<ReadShortcut>>{
	private PropertyLoader propertyLoader;
	
	@Override
	public List<ReadShortcut> apply(PropertyLoader propertyLoader) {
		this.propertyLoader = propertyLoader;
		return createPossibleShortcutsFromFilesName();
	}
	
	private List<ReadShortcut> createPossibleShortcutsFromFilesName() {
		List<String> keys = readKeysFromFilename();
		return keys.stream().map(createReadShortcuts()).collect(Collectors.toList());
	}

	private Function<? super String, ? extends ReadShortcut> createReadShortcuts() {
		return key -> {
			 String description = loadDescriptionFromProperty(key);
			 return new ReadShortcut(key,description);
		 };
	}

	private String loadDescriptionFromProperty(String key) {
		return propertyLoader.get(key);
	}
	
	private List<String> readKeysFromFilename() {
		File[] files = new File(propertyLoader.get("conf.PATH_TO_IMAGES")).listFiles();

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
		if (s.contains(propertyLoader.get("conf.SUFFIX")))
			return false;
		return true;
	}

	private String removeExtension(String s) {
		return s.replace(propertyLoader.get("conf.EXTENSION"), "");
	}

}
