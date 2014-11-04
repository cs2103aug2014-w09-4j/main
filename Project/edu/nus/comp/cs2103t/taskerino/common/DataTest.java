//@author A0110574N
package edu.nus.comp.cs2103t.taskerino.common;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.Test;

public class DataTest {

	@Test
	public void testAddTask() throws FileNotFoundException, UnsupportedEncodingException {
		Task testTask = new Task();
		testTask.setTaskName("test");
		ArrayList<Task> test = new ArrayList<Task>();
		test.add(testTask);
		Data.task = new ArrayList<Task>();
		Data.addTask(testTask);
		assertEquals(test, Data.task);
	}

	@Test
	public void testRemoveTask() throws FileNotFoundException, UnsupportedEncodingException {
		Task testTask = new Task();
		testTask.setTaskName("test");
		ArrayList<Task> test = new ArrayList<Task>();
		Data.task = new ArrayList<Task>();
		Data.addTask(testTask);
		Data.removeTask("test");
		assertEquals(test, Data.task);
	}

	@Test
	public void testUpdateTask() throws FileNotFoundException, UnsupportedEncodingException {
		Task testTask = new Task();
		Task modifiedTask = new Task();
		testTask.setTaskName("test");
		modifiedTask.setTaskName("modifiedTask");
		ArrayList<Task> test = new ArrayList<Task>();
		test.add(modifiedTask);
		Data.task = new ArrayList<Task>();
		Data.addTask(testTask);
		Data.updateTask("test", "modifiedTask");
		assertEquals(test.get(0).getTaskName(), Data.task.get(0).getTaskName());
	}

	@Test
	public void testGetTask() throws FileNotFoundException, UnsupportedEncodingException {
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
