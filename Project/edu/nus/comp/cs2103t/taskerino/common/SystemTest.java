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

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.junit.Test;

//@author A0113742N

/**
 * System testing for all components except classes in GUI package.
 */
public class SystemTest {
	private Controller controller;
	private File taskFile;
	private File backupFile;
	private File testingFile;
	private String expectedOutput = "";
	private String actualOutput = "";

	// format of storage into Gson
	private static final String QUOTATION_MARK = "\\u0027";
	private static final String DATE_CLASS_PATH = (new DateAndTime()).getClass().getName();
	private static final String FLOATING_TASK_FORMAT = "[{\"taskName\":\"%1$s\"," + "\"status\":%2$s}]";
	private static final String DEADLINE_TASK_FORMAT = "[{\"taskName\":\"%1$s\"," 
			+ "\"dueDate\":{\"className\":\"" + DATE_CLASS_PATH + "\",\"year\":%2$s,\"month\":%3$s,\"day\":%4$s}," 
			+ "\"taskType\":\"deadline\"," + "\"status\":%5$s}]";
	private static final String TIMED_TASK_FORMAT = "[{\"taskName\":\"%1$s\"," 
			+ "\"startDate\":{\"className\":\"" + DATE_CLASS_PATH + "\",\"year\":%2$s,\"month\":%3$s,\"day\":%4$s}," 
			+ "\"dueDate\":{\"className\":\"" + DATE_CLASS_PATH + "\",\"year\":%5$s,\"month\":%6$s,\"day\":%7$s}," 
			+ "\"taskType\":\"timed\"," + "\"status\":%8$s}]";
	private static final String EMPTY_TASK_FORMAT = "[]";


	@Test
	public void testBasicTaskCommands() {
		setUp();

		// test for adding floating task
		controller.executeUserCommand("add do tutorial");
		// check for feedback
		expectedOutput = "Add task do tutorial successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file
		String[] expectedData01 = {"floating", "do tutorial", "false"};
		writeTestFile(expectedData01);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for deleting floating task via task name
		controller.executeUserCommand("delete do tutorial");
		// check for feedback
		expectedOutput = "Delete task do tutorial successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file
		String[] expectedData02 = {"empty"};
		writeTestFile(expectedData02);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for deleting none-existent task
		controller.executeUserCommand("delete do tutorial");
		// check for feedback
		expectedOutput = "Task with name: do tutorial not found!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file
		String[] expectedData03 = {"empty"};
		writeTestFile(expectedData03);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing existing task's description
		controller.executeUserCommand("add go to lecture");
		controller.executeUserCommand("change go to lecture to~ don't go to lecture");
		// check for feedback
		expectedOutput = "Update task successfully from go to lecture to don't go to lecture";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData04 = {"floating", "don" + QUOTATION_MARK + "t go to lecture", "false"};
		writeTestFile(expectedData04);
		assertEquals(true, fileCompare(taskFile, testingFile));
		

		// test for completing a existing task
		controller.executeUserCommand("complete don't go to lecture");
		// check for feedback
		expectedOutput = "Complete task don't go to lecture";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData05 = {"floating", "don" + QUOTATION_MARK + "t go to lecture", "true"};
		writeTestFile(expectedData05);
		assertEquals(true, fileCompare(taskFile, testingFile));

		
		// test for deleting a existing task via index
		controller.executeUserCommand("delete 1");
		// check for feedback
		expectedOutput = "Delete task don't go to lecture successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData06 = {"empty"};
		writeTestFile(expectedData06);
		assertEquals(true, fileCompare(taskFile, testingFile));
				

		// test for changing none-existent task's description
		controller.executeUserCommand("change test case 1 to~ hey!");
		// check for feedback
		expectedOutput = "Task with name: test case 1 not found!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);		
		// check for file 
		String[] expectedData07 = {"empty"};
		writeTestFile(expectedData07);
		assertEquals(true, fileCompare(taskFile, testingFile));

		closeDown();
	}


	@Test
	public void testTaskWithDateCommands() {
		setUp();

		// test for adding timed task
		controller.executeUserCommand("add test from~ 29 10 2014 to~ 30 12 2014");	
		// check for feedback
		expectedOutput = "Add task test successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData01 = {"timed", "test", "2014", "10", "29", "2014", "12", "30", "false"};
		writeTestFile(expectedData01);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing start date for timed task
		controller.executeUserCommand("change start date to~ 30 11 2014 from~ test");
		expectedOutput = "Updated test start time successfully to 30/11/2014";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData02 = {"timed", "test", "2014", "11", "30", "2014", "12", "30", "false"};
		writeTestFile(expectedData02);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing start date for timed task for different input month format
		controller.executeUserCommand("change start date to~ 12 December 2014 from~ test");
		expectedOutput = "Updated test start time successfully to 12/12/2014";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData03 = {"timed", "test", "2014", "12", "12", "2014", "12", "30", "false"};
		writeTestFile(expectedData03);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing due date for timed task
		controller.executeUserCommand("change due date to~ 01 january 2015 from~ test");
		expectedOutput = "Updated test end time successfully to 1/1/2015";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData04 = {"timed", "test", "2014", "12", "12", "2015", "1", "1", "false"};
		writeTestFile(expectedData04);
		assertEquals(true, fileCompare(taskFile, testingFile));

		closeDown();
	}

	
/****************************************************
    Functions facilitates system testing.
*****************************************************/	

	/**
	 * Based on Data's type, format the expected output result, and write it into the testingFile.
	 * @param expectedData String of Data's attributes.
	 */
	private void writeTestFile(String[] expectedData) {
		// input String array should contain at least a command word
		assert expectedData.length >= 1 : expectedData;
		
		PrintWriter printer = null;
		try {
			printer = new PrintWriter(new FileOutputStream(testingFile), true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		switch (expectedData[0]) {
			case "floating":
				// String array length should be at least 3
				assert expectedData.length >= 3 : expectedData;
				printer.printf(FLOATING_TASK_FORMAT, expectedData[1], expectedData[2]);
				printer.println("");
				break;
			case "deadline":
				// String array length should be at least 6
				assert expectedData.length >= 6 : expectedData;
				printer.printf(DEADLINE_TASK_FORMAT, expectedData[1], expectedData[2], expectedData[3],
						expectedData[4], expectedData[5]);
				printer.println("");
				break;
			case "timed":
				// String array length should be at least 9
				assert expectedData.length >= 9 : expectedData;
				printer.printf(TIMED_TASK_FORMAT, expectedData[1], expectedData[2], expectedData[3],
						expectedData[4], expectedData[5], expectedData[6], expectedData[7], expectedData[8]);
				printer.println("");
				break;
			case "empty":
				printer.println(EMPTY_TASK_FORMAT);
				break;
			default :
				assert false : expectedData[0];
		}
		
		printer.close();
	}


	/**
	 * Close down system testing, making sure local directory is clean: <br>
	 * 1. Delete and re-create task file to make sure it is clean. <br>
	 * 1. Copy backup file to original task file. <br>
	 * 2. Delete testing file and backup file. <br>
	 */
	private void closeDown() {
		taskFile.delete();
		taskFile = new File("Tasks.txt");

		try {
			copyFile(backupFile, taskFile);		
			backupFile.delete();
			testingFile.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Compare two local files byte by byte, check if they have the same content.
	 * @param sourceFile
	 * @param destFile
	 * @return boolean indicating if two files are the same.
	 * @throws IOException file too large to compare
	 */
	private boolean fileCompare(File sourceFile, File destFile) {
		try {
			InputStream srcIs = new FileInputStream(sourceFile);
			BufferedInputStream srcBis = new BufferedInputStream(srcIs);
			final byte[] srcByte = new byte[1024];

			InputStream destIs = new FileInputStream(destFile);
			BufferedInputStream destBis = new BufferedInputStream(destIs);
			final byte[] destByte = new byte[1024];

			int srcCount = srcBis.read(srcByte);
			int destCount = destBis.read(destByte);

			do {
				// compare
				if (srcCount != destCount) {
					srcBis.close();
					destBis.close();
					return false;
				}
				for(int i = 0; i < srcCount; i++) {
					if(srcByte[i] != destByte[i]) {
						srcBis.close();
						destBis.close();
						return false;
					}
				}

				srcCount = srcBis.read(srcByte);
				destCount = destBis.read(destByte);

			} while (srcCount > 0 && destCount > 0);

			srcBis.close();
			destBis.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * Set up file comparison necessities: <br>
	 * 1. Load local file. <br>
	 * 2. Initialize Controller. <br>
	 * 3. Make copy for local file (ensure local data is not dirty after testing). <br>
	 * 4. Clear the local file by deleting and recreating it. <br>
	 * 5. Create testing file.
	 */
	private void setUp() {
		Controller.loadData();
		controller = Controller.getController();

		taskFile = new File("Tasks.txt");
		backupFile = new File("SystemTestingBackUp.txt"); 

		try {
			copyFile(taskFile, backupFile);
		} catch (IOException e) {
			// file not found exception
			e.printStackTrace();
		}

		taskFile.delete();
		taskFile = new File("Tasks.txt");
		Controller.loadData();

		testingFile = new File("SystemTesting.txt");
	}


	/**
	 * Make copy of a local file.
	 * @param sourceFile
	 * @param destFile
	 * @throws IOException
	 */
	private void copyFile(File sourceFile, File destFile) throws IOException {
		InputStream inputStream = new FileInputStream(sourceFile);
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		final byte[] bytes = new byte[1024];

		FileOutputStream outputStream = new FileOutputStream(destFile);
		BufferedOutputStream bos = new BufferedOutputStream(outputStream);

		int count = bis.read(bytes);
		while (count > 0) {
			bos.write(bytes, 0, count);
			count = bis.read(bytes);
		}

		bos.close();
		bis.close();

		inputStream.close();
		outputStream.close();
	}

}
