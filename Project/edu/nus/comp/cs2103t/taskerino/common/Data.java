//@author A0110574N

/** 
 *---------------------------------------------------------------------------------- DATA CLASS ----------------------------------------------------------------------------------
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
 * -clearTask <removes all tasks within the existing task arraylist
 *
**/
package edu.nus.comp.cs2103t.taskerino.common;

import java.util.ArrayList;

public class Data {
	private static Controller controller = Controller.getController();
	
/*****************
 Static Variables 
******************/
	private static String command;	
	private static String input;	
	
	private static String description;	
	private static String newDescription;
	private static String searchedKeyWord;
	
	public static String addType;	
	public static String changeType;	
	public static String deleteType;	
	
	public static int fromDay;		
	public static int fromMonth;	
	public static int fromYear;	
	
	public static int toDay;		
	public static int toMonth;	
	public static int toYear;	
	
	public static int byDay;		
	public static int byMonth;	
	public static int byYear;
	
	public static ArrayList<Command> commandList = new ArrayList<Command>();
	public static ArrayList<Task> task;
	public static ArrayList<Task> searchedTasks = new ArrayList<Task>();
	public static ArrayList<Task> completedTasks = new ArrayList<Task>();
	public static ArrayList<Task> uncompletedTasks = new ArrayList<Task>();
	
	public static int taskIndexInList;
		
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
	
	public static String getSearchedKeyWord(){
		return searchedKeyWord;
	}
	
	public static String getAddType(){
		return addType;
	}
	
	public static String getChangeType(){
		return changeType;
	}
	
	public static String getDeleteType(){
		return deleteType;
	}
	
	public static int getFromDay(){
		return fromDay;
	}
	
	public static int getFromMonth(){
		return fromMonth;
	}
	
	public static int getFromYear(){
		return fromYear;
	}
	
	public static int getToDay(){
		return toDay;
	}
	
	public static int getToMonth(){
		return toMonth;
	}
	
	public static int getToYear(){
		return toYear;
	}
	
	public static int getByDay(){
		return byDay;
	}
	
	public static int getByMonth(){
		return byMonth;
	}
	
	public static int getByYear(){
		return byYear;
	}
	
	public static int getTaskIndexInList(){
		return taskIndexInList;
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
	
	public static void setSearchedKeyWord(String newSearchedKeyWord){
		searchedKeyWord = newSearchedKeyWord;
	}
	
	public static void setAddType(String newAddType){
		addType = newAddType;
	}
	
	public static void setChangeType(String newChangeType){
		changeType = newChangeType;
	}
	
	public static void setDeleteType(String newDeleteType){
		deleteType = newDeleteType;
	}

	public static void setFromDay(int newFromDay){
		fromDay = newFromDay;
	}

	public static void setFromMonth(int newFromMonth){
		fromMonth = newFromMonth;
	}

	public static void setFromYear(int newFromYear){
		fromYear = newFromYear;
	}

	public static void setToDay(int newToDay){
		toDay = newToDay;
	}

	public static void setToMonth(int newToMonth){
		toMonth = newToMonth;
	}

	public static void setToYear(int newToYear){
		toYear = newToYear;
	}

	public static void setByDay(int newByDay){
		byDay = newByDay;
	}

	public static void setByMonth(int newByMonth){
		byMonth = newByMonth;
	}

	public static void setByYear(int newByYear){
		byYear = newByYear;
	}
	
	public static void setTaskIndexInList(int newTaskIndexInList){
		taskIndexInList = newTaskIndexInList;
	}
	
/*****************
     Functions 
******************/	
	
	/**
	 * Clear all attributes.
	 */
	public static void resetAll() {
		command = "";	
		input = "";	
		description = "";	
		newDescription = "";
		
		taskIndexInList = -1;
		
		addType = "";	
		changeType = "";	
		
		fromDay = -1;		
		fromMonth = -1;	
		fromYear = -1;	
		
		toDay = -1;		
		toMonth = -1;	
		toYear = -1;	
		
		byDay = -1;		
		byMonth = -1;	
		byYear = -1;
	}

	/**
	 * Adds task into the temporary arraylist
	 */
	public static void addTask(Task newTask) {
		Data.task.add(newTask);
	}
	
	/**
	 * Searches for task and removes it
	*/
	public static boolean removeTask(Task task) throws ArrayIndexOutOfBoundsException {
		return Data.task.remove(task);
	}
	
	/**
	 * Clear all task from existing list
	*/
	public static void clearTask() {
		Data.task.clear();
	}
	
	/**
	 * Updates task's relevant fields
	*/
	public static void updateTask(Task task, String newDescription) {
		task.setTaskName(newDescription);
	}
	
	/**
	 * Searches for task and if task exists, return the index of task. If not, return -1
	*/
	private static int searchTask(String description) {
		int indexOfTask = -1;
		ArrayList<Task> targetList;
		try {
			targetList = controller.getUserTasks();
		} catch (NullPointerException e) {
			// only occur when doing unit testing as GUIComponents is not initialized
			targetList = Data.task;
		}
		
		for(int i = 0; i < targetList.size(); i++) {
			if(targetList.get(i).getTaskName().equals(description)) {
				indexOfTask = i;
			}
		}
		return indexOfTask;
	}
	
	/**
	 * Searches for task based on Description and returns the specific task
	*/	
	public static Task getTask(String description) {
		int indexOfTask = searchTask(description);
		if(indexOfTask != -1) {
			taskIndexInList = indexOfTask;
			
			ArrayList<Task> targetList;
			try {
				targetList = controller.getUserTasks();
			} catch (NullPointerException e) {
				// only occur when doing unit testing as GUIComponents is not initialized
				targetList = Data.task;
			}
			
			return targetList.get(indexOfTask);
		}
		return null;
	}	

}