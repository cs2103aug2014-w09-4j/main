package edu.nus.comp.cs2103t.taskerino.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nus.comp.cs2103t.taskerino.common.Data;

/**

	Parser accepts a String as input and modify the Data class to be used by other class
	
	@author kester


*/

public class Parser {
	
	/**
	
		Method parse a raw user input that is of type String
	
	*/
	
	public int convert_date(String the_month){
		
		int month;
		
		switch (the_month.toLowerCase()) {
            case "january": 	month = 1;
            					break;
					
            case "february": 	month = 2;
            					break;
					
            case "march":		month = 3;
					 			break;

            case "april":		month = 4;
            					break;

            case "may":			month = 5;
					 			break;

            case "june":		month = 6;
					 			break;

            case "july":		month = 7;
					 			break;

            case "august":		month = 8;
					 			break;

            case "september":	month = 9;
					 			break;

            case "october":		month = 10;
            					break;

            case "november": 	month = 11;
					 			break;

            case "december":	month = 12;
					 			break;

            default: 			month = 0;
                     			break;
        }

		return month;
	}
	
	public void parse(){
		
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
				
				Data.setNewType("timed");
				
				Data.setFromDay(Integer.parseInt(from_day));
				
				Data.setFromMonth(from_month_int);
				
				Data.setFromYear(Integer.parseInt(from_year));
				
				Data.setToDay(Integer.parseInt(to_day));
				
				Data.setToMonth(to_month_int);
				
				Data.setToYear(Integer.parseInt(to_year));
				
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
				
				Data.setNewType("deadline");
				
				Data.setByDay(Integer.parseInt(by_day));
				
				Data.setByMonth(by_month_int);
				
				Data.setByYear(Integer.parseInt(by_year));
				
				return;		
			}
			
			
			pattern = "(add) (.*)";
			
			r = Pattern.compile(pattern);

			// Now create matcher object.
			
			m = r.matcher(raw);
			
			if (m.find()) {
	
				String add = m.group(2);
				
				Data.setDescription(add.trim());
				
				Data.setNewType("floating");
				
				return;			
			}			
		}


		// change
		
		
		if (command.equals("change")){
			pattern = "(change) (.*) to~ (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {


				String old = m.group(2);

				String _new = m.group(3);
				
				
				Data.setDescription(old.trim());
				
				
				Data.setNewDescription(_new.trim());
				

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
		
		if (command.equals("delete") || command.equals("complete")){
			pattern = "(delete|complete) (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {

				String task = m.group(2);

				Data.setDescription(task.trim());

				return;
			}				
		}
		
		
	}

}

