package Project;

public class Task{
	private String taskName;
	private String newTaskName;
	private String date;
	private boolean status;
	
	public String getTaskName(){
		return this.taskName;
	}

	public void setTaskName(String taskName){
		this.taskName = taskName;
	}
	
	public String newTaskName(){
		return this.newTaskName;
	}

	public void setNewTaskName(String newTaskName){
		this.newTaskName = newTaskName;
	}
	
	public boolean getStatus(){
		return this.status;
	}

	public void setStatus(boolean status){
		this.status = status;
	}
	
	public String displayTaskDetails() {
		return "Name of Task: " + taskName + "\nDate of Task: " + date + "\nStatus of Task: " + status;
	}
}