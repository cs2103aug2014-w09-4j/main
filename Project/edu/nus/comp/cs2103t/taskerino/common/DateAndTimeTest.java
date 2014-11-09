//@author A0113742N
package edu.nus.comp.cs2103t.taskerino.common;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

/**
 * JUnit Testing class for DateAndTime class. <br>
 * Test cases consisting: <br>
 * 1. default date constructor <br>
 * 2. valid specific date input constructor <br>
 * 3. invalid specific date input constructor
 */
public class DateAndTimeTest {
	@Test
	public void testDefaultDateConstructor() {
		DateAndTime testCase00 = new DateAndTime();
		String testOutput = testCase00.toString();

		Calendar calendar = Calendar.getInstance();
		String expectedOutput = calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) 
				+ "/" + calendar.get(Calendar.YEAR);
		assertEquals("Test for default date constructor.", testOutput, expectedOutput);
	}
	
	@Test
	public void testValidSpecificDateInputConstructor() {
		String testingName = "Test for valid specific date input constructor.";
		
		try {
			// Test a valid specific date input
			DateAndTime testCase01 = new DateAndTime(2014, 10, 29);
			String testOutput = testCase01.toString();
			String expectedOutput = "29/10/2014";
			assertEquals(testingName, testOutput, expectedOutput);

			// Test for lower boundary value for a valid specific month input
			DateAndTime testCase02 = new DateAndTime(2014, 1, 15);
			testOutput = testCase02.toString();
			expectedOutput = "15/1/2014";
			assertEquals(testingName, testOutput, expectedOutput);

			// Test for lower boundary value for a valid specific month input
			DateAndTime testCase03 = new DateAndTime(2014, 01, 15);
			testOutput = testCase03.toString();
			expectedOutput = "15/1/2014";
			assertEquals(testingName, testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific month input
			DateAndTime testCase04 = new DateAndTime(2014, 12, 15);
			testOutput = testCase04.toString();
			expectedOutput = "15/12/2014";
			assertEquals(testingName, testOutput, expectedOutput);

			// Test for lower boundary value for a valid specific day input
			DateAndTime testCase05 = new DateAndTime(2014, 1, 1);
			testOutput = testCase05.toString();
			expectedOutput = "1/1/2014";
			assertEquals(testingName, testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific day input
			DateAndTime testCase06 = new DateAndTime(2014, 1, 31);
			testOutput = testCase06.toString();
			expectedOutput = "31/1/2014";
			assertEquals(testingName, testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific day input
			DateAndTime testCase07 = new DateAndTime(2014, 4, 30);
			testOutput = testCase07.toString();
			expectedOutput = "30/4/2014";
			assertEquals(testingName, testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific day input for February
			DateAndTime testCase08 = new DateAndTime(2012, 2, 29);
			testOutput = testCase08.toString();
			expectedOutput = "29/2/2012";
			assertEquals(testingName, testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific day input for February
			DateAndTime testCase09 = new DateAndTime(2014, 2, 28);
			testOutput = testCase09.toString();
			expectedOutput = "28/2/2014";
			assertEquals(testingName, testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific day input for February
			DateAndTime testCase10 = new DateAndTime(2000, 2, 29);
			testOutput = testCase10.toString();
			expectedOutput = "29/2/2000";
			assertEquals(testingName, testOutput, expectedOutput);

		} catch (IllegalArgumentException e) {
			// should not be executed
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalidSpecificDateInputConstructor() {
		String testingName = "Test for invalid specific date input constructor.";
		String expectedError = "";

		// Test for lower bound of invalid specific month input
		try {
			new DateAndTime(2014, 0, 30);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of month input: 0";
			assertEquals(testingName, e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific month input
		try {
			new DateAndTime(2014, 13, 31);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of month input: 13";
			assertEquals(testingName, e.getMessage(), expectedError);
		}

		// Test for lower bound of invalid specific day input
		try {
			new DateAndTime(2014, 10, 0);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 0";
			assertEquals(testingName, e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific day input
		try {
			new DateAndTime(2014, 2, 31);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 31";
			assertEquals(testingName, e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific day input
		try {
			new DateAndTime(2014, 10, 32);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 32";
			assertEquals(testingName, e.getMessage(), expectedError);
		}

		// Test for if both day and month are invalid specific inputs
		try {
			new DateAndTime(2014, 13, 32);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of month input: 13";
			assertEquals(testingName, e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific day input for February
		try {
			new DateAndTime(2014, 2, 30);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 30";
			assertEquals(testingName, e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific day input for February
		try {
			new DateAndTime(2013, 2, 29);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 29";
			assertEquals(testingName, e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific day input for February
		try {
			new DateAndTime(1900, 2, 29);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 29";
			assertEquals(testingName, e.getMessage(), expectedError);
		}
	}
	
	@Test
	public void testComparatorAndComparable() {
		String testingComparator = "Test for Comparator interface";
		String testingComparable = "Test for Comparable interface";
		
		try {
			// Test for two date with equal value
			DateAndTime testCase01a = new DateAndTime(2014, 10, 29);
			DateAndTime testCase01b = new DateAndTime(2014, 10, 29);
			// Test for Comparator
			boolean testOutput01a = testCase01a.compare(testCase01a, testCase01b) == 0;
			assertEquals(testingComparator, testOutput01a, true);
			// Test for Comparator
			boolean testOutput01b = testCase01a.compareTo(testCase01b) == 0;
			assertEquals(testingComparable, testOutput01b, true);
			
			
			// Test for two date with different year value
			DateAndTime testCase02a = new DateAndTime(2016, 10, 29);
			DateAndTime testCase02b = new DateAndTime(1987, 10, 29);
			// Test for Comparator
			boolean testOutput02a = testCase02a.compare(testCase02a, testCase02b) > 0;
			assertEquals(testingComparator, testOutput02a, true);
			// Test for Comparator
			boolean testOutput02b = testCase02a.compareTo(testCase02b) > 0;
			assertEquals(testingComparable, testOutput02b, true);
			
			
			// Test for two date with same year but different month value
			DateAndTime testCase03a = new DateAndTime(1992, 1, 29);
			DateAndTime testCase03b = new DateAndTime(1992, 11, 29);
			// Test for Comparator
			boolean testOutput03a = testCase03a.compare(testCase03a, testCase03b) < 0;
			assertEquals(testingComparator, testOutput03a, true);
			// Test for Comparator
			boolean testOutput03b = testCase03b.compareTo(testCase03a) > 0;
			assertEquals(testingComparable, testOutput03b, true);
			
			
			// Test for two date with same year and month but different day value
			DateAndTime testCase04a = new DateAndTime(2014, 10, 31);
			DateAndTime testCase04b = new DateAndTime(2014, 10, 01);
			// Test for Comparator
			boolean testOutput04a = testCase04a.compare(testCase04b, testCase04a) < 0;
			assertEquals(testingComparator, testOutput04a, true);
			// Test for Comparator
			boolean testOutput04b = testCase04a.compareTo(testCase04b) > 0;
			assertEquals(testingComparable, testOutput04b, true);
			
		} catch (IllegalArgumentException e) {
			// should not be executed
			e.printStackTrace();
		}
	}
}
