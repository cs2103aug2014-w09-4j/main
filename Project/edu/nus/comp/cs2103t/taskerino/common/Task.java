package edu.nus.comp.cs2103t.taskerino.common;

/**
 * 
 * @author A0110574N
 *
 * This class contains the definition of the Object <Task> that will be used in this program.
 */

public class Task{
	private String taskName;
	private DateAndTime startDate;
	private DateAndTime dueDate;
	private String taskType;
	private String tag;
	private boolean status;
	
	public Task() {
	}
	
	public Task(String description) {
		this.setTaskName(description);
		this.setTaskType("floating");
	}

	public Task(String description, DateAndTime startDate, DateAndTime dueDate) {
		this.setTaskType("timed");
		this.setTaskName(description);
		this.setStartDate(startDate);
		this.setDueDate(dueDate);
	}
	
	public Task(String description, DateAndTime dueDate) {
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
	
	private String getTaskType() {
		return this.taskType;
	}
	
	public void setTaskType(String string) {
		this.taskType = string;
	}
	
	public String getStatus(){
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

}