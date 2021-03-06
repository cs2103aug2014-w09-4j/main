//@author A0113742N
package edu.nus.comp.cs2103t.taskerino.common;

import java.util.Calendar;
import java.util.Comparator;
import java.util.logging.Level;

/**
 * This class specifies the exact date and time for any events.
 */
public class DateAndTime implements Comparator<DateAndTime>, Comparable<DateAndTime> {
	private String className = "edu.nus.comp.cs2103t.taskerino.common.DateAndTime";

	private int year;
	private int month;
	private int day;

	// constructors
	/**
	 * Construct DateAndTime instance with current time.
	 */
	public DateAndTime() {
		Calendar tc = Calendar.getInstance();
		this.year = tc.get(Calendar.YEAR);
		this.month = tc.get(Calendar.MONTH) + 1;
		this.day = tc.get(Calendar.DATE);
	}
	
	/**
	 * Construct DateAndTime instance with current year
	 * and specified input month and day value.
	 * @throws Exception indicating incorrect input format
	 */
	public DateAndTime(int month, int day) throws IllegalArgumentException {
		Calendar tc = Calendar.getInstance();
		this.year = tc.get(Calendar.YEAR);
		this.setMonth(month);
		this.setDay(day);
	}

	/**
	 * Construct DateAndTime instance with specified input values.
	 * @throws Exception indicating incorrect input format
	 */
	public DateAndTime(int year, int month, int day) throws IllegalArgumentException {
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
	}

	// getters and setters
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	/**
	 * Reset month value if the input is valid.
	 * 
	 * @param int value month that is larger than or equals to 1
	 * and smaller than or equals to 12 
	 * 
	 * @throws Exception 
	 */
	public void setMonth(int month) throws IllegalArgumentException {
		if (month >= 1 && month <= 12) {
			this.month = month;
		} else {
			// throw exception
			IllegalArgumentException e = new IllegalArgumentException("Unacceptable value of month input: " + month);
			LoggerFactory.logp(Level.WARNING, className, "setMonth", e.getMessage());
			throw e;
		}
	}

	public int getDay() {
		return day;
	}
	
	/**
	 * Reset day value if the input is valid.
	 * 
	 * @param positive int day value which has upper bound value depends on month.
	 * 
	 * @throws Exception 
	 */
	public void setDay(int day) throws IllegalArgumentException {
		boolean isValidInput = false;

		switch (this.getMonth()) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				isValidInput = (day >= 1 && day <= 31);
				break;
			case 4: case 6: case 9: case 11:
				isValidInput = (day >= 1 && day <= 30);
				break;
			case 2:
				// check for leap year
				if ((year % 100 != 0 && year % 4 == 0) || (year % 400 == 0)) {
					isValidInput = (day >= 1 && day <= 29);
				} else {
					isValidInput = (day >= 1 && day <= 28);
				}
				break;
			default:
			    assert false : "Month: " + this.getMonth() + " Day: " + day;
		}

		if (isValidInput) {
			this.day = day;
		} else {
			// throw exception
			IllegalArgumentException e = new IllegalArgumentException("Unacceptable value of day input: " + day);
			LoggerFactory.logp(Level.WARNING, className, "setDay", e.getMessage());
			throw e;
		}
	}

	@Override
	public String toString() {
		return this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
	}

	
	/** 
	 * Compares two DateAndTimes for ordering.
	 * @param date1 -- first DateAndTime to be compared.
	 * @param date2 -- second DateAndTime to be compared.
	 * @return the value 0 if first DateAndTime is equal to second DateAndTime; <br>
	 * 			a value less than 0 if first DateAndTime is before second DateAndTime argument; <br>
	 * 			and a value greater than 0 if first DateAndTime is after second DateAndTime argument.
	 */	
	@Override
	public int compare(DateAndTime date1, DateAndTime date2) {
		if (date1.getYear() > date2.getYear()) {
			return 1;
		} else if (date1.getYear() < date2.getYear()) {
			return -1;
		}
		
		if (date1.getMonth() > date2.getMonth()) {
			return 1;
		} else if (date1.getMonth() < date2.getMonth()) {
			return -1;
		}
		
		if (date1.getDay() > date2.getDay()) {
			return 1;
		} else if (date1.getDay() < date2.getDay()) {
			return -1;
		}
		
		return 0;
	}

	
	/** 
	 * Compares two DateAndTimes for ordering.
	 * @param date -- DateAndTime to be compared.
	 * @return the value 0 if the argument DateAndTime is equal to this Date; <br>
	 * 			a value less than 0 if this DateAndTime is before the DateAndTime argument; <br>
	 * 			and a value greater than 0 if this DateAndTime is after the DateAndTime argument.
	 */
	@Override
	public int compareTo(DateAndTime date) {
		if (this.getYear() > date.getYear()) {
			return 1;
		} else if (this.getYear() < date.getYear()) {
			return -1;
		}
		
		if (this.getMonth() > date.getMonth()) {
			return 1;
		} else if (this.getMonth() < date.getMonth()) {
			return -1;
		}
		
		if (this.getDay() > date.getDay()) {
			return 1;
		} else if (this.getDay() < date.getDay()) {
			return -1;
		}
		
		return 0;
	}
}
