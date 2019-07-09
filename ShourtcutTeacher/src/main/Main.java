package main;
import java.awt.EventQueue;

import shortcut.ShortcutReaderFromFilesName;
import shortcut.ShortcutReaderFromProperties;
import shortcut.ShortcutsFactory;
import task.TasksFactory;
import view.ScreenDialog;

public class Main {

	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	// Two strategies of reading shortcuts
	            	// 1. Read .JPG names and convert to keys 
	            	// 2. Read from property file
	            	
	            	// 1
	            	new ScreenDialog(new TasksFactory(new ShortcutsFactory(new ShortcutReaderFromFilesName())));
	            	// 2
//	            	new ScreenDialog(new TasksFactory(new ShortcutsFactory(new ShortcutReaderFromProperties()))); 
	            }
	        });
	    }
}