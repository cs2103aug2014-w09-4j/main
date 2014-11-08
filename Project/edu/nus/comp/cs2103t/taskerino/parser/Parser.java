//@author A0110594L
package edu.nus.comp.cs2103t.taskerino.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nus.comp.cs2103t.taskerino.common.Controller;
import edu.nus.comp.cs2103t.taskerino.common.Data;

/**

	Parser accepts a String as input and modify the Data class to be used by other class

*/

public class Parser {
	private static Controller controller = Controller.getController();
	/**
	
		Method parse a raw user input that is of type String
	
	*/
	
	public int convert_date(String the_month){
		
		int month;
		
		switch (the_month.toLowerCase()) {
            case "january": case "1": case "01": month = 1;
            					break;
					
            case "february": case "2": case "02": month = 2;
            					break;
					
            case "march": case "3": case "03": month = 3;
					 			break;

            case "april": case "4":	case "04": month = 4;
            					break;

            case "may":	 case "5":	case "05": month = 5;
					 			break;

            case "june": case "6":	case "06": month = 6;
					 			break;

            case "july": case "7":	case "07": month = 7;
					 			break;

            case "august": case "8": case "08": month = 8;
					 			break;

            case "september":  case "9": case "09": month = 9;
					 			break;

            case "october":	 case "10": month = 10;
            					break;

            case "november":   case "11": month = 11;
					 			break;

            case "december":  case "12": month = 12;
					 			break;

            default: 			month = 0;
                     			break;
        }

		return month;
	}
	
	public void parse() {
		
		String raw = Data.getInput();
		
		String[] parts = Data.getInput().split(" ");
		
		String command = parts[0];
		
		Data.setCommand(command);
		
		parts[0] = "";
		
		String pattern;
		
		Pattern r;
		
		Matcher m;
		
		if (command.equals("add")){
						
			pattern = "(add) (.*) from~ (.*) (.*) (.*) to~ (.*) (.*) (.*)";
			
			r = Pattern.compile(pattern);

			// Now create matcher object.
			
			m = r.matcher(raw);
			
			if (m.find()) {

				String add = m.group(2);
			
				String from_day = m.group(3);
			
				String from_month = m.group(4);
											
				String from_year = m.group(5);

				int from_month_int = convert_date(from_month);
			
				String to_day = m.group(6);
			
				String to_month = m.group(7);
			
				int to_month_int = convert_date(to_month);
			
				String to_year = m.group(8);
				
				Data.setDescription(add.trim());
				
				Data.setAddType("timed");
								
				Data.setFromDay(Integer.parseInt(from_day));
				
				Data.setFromMonth(from_month_int);
				
				Data.setFromYear(Integer.parseInt(from_year));
				
				Data.setToDay(Integer.parseInt(to_day));
				
				Data.setToMonth(to_month_int);
				
				Data.setToYear(Integer.parseInt(to_year));
				
				Data.setAddType("timed");
				
				return;
			}
			
			
			pattern = "(add) (.*) by~ (.*) (.*) (.*)";
			
			r = Pattern.compile(pattern);

			// Now create matcher object.
			
			m = r.matcher(raw);
			
			if (m.find()) {

				String add = m.group(2);
			
				String by_day = m.group(3);
			
				String by_month = m.group(4);
			
				int by_month_int = convert_date(by_month);
			
				String by_year = m.group(5);
				
				Data.setDescription(add.trim());
				
				Data.setAddType("deadline");
				
								
				Data.setByDay(Integer.parseInt(by_day));
				
				Data.setByMonth(by_month_int);
				
				Data.setByYear(Integer.parseInt(by_year));
				
				Data.setAddType("deadline");
				
				return;		
			}
			
			
			pattern = "(add) (.*)";
			
			r = Pattern.compile(pattern);

			// Now create matcher object.
			
			m = r.matcher(raw);
			
			if (m.find()) {
	
				String add = m.group(2);
				
				Data.setDescription(add.trim());
				
				Data.setAddType("floating");
				
				return;			
			}			
		}


		// change
		
		
		if (command.equals("change")){
			
			pattern = "(change) start date to~ (.*) (.*) (.*) from~ (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {


				String from_day = m.group(2);

				String from_month = m.group(3);

				String from_year = m.group(4);
				
				String task = m.group(5);
				
				try {
					int taskRowIndex = Integer.parseInt(task) - 1;
					Data.setDescription(Data.task.get(taskRowIndex).getTaskName());
				} catch (NumberFormatException e) {
					// task name input command
					Data.setDescription(task.trim());
				}
				
				
				int from_month_int = convert_date(from_month);
				
				Data.setFromDay(Integer.parseInt(from_day));

				Data.setFromMonth(from_month_int);

				Data.setFromYear(Integer.parseInt(from_year));
				
				Data.setChangeType("startTime");

				return;
			}
			
			
			pattern = "(change) due date to~ (.*) (.*) (.*) from~ (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {


				String to_day = m.group(2);

				String to_month = m.group(3);

				String to_year = m.group(4);
				
				String task = m.group(5);
				
				try {
					int taskRowIndex = Integer.parseInt(task) - 1;
					Data.setDescription(Data.task.get(taskRowIndex).getTaskName());
				} catch (NumberFormatException e) {
					// task name input command
					Data.setDescription(task.trim());
				}
				
				int to_month_int = convert_date(to_month);
				
				Data.setToDay(Integer.parseInt(to_day));

				Data.setToMonth(to_month_int);

				Data.setToYear(Integer.parseInt(to_year));
				
				Data.setChangeType("endTime");
				
				return;
			}
			
			
			pattern = "(change) (.*) to~ (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {


				String old = m.group(2);

				String _new = m.group(3);
				
				try {
					// task index input command
					int index = Integer.parseInt(old) - 1;
					Data.setTaskIndexInList(index);
				} catch (NumberFormatException e) {
					// task name input command
					Data.setDescription(old.trim());
				}
				
				Data.setNewDescription(_new.trim());
				
				Data.setChangeType("taskName");
				return;
			}				
		}	
		
		
		
		// search	
		
		if (command.equals("search")){
			pattern = "(search) (.*) to~ (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {

				String start_date = m.group(2);

				String end_date = m.group(3);

				return;
			}
			
			pattern = "(search) (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {

				String keywords = m.group(2);

				Data.setDescription(keywords.trim());

				return;
			}			
		}
		
		
		// delete + complete
		
		if (command.equals("delete")){
			
			
			pattern = "(delete) start date from~ (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {

				String task = m.group(2);

				Data.setDeleteType("start");
				
				return;
			}
			
			pattern = "(delete) due date from~ (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {

				String task = m.group(2);

				Data.setDeleteType("end");

				return;
			}
			
			pattern = "(delete) (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {

				String task = m.group(2);
				
				Data.setDeleteType("none");
				
				try {
					// task index input command
					int index = Integer.parseInt(task) - 1;
					Data.setTaskIndexInList(index);
				} catch (NumberFormatException e) {
					// task name input command
					Data.setDescription(task.trim());
				}
				

				return;
			}				
		}
		
		if (command.equals("goto")){
			pattern = "(goto) (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {

				String task = m.group(2);
				
				try {
					// task index input command
					int index = Integer.parseInt(task) - 1;
					Data.setTaskIndexInList(index);
				} catch (NumberFormatException e) {
					// task name input command
					Data.setDescription(task.trim());
				}
				

				return;
			}				
		}
		
		if (command.equals("help")){
			pattern = "(help) (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {

				String task = m.group(2);
				
				try {
					// task index input command
					int index = Integer.parseInt(task) - 1;
					Data.setTaskIndexInList(index);
				} catch (NumberFormatException e) {
					// task name input command
					Data.setDescription(task.trim());
				}
				

				return;
			}				
		}
		
		if (command.equals("complete")){
			pattern = "(complete) (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {

				String task = m.group(2);
				
				try {
					// task index input command
					int index = Integer.parseInt(task) - 1;
					Data.setTaskIndexInList(index);
				} catch (NumberFormatException e) {
					// task name input command
					Data.setDescription(task.trim());
				}
				

				return;
			}				
		}
		
		
	}

}

