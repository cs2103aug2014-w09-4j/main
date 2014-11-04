package edu.nus.comp.cs2103t.taskerino.logic;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.nus.comp.cs2103t.taskerino.common.Data;
import edu.nus.comp.cs2103t.taskerino.common.Task;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
public class LogicTest {

	@Test
	public void addFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Data.setAddType("floating");
		Data.setDescription("do tutorial");
		Data.task = new ArrayList<Task>();
		Command newCommand = new Command();
		newCommand.setCommand("add");
		newCommand.setTaskModified(newTask);
		Data.commandList.add(newCommand);
		Logic logic = new Logic();
		assertEquals("add task do tutorial successfully", logic.addTask());
	}
	
	@Test
	public void deleteFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Data.setDescription("do tutorial");
		Data.setAddType("floating");
		Data.task = new ArrayList<Task>();
		Logic logic = new Logic();
		Command newCommand = new Command();
		newCommand.setCommand("add");
		newCommand.setTaskModified(newTask);
		Data.commandList.add(newCommand);
		logic.addTask();
		newCommand.setCommand("delete");
		newCommand.setTaskModified(Data.getTask(description));
		newCommand.setIndexOfTaskModified(Data.task.indexOf(Data.getTask(description)));
		Data.commandList.add(newCommand);
		assertEquals("delete task do tutorial successfully", logic.deleteTask());
		assertEquals("Task with name: do tutorial not found!", logic.deleteTask());
	}
	
	@Test
	public void changeFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Logic logic = new Logic();
		Data.setDescription("help");
		Data.setAddType("floating");
		Data.task = new ArrayList<Task>();
		Command newCommand = new Command();
		newCommand.setCommand("add");
		newCommand.setTaskModified(newTask);
		Data.commandList.add(newCommand);
		logic.addTask();
		Data.setChangeType("taskName");
		Data.setNewDescription("no help");
		newCommand.setCommand("change");
		newCommand.setDueDate(Data.getTask(description).getDueDate());
		newCommand.setStartDate(Data.getTask(description).getStartDate());
		newCommand.setNameOfTaskModified(Data.getTask(description).getTaskName());
		if(Data.getTask(description).getStatus().equals("completed")) {
			newCommand.setStatusOfTask(true);
		} else {
			newCommand.setStatusOfTask(false);
		}
		newCommand.setIndexOfTaskModified(Data.task.indexOf(Data.getTask(description)));
		Data.commandList.add(newCommand);
		assertEquals("update task successfully from help to no help", logic.changeTask());
		assertEquals("Task with name: help not found!", logic.changeTask());
	}
	
	@Test
	public void searchFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Logic logic = new Logic();
		Data.setDescription("an apple");
		Data.setAddType("floating");
		Data.task = new ArrayList<Task>();
		logic.addTask();
		Data.setDescription("apple");
		assertEquals("1. an apple \n", logic.searchTask());
		Data.setDescription("pear");
		assertEquals("Task with name: pear not found!", logic.searchTask());
	}
}
