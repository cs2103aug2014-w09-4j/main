//@author A0110594L
package edu.nus.comp.cs2103t.taskerino.common;

public class Task{
	public static int currentIndex;
	private int taskIndex;
	
	private String taskName;
	private DateAndTime startDate;
	private DateAndTime dueDate;
	private String taskType;
	private String tag;
	private boolean status;
	
	public Task() {
		currentIndex++;
		this.setTaskIndex(currentIndex);
	}
	
	public Task(boolean isTemp){
		// create temp Task which does not increase indexCount
		this.setTaskIndex(currentIndex);
	}
	
	public Task(String description) {
		currentIndex++;
		this.setTaskIndex(currentIndex);
		
		this.setTaskName(description);
		this.setTaskType("floating");
	}

	public Task(String description, DateAndTime startDate, DateAndTime dueDate) {
		currentIndex++;
		this.setTaskIndex(currentIndex);
		
		this.setTaskType("timed");
		this.setTaskName(description);
		this.setStartDate(startDate);
		this.setDueDate(dueDate);
	}
	
	public Task(String description, DateAndTime dueDate) {
		currentIndex++;
		this.setTaskIndex(currentIndex);
		
		this.setTaskType("deadline");
		this.setTaskName(description);
		this.setDueDate(dueDate);
	}
	
	public String getTaskName(){
		return this.taskName;
	}

	public void setTaskName(String taskName){
		this.taskName = taskName;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getTaskType() {
		return this.taskType;
	}
	
	public void setTaskType(String string) {
		this.taskType = string;
	}
	
	public boolean getStatus(){
		return this.status;
	}
	
	public String getStringStatus(){
		if(this.status == true) {
			return "completed";
		} else {
			return "uncompleted";
		}
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

	public int getTaskIndex() {
		return taskIndex;
	}

	public void setTaskIndex(int taskIndex) {
		this.taskIndex = taskIndex;
	}

}