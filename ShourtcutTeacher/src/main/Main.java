package main;
import java.awt.EventQueue;

import view.ScreenDialog;

public class Main {

	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	new ScreenDialog(new TaskCreator(new ShortcutsInfoCreator())); 
	            }
	        });
	    }
}