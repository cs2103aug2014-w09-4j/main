package edu.nus.comp.cs2103t.taskerino.common;

public class Task{
	private String taskName;
	private String date;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}