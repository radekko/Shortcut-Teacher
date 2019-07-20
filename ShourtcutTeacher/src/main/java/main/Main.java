package main;
import java.awt.EventQueue;

import task.TasksFactory;
import view.ScreenDialog;

public class Main {

	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	TasksFactory taskFactory;
	            	
	            	// 4 possible application configs:
	            	taskFactory = new TasksFactory(ApplicationConfig.ECLIPSE_READ_FROM_PROPERTIES);
//	            	taskFactory = new TasksFactory(ApplicationConfig.ECLIPSE_READ_FROM_FILE_NAMES);
//	            	taskFactory = new TasksFactory(ApplicationConfig.FIREFOX_READ_FROM_FILE_NAMES);
//	            	taskFactory = new TasksFactory(ApplicationConfig.FIREFOX_READ_FROM_FILE_NAMES);
					
	            	new ScreenDialog(taskFactory.getTasks());
	            }
	        });
	    }
}