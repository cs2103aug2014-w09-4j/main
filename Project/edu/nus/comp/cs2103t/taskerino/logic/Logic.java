//@author A0108310Y
package edu.nus.comp.cs2103t.taskerino.logic;

import java.util.ArrayList;

import edu.nus.comp.cs2103t.taskerino.common.Command;
import edu.nus.comp.cs2103t.taskerino.common.Controller;
import edu.nus.comp.cs2103t.taskerino.common.Data;
import edu.nus.comp.cs2103t.taskerino.common.Task;
import edu.nus.comp.cs2103t.taskerino.common.DateAndTime;

/**
 * Logic class will retrieve user input from Common class, 
 * perform various actions based on different commands,
 * and return a String feedback to GUI class.
 */
public class Logic {
	private static Controller controller = Controller.getController();
	private boolean isHelpValid;
	private boolean isTagValid;

	/**
	 * ChangeTask function replaces the exiting task details
	 * with new task details
	 * and return a String feedback to GUI class.
	 */
	public String changeTask() {
		try {
			Task modifiedTask = getModifiedTask();
			if (modifiedTask == null) {
				return "Task not found!";
			}

			String type = Data.getChangeType();

			Command newCommand = new Command();
			newCommand.setCommand("change");
			newCommand.setDueDate(modifiedTask.getDueDate());
			newCommand.setStartDate(modifiedTask.getStartDate());
			newCommand.setNameOfTaskModified(modifiedTask.getTaskName());
			newCommand.setStatusOfTask(modifiedTask.getStatus());
			newCommand.setIndexOfTaskModified(Data.task.indexOf(modifiedTask));
			Data.commandList.add(newCommand);

			//changing of task details
			if(type.equals("taskName")) {
				Data.updateTask(modifiedTask, Data.getNewDescription());
				return "Update task successfully from " + modifiedTask.getTaskName() + " to " + Data.getNewDescription();
			} else if(type.equals("startTime")) {
				int day = Data.getFromDay();
				int month = Data.getFromMonth();
				int year = Data.getFromYear();
				DateAndTime newStartDate = new DateAndTime(year,month,day);

				if (modifiedTask.getDueDate() == null ||
						isValidDate(newStartDate, modifiedTask.getDueDate())) {
					modifiedTask.setStartDate(newStartDate);
					return "Updated start date successfully to " + day + "/" + month + "/" + year;
				} else {
					return "Start date cannot be later than the due date!";
				} 
			} else if(type.equals("endTime")) {
				int day = Data.getToDay();
				int month = Data.getToMonth();
				int year = Data.getToYear();
				DateAndTime newEndDate = new DateAndTime(year,month,day);

				if (modifiedTask.getStartDate() == null || 
						isValidDate(modifiedTask.getStartDate(), newEndDate)) {
					modifiedTask.setDueDate(newEndDate);
					return "Updated due date successfully to " + day + "/" + month + "/" + year;
				} else {
					return "Due date cannot be earlier than the start date!";
				}
			} else {
				return "User command not recognized, please try again!";
			}

		} catch (Exception e) {
			return "User command not recognized, please try again!";
		}
	}

	/**
	 * deleteTask removes the task from the existing tasks list 
	 * and return a String feedback to GUI class.
	 */
	public String deleteTask() {
		try {
			Task modifiedTask = getModifiedTask();
			if (modifiedTask == null) {
				System.out.println("null task");
				return "Task not found!";
			}

			Command newCommand = new Command();
			newCommand.setCommand("delete");
			newCommand.setTaskModified(modifiedTask);
			newCommand.setIndexOfTaskModified(Data.task.indexOf(modifiedTask));
			Data.commandList.add(newCommand);

			Data.removeTask(modifiedTask);
			return "Delete task " + modifiedTask.getTaskName() + " successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Task not found!";
		}
	}

	/**
	 * addTask adds the task into the existing tasks list 
	 * and return a String feedback to GUI class.
	 */
	public String addTask() {
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
			newCommand.setIndexOfTaskModified(Data.task.size() - 1);
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
	public String completeTask() {
		Task toBeCompleted = getModifiedTask();
		if (toBeCompleted == null) {
			return "Task not found!";
		}

		Command newCommand = new Command();
		newCommand.setCommand("complete");
		newCommand.setTaskModified(toBeCompleted);
		newCommand.setStatusOfTask(toBeCompleted.getStatus());
		newCommand.setIndexOfTaskModified(Data.task.indexOf(toBeCompleted));
		Data.commandList.add(newCommand);

		toBeCompleted.setStatus(true);
		return "Complete task " + toBeCompleted.getTaskName() + " successfully.";
	}

	/**
	 * searchTask search the existing tasks list 
	 * obtain the tasks from the existing tasks list
	 * and return the details of the tasks to GUI class
	 */
	public String searchTask() {
		Data.searchedTasks.clear();
		if (Data.getSearchedKeyWord() == null) {
			return "No search results found.";
		}
		String description = Data.getSearchedKeyWord().toLowerCase();
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
			return "No search results found.";
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
	public String clearTask() {
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
	public String undoTask() {
		int indexOfCommandToUndo = Data.commandList.size() - 1;
		if(indexOfCommandToUndo < 0) {
			return "No previous step detected!";
		} else {
			Command commandToUndo = Data.commandList.get(indexOfCommandToUndo);
			String commandType = commandToUndo.getCommand();
			if(commandType.equals("add")) {
				Data.removeTask(commandToUndo.getTask());
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
				if (!commandToUndo.getStatusOfTask()) {
					Data.task.get(commandToUndo.getIndexOfTaskModified()).setStatus(false);
				}
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
			if (task.getStatus()) {
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

	/**
	 * Search for and return task result based on user input provided.
	 * @return Task  
	 */
	private Task getModifiedTask() {
		Task result = getTask(Data.taskIndexInList);

		if (result != null) {
			// user use valid index to access to task
			return result;
		} else {
			// 1. user use description to access to task
			// 2. user use invalid index to access to task
			result = Data.getTask(Data.getDescription());
			return result;
		}
	}


	/**
	 * Searches for task based on Task Index and returns the specific task.
	 * @param int index of task in current ArrayList user in accessing to
	 * @return Task or null if not found
	 */	
	private Task getTask(int index) {
		if (index < 0 && index >= controller.getUserTasks().size()) {
			return null;
		} else {
			return controller.getUserTasks().get(index);
		}
	}	

}
