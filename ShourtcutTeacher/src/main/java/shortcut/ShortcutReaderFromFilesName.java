package shortcut;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import propertyLoaders.ConfigurationPropertyLoader;

public class ShortcutReaderFromFilesName implements Function<ConfigurationPropertyLoader,List<Shortcut>>{
	private ConfigurationPropertyLoader propertyLoader;
	
	@Override
	public List<Shortcut> apply(ConfigurationPropertyLoader propertyLoader) {
		this.propertyLoader = propertyLoader;
		return createPossibleShortcutsFromFilesName();
	}
	
	private List<Shortcut> createPossibleShortcutsFromFilesName() {
		List<String> keys = readKeysFromFilename();
		return keys.stream().map(createShortcut()).collect(Collectors.toList());
	}

	private Function<? super String, ? extends Shortcut> createShortcut() {
		return key -> {
			 String description = loadDescriptionFromProperty(key);
			 return new Shortcut(key,description);
		 };
	}

	private String loadDescriptionFromProperty(String key) {
		return propertyLoader.getShortcutDescription(key);
	}
	
	private List<String> readKeysFromFilename() {
		File[] files = new File(propertyLoader.getTaskInfo().getPathToImages()).listFiles();

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
		if (s.contains(propertyLoader.getTaskInfo().getSuffix()))
			return false;
		return true;
	}

	private String removeExtension(String s) {
		return s.replace(propertyLoader.getTaskInfo().getExtension(), "");
	}

}
