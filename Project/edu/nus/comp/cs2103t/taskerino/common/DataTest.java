//@author A0110574N
package edu.nus.comp.cs2103t.taskerino.common;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DataTest {

	@Test
	public void testAddTask() {
		Task testTask = new Task();
		testTask.setTaskName("test");
		ArrayList<Task> test = new ArrayList<Task>();
		test.add(testTask);
		Data.task = new ArrayList<Task>();
		Data.addTask(testTask);
		assertEquals(test, Data.task);
	}

	@Test
	public void testRemoveTask() {
		Task testTask = new Task();
		testTask.setTaskName("test");
		ArrayList<Task> test = new ArrayList<Task>();
		Data.task = new ArrayList<Task>();
		Data.addTask(testTask);
		Data.removeTask(testTask);
		assertEquals(test, Data.task);
	}

	@Test
	public void testUpdateTask() {
		Task testTask = new Task();
		Task modifiedTask = new Task();
		testTask.setTaskName("test");
		modifiedTask.setTaskName("modifiedTask");
		ArrayList<Task> test = new ArrayList<Task>();
		test.add(modifiedTask);
		Data.task = new ArrayList<Task>();
		Data.addTask(testTask);
		Data.updateTask(testTask, "modifiedTask");
		assertEquals(test.get(0).getTaskName(), Data.task.get(0).getTaskName());
	}

	@Test
	public void testGetTask() {
		Data.task = new ArrayList<Task>();
		Task testTask = new Task();
		Task modifiedTask = new Task();
		testTask.setTaskName("test");
		modifiedTask.setTaskName("modifiedTask");
		Data.addTask(testTask);
		Data.addTask(modifiedTask);
		assertEquals(modifiedTask, Data.getTask("modifiedTask"));
	}

}
