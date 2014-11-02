package edu.nus.comp.cs2103t.taskerino.common;

import java.util.ArrayList;

public class Assist {
	
	private static final String TITLE_GENERALHELP = "HELP";
	
	private static final String TITLE_HELP_ADD = "HELP ADD";
	
	private static final String TITLE_HELP_DELETE = "HELP DELETE";
	
	private static final String TITLE_HELP_CHANGE = "HELP CHANGE";
	
	private static final String TITLE_HELP_COMPLETE = "HELP COMPLETE";
	
	private static final String TITLE_HELP_SEARCH = "HELP SEARCH";
	
	private static final String TITLE_HELP_GOTO = "HELP GOTO";
	
	private static final String MESSAGE_GENERALHELP = "For more information on a specific command, type \"help command-name\""
													+ "1. add - Adds a task to the task list. Supports floating, deadline and timed tasks"
													+ "2. delete - Removes a task from the task list."
													+ "3. change - Changes the details of the task."
													+ "4. complete - Marks task as completed"
													+ "5. search - Searches based on user input displays relevant tasks";
	private static final String MESSAGE_HELP_ADD = "Adds tasks to the task list." + "\n\n"
												+ "Any additions are immediately saved into local file \"Tasks.txt\"." + "\n"
												+ "1. To add a FLOATING task:" + "\n"
												+ ">> add <TASK NAME>" + "\n"
												+ "2. To add a DEADLINE task:" + "\n"
												+ ">> add <TASK NAME> by~ <DATE>" + "\n"
												+ "3. To add a TIMED task:" + "\n"
												+ ">> add <TASK NAME> from~ <START DATE> to~ <DUE DATE>";
	private static final String MESSAGE_HELP_DELETE = "Deletes:"
													+ "a. Task from task list"
													+ "b. Start/Due date from specific task"
													+ "Any changes are immediately saved into local file \"Tasks.txt\"."
													+ "Deleting task from task list:"
													+ "1. To delete task using TASK NAME"
													+ ">> delete <TASK NAME>"
													+ "2. To delete task using TASK INDEX"
													+ ">> delete <TASK INDEX>"
													+ "Deleting start/due date from task"
													+ "1. To delete START DATE of a task"
													+ ">> delete start date from~ <TASK INDEX>"
													+ "2. To delete DUE DATE of a task"
													+ ">>delete due date from~ <TASK INDEX>";
	private static final String MESSAGE_HELP_CHANGE = "Changes details of tasks."
													+ "Possible changes are: TASK NAME, START DATE and DUE DATE "
													+ "Any changes are immediately saved into local file \"Tasks.txt\"."
													+ "1. change TASK NAME of existing task"
													+ ">> change <TASK INDEX> to~ <NEW TASK NAME>"
													+ "2. change START DATE of existing task"
													+ ">> change start date to~ <NEW DATE> from~ <TASK INDEX>"
													+ "3. change DUE DATE of existing task"
													+ ">> change due date to~ <NEW DATE> from~ <TASK INDEX>";
	private static final String MESSAGE_HELP_COMPLETE = "Marks a task as complete." 
													+ "Any changes are immediately saved into local file \"Tasks.txt\"."
													+ "1. mark a task as completed using TASK NAME"
													+ ">> complete <TASK NAME>"
													+ "2. mark a task as completed using TASK INDEX"
													+ ">> complete <TASK INDEX>";
	private static final String MESSAGE_HELP_SEARCH = "Searches for tasks that matches criteria and displays them."
													+ "1. searching for tasks containing a certain keyword"
													+ ">> search <KEYWORD>"
													+ "2. searching for tasks within a specific range of dates"
													+ ">> search from~ <START DATE> to~ <DUE DATE>";
	private static final String MESSAGE_HELP_GOTO = "Changes tasks displayed to those with the specified attached tag."
													+ "1. change tag to \"all\"."
													+ ">> goto all"
													+ "2. change tag to \"search\"."
													+ ">> goto search"
													+ "3. change tag to \"uncompleted\"."
													+ ">> goto uncompleted";
	
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
