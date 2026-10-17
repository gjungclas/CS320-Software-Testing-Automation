
import java.util.HashMap;
import java.util.Map;


/* 
 * Add, update, and delete task objects
 * 
 * The task service shall be able to add tasks with a unique ID.
 * The task service shall be able to delete tasks per task ID.
 * The task service shall be able to update task fields per task ID. The following fields are updatable:
 			* Name
 			* Description
 */

public class TaskService {
	// declare private Map to store Contact objects in memory
	// Key: String (the contactID), Value: Contact (the actual object)
	private final Map<String, Task> tasks = new HashMap<>();
	
	/* 
	 * Add tasks with a unique ID.
	 * 
	 * @param unique ID
	 */
	public void addTask(Task task) {
		// check if incoming task object is null
		if (task == null) {
			throw new IllegalArgumentException("Task must not be null");
		}
		// check if contact ID is already in map
		if (tasks.containsKey(task.getTaskId())) {
			throw new IllegalArgumentException("Task ID must be unique");
		}
		// else condition: add to map
		tasks.put(task.getTaskId(), task);
	}
	
	/*
	 * Delete tasks using unique task ID.
	 * 
	 * @param task object
	 */
	public void deleteTask(String taskId) {
		// check if incoming task object is null
		if (taskId == null) {
			throw new IllegalArgumentException("Task ID must not be null");
		}
		// check if task ID is NOT present in map
		if (!tasks.containsKey(taskId)) {
			throw new IllegalArgumentException("Task ID not found. Please try again.");
		}
		// else condition: unique ID found. ok to proceed to deletion
		tasks.remove(taskId);
	}
	
	/*
	 * Update task fields per task ID, including Name and Description
	 * 
	 * @param task object referencing unique id
	 */
	public void updateTask(String taskId, String name, String description) {
		// check if ID is null
		if (taskId == null) {
			throw new IllegalArgumentException("Task ID cannot be null.");
		}
		// check if task exists and therefore is able to be updated
		if (!tasks.containsKey(taskId)) {
			throw new IllegalArgumentException("Task ID not found.");
		}
		// find the task using the unique ID
		Task task = tasks.get(taskId);
		
		// update using setters, validation handled by setters
		task.setName(name);
		task.setDescription(description);
		}	
}
