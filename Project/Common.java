public class Common {

	private String command;
	
	private String input;
	
	private String description;
	
	private String newDescription;
	
	
	public ArrayList<Task> task = new ArrayList<Task>();
	
	public String getCommand(){
		return this.command;
	}
	
	public String setCommand(String command){
		this.command = command;
	}

	public String getInput(){
		return this.input;
	}
	
	public String setInput(String input){
		this.input = input;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String setDescription(String description){
		this.description = description;
	}
	
	public String getNewDescription(){
		return this.newDescription;
	}
	
	public String setNewDescription(String newDescription){
		this.newDescription = newDescription;
	}


}