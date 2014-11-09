//@author A0113742N
package edu.nus.comp.cs2103t.taskerino.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gson.JsonSyntaxException;

import edu.nus.comp.cs2103t.taskerino.gui.GUIComponents;
import edu.nus.comp.cs2103t.taskerino.gui.GUIFrame;
import edu.nus.comp.cs2103t.taskerino.gui.HelpFrame;
import edu.nus.comp.cs2103t.taskerino.logic.Logic;
import edu.nus.comp.cs2103t.taskerino.parser.Parser;
import edu.nus.comp.cs2103t.taskerino.storage.TaskerinoIO;

/**
 * Main class that initialize and controls the program Taskerino
 * by providing methods that integrating classes from different
 * packages together.
 */
public class Controller {
	private static final String className = new Throwable() .getStackTrace()[0].getClassName();
	private static final String INVALID_COMMAND_FEEDBACK = "User command not recognized, please try again!";

	private String userCommand;
	private static final String COMMAND_HELP = "help";
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_CHANGE = "change";
	private static final String COMMAND_COMPLETE = "complete";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_EXIT = "exit";
	private static final String COMMAND_CLEAR = "clear";
	private static final String COMMAND_UNDO = "undo";
	private static final String COMMAND_GOTO = "goto";
	
	private static final String TAG_ALL = "all";
	private static final String TAG_SEARCH = "search";
	private static final String TAG_COMPLETED = "completed";
	private static final String TAG_UNCOMPLETED = "uncompleted";
	
	private static Controller singletonController;
	private CommandHistory commandHistory;
	private Logic logic;
	private Parser parser;
	private String outputFeedBack = "";
	
	// private constructor
	private Controller(){
		logic = new Logic();
		parser = new Parser();
		commandHistory = CommandHistory.getCommandHistory();
	}
	
	
	/**
	 * Returns Controller singleton instance 
	 * 
	 * @return Controller singleton
	 */
	public static Controller getController() {
		if (singletonController == null) {
			singletonController = new Controller();
		}
		return singletonController;
	}
	
	
	public static void main(String[] args) {
		final String methodName = "Main";
		LoggerFactory.logp(Level.INFO, className, methodName, "Start logger!");
		
		LoggerFactory.logp(Level.INFO, className, methodName, "Loading user Tasks...");
		loadData();
		
		LoggerFactory.logp(Level.INFO, className, methodName, "Initialize GUI!");
		new GUIFrame();
	}
	
	
	/**
	 * Load existing user data from local file.
	 */
	public static void loadData() {
		final String methodName = "Main";
		Data.searchedTasks = new ArrayList<Task>();
		Data.commandList = new ArrayList<Command>();
		
		try {
			Data.task = TaskerinoIO.loadTasksFromFile();
			Task.currentIndex = (Data.task.isEmpty() ? 0 : Data.task.get(Data.task.size() - 1).getTaskIndex());
		} catch (JsonSyntaxException | IOException e) {
			LoggerFactory.logp(Level.SEVERE, className, methodName, e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * Execute user command by passing the input userCommand String to
	 * Parser and Logic classes.
	 * 
	 * @param userCommand : user's input command
	 */
	public void executeUserCommand(String userCommand) {
		final String methodName = "executeUserCommand";
		boolean isParseSuccessful = false;
		
		// clear Data
		Data.resetAll();
		
		// store user command if it is not empty
		if (!userCommand.isEmpty()) {
			LoggerFactory.logp(Level.INFO, className, methodName, "Store user command into commandHistory.");
			commandHistory.storeCommand(userCommand);
		}
		
		// inputCommand is passed into parser and logic
		LoggerFactory.logp(Level.INFO, className, methodName, "Send user input commands to Data.");
		Data.setInput(userCommand);

		try {
			LoggerFactory.logp(Level.INFO, className, methodName, "Calling Parser.");
			parser.parse();
			isParseSuccessful = true;
		} catch (Exception e) {
			outputFeedBack = INVALID_COMMAND_FEEDBACK;
			LoggerFactory.logp(Level.SEVERE, className, methodName, e.getMessage());
			e.printStackTrace();
		}

		if (isParseSuccessful) {
			try {
				LoggerFactory.logp(Level.INFO, className, methodName, "Executing commands...");
				execute((Data.getCommand()).toLowerCase());
			} catch (Exception e) {
				outputFeedBack = INVALID_COMMAND_FEEDBACK;
				LoggerFactory.logp(Level.SEVERE, className, methodName, e.getMessage());
				e.printStackTrace();
			}
		}
		LoggerFactory.logp(Level.INFO, className, methodName, "Successfully get feedback from Logic: \n" + outputFeedBack);

		// reset CommandHistory pointer
		CommandHistory.getCommandHistory().setPointerToLatestTask();
	}
	
	
	/**
	 * Based on user's input command Parser has parsed, call different methods from different classes to execute the command.
	 * @param command
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	//@author A0108310Y
	private void execute(String command) throws FileNotFoundException, UnsupportedEncodingException {
		final String methodName = "execute";
		
		userCommand = command;
		switch (command.toLowerCase()) {
			case COMMAND_HELP:
				LoggerFactory.logp(Level.INFO, className, methodName, "Execute help command.");
				outputFeedBack = logic.help();
				if (logic.isHelpValid()) {
					new HelpFrame(Data.getDescription());
				}
				break;
		
			case COMMAND_ADD:
				LoggerFactory.logp(Level.INFO, className, methodName, "Execute add command.");
				outputFeedBack = logic.addTask();
				TaskerinoIO.saveTasksIntoFile();
				break;
				
			case COMMAND_DELETE:
				LoggerFactory.logp(Level.INFO, className, methodName, "Execute delete command.");
				outputFeedBack = logic.deleteTask();
				TaskerinoIO.saveTasksIntoFile();
				break;
				
			case COMMAND_CHANGE:
				LoggerFactory.logp(Level.INFO, className, methodName, "Execute change command.");
				outputFeedBack = logic.changeTask();
				TaskerinoIO.saveTasksIntoFile();
				break;
				
			case COMMAND_COMPLETE:
				LoggerFactory.logp(Level.INFO, className, methodName, "Execute complete command.");
				outputFeedBack = logic.completeTask();
				TaskerinoIO.saveTasksIntoFile();
				break;
				
			case COMMAND_SEARCH:
				LoggerFactory.logp(Level.INFO, className, methodName, "Execute search command.");
				outputFeedBack = logic.searchTask();
				GUIComponents.setSelectedItem(TAG_SEARCH);
				break;
				
			case COMMAND_CLEAR:
				LoggerFactory.logp(Level.INFO, className, methodName, "Execute clear command.");
				outputFeedBack = logic.clearTask();
				TaskerinoIO.saveTasksIntoFile();
				break;
				
			case COMMAND_UNDO:
				LoggerFactory.logp(Level.INFO, className, methodName, "Execute undo command.");
				outputFeedBack = logic.undoTask();
				TaskerinoIO.saveTasksIntoFile();
				break;
				
			case COMMAND_GOTO:
				LoggerFactory.logp(Level.INFO, className, methodName, "Execute goto command.");
				outputFeedBack = logic.gotoTag();
				if (logic.isTagValid()) {
					GUIComponents.setSelectedItem(Data.getDescription().toLowerCase());
				}
				break;
				
			case COMMAND_EXIT:
				LoggerFactory.logp(Level.INFO, className, methodName, "Execute exit command.");
				System.exit(0);
				
			default:
				outputFeedBack = INVALID_COMMAND_FEEDBACK;
		}
	}

	//@author A0113742N
	/**
	 * Get feedback to be displayed to user.
	 * 
	 * @return String outputFeedBack
	 */
	public String getUserFeedback() {
		return outputFeedBack;
	}
	
	
	/**
	 * Get an ArrayList of tasks that user is currently accessing to.
	 * 
	 * @return ArrayList of Tasks
	 */
	public ArrayList<Task> getUserTasks() {
		switch (GUIComponents.getSelectedTag().toLowerCase()) {
			case TAG_ALL:
				return Data.task;
			case TAG_SEARCH:
				logic.searchTask();
				return Data.searchedTasks;
			case TAG_COMPLETED:
				logic.sortTasksByStatus();
				return Data.completedTasks;
			case TAG_UNCOMPLETED:
				logic.sortTasksByStatus();
				return Data.uncompletedTasks;
			default:
				return Data.task;
		}
	}


	/**
	 * Reset GUI taskTable focus depending on user's command.
	 */
	public void resetGUIFocus() {
		switch (userCommand) {
			case COMMAND_ADD:
				// return to tag ALL and set focus to newly added row
				GUIComponents.setSelectedItem(TAG_ALL);
				GUIComponents.setTaskTableFocus(Data.task.size() - 1);
				break;
			
			case COMMAND_DELETE:
				setDeleteFocus();
				break;
				
			case COMMAND_CHANGE : case COMMAND_COMPLETE:
				GUIComponents.setTaskTableFocus(Data.getTaskIndexInList());
				break;
		}
	}


	/**
	 * Set focus for GUI TaskTable for delete command: <br>
	 * 1. Focus on next row of task if exists. <br>
	 * 2. Else focus on previous row of task if exists. <br> 
	 * 3. Otherwise don't set focus.
	 */
	private void setDeleteFocus() {
		int index = Data.getTaskIndexInList();
		int size = getUserTasks().size();
		if (index == size) {
			GUIComponents.setTaskTableFocus(index - 1);
		} else if (index < size && index >= 0) {
			GUIComponents.setTaskTableFocus(index);
		}
	}

}
