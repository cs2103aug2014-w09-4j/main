package edu.nus.comp.cs2103t.taskerino.logic;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import edu.nus.comp.cs2103t.taskerino.common.*;
import edu.nus.comp.cs2103t.taskerino.storage.Storage;


/**
 * Logic class will retrieve user input from Common class, 
 * perform various actions based on different commands,
 * and return a String feedback to GUI class.
 */
public class Logic {
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_CHANGE = "change";
	private static final String COMMAND_COMPLETE = "complete";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_EXIT = "exit";
	
	
	public String executeUserCommand() throws FileNotFoundException, UnsupportedEncodingException{
		String command = (Data.getCommand()).toLowerCase();
		
		switch (command) {
			case COMMAND_ADD:
				return addTask();
			case COMMAND_DELETE:
				return deleteTask();
			case COMMAND_CHANGE:
				return changeTask();
			case COMMAND_COMPLETE:
				
			case COMMAND_SEARCH:
				
			case COMMAND_EXIT:
				System.exit(0);
				
			default:
				return "invalid msg";
		}
			
	}
	
	// changeTask replaces the existing task details with new task details 
	private String changeTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Data.getDescription();
		String newDescription = Data.getNewDescription();
		
		try {
			Storage.updateTask(description, newDescription);
			return "update task successfully from " + description + " to " + newDescription;
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Task with name: " + description + " not found!";
		}
	}
	
	
	// deleteTask removes the task from the existing tasks list
	private String deleteTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Data.getDescription();
		try {
			Storage.removeTask(description);
			return "delete task " + description + " successfully";
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Task with name: " + description + " not found!";
		}
	}
	
	
	// addTask adds the task into the existing tasks list 
	private String addTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Data.getDescription();
		Task newTask = new Task();
		
		newTask.setTaskName(description);
		Storage.addTask(newTask);
		
		// dummy
		return "add task " + newTask.getTaskName() + " successfully";
	}
}
