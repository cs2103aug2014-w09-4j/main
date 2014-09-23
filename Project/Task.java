
public class Task(){
	private String taskName;
	private String date;
	private boolean status;
	
	public String getTaskName(){
		return this.taskName;
	}

	public String setTaskName(String taskName){
		this.taskName = taskName;
	}
	
	public boolean getStatus(){
		return this.status;
	}

	public boolean setStatus(boolean status){
		this.status = status;
	}
}