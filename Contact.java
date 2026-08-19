/*
 * Contact Object storing Contact information
 * 
 * Attributes:
 * contactId: String (unique, required, cannot be longer than 10 characters, not null, and not updatable)
 * firstName: String (required, cannot be longer than 10 characters, not null) 
 * lastName: String (required, cannot be longer than 10 characters, not null)
 * phone: String (required, exactly 10 digits, not null)
 * address: String (required, no longer than 30 characters, not null)
 */

public class Contact {
	// private instance variables: contactID, firstName, lastName, phone, address
	private final String contactID; // can only be set once in constructor
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
		
	/*
	 * Overload constructor that sets a unique ID, first name, lastName, phone, and address
	 * 
	 * @param contactID: unique string (<=10 characters, non-null)
	 * @param firstName: string (<=10 characters, non-null)
	 * @param lastName: string (<=10 characters, non-null)
	 * @param phone: string (exactly 10 numeric characters, non null)
	 * @param Address: string (<=30 characters, non-null
	 */
	public Contact(String contactID, String firstName, String lastName, String phone, String address) {
		// Immediately Validate and set ID check if null or exceeds 10 characters 
		if (contactID == null || contactID.length() > 10) {
			// throw error message
			throw new IllegalArgumentException("ID is invalid. Please enter 10 characters or less.");
		}
		// only set if passes constraints above
		this.contactID = contactID;
		
		// call setters for other parameters
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setAddress(address);
	}
	
	// ================================================================================================
	// Getter Methods
	// ================================================================================================	
	public String getID() {
		return this.contactID;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	// ================================================================================================
	// Setter Methods
	// ================================================================================================	
	
	// The contact object shall have a required firstName String field that cannot be longer than 10 characters.
	// The firstName field shall not be null.
	public void setFirstName(String firstName) {
		// check if null  
		if (firstName == null) {
			// throw error message
			throw new IllegalArgumentException("First name cannot be null.");
		}
		// check if greater than 10 characters
		if (firstName.length() > 10) {
			// throw error message
			throw new IllegalArgumentException("First name must be 10 characters or less.");
		}
		// only set if passes constraints above
		this.firstName = firstName;
	}
	
	// The contact object shall have a required lastName String field that cannot be longer than 10 characters. 
	// The lastName field shall not be null.
	public void setLastName(String lastName) {
		// check if null  
		if (lastName == null) {
			// throw error message
			throw new IllegalArgumentException("Last name cannot be null.");
		}
		// check if greater than 10 characters
		if (lastName.length() > 10) {
			// throw error message
			throw new IllegalArgumentException("Last name must be 10 characters or less.");
		}
		// only set if passes constraints above
		this.lastName = lastName;
	}
	
	// The contact object shall have a required phone String field that must be exactly 10 digits.
	// The phone field shall not be null.
	public void setPhone (String phone) {
		// check if null  
		if (phone == null) {
			// throw error message
			throw new IllegalArgumentException("Phone cannot be null.");
		}
		// check if exactly 10 digits
		if (phone.length() != 10) {
			// throw error message
			throw new IllegalArgumentException("Phone must be exactly 10 digits.");
		}
		// check if characters are digits
		for (int i = 0; i < phone.length(); ++i) {
			if (!Character.isDigit(phone.charAt(i))) {
				throw new IllegalArgumentException("Phone number must contain only digits.");
			}
		}
		// only set if passes constraints above		
		this.phone = phone;
	}

	// The contact object shall have a required address field that must be no longer than 30 characters. 
	// The address field shall not be null.
	public void setAddress (String address) {
		// check if null or exceeds 30 characters 
		if (address == null) {
			// throw error message
			throw new IllegalArgumentException("Address cannot be null.");
		}
		if (address.length() > 30) {
			// throw error message
			throw new IllegalArgumentException("Address must be 30 characters or less.");
		}
		// only set if passes constraints above			
		this.address = address;
	}
}
