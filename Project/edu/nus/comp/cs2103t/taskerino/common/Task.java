package edu.nus.comp.cs2103t.taskerino.common;

public class Task{
	private String taskName;
	private String startDate;
	private String dueDate;
	private boolean status;
	
	public String getTaskName(){
		return this.taskName;
	}

	public void setTaskName(String taskName){
		this.taskName = taskName;
	}
	
	public boolean getStatus(){
		return this.status;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String date) {
		this.startDate = date;
	}
	
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String date) {
		this.dueDate = date;
	}
}