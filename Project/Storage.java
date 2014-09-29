/* 
---------------------------------------------------------------------------------- STORAGE CLASS ----------------------------------------------------------------------------------

This class handles the storing and retrieving of existing tasks from the local file "Tasks.txt".

It has the following operations:

-addTask <addition of tasks to the list of tasks>
-removeTask <removal of specific task from list of tasks>
-getTask <retrieve specific task from list of tasks>

-loadTasksFromFile <retrieves list of tasks (JSON format) from local file and converts it into ArrayList<Task> for easy manipulation>
-saveTasksIntoFile <converts ArrayList<Task> to (JSON format) String and store it in local file "Tasks.txt">

NOTE: changes made to task list will be saved during addTask and removeTask function calls as saveTasksIntoFile is called at end of both operations.
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
package Project;

import java.util.ArrayList;
import java.util.List;
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
	
	private File file;
	
	public Storage() throws JsonSyntaxException, IOException {
		this.file = new File("Tasks.txt");
		if(this.file.exists()) {
			Common.task = loadTasksFromFile(file);
		} else {
			file.createNewFile();
			Common.task = new ArrayList<Task>();
		}
	}
	
	public void addTask(Task newTask) throws FileNotFoundException, UnsupportedEncodingException {
		Common.task.add(newTask);
		saveTasksIntoFile(file);
	}
	
	public void removeTask(Task task) throws FileNotFoundException, UnsupportedEncodingException {
		Common.task.remove(task);
		saveTasksIntoFile(file);
	}
	
	public Task getTask(Task task) {
		int indexOfTask = Common.task.indexOf(task);
		return Common.task.get(indexOfTask);
	}
	
	private ArrayList<Task> loadTasksFromFile(File fileName) throws IOException, JsonSyntaxException {
		Gson gson = new Gson();
		String jsonTasks = "";
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = null;
		while ((line = reader.readLine()) != null) {
		    jsonTasks = line;
		}
		Type type = new TypeToken<ArrayList<Task>>() {
        	}.getType();
        ArrayList<Task> taskList = gson.fromJson(jsonTasks, type);
        reader.close();
        return taskList;
	}
	
	private void saveTasksIntoFile(File fileName) throws FileNotFoundException, UnsupportedEncodingException {
		Gson gson = new Gson();
		String jsonTasks = gson.toJson(Common.task);
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		writer.println(jsonTasks);
		writer.close();
	}
}
