package edu.nus.comp.cs2103t.taskerino.common;

public class Command {
	
	private static String commandType;
	private static int indexOfTaskModified;
	private static DateAndTime dueDate;
	private static DateAndTime startDate;
	private static Task taskModified;
	private static String nameOfTaskModified;
	private static boolean statusOfTask;
	
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
}
