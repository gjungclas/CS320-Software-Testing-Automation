

/*
 * Task Object storing Task information
 * 
 * Attributes:
 * taskId: String (required, unique, cannot be longer than 10 characters, not null, and not updatable)
 * name: String (required, cannot be longer than 20 characters, not null)
 * description: String (required, cannot be longer than 50 characters, not null)
 */

public class Task {
	// private instance variables
	private final String taskId; // not update-able
	private String name;
	private String description;
	
	// public methods/variables
	
	/*
	 * Overload constructor that sets a unique task ID that cannot be null and is less than 10 characters in length
	 * 
	 * @params unique string task ID, string name, string description
	 */
	public Task(String taskId, String name, String description) {
		// Immediately Validate and set ID check if null or exceeds 10 characters 
		if (taskId == null || taskId.length() > 10) {
			// throw error message
			throw new IllegalArgumentException("ID is invalid. Please enter 10 characters or less.");
		}
			
		// only set if passes constraints above
		this.taskId = taskId;
					
		// call setters for other strings
		setName(name);
		setDescription(description);
	}
	
	// ================================================================================================
	// Getter Methods
	// ================================================================================================	
	public String getTaskId() {
		return this.taskId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	// ================================================================================================
	// Setter Methods
	// ================================================================================================	
	
	// Name: required, String < 20 characters, and not null.
	public void setName(String name) {
		// check if null  
		if (name == null) {
			// throw error message
			throw new IllegalArgumentException("First name cannot be null.");
		}
		// check if greater than 20 characters
		if (name.length() > 20) {
			// throw error message
			throw new IllegalArgumentException("Name must be 20 characters or less.");
		}
		// only set if passes constraints above
		this.name = name;
	}
	
	// Description: required, String < 50 characters, and not null.
	public void setDescription(String description) {
		// check if null
		if (description == null) {
			// throw error message
			throw new IllegalArgumentException("Name cannot be null.");
		}
		// check if greater than 50 characters
		if (description.length() > 50) {
			// throw error message
			throw new IllegalArgumentException("Description cannot be null.");
		}
		// only set if passes constraints above
		this.description = description;
	}
}
