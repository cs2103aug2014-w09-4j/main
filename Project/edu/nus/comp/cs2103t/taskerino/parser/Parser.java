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
						
			pattern = "(add) (.*) from (.*) to (.*)";
			
			r = Pattern.compile(pattern);

			// Now create matcher object.
			
			m = r.matcher(raw);
			
			if (m.find()) {

				String add = m.group(2);
			
				String from = m.group(3);
			
				String to = m.group(4);
				
				return;
			}
			
			
			pattern = "(add) (.*) by (.*)";
			
			r = Pattern.compile(pattern);

			// Now create matcher object.
			
			m = r.matcher(raw);
			
			if (m.find()) {

				String add = m.group(2);
			
				String by = m.group(3);
			
				return;		
			}
			
			
			pattern = "(add) (.*)";
			
			r = Pattern.compile(pattern);

			// Now create matcher object.
			
			m = r.matcher(raw);
			
			if (m.find()) {
	
				String add = m.group(2);
				
				Data.setDescription(add.trim());			
				
				return;			
			}			
		}


		// change
		
		
		if (command.equals("change")){
			pattern = "(change) (.*) to (.*)";

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
			pattern = "(search) (.*) to (.*)";

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

