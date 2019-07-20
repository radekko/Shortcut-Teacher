package propertyLoaders;

import java.util.Map;

import task.TaskInfo;

public class ConfigurationPropertyLoader {

	private final String PATH_TO_IMAGES = "conf.PATH_TO_IMAGES";
	private final String SUFFIX = "conf.SUFFIX";
	private final String EXTENSION = "conf.EXTENSION";
	private final PropertyLoader propertyLoader;
	private final TaskInfo taskInfo;
	
	public ConfigurationPropertyLoader(Configuration applicationMode) {
		this.propertyLoader = new PropertyLoader(applicationMode);
		this.taskInfo = new TaskInfo(getPathToImages(),getSuffix(),getExtension());
	}
	
	public String getShortcutDescription(String keys) {
		return propertyLoader.get(keys);
	}
	
	public Map<String, String> getAllFromPropertyFile(){
		return propertyLoader.getAll();
	}

	public TaskInfo getTaskInfo() {
		return taskInfo;
	}

	private String getPathToImages() {
		return propertyLoader.get(PATH_TO_IMAGES);
	}
	
	private String getSuffix() {
		return propertyLoader.get(SUFFIX);
	}
	
	private String getExtension() {
		return propertyLoader.get(EXTENSION);
	}
	
}
