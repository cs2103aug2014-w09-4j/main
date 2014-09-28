import java.util.*;

public class Parser(){
	
	public void parse(){
		
		String[] parts = string.split(" ");
		
		common.setCommand(parts[0]);
		parts[0] = "";
		
		String description = "";
		
		for (int i = 0; i < parts.length; i++){
			description = description + parts[i];
		}
		
		description = description.trim();
		
		common.setDescription(description);
		
	}


}

