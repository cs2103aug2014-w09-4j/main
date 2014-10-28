package edu.nus.comp.cs2103t.taskerino.common;
import static org.junit.Assert.*;
import org.junit.Test;
import edu.nus.comp.cs2103t.taskerino.common.Data;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class SystemTest {

	@Test
	public void add() throws FileNotFoundException, UnsupportedEncodingException{
		Controller controller = Controller.getController();
		controller.executeUserCommand("add do tutorial");
		assertEquals("add task do tutorial successfully", controller.getUserFeedback());

		controller.executeUserCommand("delete do tutorial");
		assertEquals("delete task do tutorial successfully", controller.getUserFeedback());
		
		controller.executeUserCommand("delete do tutorial");
		assertEquals("Task with name: do tutorial not found!", controller.getUserFeedback());
		
		controller.executeUserCommand("add help");
		controller.executeUserCommand("change help to no help");
		assertEquals("Update task from help to no help", controller.getUserFeedback());
		
		controller.executeUserCommand("change help to no help");
		assertEquals("Task with name: help not found!", controller.getUserFeedback());
	}
}
