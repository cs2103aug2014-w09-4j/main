//@author A0113742N
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
	private static final String DOLLAR_MARK = "\\u0026";
	private static final String SINGLE_QUOTATION_MARK = "\\u0027";
	private static final String DATE_CLASS_PATH = (new DateAndTime()).getClass().getName();

	private static final String FLOATING_TASK_FORMAT = "[{\"taskIndex\":%1$s," + "\"taskName\":\"%2$s\"," + "\"status\":%3$s}]";
	private static final String DEADLINE_TASK_FORMAT = "[{\"taskIndex\":%1$s," + "\"taskName\":\"%2$s\"," 
			+ "\"dueDate\":{\"className\":\"" + DATE_CLASS_PATH + "\",\"year\":%3$s,\"month\":%4$s,\"day\":%5$s}," 
			+ "\"taskType\":\"deadline\"," + "\"status\":%6$s}]";
	private static final String TIMED_TASK_FORMAT = "[{\"taskIndex\":%1$s," + "\"taskName\":\"%2$s\"," 
			+ "\"startDate\":{\"className\":\"" + DATE_CLASS_PATH + "\",\"year\":%3$s,\"month\":%4$s,\"day\":%5$s}," 
			+ "\"dueDate\":{\"className\":\"" + DATE_CLASS_PATH + "\",\"year\":%6$s,\"month\":%7$s,\"day\":%8$s}," 
			+ "\"taskType\":\"timed\"," + "\"status\":%9$s}]";
	private static final String EMPTY_TASK_FORMAT = "[]";


	@Test
	public void testBasicTaskFunctions() {
		setUp();

		// test for adding floating task
		controller.executeUserCommand("add do tutorial");
		// check for feedback
		expectedOutput = "Add task do tutorial successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file
		String[] expectedData01 = {"floating", "1", "do tutorial", "false"};
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
		expectedOutput = "Task not found!";
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
		String[] expectedData04 = {"floating", "2", "don" + SINGLE_QUOTATION_MARK + "t go to lecture", "false"};
		writeTestFile(expectedData04);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for completing a existing task
		controller.executeUserCommand("complete don't go to lecture");
		// check for feedback
		expectedOutput = "Complete task don't go to lecture successfully.";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData05 = {"floating", "2", "don" + SINGLE_QUOTATION_MARK + "t go to lecture", "true"};
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
		expectedOutput = "Task not found!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);		
		// check for file 
		String[] expectedData07 = {"empty"};
		writeTestFile(expectedData07);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for adding task with random symbols
		controller.executeUserCommand("add he.l~l,@$$^%&$$@'$%@%@%$#*()(!@!:\"{}|");// check for feedback
		expectedOutput = "Add task he.l~l,@$$^%&$$@'$%@%@%$#*()(!@!:\"{}| successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file
		String[] expectedData08 = {"floating", "3", "he.l~l,@$$^%" + DOLLAR_MARK + "$$@"+ SINGLE_QUOTATION_MARK + 
				"$%@%@%$#*()(!@!:\\\"{}|", "false"};
		writeTestFile(expectedData08);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing start date for floating task (not allowed)
		controller.executeUserCommand("change start date to~ 01 01 2014 from~ 1");
		// check for feedback
		expectedOutput = "Can not modify starting time for a floating task!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file
		String[] expectedData09 = {"floating", "3", "he.l~l,@$$^%" + DOLLAR_MARK + "$$@"+ SINGLE_QUOTATION_MARK + 
				"$%@%@%$#*()(!@!:\\\"{}|", "false"};
		writeTestFile(expectedData09);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for clear command
		controller.executeUserCommand("clear");
		// check for feedback
		expectedOutput = "Cleared all tasks";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);		
		// check for file 
		String[] expectedData10 = {"empty"};
		writeTestFile(expectedData10);
		assertEquals(true, fileCompare(taskFile, testingFile));

		closeDown();
	}


	@Test
	public void testTimedTaskFuncions() {
		setUp();

		// test for adding timed task
		controller.executeUserCommand("add test from~ 29 10 2014 to~ 30 12 2014");	
		// check for feedback
		expectedOutput = "Add task test successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData01 = {"timed", "1", "test", "2014", "10", "29", "2014", "12", "30", "false"};
		writeTestFile(expectedData01);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing start date for timed task
		controller.executeUserCommand("change start date to~ 30 11 2014 from~ test");
		expectedOutput = "Updated start date successfully to 30/11/2014";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData02 = {"timed", "1", "test", "2014", "11", "30", "2014", "12", "30", "false"};
		writeTestFile(expectedData02);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing start date for timed task for different input month format
		controller.executeUserCommand("change start date to~ 12 December 2014 from~ test");
		expectedOutput = "Updated start date successfully to 12/12/2014";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData03 = {"timed", "1", "test", "2014", "12", "12", "2014", "12", "30", "false"};
		writeTestFile(expectedData03);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing due date for timed task
		controller.executeUserCommand("change due date to~ 01 january 2015 from~ test");
		expectedOutput = "Updated due date successfully to 1/1/2015";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData04 = {"timed", "1", "test", "2014", "12", "12", "2015", "1", "1", "false"};
		writeTestFile(expectedData04);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for change start date to later than due date
		controller.executeUserCommand("change start date to~ 01 01 2020 from~ test");
		expectedOutput = "Start date cannot be later than the due date!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData05 = {"timed", "1", "test", "2014", "12", "12", "2015", "1", "1", "false"};
		writeTestFile(expectedData05);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for change due date to earlier than start date
		controller.executeUserCommand("change due date to~ 01 01 1993 from~ test");
		expectedOutput = "Due date cannot be earlier than the start date!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData06 = {"timed", "1", "test", "2014", "12", "12", "2015", "1", "1", "false"};
		writeTestFile(expectedData06);
		assertEquals(true, fileCompare(taskFile, testingFile));

		closeDown();
	}

	@Test
	public void testDeadlineTaskFuncions() {
		setUp();

		// test for adding deadline task
		controller.executeUserCommand("add deadlined task by~ 30 12 2014");	
		// check for feedback
		expectedOutput = "Add task deadlined task successfully";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData01 = {"deadline", "1", "deadlined task", "2014", "12", "30", "false"};
		writeTestFile(expectedData01);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing due date for deadline task
		controller.executeUserCommand("change due date to~ 01 01 1993 from~ 1");	
		// check for feedback
		expectedOutput = "Updated due date successfully to 1/1/1993";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData02 = {"deadline", "1", "deadlined task", "1993", "1", "1", "false"};
		writeTestFile(expectedData02);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing deadline task to timed task via changing start date
		controller.executeUserCommand("change start date to~ 01 10 1949 from~ 1");	
		// check for feedback
		expectedOutput = "Updated start date successfully to 1/10/1949";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData03 = {"timed", "1", "deadlined task", "1949", "10", "1", "1993", "1", "1", "false"};
		writeTestFile(expectedData03);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for changing floating task to deadline task via changing due date
		controller.executeUserCommand("clear");
		controller.executeUserCommand("add deadlined task");
		controller.executeUserCommand("change due date to~ 01 10 1949 from~ 1");	
		// check for feedback
		expectedOutput = "Updated due date successfully to 1/10/1949";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file 
		String[] expectedData04 = {"deadline", "2", "deadlined task", "1949", "10", "1", "false"};
		writeTestFile(expectedData04);
		assertEquals(true, fileCompare(taskFile, testingFile));

		closeDown();
	}


	@Test
	public void testUndoFunctions() {
		setUp();

		// test for undo adding task
		controller.executeUserCommand("add temp");
		controller.executeUserCommand("undo");
		// check for feedback
		expectedOutput = "Undo successful!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file
		String[] expectedData01 = {"empty"};
		writeTestFile(expectedData01);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for undo deleting task
		controller.executeUserCommand("add temp");
		controller.executeUserCommand("delete 1");
		controller.executeUserCommand("undo");
		// check for feedback
		expectedOutput = "Undo successful!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file
		String[] expectedData02 = {"floating", "2", "temp", "false"};
		writeTestFile(expectedData02);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for undo complete task
		controller.executeUserCommand("complete temp");
		controller.executeUserCommand("undo");
		// check for feedback
		expectedOutput = "Undo successful!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file
		String[] expectedData03 = {"floating", "2", "temp", "false"};
		writeTestFile(expectedData03);
		assertEquals(true, fileCompare(taskFile, testingFile));


		// test for undo change task
		controller.executeUserCommand("complete temp");
		controller.executeUserCommand("change due date to~ 10 11 2014 from~ 1");
		controller.executeUserCommand("change start date to~ 10 10 2014 from~ 1");
		controller.executeUserCommand("undo");
		// check for feedback
		expectedOutput = "Undo successful!";
		actualOutput = controller.getUserFeedback();
		assertEquals(expectedOutput, actualOutput);
		// check for file
		String[] expectedData04 = {"deadline", "2", "temp", "2014", "11", "10", "true"};
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
			// String array length should be at least 4
			assert expectedData.length >= 4 : expectedData;
			printer.printf(FLOATING_TASK_FORMAT, expectedData[1], expectedData[2], expectedData[3]);
			printer.println("");
			break;
		case "deadline":
			// String array length should be at least 7
			assert expectedData.length >= 7 : expectedData;
			printer.printf(DEADLINE_TASK_FORMAT, expectedData[1], expectedData[2], expectedData[3],
					expectedData[4], expectedData[5], expectedData[6]);
			printer.println("");
			break;
		case "timed":
			// String array length should be at least 10
			assert expectedData.length >= 10 : expectedData;
			printer.printf(TIMED_TASK_FORMAT, expectedData[1], expectedData[2], expectedData[3],
					expectedData[4], expectedData[5], expectedData[6], 
					expectedData[7], expectedData[8], expectedData[9]);
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
