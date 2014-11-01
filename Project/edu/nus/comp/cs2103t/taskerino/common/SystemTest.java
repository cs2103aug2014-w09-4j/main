/**
// code is far away from bug with Buddha protection
//
//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |) 
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package edu.nus.comp.cs2103t.taskerino.common;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * System testing for all components except classes in GUI package.
 * 
 * @author Wang YanHao
 *
 */
public class SystemTest {

	@Test
	public void testBasicTaskCommands() {
		// set up
		Controller.loadData();
		Controller controller = Controller.getController();
		String expectedOutput = "";
		String actualOutput = "";


		// test for adding floating task
		controller.executeUserCommand("add do tutorial");
		expectedOutput = "Add task do tutorial successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);

		// test for deleting floating task
		controller.executeUserCommand("delete do tutorial");
		expectedOutput = "Delete task do tutorial successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);

		// test for deleting none-existent task
		controller.executeUserCommand("delete do tutorial");
		expectedOutput = "Task with name: do tutorial not found!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);

		// test for changing existing task's description
		controller.executeUserCommand("add go to lecture");
		controller.executeUserCommand("change go to lecture to~ don't go to lecture");
		expectedOutput = "Update task successfully from go to lecture to don't go to lecture";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);

		// test for changing none-existent task's description
		controller.executeUserCommand("change test case 1 to~ hey!");
		expectedOutput = "Task with name: test case 1 not found!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);

		// delete test case to make local file clean
		controller.executeUserCommand("delete don't go to lecture");
		expectedOutput = "Delete task don't go to lecture successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
	}


	@Test
	public void testTaskWithDateCommands() {
		// set up
		Controller.loadData();
		Controller controller = Controller.getController();
		String expectedOutput = "";
		String actualOutput = "";

		// test for adding timed task
		controller.executeUserCommand("add test from~ 29 10 2014 to~ 30 10 2014");
		expectedOutput = "Add task test successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);

		// test for changing start date for timed task
		controller.executeUserCommand("change start date to~ 30 11 2014 from~ test");
		expectedOutput = "Updated test start time successfully to 30/11/2014";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		
		// test for changing start date for timed task for different input month format
		controller.executeUserCommand("change start date to~ 12 December 2014 from~ test");
		expectedOutput = "Updated test start time successfully to 12/12/2014";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);

		// test for changing due date for timed task
		controller.executeUserCommand("change due date to~ 01 january 2014 from~ test");
		expectedOutput = "Updated test end time successfully to 1/1/2014";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		
		// delete test case to make local file clean
		controller.executeUserCommand("delete test");
		expectedOutput = "Delete task test successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
	}
}
