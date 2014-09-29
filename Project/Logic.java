package Project;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Logic {
	
	public String userCommands() throws FileNotFoundException, UnsupportedEncodingException{
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

	private String updateTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Common.getDescription();
		String newDescription = Common.getNewDescription();
		Task deleteTask = new Task();
		Task newTask = new Task();
		deleteTask.setTaskName(description);
		newTask.setTaskName(newDescription);
		Storage.removeTask(deleteTask);
		Storage.addTask(newTask);
		
		// dummy
		return "update task successfully from " + deleteTask.getTaskName() + " to " + newTask.getTaskName();
	}

	private String deleteTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Common.getDescription();
		Task deleteTask = new Task();
		deleteTask.setTaskName(description);
		Storage.removeTask(deleteTask);
		
		// dummy
		return "delete task " + deleteTask.getTaskName() + " successfully";
	}

	private String addTask() throws FileNotFoundException, UnsupportedEncodingException {
		String description = Common.getDescription();
		Task newTask = new Task();
		newTask.setTaskName(description);
		Storage.addTask(newTask);
		
		// dummy
		return "add task " + newTask.getTaskName() + " successfully";
	}
}
