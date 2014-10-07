package Project;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;


/**
 * Logic class will retrieve user input from Common class, 
 * perform various actions based on different commands,
 * and return a String feedback to GUI class.
 */
public class Logic {
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_CHANGE = "change";
	private static final String COMMAND_COMPLETE = "complete";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_EXIT = "exit";
	
	
	public String executeUserCommand() throws FileNotFoundException, UnsupportedEncodingException{
		String command = (Common.getCommand()).toLowerCase();
		
		switch (command) {
			case COMMAND_ADD:
				return addTask();
			case COMMAND_DELETE:
				return deleteTask();
			case COMMAND_CHANGE:
				return changeTask();
				
			// The method "Display all tasks" is not in the user menu, 
			// but I created this method to test the GUI output format
			case COMMAND_DISPLAY:
				return displayTask();
				
			case COMMAND_COMPLETE:
				
			case COMMAND_SEARCH:
				
			case COMMAND_EXIT:
				System.exit(0);
				
			default:
				return "invalid msg";
		}
			
	}
	
	//displayTask shows the tasks list
	private String displayTask() {
		String returnStatement = "<html>";
		
		if (Common.task.isEmpty()) {
			returnStatement += "File is empty!"; 
		} else {
			returnStatement += ("1. " + Common.task.get(0).displayTaskDetails());
			for (int i=1; i<Common.task.size(); i++) {
				returnStatement += ("<br>" + (i+1) + ". " + Common.task.get(i).displayTaskDetails() + "</br>");
			}
		}
		returnStatement += "</html>";
		
		return returnStatement;
	}

	
	// changeTask replaces the existing task details with new task details 
	private String changeTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Common.getDescription();
		String newDescription = Common.getNewDescription();
		
		try {
			Storage.updateTask(description, newDescription);
			return "update task successfully from " + description + " to " + newDescription;
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Task with name: " + description + " not found!";
		}
	}
	
	
	// deleteTask removes the task from the existing tasks list
	private String deleteTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Common.getDescription();
		try {
			Storage.removeTask(description);
			return "delete task " + description + " successfully";
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Task with name: " + description + " not found!";
		}
	}
	
	
	// addTask adds the task into the existing tasks list 
	private String addTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Common.getDescription();
		Task newTask = new Task();
		
		newTask.setTaskName(description);
		Storage.addTask(newTask);
		
		// dummy
		return "add task " + newTask.getTaskName() + " successfully";
	}
}
