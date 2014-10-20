package edu.nus.comp.cs2103t.taskerino.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nus.comp.cs2103t.taskerino.common.Data;

public class LogicTest {

	@Test
	public void addFunction() {
		Data data = new Data();
		Logic logic = new Logic();
		data.setDescription("do tutorial");
		assertEquals("1", logic.addTask(), "add task do tutorial successfully");
	}
	
	@Test
	public void deleteFunction() {
		Data data = new Data();
		data.setDescription("do tutorial");
		Logic logic = new Logic();
		logic.addTask();
		assertEquals("1", logic.deleteTask(), "delete task do tutorial successfully");
		assertEquals("1", logic.deleteTask(), "Task with name: do tutorial not found!");
	}
	
	@Test
	public void changeFunction() {
		Data data = new Data();
		Logic logic = new Logic();
		data.setDescription("help");
		logic.addTask();
		data.setNewDescription("no help");
		assertEquals("1", logic.changeTask(), "Update task from help to no help");
		assertEquals("1", logic.changeTask(), "Task with name: help not found!");
	}
}
