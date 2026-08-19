
import java.util.Map;
import java.util.HashMap;


/* 
 * Add, update, and delete contact objects
 * 
 * The contact service shall be able to add contacts with unique ID.
 * The contact service shall be able to delete contacts per contactId.
 * The contact service shall be able to update contact fields per contactId. The following fields are updatable:
 		* firstName
 		* lastName
 		* PhoneNumber
 		* Address
 */
public class ContactService {
	// declare private Map to store Contact objects in memory
	// Key: String (the contactID), Value: Contact (the actual object)
	private final Map<String, Contact> contacts = new HashMap<>();
	
	/* 
	 * Add contacts with a unique ID.
	 * 
	 * @param unique ID
	 */
	public void addContact(Contact contact) {
		// check if incoming contact object is null
		if (contact == null) {
			throw new IllegalArgumentException("Contact must not be null");
		}
		// check if contact ID is already in map
		if (contacts.containsKey(contact.getID())) {
			throw new IllegalArgumentException("Contact ID must be unique");
		}
		// else condition: add to map
		contacts.put(contact.getID(), contact);
	}
	
	/*
	 * Delete contacts using unique contact ID.
	 * 
	 * @param contacg object
	 */
	public void deleteContact(String contactID) {
		// check if incoming contact object is null
		if (contactID == null) {
			throw new IllegalArgumentException("Contact ID must not be null");
		}
		// check if contact ID is NOT present in map
		if (!contacts.containsKey(contactID)) {
			throw new IllegalArgumentException("Contact ID not found. Please try again.");
		}
		// else condition: unique ID found. ok to proceed to deletion
		contacts.remove(contactID);
	}
	
	/*
	 * Update contact fields per contact ID, including firstName, lastName, phone, and address
	 * 
	 * @param contact object referencing unique id
	 */
	public void updateContact(String contactID, String firstName, String lastName, String phone, String address) {
		// check if ID is null
		if (contactID == null) {
			throw new IllegalArgumentException("Contact ID cannot be null.");
		}
		// check if contact exists and therefore is able to be updated
		if (!contacts.containsKey(contactID)) {
			throw new IllegalArgumentException("Contact ID not found.");
		}
		// find the contact using the unique ID
		Contact contact = contacts.get(contactID);
		
		// update using setters, validation handled by setters
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setPhone(phone);
		contact.setAddress(address);
		}	
}
