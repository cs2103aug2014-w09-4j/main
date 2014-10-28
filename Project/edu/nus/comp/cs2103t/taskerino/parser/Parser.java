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
		
		int month = 0;
		
		switch (month) {
            case 1:  the_month = "january";
                     month = 1;
					 break;
					
            case 2:  the_month = "february";
                     month = 2;
					 break;
					
            case 3:  the_month = "march";
                     month = 3;
					 break;

            case 4:  the_month = "april";
                     month = 4;
					 break;

            case 5:  the_month = "may";
                     month = 5;
					 break;

            case 6:  the_month = "june";
                     month = 6;
					 break;

            case 7:  the_month = "july";
                     month = 7;
					 break;

            case 8:  the_month = "august";
                     month = 8;
					 break;

            case 9:  the_month = "september";
                     month = 9;
					 break;

            case 10: the_month = "october";
                     month = 10;
					 break;

            case 11: the_month = "november";
                     month = 11;
					 break;

            case 12: the_month = "december";
                     month = 12;
					 break;

            default: the_month = "Invalid month";
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
			
			pattern = "(change) starting time to~ (.*) (.*) (.*) from~ (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {


				String from_day = m.group(2);

				String from_month = m.group(3);

				String from_year = m.group(4);
				
				String task = m.group(5);
				
				int from_month_int = convert_date(from_month);
				
				Data.setFromDay(Integer.parseInt(from_day));

				Data.setFromMonth(from_month_int);

				Data.setFromYear(Integer.parseInt(from_year));
				
				Data.setDescription(task.trim());
				

				return;
			}
			
			
			pattern = "(change) ending time to~ (.*) (.*) (.*) from~ (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {


				String to_day = m.group(2);

				String to_month = m.group(3);

				String to_year = m.group(4);
				
				String task = m.group(5);
				
				int to_month_int = convert_date(to_month);
				
				Data.setToDay(Integer.parseInt(to_day));

				Data.setToMonth(to_month_int);

				Data.setToYear(Integer.parseInt(to_year));
				
				Data.setDescription(task.trim());
				

				return;
			}
			
			
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

