public class Storage {
	
	private ArrayList<Task> tasks;
	private File file;
	
	public Storage() {
		this.file = new File("Tasks.txt");
		if(this.file.exists()) {
			tasks = loadTasksFromFile(file);
		} else {
			file.createNewFile();
			tasks = new ArrayList<Task>();
		}
	}
	
	private void addTask(Task newTask) {
		this.tasks.add(newTask);
		saveTasksIntoFile(file);
	}
	
	private Task removeTask(Task task) {
		this.tasks.remove(task);
		saveTasksIntoFile(file);
	}
	
	private Task getTask(Task task) {
		int indexOfTask = this.tasks.getIndexOf(task);
		return this.tasks.get(indexOfTask);
	}
	
	private ArrayList<Task> loadTasksFromFile(File fileName) {
		ArrayList<Task> temp = new ArrayList<Task>();
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
        temp = (ArrayList<Task>)ois.readObject();
        ois.close();
        fis.close();
        return temp;
	}
	
	private ArrayList<Task> saveTasksIntoFile(File fileName) {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this.tasks);
		oos.close();
		fos.close();
	}
}
