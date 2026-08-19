import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/*
 * JUnit testing of ContactService.java
 */

@DisplayName("Contact Service Test Suite")
public class ContactServiceTest {
	// declare object variables for all methods to use
	private Contact contact;
	private ContactService service;
	
	@BeforeAll
	static void beginTesting() {
		System.out.println("Beginning JUnit testing...");
	}
		
	@AfterAll
	static void testingComplete() {
		System.out.println("...JUnit testing has completed.");
	}	
	
	@BeforeEach
	void testContactServiceSetUp() {
		// clear or re-instantiate objects BEFORE each test run (fresh instances for each test)
		service = new ContactService();
		contact = new Contact("1234567891", "Grace", "Jungclas", "8008675309", "123 Wolverines Ct");
	}

	// ================================================================================================
	// ADD CONTACT SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Add Contact Tests")
	class AddContactTests{		
		@Test
		@DisplayName("Add Contact Valid- Does Not Throw")
		void testContactServiceAddContactSuccess() {
			// verify its added without throwing an exception
			assertDoesNotThrow(() -> service.addContact(contact));
		}
		
		@Test
		@DisplayName("Add Multiple Contacts - Unique IDs Success")
		void testAddMultipleContactsSuccess() {
			// instantiate a second contact and unique ID
			Contact contact2 = new Contact("9876543210", "John", "Smith", "8007345916", "123 Street Dr");
			
			// already tested addContact, so add initial contact first 
			service.addContact(contact);
			// verify error not thrown when contact2, which has a unique contactId, is passed into addContact method
			assertDoesNotThrow( () -> service.addContact(contact2));
		}
		
		@Test
		@DisplayName("Add Contact - Null Object Throws Exception")
		void testAddContactIsNullThrows( ) {
			// check if service throws error when null contact is passed into addContact method of service class
			assertThrows(IllegalArgumentException.class, ()-> service.addContact(null));
		}
		
		@Test
		@DisplayName("Add Contact - Duplicate ID Throws Exception")
		void testContactServiceDuplicateIDThrows() {
			// create another contact with same id as contact initialized in set up
			Contact contact1 = new Contact("1234567891", "John", "Smith", "8007345916", "123 Street Dr");
			
			// add first contact to Contacts
			service.addContact(contact);
			// check if program will allow duplicative IDs
			assertThrows(IllegalArgumentException.class, () -> service.addContact(contact1));
		}
	}
	
	// ================================================================================================
	// DELETE CONTACT SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Delete Contact Tests")
	class testDeleteContact{
		@Test
		@DisplayName("Delete Contact - Success and Verified Removed")
		void testDeleteContactSuccess() {
			// add appointment to service
			service.addContact(contact);
			// pass contactId from contact object and verify it deletes without throwing error
			assertDoesNotThrow( () -> service.deleteContact(contact.getID()));
			// verify error thrown when trying to delete again
			assertThrows(IllegalArgumentException.class, () -> service.deleteContact(contact.getID()));
		}
		
		@Test
		@DisplayName("Delete Contact - Null ID Throws Exception")
		void testDeleteContactIdIsNullThrows() {			
			// verify program throws error when contact is null and passed into deleteContact method of service class
			assertThrows(IllegalArgumentException.class, () -> service.deleteContact(null));
		}
		
		@Test
		@DisplayName("Delete Contact - Non-Existent ID Throws Exception")
		void testDeleteContactNotFoundThrows() {
			// verify exception thrown when trying to delete a appointmentId that was never entered
			assertThrows(IllegalArgumentException.class, () -> service.deleteContact("9999999999"));
		}
	}
	
	// ================================================================================================
	// UPDATE CONTACT SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Update Contact Tests")
	class UpdateContactTests{
		@Test
		@DisplayName("Update Contact - Does Not Throw and Equals")
		void testUpdateContactSuccess() {
			// add contact to service
			service.addContact(contact);
			// pass contactID from contact object and verify it updates firstName, lastName, phone, and address without throwing error
			assertDoesNotThrow( () -> service.updateContact("1234567891", "John", "Smith", "8007345916", "123 Street Dr"));
			// verify firstName, lastName, phone, and address have been updated
			assertAll(() -> assertEquals("John", contact.getFirstName()), 
					() -> assertEquals("Smith", contact.getLastName()),
					() -> assertEquals("8007345916", contact.getPhone()),
					()-> assertEquals("123 Street Dr", contact.getAddress()));
		}
	
		@Test
		@DisplayName("Update Contact ID  - Null Throws Exception")
		void testUpdateContactNullIdThrows() {
			// // verify program throws error when task is null and passed into updateTask method of service class
			assertThrows(IllegalArgumentException.class, () -> service.updateContact(null, "John", "Smith", "8007345916", "123 Street Dr"));
		}
		
		@Test
		@DisplayName("Update Contact - Non-Existent ID Throws Exception")
		void testeUpdateContactNotFound() {
			// Try to update contact that does not exist
			assertThrows(IllegalArgumentException.class, () -> service.updateContact("1000000000", "John", "Smith", "8007345916", "123 Street Dr"));
		}
	}
}
