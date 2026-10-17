package appointmentservice;

import java.util.Date;

/*
 * Appointment Object storing Appointment information
 * 
 * Attributes:
 * appointmentId: String (required, unique, cannot be longer than 10 characters, not null, and not updatable)
 * appointmentDate: Date (required, cannot be in the past, not null) 
 * description: String (required, cannot be longer than 50 characters, not null)
 */
public class Appointment {
		// private instance variables: apptId, apptDate, description
		private final String apptId; // immutable, set once in constructor
		private Date apptDate;
		private String description;
			
		/*
		 * Overload constructor that sets a unique ID, Date, and Description
		 * 
		 * @param apptID unique string ID (<=10 characters, non-null)
		 * @param apptDate Appointment date (non-null, not in past)
		 * @param description Description string (<= 50 chars, non-null)
		 */
			public Appointment(String apptId, Date apptDate, String description) {
				// Immediately Validate and set ID check if null or exceeds 10 characters 
				if (apptId == null || apptId.length() > 10) {
					// throw error message
					throw new IllegalArgumentException("ID is invalid. Please enter 10 characters or less.");
				}
				// only set if passes constraints above
				this.apptId = apptId;
				
				// call setters for other parameters
				setApptDate(apptDate);
				setApptDescription(description);
			}
		
		// ================================================================================================
		// Getter Methods
		// ================================================================================================	
		public String getApptId() {
			return this.apptId;
		}
		
		public Date getApptDate() {
			return this.apptDate;
		}
		
		public String getDescription() {
			return this.description;
		}
		
		
		// ================================================================================================
		// Setter Methods
		// ================================================================================================	
		
		// The appointment object shall have a required appointment Date field. 
		// The appointment Date field cannot be in the past. The appointment Date field shall not be null.
		public void setApptDate(Date apptDate) {
			// check if null  
			if (apptDate == null) {
				// throw error message
				throw new IllegalArgumentException("Appointment Date cannot be null.");
			}
			// check if in the past
			if (apptDate.before(new Date())) {
				// throw error message
				throw new IllegalArgumentException("Appointment Date cannot be in the past");
			}
			// only set if passes constraints above
			this.apptDate = apptDate;			
		}
		
		// The appointment object shall have a required description String field that cannot be longer than 50 characters.
		// The description field shall not be null.
		public void setApptDescription(String description) {
			// check if null  
			if (description == null) {
				// throw error message
				throw new IllegalArgumentException("Description cannot be null.");
			}
			// check if greater than 50 characters
			if (description.length() > 50) {
				// throw error message
				throw new IllegalArgumentException("Description must be 50 characters or less.");
			}
			// only set if passes constraints above
			this.description = description;
		}
}
