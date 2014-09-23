import java.util.*;

public class Logic {
	public void userCommands(){
		String command = common.getCommand();
		if(command.equals("add")){
			String description = common.getDescription();
			Task newtask = new Task();
			newtask.setTaskName(description);
			common.task.add(newtask);
		}
		else if(command.equals("delete")){
			String description = common.getDescription();
			Task deleteTask = new Task();
			deleteTask.setTaskName(description);
			common.task.remove(deleteTask);
		}
		else if(command.equals("update")){
			String description = common.getDescription();
			String newDescription = common.getNewDescription();
			Task deleteTask = new Task();
			Task newTask = new Task();
			deleteTask.setTaskName(description);
			newTask.setTaskName(newDescription);
			common.task.remove(deleteTask);
			common.task.add(newTask);
		}
		else if(command.equals("done")){
			String description = common.getDescription();
			for(int i = 0; i < common.task.size(); i++){
				String compare = common.task.getTaskName();
				if(description.equals(compare);
				common.task.setStatus(true);
				}
			}
		}
	}
}
