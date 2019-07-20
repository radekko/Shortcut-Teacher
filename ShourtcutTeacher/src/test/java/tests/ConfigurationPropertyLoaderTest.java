package tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import propertyLoaders.Configuration;
import propertyLoaders.ConfigurationPropertyLoader;
import task.TaskInfo;
@RunWith(MockitoJUnitRunner.class)
public class ConfigurationPropertyLoaderTest {

	private static final String USER_DIR = System.getProperty("user.dir");
	private static final String PATH_TO_PROPERTY_FILE = USER_DIR + "\\src\\test\\java\\tests\\";
	
	@Mock
	private Configuration conf;
	
	@Test
	public void readingPropertiesAreValid() {
		when(conf.getPropertyFileName()).thenReturn(PATH_TO_PROPERTY_FILE + "testing.properties");
		ConfigurationPropertyLoader configurationPropertyLoader = new ConfigurationPropertyLoader(conf);
		assertEquals(configurationPropertyLoader.getAllFromPropertyFile().size(), 4);
		assertEquals(configurationPropertyLoader.getShortcutDescription("alt+shift+c"), "change method parameters order");
		
		TaskInfo result = configurationPropertyLoader.getTaskInfo();
		assertEquals(result.getPathToImages(), "src/main/resources/eclipse/");
		assertEquals(result.getExtension(), ".jpg");
		assertEquals(result.getSuffix(), "_2");
	}
	
	@Test(expected = NullPointerException.class)
	public void readingPropertiesAreInvalid() {
		when(conf.getPropertyFileName()).thenReturn(PATH_TO_PROPERTY_FILE + "incomplete.properties");
		new ConfigurationPropertyLoader(conf).getTaskInfo();
	}

}
