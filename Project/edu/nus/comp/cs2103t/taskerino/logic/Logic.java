package edu.nus.comp.cs2103t.taskerino.logic;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import edu.nus.comp.cs2103t.taskerino.common.Data;
import edu.nus.comp.cs2103t.taskerino.common.Task;
import edu.nus.comp.cs2103t.taskerino.common.DateAndTime;


/**
 * Logic class will retrieve user input from Common class, 
 * perform various actions based on different commands,
 * and return a String feedback to GUI class.
 */
public class Logic {
	private boolean isHelpValid;
	
	/**
	 * ChangeTask function replaces the exiting task details
	 * with new task details
	 * and return a String feedback to GUI class.
	 */
	public String changeTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Data.getDescription();
		String newDescription = Data.getNewDescription();
		String type = Data.getChangeType();
		
		if(type.equals("taskName")) {
			try {
				Data.updateTask(description, newDescription);
				return "Update task successfully from " + description + " to " + newDescription;
			} catch (ArrayIndexOutOfBoundsException e) {
				return "Task with name: " + description + " not found!";
			}
		} else if(type.equals("startTime")) {
			try {
				int day = Data.getFromDay();
				int month = Data.getFromMonth();
				int year = Data.getFromYear();
				DateAndTime newStartDate = new DateAndTime(year,month,day);
				Data.getTask(description).setStartDate(newStartDate);
				return "Updated " + description + " start time successfully to " + day + "/" + month + "/" + year;
			} catch (ArrayIndexOutOfBoundsException e) {
				return "Task with name: " + description + " not found!";
			}
		} else if(type.equals("endTime")) {
			try {
				int day = Data.getToDay();
				int month = Data.getToMonth();
				int year = Data.getToYear();
				DateAndTime newEndDate = new DateAndTime(year,month,day);
				Data.getTask(description).setDueDate(newEndDate);
				return "Updated " + description + " end time successfully to " + day + "/" + month + "/" + year;
			} catch (ArrayIndexOutOfBoundsException e) {
				return "Task with name: " + description + " not found!";
			}
		} else {
			return "User command not recognized, please try again!";
		}
	}
	
	/**
     * deleteTask removes the task from the existing tasks list 
     * and return a String feedback to GUI class.
     */
	public String deleteTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Data.getDescription();
		try {
			Data.removeTask(description);
			return "Delete task " + description + " successfully";
		} catch (ArrayIndexOutOfBoundsException e) {
			return "Task with name: " + description + " not found!";
		}
	}
	
	/**
     * addTask adds the task into the existing tasks list 
     * and return a String feedback to GUI class.
     */
	public String addTask() throws FileNotFoundException, UnsupportedEncodingException {
		String type = Data.getAddType();
		Task newTask = new Task();
		
		if (type.equals("floating")) {
			String description = Data.getDescription();
			newTask.setTaskName(description);
			Data.addTask(newTask);
		}
		else if (type.equals("deadline")) {
			String description = Data.getDescription();
			int byDay = Data.getByDay();
			int byMonth = Data.getByMonth();
			int byYear = Data.getByYear();
			DateAndTime byDateAndTime = new DateAndTime(byYear,byMonth,byDay);
			newTask.setTaskName(description);
			newTask.setDueDate(byDateAndTime);
			newTask.setTaskType("deadline");
			Data.addTask(newTask);	 
		}
		else if (type.equals("timed")) {
			String description = Data.getDescription();
			newTask.setTaskName(description);
			int fromDay = Data.getFromDay();
			int fromMonth = Data.getFromMonth();
			int fromYear = Data.getFromYear();
			DateAndTime fromDateAndTime = new DateAndTime(fromYear,fromMonth,fromDay);
			newTask.setStartDate(fromDateAndTime);
			int toDay = Data.getToDay();
			int toMonth = Data.getToMonth();
			int toYear = Data.getToYear();
			DateAndTime toDateAndTime = new DateAndTime(toYear,toMonth,toDay);
			newTask.setDueDate(toDateAndTime);
			newTask.setTaskType("timed");
			Data.addTask(newTask);
		}
		// dummy
		return "Add task " + newTask.getTaskName() + " successfully";
	}
	
	/**
     * completeTask marks the task from the existing tasks list 
     * and return a String feedback to GUI class.
     */
	public String completeTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Data.getDescription();
		Task toBeCompleted = Data.getTask(description);
		if(toBeCompleted != null) {
			toBeCompleted.setStatus(true);
			return "Complete task " + description;
		} else {
			return "Task with name: " + description + " not found!";
		}
	}
	
	/**
     * searchTask search the existing tasks list 
     * obtain the tasks from the existing tasks list
	 * and return the details of the tasks to GUI class
     */
	public String searchTask() {
		Data.searchedTasks.clear();
		String description = Data.getDescription().toLowerCase();
		int numberOfTasks = 0;
		for(int i = 0; i < Data.task.size(); i++){
			String details = Data.task.get(i).getTaskName().toLowerCase();
			String[] parts = details.split(" ");
			for(int j = 0; j < parts.length; j++){
				if(parts[j].equals(description)){
					Data.searchedTasks.add(Data.task.get(i));
					numberOfTasks++;
					break;
				}
			}
		}
		if(numberOfTasks == 0){
			return "Task with name: " + description + " not found!";
		} else {
			return numberOfTasks + " tasks found with given description. ";
		}
	}
	
	/**
     * clearTask clears the existing tasks list 
	 * and return an empty list to GUI class
     */
	 public String clearTask() throws FileNotFoundException, UnsupportedEncodingException {
		Data.clearTask();
		return "Cleared all tasks";
	}
	
	/**
     * undoTask undo the previous command input 
	 * and return the previous list to GUI class
     */
	public String undoTask() throws FileNotFoundException, UnsupportedEncodingException {
		int indexOfUndoTask = Data.undoTasks.size() - 2;
		if(indexOfUndoTask < 0) {
			return "invalid command";
		} else {
			Data.undoTask(indexOfUndoTask);
			return "Undo task completed";
		}
	}
	
	/**
	 * Execute help command.
	 */
	public String help() {
		String feedback = " ";
		if (Data.getDescription() != null) {
			String cmd = Data.getDescription();
			if (cmd.equals("add") || cmd.equals("delete") || cmd.equals("change") || cmd.equals("search") 
					|| cmd.equals("complete") || cmd.equals("goto")) {
				isHelpValid = true;
			} else {
				isHelpValid = false;
				feedback = "User command not recognized, please try again!";
			}
		}
		return feedback;
	}
	
	/**
	 * Return if the help command user input is valid.
	 */
	public boolean isHelpValid() {
		return isHelpValid;
	}
}
	