package Project;

public class Parser {
	
	public void parse(){
		
		String[] parts = Common.getInput().split(" ");
		
		Common.setCommand(parts[0]);
		parts[0] = "";
		
		String description = "";
		
		for (int i = 0; i < parts.length; i++){
			description = description + parts[i];
		}
		
		description = description.trim();
		
		Common.setDescription(description);
		
	}

}

