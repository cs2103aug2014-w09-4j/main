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
		Logic logic = new Logic();
		try {
			assertEquals("add task do tutorial successfully", logic.addTask());
		} catch (NullPointerException e) {
		}
	}
	
	@Test
	public void deleteFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Data.setDescription("do tutorial");
		Data.setAddType("floating");
		Data.task = new ArrayList<Task>();
		Logic logic = new Logic();
		try {
			logic.addTask();
			assertEquals("delete task do tutorial successfully", logic.deleteTask());
			assertEquals("Task with name: do tutorial not found!", logic.deleteTask());
		} catch (NullPointerException e) {
		}
	}
	
	@Test
	public void changeFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Logic logic = new Logic();
		Data.setDescription("help");
		Data.setAddType("floating");
		Data.task = new ArrayList<Task>();
		try{
			logic.addTask();
			assertEquals("update task successfully from help to no help", logic.changeTask());
			assertEquals("Task with name: help not found!", logic.changeTask());
		} catch (NullPointerException e) {
		}
	}
	
	@Test
	public void searchFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Logic logic = new Logic();
		Data.setDescription("an apple");
		Data.setAddType("floating");
		Data.task = new ArrayList<Task>();
		try{
			logic.addTask();
			Data.setDescription("apple");
			assertEquals("1. an apple \n", logic.searchTask());
			Data.setDescription("pear");
			assertEquals("Task with name: pear not found!", logic.searchTask());
		} catch (NullPointerException e) {
		}
	}
}
