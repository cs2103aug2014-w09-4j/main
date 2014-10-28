package edu.nus.comp.cs2103t.taskerino.logic;
import static org.junit.Assert.*;
import org.junit.Test;
import edu.nus.comp.cs2103t.taskerino.common.Data;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
public class LogicTest {

	@Test
	public void addFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Data data = new Data();
		Logic logic = new Logic();
		data.setAddType("floating");
		data.setDescription("do tutorial");
		assertEquals("add task do tutorial successfully", logic.addTask());
	}
	
	@Test
	public void deleteFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Data data = new Data();
		data.setDescription("do tutorial");
		data.setAddType("floating");
		Logic logic = new Logic();
		logic.addTask();
		assertEquals("delete task do tutorial successfully", logic.deleteTask());
		assertEquals("Task with name: do tutorial not found!", logic.deleteTask());
	}
	
	@Test
	public void changeFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Data data = new Data();
		Logic logic = new Logic();
		data.setDescription("help");
		data.setAddType("floating");
		logic.addTask();
		data.setNewDescription("no help");
		assertEquals("Update task from help to no help", logic.changeTask());
		assertEquals("Task with name: help not found!", logic.changeTask());
	}
	
	@Test
	public void searchFunction() throws FileNotFoundException, UnsupportedEncodingException{
		Data data = new Data();
		Logic logic = new Logic();
		data.setDescription("an apple");
		data.setAddType("floating");
		logic.addTask();
		Data.setDescription("apple");
		assertEquals("1. an apple", logic.searchTask());
		data.setDescription("pear");
		assertEquals("Task with name : pear not found!", logic.searchTask());
	}
}
