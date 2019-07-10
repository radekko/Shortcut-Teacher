package main;
import java.awt.EventQueue;
import java.util.List;
import java.util.function.Function;

import shortcut.ReadShortcut;
import shortcut.ShortcutReaderFromFilesName;
import shortcut.ShortcutReaderFromProperties;
import shortcut.ShortcutsFactory;
import task.TasksFactory;
import utils.PropertyLoader;
import view.ScreenDialog;

public class Main {

	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	// Possible states of application
//	            	ApplicationMode mode = ApplicationMode.ECLIPSE;
	            	ApplicationMode mode = ApplicationMode.FIREFOX;
	            	
	            	// Possible strategies of reading shortcuts
	            	//
	            	// 1. Read .JPG names
	            	Function<PropertyLoader,List<ReadShortcut>> shortcutsProduceStrategy = new ShortcutReaderFromFilesName();
	            	//
	            	// 2. Read from property file
//	            	Function<ApplicationMode,List<ReadShortcut>> shortcutsProducer = new ShortcutReaderFromProperties();
	            	
					ShortcutsFactory shortcutsFactory = new ShortcutsFactory(shortcutsProduceStrategy);
	            	
					TasksFactory taskFactory = new TasksFactory(shortcutsFactory, mode);
					
	            	new ScreenDialog(taskFactory);
	            }
	        });
	    }
}