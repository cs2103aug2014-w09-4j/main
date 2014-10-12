package Project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	
	public void parse(){
		
		String raw = Common.getInput();
		
		String[] parts = Common.getInput().split(" ");
		
		String command = parts[0];
		
		Common.setCommand(command);
		
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
				
				Common.setDescription(add.trim());			
				
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
				
				
				Common.setDescription(old.trim());
				
				
				Common.setNewDescription(_new.trim());
				

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
		}
		
		
		// delete + complete
		
		if (command.equals("delete") || command.equals("complete")){
			pattern = "(delete|complete) (.*)";

			r = Pattern.compile(pattern);

			// Now create matcher object.

			m = r.matcher(raw);

			if (m.find()) {

				String task = m.group(2);

				Common.setDescription(task.trim());

				return;
			}				
		}
		
		
	}

}

