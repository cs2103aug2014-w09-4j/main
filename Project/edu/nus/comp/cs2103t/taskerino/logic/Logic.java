//@author A0108310Y
package edu.nus.comp.cs2103t.taskerino.logic;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import edu.nus.comp.cs2103t.taskerino.common.Command;
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
	private boolean isTagValid;
	
	/**
	 * ChangeTask function replaces the exiting task details
	 * with new task details
	 * and return a String feedback to GUI class.
	 */
	public String changeTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Data.getDescription();
		String newDescription = Data.getNewDescription();
		String type = Data.getChangeType();
		Command newCommand = new Command();
		newCommand.setCommand("change");
		try{
			newCommand.setDueDate(Data.getTask(description).getDueDate());
			newCommand.setStartDate(Data.getTask(description).getStartDate());
			newCommand.setNameOfTaskModified(Data.getTask(description).getTaskName());
			if(Data.getTask(description).getStatus().equals("completed")) {
				newCommand.setStatusOfTask(true);
			} else {
				newCommand.setStatusOfTask(false);
			}
		}
		catch (NullPointerException e) {
		}
		newCommand.setIndexOfTaskModified(Data.task.indexOf(Data.getTask(description)));
		Data.commandList.add(newCommand);
		
		//changing of task details
		if(type.equals("taskName")) {
			try {
				Data.updateTask(description, newDescription);
				return "Update task successfully from " + description + " to " + newDescription;
			} catch (ArrayIndexOutOfBoundsException e) {
				return "Task with name: " + description + " not found!";
			}
		//changing of start date
		} else if(type.equals("startTime")) {
			try {
				int day = Data.getFromDay();
				int month = Data.getFromMonth();
				int year = Data.getFromYear();
				DateAndTime newStartDate = new DateAndTime(year,month,day);
				if (Data.getTask(description).getDueDate() == null ||
						isValidDate(newStartDate, Data.getTask(description).getDueDate())) {
					Data.getTask(description).setStartDate(newStartDate);
					return "Updated " + description + " start time successfully to " + day + "/" + month + "/" + year;
				} else {
					return "Start date cannot be later than the due date!";
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return "Task with name: " + description + " not found!";
			}
		//changing of due date
		} else if(type.equals("endTime")) {
			try {
				int day = Data.getToDay();
				int month = Data.getToMonth();
				int year = Data.getToYear();
				DateAndTime newEndDate = new DateAndTime(year,month,day);
				
				if (Data.getTask(description).getStartDate() == null || 
						isValidDate(Data.getTask(description).getStartDate(), newEndDate)) {
					Data.getTask(description).setDueDate(newEndDate);
					return "Updated " + description + " end time successfully to " + day + "/" + month + "/" + year;
				} else {
					return "Due date cannot be earlier than the start date!";
				}
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
			Command newCommand = new Command();
			newCommand.setCommand("delete");
			newCommand.setTaskModified(Data.getTask(description));
			newCommand.setIndexOfTaskModified(Data.task.indexOf(Data.getTask(description)));
			Data.commandList.add(newCommand);
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
		boolean isAddSuccess = true;
		
		//adding of floating task without any start or due date
		if (type.equals("floating")) {
			String description = Data.getDescription();
			newTask.setTaskName(description);
			Data.addTask(newTask);
		}
		//adding of deadline tasks without a start but having a due date
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
		//adding of timed tasks with a start and due date
		else if (type.equals("timed")) {
			String description = Data.getDescription();
			newTask.setTaskName(description);
			int fromDay = Data.getFromDay();
			int fromMonth = Data.getFromMonth();
			int fromYear = Data.getFromYear();
			DateAndTime fromDateAndTime = new DateAndTime(fromYear,fromMonth,fromDay);
			int toDay = Data.getToDay();
			int toMonth = Data.getToMonth();
			int toYear = Data.getToYear();
			DateAndTime toDateAndTime = new DateAndTime(toYear,toMonth,toDay);

			if (isValidDate(fromDateAndTime, toDateAndTime)) {
				newTask.setStartDate(fromDateAndTime);
				newTask.setDueDate(toDateAndTime);
				newTask.setTaskType("timed");
				Data.addTask(newTask);
			} else {
				isAddSuccess = false;
			}
		}
		
		if (isAddSuccess) {
			Command newCommand = new Command();
			newCommand.setCommand("add");
			newCommand.setTaskModified(newTask);
			Data.commandList.add(newCommand);	
			return "Add task " + newTask.getTaskName() + " successfully";
		} else {
			return "Start date cannot be later than the due date!";
		}
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
			Command newCommand = new Command();
			newCommand.setCommand("complete");
			newCommand.setTaskModified(toBeCompleted);
			Data.commandList.add(newCommand);
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
		Command newCommand = new Command();
		newCommand.setCommand("search");
		Data.commandList.add(newCommand);
		if(numberOfTasks == 0){
			return "Task with name: " + description + " not found!";
		} else if (numberOfTasks == 1){
			return numberOfTasks + " task found with given description. ";
		} else {
			return numberOfTasks + " tasks found with given description. ";
		}
	}
	
	/**
     * clearTask clears the existing tasks list 
	 * and return an empty list to GUI class
     */
	public String clearTask() throws FileNotFoundException, UnsupportedEncodingException {
		Command newCommand = new Command();
		ArrayList<Task> oldTaskList = new ArrayList<Task>();
		for(int count = 0; count < Data.task.size(); count++) {
			oldTaskList.add(Data.task.get(count));
		}
		newCommand.setOldTaskList(oldTaskList);
		newCommand.setCommand("clear");
		Data.commandList.add(newCommand);
		Data.clearTask();
		return "Cleared all tasks";
	}
	
	/**
     * undoTask undo the previous command input 
	 * and return the previous list to GUI class
     */
	public String undoTask() throws FileNotFoundException, UnsupportedEncodingException {
		int indexOfCommandToUndo = Data.commandList.size() - 1;
		if(indexOfCommandToUndo < 0) {
			return "No previous step detected!";
		} else {
			Command commandToUndo = Data.commandList.get(indexOfCommandToUndo);
			String commandType = commandToUndo.getCommand();
			if(commandType.equals("add")) {
				Data.removeTask(commandToUndo.getTask().getTaskName());
				Data.commandList.remove(indexOfCommandToUndo);
				return "Undo successful!";
			}
			else if(commandType.equals("delete")) {
				Data.task.add(commandToUndo.getIndexOfTaskModified(), commandToUndo.getTask());
				Data.commandList.remove(indexOfCommandToUndo);
				return "Undo successful!";
			}
			else if(commandType.equals("change")) {
				Task oldTask = new Task();
				oldTask.setTaskName(commandToUndo.getNameOfTaskModified());
				oldTask.setDueDate(commandToUndo.getDueDate());
				oldTask.setStartDate(commandToUndo.getStartDate());
				oldTask.setStatus(commandToUndo.getStatusOfTask());
				Data.task.remove(commandToUndo.getIndexOfTaskModified());
				Data.task.add(commandToUndo.getIndexOfTaskModified(), oldTask);
				Data.commandList.remove(indexOfCommandToUndo);
				return "Undo successful!";
			}
			else if(commandType.equals("complete")) {
				Data.task.get(commandToUndo.getIndexOfTaskModified()).setStatus(false);
				Data.commandList.remove(indexOfCommandToUndo);
				return "Undo successful!";
			}
			else if(commandType.equals("clear")) {
				Data.task = commandToUndo.getOldTaskList();
				Data.commandList.remove(indexOfCommandToUndo);
				return "Undo successful!";
			}			
			else {
				Data.commandList.remove(indexOfCommandToUndo);
				return "Last action cannot be undone.";
			}
		}
	}
	

	//@author A0113742N
	/**
	 * Execute help command.
	 */
	public String help() {
		String feedback = " ";
		isHelpValid = true;
		if (Data.getDescription() != null) {
			String cmd = Data.getDescription().toLowerCase();
			if (cmd.equals("add") || cmd.equals("delete") || cmd.equals("change") || cmd.equals("search") 
					|| cmd.equals("complete") || cmd.equals("goto") || cmd.equals("")) {
				isHelpValid = true;
			} else {
				isHelpValid = false;
				feedback = "User command not recognized, please try again!";
			}
		}
		return feedback;
	}
	
	public boolean isHelpValid() {
		return isHelpValid;
	}

	/**
	 * Update tasks in the selected tag file.
	 */
	public String gotoTag() {
		String feedback = " ";
		if (Data.getDescription() != null) {
			String tag = Data.getDescription().toLowerCase();
			if (tag.equals("all") || tag.equals("search") || tag.equals("completed") || tag.equals("uncompleted")) {
				isTagValid = true;
			} else {
				isTagValid = false;
				feedback = "User command not recognized, please try again!";
			}
		}
		sortTasksByStatus();
		return feedback;
	}
	
	public boolean isTagValid() {
		return isTagValid;
	}
	
	/**
	 * Initialize and modify completedTasks and incompletedTasks ArrayLists.
	 */
	public void sortTasksByStatus() {
		Data.completedTasks.clear();
		Data.uncompletedTasks.clear();
		for (Task task: Data.task) {
			if (task.getStatus().equalsIgnoreCase("completed")) {
				Data.completedTasks.add(task);
			} else {
				Data.uncompletedTasks.add(task);
			}
		}
	}
	
	/**
	 * Check for validity of start date and due date.
	 */
	public boolean isValidDate(DateAndTime startDate, DateAndTime dueDate) {
		return (startDate.compareTo(dueDate) <= 0);
	}
}
	