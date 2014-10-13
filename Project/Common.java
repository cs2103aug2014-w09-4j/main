package Project;

import java.util.ArrayList;

public class Common {

	private static String command;
	
	private static String input;
	
	private static String description;
	
	private static String newDescription;
	
	public static ArrayList<Task> task;
	
	public static String getCommand(){
		return command;
	}
	
	public static void setCommand(String newCommand){
		command = newCommand;
	}

	public static String getInput(){
		return input;
	}
	
	public static void setInput(String newInput){
		input = newInput;
	}
	
	public static String getDescription(){
		return description;
	}
	
	public static void setDescription(String newDescription){
		description = newDescription;
	}
	
	public static String getNewDescription(){
		return newDescription;
	}
	
	public static void setNewDescription(String newNewDescription){
		newDescription = newNewDescription;
	}

}