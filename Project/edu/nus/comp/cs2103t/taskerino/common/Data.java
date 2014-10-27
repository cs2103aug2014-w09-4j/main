/** 
 *---------------------------------------------------------------------------------- DATA CLASS ----------------------------------------------------------------------------------
 *
 * @author Nicholas Low Jun Han
 * 
 * This class handles any manipulation to the temporary list of task object.
 * 
 * Functions included within this class:
 * 
 * -addTask <addition of tasks to the list of tasks>
 * -removeTask <removal of specific task from list of tasks>
 * -getTask <retrieve specific task from list of tasks>
 * -searchTask <retrives the index of the the specific task from the list of tasks>
 * -updateTask <retrieves specific task and updates relevant fields>
 *
 *
**/
package edu.nus.comp.cs2103t.taskerino.common;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Data {

/*****************
 Static Variables 
******************/
	private static String command;
	
	private static String input;
	
	private static String description;
	
	private static String newDescription;
	
	public static ArrayList<Task> task;
	
	public static ArrayList<Task> addType;
	
/*****************
     Getters 
******************/
	
	public static String getCommand(){
		return command;
	}
	
	public static String getInput(){
		return input;
	}
	
	public static String getDescription(){
		return description;
	}
	
	public static String getNewDescription(){
		return newDescription;
	}
	
	public static String getAddType(){
		return addType;
	}
	
/*****************
      Setters 
******************/	
	
	public static void setCommand(String newCommand){
		command = newCommand;
	}
	
	public static void setInput(String newInput){
		input = newInput;
	}
	
	public static void setDescription(String newDescription){
		description = newDescription;
	}
	
	public static void setNewDescription(String newNewDescription){
		newDescription = newNewDescription;
	}
	
	public static void setNewDescription(String newAddType){
		addType = newAddType;
	}

/*****************
     Functions 
******************/	
	
	/**
	 * Adds task into the temporary arraylist
	 */
	public static void addTask(Task newTask) throws FileNotFoundException, UnsupportedEncodingException {
		Data.task.add(newTask);
	}
	
	/**
	 * Searches for task and removes it
	*/
	public static void removeTask(String description) throws ArrayIndexOutOfBoundsException, FileNotFoundException, UnsupportedEncodingException {
		int indexOfTask = searchTask(description);
		Data.task.remove(indexOfTask);
	}
	
	/**
	 * Searches for task and updates relevant fields
	*/
	public static void updateTask(String description, String newDescription) throws FileNotFoundException, UnsupportedEncodingException {
		int indexOfTask = searchTask(description);
		Task taskToBeUpdated = Data.task.get(indexOfTask);
		taskToBeUpdated.setTaskName(newDescription);
	}

	/**
	 * Searches for task and if task exists, return the index of task. If not, return -1
	*/
	private static int searchTask(String description) {
		int indexOfTask = -1;
		for(int i = 0; i < Data.task.size(); i++) {
			if(Data.task.get(i).getTaskName().equals(description)) {
				indexOfTask = i;
			}
		}
		return indexOfTask;
	}
	
	/**
	 * Searches for task and returns the specific task
	*/	
	public static Task getTask(String description) {
		int indexOfTask = searchTask(description);
		if(indexOfTask != -1) {
			return Data.task.get(indexOfTask);
		}
		return null;
	}	

}