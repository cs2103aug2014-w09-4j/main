package Project;

public class Parser {
	
	public void parse(){
		
		String[] parts = Data.getInput().split(" ");
		
		String command = parts[0];
		
		Data.setCommand(command);
		
		parts[0] = "";
		
		if (command.equals("change")){
			for (int i = 0; i < parts.length; i++){			
				if (parts[i].equals("to")){
					parts[i] = "";
					
					String newDescription = "";
					
					for (int j = i + 1; j < parts.length; j++){
						newDescription = newDescription + parts[j];					
					}
					
					Data.setNewDescription(newDescription);
					
					String description = "";
					
					for (int k = 0; k < i; k++){
						
						description = description + parts[k];
						
					}
					
					description = description.trim();
					
					Data.setDescription(description);

					break;		
					
				}
			}
			
		} else {
			
			
			String description = "";


			for (int i = 0; i < parts.length; i++){
				description = description + parts[i];
			}

			description = description.trim();

			Data.setDescription(description);
		}
		
		
	}

}

