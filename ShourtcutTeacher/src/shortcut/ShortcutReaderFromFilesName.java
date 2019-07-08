package shortcut;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ShortcutReaderFromFilesName implements Supplier<List<Shortcut>>{
	private static final String PATH_TO_IMAGES = "images/";
	private static final String EXTENSION = ".jpg";
	private static final String SUFFIX = "_2";
	
	@Override
	public List<Shortcut> get() {
		return createPossibleShortcutsFromFilesName();
	}
	
	private List<Shortcut> createPossibleShortcutsFromFilesName() {
		List<String> keys = readKeysFromFilename();
		return keys.stream().map(Shortcut::new).collect(Collectors.toList());
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

}
