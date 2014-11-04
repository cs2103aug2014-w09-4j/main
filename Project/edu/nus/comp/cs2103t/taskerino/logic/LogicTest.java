//@author A0108310Y
package edu.nus.comp.cs2103t.taskerino.logic;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.nus.comp.cs2103t.taskerino.common.Data;
import edu.nus.comp.cs2103t.taskerino.common.Task;
import edu.nus.comp.cs2103t.taskerino.common.Command;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * LogicTest test the individual functions in the Logic component
 *
 */ 

public class LogicTest {

	@Test
	public void addFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Data.setAddType("floating");
		Data.setDescription("do tutorial");
		Data.task = new ArrayList<Task>();
		Data.commandList = new ArrayList<Command>();
		Logic logic = new Logic();
		assertEquals("Add task do tutorial successfully", logic.addTask());
	}
	
	@Test
	public void deleteFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Data.setDescription("do tutorial");
		Data.setAddType("floating");
		Data.task = new ArrayList<Task>();
		Logic logic = new Logic();
		Data.commandList = new ArrayList<Command>();
		logic.addTask();
		assertEquals("Delete task do tutorial successfully", logic.deleteTask());
		assertEquals("Task with name: do tutorial not found!", logic.deleteTask());
	}
	
	@Test
	public void changeFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Logic logic = new Logic();
		Data.setDescription("help");
		Data.setAddType("floating");
		Data.task = new ArrayList<Task>();
		Data.commandList = new ArrayList<Command>();
		logic.addTask();
		Data.setNewDescription("no help");
		Data.setChangeType("taskName");
		assertEquals("Update task successfully from help to no help", logic.changeTask());
		assertEquals("Task with name: help not found!", logic.changeTask());
	}
	
	@Test
	public void searchFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Logic logic = new Logic();
		Data.setDescription("an apple");
		Data.setAddType("floating");
		Data.task = new ArrayList<Task>();
		Data.commandList = new ArrayList<Command>();
		Data.searchedTasks = new ArrayList<Task>();
		logic.addTask();
		Data.setDescription("apple");
		assertEquals("1 task found with given description. ", logic.searchTask());
		Data.setDescription("pear");
		assertEquals("Task with name: pear not found!", logic.searchTask());
		
	}
}
