package task;

import java.util.Objects;

public class TaskInfo {
	private final String pathToImages;
	private final String suffix;
	private final String extension;
	
	public TaskInfo(String pathToImages, String suffix, String extension) {
		this.pathToImages = Objects.requireNonNull(pathToImages);
		this.suffix = Objects.requireNonNull(suffix);
		this.extension = Objects.requireNonNull(extension);
	}

	public String getPathToImages() {
		return pathToImages;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getExtension() {
		return extension;
	}
	
}
