//@author A0110574N
package edu.nus.comp.cs2103t.taskerino.common;

/**
 *
 * 
 * This class contains all the text for the various help commands.
 */

import java.util.ArrayList;

public class Assist {
	
	private static final String TITLE_GENERALHELP = "HELP";
	
	private static final String TITLE_HELP_ADD = "HELP ADD";
	
	private static final String TITLE_HELP_DELETE = "HELP DELETE";
	
	private static final String TITLE_HELP_CHANGE = "HELP CHANGE";
	
	private static final String TITLE_HELP_COMPLETE = "HELP COMPLETE";
	
	private static final String TITLE_HELP_SEARCH = "HELP SEARCH";
	
	private static final String TITLE_HELP_GOTO = "HELP GOTO";
	
	private static final String MESSAGE_GENERALHELP = "\nFor more information on a specific command, type \"help command-name\" \n\n\n"
													+ "1. add - Adds a task to the task list. Supports floating, deadline and timed tasks \n\n"
													+ "2. delete - Removes a task from the task list. \n\n"
													+ "3. change - Changes the details of the task. \n\n"
													+ "4. complete - Marks task as completed. \n\n"
													+ "5. search - Searches based on user input displays relevant tasks. \n\n"
													+ "6. undo - Undo the latest change made to the task list. \n\n"
													+ "7. goto - Switches display to the list of tasks with specified tag. \n";
	private static final String MESSAGE_HELP_ADD = "\nAdds tasks to the task list." + "\n\n"
												+ "Any additions are immediately saved into local file \"Tasks.txt\"." + "\n\n"
												+ "1. To add a FLOATING task:" + "\n"
												+ ">> add <TASK NAME>" + "\n\n"
												+ "2. To add a DEADLINE task:" + "\n"
												+ ">> add <TASK NAME> by~ <DATE>" + "\n\n"
												+ "3. To add a TIMED task:" + "\n"
												+ ">> add <TASK NAME> from~ <START DATE> to~ <DUE DATE>";
	private static final String MESSAGE_HELP_DELETE = "\nDeletes: \n"
													+ "a. Task from task list \n"
													+ "b. Start/Due date from specific task \n\n"
													+ "Any changes are immediately saved into local file \"Tasks.txt\".\n\n"
													+ "Deleting task from task list:\n\n"
													+ "1. To delete task using TASK NAME \n"
													+ ">> delete <TASK NAME> \n\n"
													+ "2. To delete task using TASK INDEX \n"
													+ ">> delete <TASK INDEX> \n";
	private static final String MESSAGE_HELP_CHANGE = "\nChanges details of tasks. \n\n"
													+ "Possible changes are: TASK NAME, START DATE and DUE DATE \n\n"
													+ "Any changes are immediately saved into local file \"Tasks.txt\". \n\n"
													+ "1. change TASK NAME of existing task \n"
													+ ">> change <TASK INDEX> to~ <NEW TASK NAME> \n\n"
													+ "2. change START DATE of existing task \n"
													+ ">> change start date to~ <NEW DATE> from~ <TASK INDEX> \n\n"
													+ "3. change DUE DATE of existing task \n"
													+ ">> change due date to~ <NEW DATE> from~ <TASK INDEX> \n\n";
	private static final String MESSAGE_HELP_COMPLETE = "\nMarks a task as complete. \n\n" 
													+ "Any changes are immediately saved into local file \"Tasks.txt\". \n\n"
													+ "1. mark a task as completed using TASK NAME \n"
													+ ">> complete <TASK NAME> \n\n"
													+ "2. mark a task as completed using TASK INDEX \n"
													+ ">> complete <TASK INDEX> \n\n";
	private static final String MESSAGE_HELP_SEARCH = "\nSearches for tasks that matches criteria and displays them. \n\n"
													+ "1. searching for tasks containing a certain keyword \n"
													+ ">> search <KEYWORD> \n\n";
	private static final String MESSAGE_HELP_GOTO = "\nChanges tasks displayed to those with the specified attached tag. \n\n"
													+ "1. change tag to \"all\". \n"
													+ ">> goto all \n\n"
													+ "2. change tag to \"search\". \n"
													+ ">> goto search \n\n"
													+ "3. change tag to \"uncompleted\". \n"
													+ ">> goto uncompleted \n\n"
													+ "4. change tag to \"completed\". \n"
													+ ">> goto completed \n\n";
	
	private static ArrayList<String> message;
	
	public ArrayList<String> generalHelp() {
		message = new ArrayList<String>();
		message.add(TITLE_GENERALHELP);
		message.add(MESSAGE_GENERALHELP);
		return message;
	}
	
	public ArrayList<String> addHelp() {
		message = new ArrayList<String>();
		message.add(TITLE_HELP_ADD);
		message.add(MESSAGE_HELP_ADD);
		return message;
	}
	
	public ArrayList<String> deleteHelp() {
		message = new ArrayList<String>();
		message.add(TITLE_HELP_DELETE);
		message.add(MESSAGE_HELP_DELETE);
		return message;
	}
	
	public ArrayList<String> changeHelp() {
		message = new ArrayList<String>();
		message.add(TITLE_HELP_CHANGE);
		message.add(MESSAGE_HELP_CHANGE);
		return message;
	}
	
	public ArrayList<String> completeHelp() {
		message = new ArrayList<String>();
		message.add(TITLE_HELP_COMPLETE);
		message.add(MESSAGE_HELP_COMPLETE);
		return message;
	}
	
	public ArrayList<String> searchHelp() {
		message = new ArrayList<String>();
		message.add(TITLE_HELP_SEARCH);
		message.add(MESSAGE_HELP_SEARCH);
		return message;
	}
	
	public ArrayList<String> gotoHelp() {
		message = new ArrayList<String>();
		message.add(TITLE_HELP_GOTO);
		message.add(MESSAGE_HELP_GOTO);
		return message;
	}
}
