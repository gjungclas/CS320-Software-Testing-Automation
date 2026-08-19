import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.*;

/*
 * Class used to test Contact.java
 */
public class ContactTest {
	// Declare contact object for all methods to use
	private Contact contact;
	
	@BeforeAll
	static void beginTesting() {
		System.out.println("Beginning JUnit testing...");
	}
		
	@AfterAll
	static void testingComplete() {
		System.out.println("...JUnit testing has completed.");
	}	
	
	// instantiate new contact object BEFORE each test runs
	@BeforeEach
	void testContactSetUp() {
		contact = new Contact("1234567891", "Grace", "Jungclas", "8008675309", "123 Wolverines Ct");
	}
	
	// ================================================================================================
	// CONTACT OBJECT SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Contact Ojbect Tests")
	class TestContactObject{
		@Test
		@DisplayName ("Valid Attributes - Does Not Throw Exception")
		void testContactClass() {
			// // verify object attributes were correctly set 
			assertAll(() -> assertEquals("1234567891", contact.getID()),
					() -> assertEquals("Grace", contact.getFirstName()),
					() -> assertEquals("Jungclas", contact.getLastName()),
					() -> assertEquals("8008675309", contact.getPhone()),
					() -> assertEquals("123 Wolverines Ct", contact.getAddress()));
		}
		
		@Test
      	@DisplayName("Valid Contact Object - Does Not Throw Exception")
      	void testContactObject() {
			assertDoesNotThrow( () -> {
				new Contact("9876543210", "Grace", "Jungclas", "8008675309", "123 Wolverines Ct");
			});
		}
			
		@Test
		@DisplayName("Invalid Contact Object - Null Throws Exception")
		void testContactObjectNullThrows() {
			// check if error thrown if object is null
			assertNotNull(contact);
		}
		
		@Test
		@DisplayName ("Invalid Contact Attribute - Null Throws Exception")
		void testContactAttributesNullThrows() {
			// // verify object attributes were correctly set 
			assertAll(() -> assertNotNull(contact.getID()),
					() -> assertNotNull(contact.getFirstName()),
					() -> assertNotNull(contact.getLastName()),
					() -> assertNotNull(contact.getPhone()),
					() -> assertNotNull(contact.getAddress()));
		}
	}
	
	// ================================================================================================
	// CONTACT ID SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Contact Id Tests")
	class TestContactId {
		// success cases tested within object suite
		@Test
		@DisplayName("Invalid ID - Null Throws Exception") 
		void testContactIdIsNull() {
			// test if error is correctly thrown when contactId is null
			assertThrows(IllegalArgumentException.class, ()-> {
				new Contact(null, "Grace", "Jungclas", "8008675309", "123 Wolverines Ct"); 
			});
		}
			
		@Test
		@DisplayName("Invalid ID - Exceeds 10 Characters Throws Exception") 
		void testContactIdTooLong() {
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("12345678911", "Grace", "Jungclas", "8008675309", "123 Wolverines Ct");
			});
		}
		
		@Test
		@DisplayName("Valid ID - Edge Case : < 10 Characters allowed")
		void testContactBoundary9CharsValid() {
			assertDoesNotThrow(() -> {
				new Contact("123456789", "Grace", "Jungclas", "8008675309", "123 Wolverines Ct");
			});
		}
	}
	
	// ================================================================================================
	// CONTACT FIRST NAME SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Contact First Name Tests")
	class TestContactFirstName{
		@Test
		@DisplayName("Invalid First Name - Null Throws Exception") 
		void testContactFirstNameIsNull() {
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("9876543210", null, "Jungclas", "8008675309", "123 Wolverines Ct");
			});
		}
		
		@Test
		@DisplayName("Invalid First Name - Exceeds 10 Characters Throws Exception") 
		void testContactFirstNameTooLong() {
			// declare string > 10 chars
			String fName11Chars = "GraceGracee";
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("9876543210", fName11Chars, "Jungclas", "8008675309", "123 Wolverines Ct");
			});
		}
		
		@Test
		@DisplayName ("Valid First Name - Exactly 10 chars")
		void testContactFirstNameBoundary10Chars() {
			// declare string with 10 chars
			String fName10Chars = "tencharsss";
			assertDoesNotThrow(() -> {
				new Contact("9876543210", fName10Chars, "Jungclas", "8008675309", "123 Wolverines Ct");
			});
		}
		@Test
		@DisplayName("Valid First Name - Setter updates First Name Correctly")
		void testContactFirstNameValid() {
			// declare string for updated first name
			String newFirstName = "Greg";
			// update using setter
			contact.setFirstName(newFirstName);
			// check if equal
			assertEquals(newFirstName, contact.getFirstName());
		}	
	}
	
	// ================================================================================================
	// CONTACT LAST NAME SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Contact Last Name Tests")
	class TestContactLastName{
		@Test
		@DisplayName("Invalid Last Name - Null Throws Exception") 
		void testContactLastNameIsNull() {
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("9876543210", "Grace", null, "8008675309", "123 Wolverines Ct");
			});
		}
		
		@Test
		@DisplayName("Invalid Last Name - Exceeds 10 Characters Throws Exception") 
		void testContactLastNameTooLong() {
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("9876543210", "Grace", "Jungclassss", "8008675309", "123 Wolverines Ct");
			});
		}
		
		@Test
		@DisplayName ("Valid Last Name - Exactly 10 chars")
		void testContactLastNameBoundary10Chars() {
			// 10 chars string
			String lName10Chars = "tencharsss";
			assertDoesNotThrow(() -> {
				new Contact("9876543210", "Grace", lName10Chars, "8008675309", "123 Wolverines Ct");
			});
		}
		@Test
		@DisplayName("Valid Last Name - Setter updates Last Name Correctly")
		void testContactLastNameValid() {
			// declare string for updated first name
			String newLastName = "Smith";
			// updtae3 using setter
			contact.setLastName(newLastName);
			assertEquals(newLastName, contact.getLastName());
		}	
	}
	
	// ================================================================================================
	// CONTACT PHONE SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Contact Phone Tests")
	class TestContactPhone{
		@Test
		@DisplayName("Invalid Phone - Null Throws Exception") 
		void testContactPhoneIsNull() {
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("9876543210", "Grace", "Jungclas", null, "123 Wolverines Ct");
			});
		}
		
		@Test
		@DisplayName("Invalid Phone - Exceeds 10 Characters Throws Exception") 
		void testContactPhoneTooLong() {
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("9876543210", "Grace", "Jungclas", "80086753099", "123 Wolverines Ct");
			});
		}
		
		@Test
		@DisplayName("Invalid Phone - Less than 10 Characters Throws Exception")
		void testContactPhoneTooShort() {
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("9876543210", "Grace", "Jungclas", "800867530", "123 Wolverines Ct");
			});
		}
		
		@Test
		@DisplayName("Invalid Phone - Contains Non-numeric Characters Throws Exception")
		void testContacPhoneHasLetters() {
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("9876543210", "Grace", "Jungclas", "800867530A", "123 Wolverines Ct");
			});
		}
		
		@Test
		@DisplayName ("Valid Phone - Exactly 10 chars")
		void testContactLastNameBoundary10Chars() {
			// 10 chars string
			String phone10Chars = "1234567891";
			assertDoesNotThrow(() -> {
				new Contact("1234567891", "Grace", "Jungclas", phone10Chars, "123 Wolverines Ct");
			});
		}
		@Test
		@DisplayName("Valid Phone - Setter updates Phone Correctly")
		void testContactLastNameValid() {
			// declare string for updated first name
			String newPhone = "1234567890";
			// update using setter
			contact.setPhone(newPhone);
			assertEquals(newPhone, contact.getPhone());
		}	
	}
	
	// ================================================================================================
	// CONTACT ADDRESS SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Contact Address Tests")
	class TestContactAddress{	
		@Test
		@DisplayName("Invalid Address - Null Throws Exception")
		void testContactAddressIsNull() {
			// check if throws error when address is null
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("1234567891", "Grace", "Jungclas", "8008675309", null);
			});
		}
		
		@Test
		@DisplayName("Invalid Address - Exceeds 30 Characters Throws Exception") 
		void testContactAddressTooLong() {
			// declare string for updated address
			String address32chars = "123 Wolverines Ct, Ann Arbor, MI";
			assertThrows(IllegalArgumentException.class, () -> {
				new Contact("1234567891", "Grace", "Jungclas", "8008675309", address32chars);
			});
		}
		
		@Test
		@DisplayName ("Valid Address - Exactly 30 chars")
		void testContactAddressBoundary30Chars() {
			// 30 chars string
			String address30Chars = "This is exactly 30 charsssssss";
			assertDoesNotThrow(() -> {
				new Contact("1234567891", "Grace", "Jungclas", "8008675309", address30Chars);
			});
		}
		
		@Test
		@DisplayName("Valid Address - Setter updates Address Correctly")
		void testContactAddressValid() {
			// declare string for updated first name
			String newAddress = "100 Drive Way";
			// update using setter
			contact.setAddress(newAddress);
			assertEquals(newAddress, contact.getAddress());
		}
	}
}
