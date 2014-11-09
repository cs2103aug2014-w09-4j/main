//@author A0113742N
package edu.nus.comp.cs2103t.taskerino.common;

import java.util.ArrayList;

/**
 * This class make use of two Stacks to store all commands that user has executed. <br>
 * Note: <br>
 * All data stored in this class is temporary, once user terminates the software,
 * all history will be dumped.
 */
public class CommandHistory {
	private static CommandHistory singletonCommandHistory;

	private ArrayList<String> userCommands;
	private int currentAccessedCommandIndex;

	// private constructor
	private CommandHistory(){
		userCommands = new ArrayList<String>();
		currentAccessedCommandIndex = -1;
	}

	/**
	 * Returns CommandHistory singleton instance.
	 * @return CommandHistory singleton
	 */
	public static CommandHistory getCommandHistory() {
		if (singletonCommandHistory == null) {
			singletonCommandHistory = new CommandHistory();
		}
		return singletonCommandHistory;
	}


	/**
	 * Push a command into the userCommand ArrayList.
	 */
	public void storeCommand(String command) {
		userCommands.add(command);
	}
	

	/**
	 * Returns user's previous executed command String if there are any 
	 * or returns an empty String if there are none.
	 * @return command String
	 */
	public String getPreCommand() {
		if (currentAccessedCommandIndex == -1) {
			// user have not accessing any command!
			if (userCommands.isEmpty()) {
				// no command has been executed!
				return "";
			} else {
				// return last executed user command
				currentAccessedCommandIndex = userCommands.size() - 1;
				return userCommands.get(currentAccessedCommandIndex);
			}
		} else {
			if (currentAccessedCommandIndex == 0) {
				// no previous user command available
				return "";
			} else {
				// decrease pointer value
				currentAccessedCommandIndex--;
				// return previous executed user command
				return userCommands.get(currentAccessedCommandIndex);
			}
		}
	}

	
	/**
	 * Returns user's next executed command String if there are any 
	 * or returns an empty String if there are none.
	 * @return command String
	 */
	public String getPostCommand() {
		// increase pointer value
		currentAccessedCommandIndex++;
		if (currentAccessedCommandIndex > (userCommands.size() - 1)) {
			// invalid, reset pointer
			this.setPointerToLatestTask();
			return "";
		} else {
			// return next executed user command
			return userCommands.get(currentAccessedCommandIndex);
		}
	}

	
	public void setPointerToLatestTask() {
		currentAccessedCommandIndex = userCommands.size();
	}
}
