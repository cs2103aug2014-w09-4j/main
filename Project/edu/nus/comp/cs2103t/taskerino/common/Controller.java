/**
// code is far away from bug with Buddha protection
//
//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package edu.nus.comp.cs2103t.taskerino.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gson.JsonSyntaxException;

import edu.nus.comp.cs2103t.taskerino.gui.GUI;
import edu.nus.comp.cs2103t.taskerino.logic.Logic;
import edu.nus.comp.cs2103t.taskerino.parser.Parser;
import edu.nus.comp.cs2103t.taskerino.storage.Storage;

/**
 * Main class that initialize and controls the program Taskerino
 * by providing methods that integrating classes from different
 * packages together.
 * 
 * @author Wang YanHao
 *
 */
public class Controller {
	private static final String className = new Throwable() .getStackTrace()[0].getClassName();
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_CHANGE = "change";
	private static final String COMMAND_COMPLETE = "complete";
	private static final String COMMAND_SEARCH = "search";
	private static final String COMMAND_EXIT = "exit";
	
	private static Controller singletonController;
	private Logic logic;
	private Parser parser;
	private String outputFeedBack = "";
	
	// private constructor
	private Controller(){
		logic = new Logic();
		parser = new Parser();
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
	
	
	public static void main(String[] args) throws JsonSyntaxException, IOException {
		LoggerFactory.logp(Level.INFO, className, "Main", "Start logger!");
		
		LoggerFactory.logp(Level.INFO, className, "Main", "Loading user Tasks...");
		Data.task = Storage.loadTasksFromFile();
		Task.currentIndex = (Data.task == null ? 1 : Data.task.get(Data.task.size() - 1).getTaskIndex());
		
		LoggerFactory.logp(Level.INFO, className, "Main", "Initialize GUI!");
		new GUI();
	}
	
	
	/**
	 * Execute user command by passing the input userCommand String to
	 * Parser and Logic classes.
	 * 
	 * @param userCommand : user's input command
	 */
	public void executeUserCommand(String userCommand) {
		String methodName = "executeUserCommand";
		
		// inputCommand is passed into parser and logic
		LoggerFactory.logp(Level.INFO, className, methodName, "Send user input commands to Data.");
		Data.setInput(userCommand);

		LoggerFactory.logp(Level.INFO, className, methodName, "Calling Paser.");
		parser.parse();

		LoggerFactory.logp(Level.INFO, className, methodName, "Calling Logic and Storage.");
		try {
			execute((Data.getCommand()).toLowerCase());
		} catch (FileNotFoundException e) {
			LoggerFactory.logp(Level.SEVERE, className, methodName, e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			LoggerFactory.logp(Level.SEVERE, className, methodName, e.getMessage());
			e.printStackTrace();
		}
		LoggerFactory.logp(Level.INFO, className, methodName, "Successfully get feedback from Logic: \n" + outputFeedBack);
	}
	
	
	/**
	 * @param command
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	private void execute(String command) throws FileNotFoundException, UnsupportedEncodingException {
		switch (command) {
			case COMMAND_ADD:
				outputFeedBack = logic.addTask();
				Storage.saveTasksIntoFile();
				break;
				
			case COMMAND_DELETE:
				outputFeedBack = logic.deleteTask();
				Storage.saveTasksIntoFile();
				break;
				
			case COMMAND_CHANGE:
				outputFeedBack = logic.changeTask();
				Storage.saveTasksIntoFile();
				break;
				
			case COMMAND_COMPLETE:
				outputFeedBack = logic.completeTask();
				Storage.saveTasksIntoFile();
				break;
				
			case COMMAND_SEARCH:
				outputFeedBack = logic.searchTask();
				break;
				
			case COMMAND_EXIT:
				System.exit(0);
				
			default:
				outputFeedBack = "invalid msg";
		}
	}

	
	/**
	 * Get feedback to be displayed to user.
	 * 
	 * @return String outputFeedBack
	 */
	public String getUserFeedback() {
		return outputFeedBack;
	}
	
	
	/**
	 * Get all user's tasks.
	 * 
	 * @return ArrayList of Tasks
	 */
	public ArrayList<Task> getUserTasks() {
		return Data.task;
	}
}
