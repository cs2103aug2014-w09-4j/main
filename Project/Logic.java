import java.util.*;

public class Logic {
	public void userCommands(){
		String command = common.getCommand();
		if(command.equals("add")){
			String description = common.getDescription();
			Task newtask = new Task();
			newtask.setTaskName(description);
			common.task.add(newtask);
			Writer.store();
		}
		else if(command.equals("delete")){
			String description = common.getDescription();
			Task deleteTask = new Task();
			deleteTask.setTaskName(description);
			common.task.remove(deleteTask);
			Writer.store();
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
			Writer.store();
		}
	}
}
