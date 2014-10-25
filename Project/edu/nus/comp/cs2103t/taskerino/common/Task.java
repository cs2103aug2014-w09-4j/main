package edu.nus.comp.cs2103t.taskerino.common;

public class Task{
	private String taskName;
	private DateAndTime startDate;
	private DateAndTime dueDate;
	private boolean status;
	
	public Task() {}
	
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

	public DateAndTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateAndTime date) {
		this.startDate = date;
	}
	
	public DateAndTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateAndTime date) {
		this.dueDate = date;
	}
}