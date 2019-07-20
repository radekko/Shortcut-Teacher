package tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import shortcut.Shortcut;
import shortcut.Shortcuts;
import task.Task;
import task.TaskInfo;
import task.Tasks;

@RunWith(MockitoJUnitRunner.class)
public class TasksTest {
	
	@Mock
	private Shortcuts shortcuts;
	
	@Mock
	private TaskInfo taskInfo;
	
	private Tasks tasks;
	
	@Test
	public void everyIterationReturnTheSameElements() throws Exception {
		Shortcut mockedShortcut = Mockito.mock(Shortcut.class);
		List<Shortcut> list = Arrays.asList(mockedShortcut, mockedShortcut, mockedShortcut);
		when(shortcuts.getShortcuts()).thenReturn(list);

		tasks = new Tasks(shortcuts,taskInfo);
		
		Set<Task> firstIteration = prepareSetWith3NextTask();
		Set<Task> secondIteration = prepareSetWith3NextTask();
		Set<Task> thirdIteration = prepareSetWith3NextTask();
		
		firstIteration.addAll(secondIteration);
		firstIteration.addAll(thirdIteration);
		
		assertEquals(firstIteration.size(), list.size());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void emptyTasksListShouldReturnException() {
		when(shortcuts.getShortcuts()).thenReturn(new ArrayList<>());
		tasks = new Tasks(shortcuts,taskInfo);
		tasks.getNextTask();
	}
	
	private Set<Task> prepareSetWith3NextTask() {
		Set<Task> producedTasks = new HashSet<>();
		producedTasks.add(tasks.getNextTask());
		producedTasks.add(tasks.getNextTask());
		producedTasks.add(tasks.getNextTask());
		return producedTasks;
	}
}
