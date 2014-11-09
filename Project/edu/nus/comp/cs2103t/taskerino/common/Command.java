//@Nicholas A0110574N
package edu.nus.comp.cs2103t.taskerino.common;

import java.util.ArrayList;

/**
 * 
 * This class serves to help with the Undo functionality by keeping track of the commands entered.
 */
public class Command {
	
	private String commandType;
	private int indexOfTaskModified;
	private DateAndTime dueDate;
	private DateAndTime startDate;
	private Task taskModified;
	private String nameOfTaskModified;
	private boolean statusOfTask;
	private String typeOfTaskModified;
	private ArrayList<Task> oldTaskList;
	
	public Command() {
	}
	
	public String getCommand() {
		return commandType;
	}
	
	public Task getTask() {
		return taskModified;
	}
	
	public int getIndexOfTaskModified() {
		return indexOfTaskModified;
	}
	
	public DateAndTime getDueDate() {
		return dueDate;
	}
	
	public DateAndTime getStartDate() {
		return startDate;
	}
	
	public boolean getStatusOfTask() {
		return statusOfTask;
	}
	
	public String getNameOfTaskModified() {
		return nameOfTaskModified;
	}
	
	public ArrayList<Task> getOldTaskList() {
		return oldTaskList;
	}
	public void setCommand(String command) {
		commandType = command;
	}
	
	public void setIndexOfTaskModified(int index) {
		indexOfTaskModified = index;
	}
	
	public void setTaskModified(Task task) {
		taskModified = task;
	}
	
	public void setDueDate(DateAndTime due) {
		dueDate = due;
	}
	
	public void setStartDate(DateAndTime start) {
		startDate = start;
	}
	
	public void setStatusOfTask(boolean status) {
		statusOfTask = status;
	}
	
	public void setNameOfTaskModified(String name) {
		nameOfTaskModified = name;
	}
	
	public void setOldTaskList(ArrayList<Task> taskList) {
		oldTaskList = taskList;
	}

	public String getTypeOfTaskModified() {
		return typeOfTaskModified;
	}

	public void setTypeOfTaskModified(String typeOfTaskModified) {
		this.typeOfTaskModified = typeOfTaskModified;
	}
}
