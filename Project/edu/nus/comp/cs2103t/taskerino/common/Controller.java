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
	private static Controller singletonController;
	private Logic logic;
	private Parser parser;
	private String outputFeedBack = "";
	
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

		LoggerFactory.logp(Level.INFO, className, methodName, "Initialize Paser.");
		parser.parse();
		
		try {
			LoggerFactory.logp(Level.INFO, className, methodName, "Asking Logic for feedback...");
			outputFeedBack = logic.executeUserCommand();
			LoggerFactory.logp(Level.INFO, className, methodName, "Successfully get feedback from Logic: \n" + outputFeedBack);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// handle the exceptions
			LoggerFactory.logp(Level.WARNING, className, methodName, e.getMessage());
			e.printStackTrace();
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
	 *  Get all user's tasks.
	 * 
	 * @return ArrayList of Tasks
	 */
	public ArrayList<Task> getUserTasks() {
		return Data.task;
	}
}
