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
		assertEquals("add task do tutorial successfully", logic.addTask());
	}
	
	@Test
	public void deleteFunction() {
		Data data = new Data();
		data.setDescription("do tutorial");
		Logic logic = new Logic();
		logic.addTask();
		assertEquals("delete task do tutorial successfully", logic.deleteTask());
		assertEquals("Task with name: do tutorial not found!", logic.deleteTask());
	}
	
	@Test
	public void changeFunction() {
		Data data = new Data();
		Logic logic = new Logic();
		data.setDescription("help");
		logic.testCommand("add");
		data.setNewDescription("no help");
		assertEquals("Update task from help to no help", logic.changeTask());
		assertEquals("Task with name: help not found!", logic.changeTask());
	}
}
