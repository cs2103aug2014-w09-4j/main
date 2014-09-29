package Project;

import java.util.*;

public class Logic {
	
	public String userCommands(){
		String command = Common.getCommand();
		
		if(command.equals("add")){
			return addTask();
		}
		else if(command.equals("delete")){
			return deleteTask();
		}
		else if(command.equals("update")){
			return updateTask();
		}
		/*else if(command.equals("done")){
			String description = Common.getDescription();
			for(int i = 0; i < Common.task.size(); i++){
				String compare = Common.task.getTaskName();
				if(description.equals(compare)) {
					Common.task.setStatus(true);
				}
			}
		}*/
		else {
			return "invalid msg";
		}
	}

	private String updateTask() {
		String description = Common.getDescription();
		String newDescription = Common.getNewDescription();
		Task deleteTask = new Task();
		Task newTask = new Task();
		deleteTask.setTaskName(description);
		newTask.setTaskName(newDescription);
		Common.task.remove(deleteTask);
		Common.task.add(newTask);
		
		// dummy
		return "update task successfully from " + deleteTask.getTaskName() + " to " + newTask.getTaskName();
	}

	private String deleteTask() {
		String description = Common.getDescription();
		Task deleteTask = new Task();
		deleteTask.setTaskName(description);
		Common.task.remove(deleteTask);
		
		// dummy
		return "delete task " + deleteTask.getTaskName() + " successfully";
	}

	private String addTask() {
		String description = Common.getDescription();
		Task newTask = new Task();
		newTask.setTaskName(description);
		Common.task.add(newTask);
		
		// dummy
		return "add task " + newTask.getTaskName() + " successfully";
	}
}
