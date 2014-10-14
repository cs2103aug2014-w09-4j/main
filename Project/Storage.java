/** 
 *---------------------------------------------------------------------------------- STORAGE CLASS ----------------------------------------------------------------------------------
 * @author Nicholas Low Jun Han
 *
 * This class handles the storing and retrieving of existing tasks from the local file "Tasks.txt".
 *
 * It has the following operations:
 *
 * -addTask <addition of tasks to the list of tasks>
 * -removeTask <removal of specific task from list of tasks>
 * -getTask <retrieve specific task from list of tasks>
 * -searchTask <retrives the index of the the specific task from the list of tasks>
 * -updateTask <retrieves specific task and updates relevant fields>
 *
 * -loadTasksFromFile <retrieves list of tasks (JSON format) from local file and converts it into ArrayList<Task> for easy manipulation>
 * -saveTasksIntoFile <converts ArrayList<Task> to (JSON format) String and store it in local file "Tasks.txt">
 *
 *NOTE: changes made to task list will be saved during addTask and removeTask function calls as saveTasksIntoFile is called at end of both operations.
 *
**/
package Project;

import java.util.ArrayList;
import java.lang.reflect.Type;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


public class Storage {
	
	/**
	 * Adds task into the temporary arraylist
	 */
	public static void addTask(Task newTask) throws FileNotFoundException, UnsupportedEncodingException {
		Data.task.add(newTask);
		saveTasksIntoFile();
	}
	
	/**
	 * Searches for task and removes it
	*/
	public static void removeTask(String description) throws ArrayIndexOutOfBoundsException, FileNotFoundException, UnsupportedEncodingException {
		int indexOfTask = searchTask(description);
		Data.task.remove(indexOfTask);
		saveTasksIntoFile();
	}
	
	/**
	 * Searches for task and updates relevant fields
	*/
	public static void updateTask(String description, String newDescription) throws FileNotFoundException, UnsupportedEncodingException {
		int indexOfTask = searchTask(description);
		Task taskToBeUpdated = Data.task.get(indexOfTask);
		taskToBeUpdated.setTaskName(newDescription);
		saveTasksIntoFile();
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
	
	/**
	 * Use Gson library and BufferedReader to load content in local file into temporary arraylist of task objects
	*/
	public static ArrayList<Task> loadTasksFromFile() throws IOException, JsonSyntaxException {
		Gson gson = new Gson();
		String jsonTasks = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Tasks.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				jsonTasks = line;
			}
			Type type = new TypeToken<ArrayList<Task>>() {
        		}.getType();
        	ArrayList<Task> taskList = gson.fromJson(jsonTasks, type);
        	reader.close();
        	return taskList;
		} catch(IOException e) {
			return new ArrayList<Task>();
		}
	}
	
	/**
	 * Use Gson library and PrintWriter to save content in temporary arraylist into the local file in JSON format
	*/
	private static void saveTasksIntoFile() throws FileNotFoundException, UnsupportedEncodingException {
		Gson gson = new Gson();
		String jsonTasks = gson.toJson(Data.task);
		PrintWriter writer = new PrintWriter("Tasks.txt", "UTF-8");
		writer.println(jsonTasks);
		writer.close();
	}
}
