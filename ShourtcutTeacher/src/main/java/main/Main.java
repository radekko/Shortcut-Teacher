package main;
import java.awt.EventQueue;

import task.TasksFacade;
import view.ScreenDialog;

public class Main {

	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	TasksFacade taskFacade;
	            	
	            	// 4 possible application configs:
	            	taskFacade = new TasksFacade(ApplicationConfig.ECLIPSE_READ_FROM_PROPERTIES);
//	            	taskFacade = new TaskFacade(ApplicationConfig.ECLIPSE_READ_FROM_FILE_NAMES);
//	            	taskFacade = new TaskFacade(ApplicationConfig.FIREFOX_READ_FROM_FILE_NAMES);
//	            	taskFacade = new TaskFacade(ApplicationConfig.FIREFOX_READ_FROM_FILE_NAMES);
					
	            	new ScreenDialog(taskFacade.getTasks());
	            }
	        });
	    }
}