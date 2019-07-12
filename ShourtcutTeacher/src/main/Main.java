package main;
import java.awt.EventQueue;

import task.ITasks;
import task.TaskFactory;
import view.ScreenDialog;

public class Main {

	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	ITasks tasks;
	            	
	            	// 4 possible application configs:
					tasks = TaskFactory.getTasks(ApplicationConfig.ECLIPSE_READ_FROM_PROPERTIES);
//					tasks = TaskFactory.getTasks(ApplicationConfig.ECLIPSE_READ_FROM_FILE_NAMES);
					
//					tasks = TaskFactory.getTasks(ApplicationConfig.FIREFOX_READ_FROM_FILE_NAMES);
//					tasks = TaskFactory.getTasks(ApplicationConfig.FIREFOX_READ_FROM_FILE_NAMES);
					
	            	new ScreenDialog(tasks);
	            }
	        });
	    }
}