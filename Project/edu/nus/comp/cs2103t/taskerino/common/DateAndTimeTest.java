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
 * JUnit Testing class for DateAndTime class. 
 * Test cases consisting: 
 * 1. default date constructor
 * 2. valid specific date input constructor
 * 3. invalid specific date input constructor
 * 
 * @author Wang YanHao
 *
 */
public class DateAndTimeTest {
	@Test
	public void testDefaultDateConstructor() {
		DateAndTime testCase00 = new DateAndTime();
		String testOutput = testCase00.toString();
		String expectedOutput = "21/10/2014";
		assertEquals("Test for default date constructor.", testOutput, expectedOutput);
	}
	
	@Test
	public void testValidSpecificDateInputConstructor() {
		try {
			// Test a valid specific date input
			DateAndTime testCase01 = new DateAndTime(2014, 10, 15);
			String testOutput = testCase01.toString();
			String expectedOutput = "15/10/2014";
			assertEquals("Test for valid specific date input constructor.", testOutput, expectedOutput);

			// Test for lower boundary value for a valid specific month input
			DateAndTime testCase02 = new DateAndTime(2014, 1, 15);
			testOutput = testCase02.toString();
			expectedOutput = "15/1/2014";
			assertEquals("Test for valid specific date input constructor.", testOutput, expectedOutput);

			// Test for lower boundary value for a valid specific month input
			DateAndTime testCase03 = new DateAndTime(2014, 01, 15);
			testOutput = testCase03.toString();
			expectedOutput = "15/1/2014";
			assertEquals("Test for valid specific date input constructor.", testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific month input
			DateAndTime testCase04 = new DateAndTime(2014, 12, 15);
			testOutput = testCase04.toString();
			expectedOutput = "15/12/2014";
			assertEquals("Test for valid specific date input constructor.", testOutput, expectedOutput);

			// Test for lower boundary value for a valid specific day input
			DateAndTime testCase05 = new DateAndTime(2014, 1, 1);
			testOutput = testCase05.toString();
			expectedOutput = "1/1/2014";
			assertEquals("Test for valid specific date input constructor.", testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific day input
			DateAndTime testCase06 = new DateAndTime(2014, 1, 31);
			testOutput = testCase06.toString();
			expectedOutput = "31/1/2014";
			assertEquals("Test for valid specific date input constructor.", testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific day input
			DateAndTime testCase07 = new DateAndTime(2014, 4, 30);
			testOutput = testCase07.toString();
			expectedOutput = "30/4/2014";
			assertEquals("Test for valid specific date input constructor.", testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific day input for February
			DateAndTime testCase08 = new DateAndTime(2014, 2, 29);
			testOutput = testCase08.toString();
			expectedOutput = "29/2/2014";
			assertEquals("Test for valid specific date input constructor.", testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific day input for February
			DateAndTime testCase09 = new DateAndTime(2015, 2, 28);
			testOutput = testCase09.toString();
			expectedOutput = "28/2/2014";
			assertEquals("Test for valid specific date input constructor.", testOutput, expectedOutput);

			// Test for upper boundary value for a valid specific day input for February
			DateAndTime testCase10 = new DateAndTime(2000, 2, 29);
			testOutput = testCase10.toString();
			expectedOutput = "29/2/2000";
			assertEquals("Test for valid specific date input constructor.", testOutput, expectedOutput);

		} catch (IllegalArgumentException e) {
			// should not be executed
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalidSpecificDateInputConstructor() {
		String expectedError = "";

		// Test for lower bound of invalid specific month input
		try {
			new DateAndTime(2014, 0, 30);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of month input: 0";
			assertEquals("Test for invalid specific date input constructor.", e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific month input
		try {
			new DateAndTime(2014, 13, 31);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of month input: 13";
			assertEquals("Test for invalid specific date input constructor.", e.getMessage(), expectedError);
		}

		// Test for lower bound of invalid specific day input
		try {
			new DateAndTime(2014, 10, 0);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 0";
			assertEquals("Test for invalid specific date input constructor.", e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific day input
		try {
			new DateAndTime(2014, 2, 31);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 31";
			assertEquals("Test for invalid specific date input constructor.", e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific day input
		try {
			new DateAndTime(2014, 10, 32);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 32";
			assertEquals("Test for invalid specific date input constructor.", e.getMessage(), expectedError);
		}

		// Test for if both day and month are invalid specific inputs
		try {
			new DateAndTime(2014, 13, 32);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of month input: 13";
			assertEquals("Test for invalid specific date input constructor.", e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific day input for February
		try {
			new DateAndTime(2014, 2, 30);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 30";
			assertEquals("Test for invalid specific date input constructor.", e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific day input for February
		try {
			new DateAndTime(2013, 2, 29);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 29";
			assertEquals("Test for invalid specific date input constructor.", e.getMessage(), expectedError);
		}

		// Test for upper bound of invalid specific day input for February
		try {
			new DateAndTime(1900, 2, 29);
		} catch (IllegalArgumentException e) {
			expectedError = "Unacceptable value of day input: 29";
			assertEquals("Test for invalid specific date input constructor.", e.getMessage(), expectedError);
		}
	}
}
