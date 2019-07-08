package main;
import java.awt.EventQueue;

import shortcut.ShortcutReaderFromFilesName;
import shortcut.ShortcutsFactory;
import task.TasksFactory;
import view.ScreenDialog;

public class Main {

	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	new ScreenDialog(new TasksFactory(new ShortcutsFactory(new ShortcutReaderFromFilesName()))); 
//	            	new ScreenDialog(new TasksFactory(new ShortcutsFactory(new ShortcutReaderFromProperties()))); 
	            }
	        });
	    }
}