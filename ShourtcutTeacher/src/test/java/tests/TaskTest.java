package tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import shortcut.Shortcut;
import task.Task;
import task.TaskInfo;

@RunWith(MockitoJUnitRunner.class)
public class TaskTest {
	
	private final String keysAsString = "ctrl+alt+delete";
	private final String pathToImages = "folder/folder2";
	private final String extension = ".mp4";
	private final String suffix = "_2";

	@Mock
	private Shortcut shortcut;
	
	@Mock
	private TaskInfo taskInfo;
	
	private Task task;
	
	@Before
	public void setUp() {
		when(shortcut.getKeysAsString()).thenReturn(keysAsString);
		when(taskInfo.getPathToImages()).thenReturn(pathToImages);
		when(taskInfo.getExtension()).thenReturn(extension);
		when(taskInfo.getSuffix()).thenReturn(suffix);
		
		task = new Task(shortcut,taskInfo);
	}
	
	@Test
	public void returnValidTask() throws Exception {
		assertEquals(pathToImages + keysAsString + extension, task.getPathBefore());
		assertEquals(pathToImages + keysAsString + suffix + extension, task.getPathAfter());
		assertEquals(shortcut, task.getShortcut());
	}
}
