package task;

public class TaskInfo {
	private final String pathToImages;
	private final String suffix;
	private final String extension;
	
	public TaskInfo(String pathToImages, String suffix, String extension) {
		this.pathToImages = pathToImages;
		this.suffix = suffix;
		this.extension = extension;
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
