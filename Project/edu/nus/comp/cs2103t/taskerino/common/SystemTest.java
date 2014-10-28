package edu.nus.comp.cs2103t.taskerino.common;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nus.comp.cs2103t.taskerino.common.Data;

public class SystemTest {

	@Test
	public void add() {
		Data data = new Data();
		Controller controller = new Controller();
		data.setDescription("do tutorial");
		controller.executeCommand("add")
		assertEquals("add task do tutorial successfully", controller.getUserFeedback());
	}
	
	@Test
	public void delete() {
		Data data = new Data();
		Controller controller = new Controller();
		data.setDescription("do tutorial");
		controller.executeCommand("delete");
		assertEquals("delete task do tutorial successfully", controller.getUserFeedback());
		controller.executeCommand("delete");
		assertEquals("Task with name: do tutorial not found!", controller.getUserFeedback());
	}
	
	@Test
	public void change() {
		Data data = new Data();
		Controller controller = new Controller();
		data.setDescription("help");
		controller.executeCommand("add");
		data.setNewDescription("no help");
		controller.executeCommand("change");
		assertEquals("Update task from help to no help", controller.getUserFeedback());
		controller.executeCommand("change");
		assertEquals("Task with name: help not found!", controller.getUserFeedback());
	}
}
