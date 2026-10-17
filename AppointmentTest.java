package appointmentservice;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Class used to test Appointment.java
 */
public class AppointmentTest {
	// Declare appointment object for all methods to use
	private Appointment appointment;
	private Date futureDate;
	private SimpleDateFormat formatter;
	
	@BeforeAll
	static void beginTesting() {
		System.out.println("Beginning JUnit testing...");
	}
		
	@AfterAll
	static void testingComplete() {
		System.out.println("...JUnit testing has completed.");
	}	
	
	// instantiate a new appointment object BEFORE each test runs 
	@BeforeEach
	void testAppointmentSetUp() throws ParseException {
		// STEP 1: Parse a string into date object before passing into constructor
		// [1A] declare formatter defining date format
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		// [1B] parse string into Date object
		futureDate = formatter.parse("12/01/2026");
		// STEP 2: pass Date object into constructor 
		appointment = new Appointment("1234567891", futureDate, "Annual Physical Examination - fasting labs prior");
	}
	
	// ================================================================================================
	// APPOINTMENT OBJECT SUITE
	// ================================================================================================		
	@Nested
	@DisplayName("Appointment Object Tests")
	class TestAppointmentObject{
		@Test
		@DisplayName ("Valid Attributes - Initial instantiation of object")
		void testAppointmentClass(){
			// verify object attributes were correctly set
			assertAll(() -> assertEquals("1234567891", appointment.getApptId()), 
					() -> assertEquals(futureDate, appointment.getApptDate()),
					() -> assertEquals("Annual Physical Examination - fasting labs prior", appointment.getDescription()));
		}
		
		@Test
		@DisplayName("Invalid Appointment Object - Null Throws Exception")
		void testAppointmentObjectNullThrows() {
			// check if error thrown if object is null
			assertNotNull(appointment);
		}
		
		@Test
		@DisplayName("Invalid Appointment Attribute - Null Throws Exception")
		public void testAppointmentAttributesNullThrows() {
			// check if error thrown if attribute is null
			assertAll(() -> assertNotNull(appointment.getApptId()),
					() -> assertNotNull(appointment.getApptDate()),
					() -> assertNotNull(appointment.getDescription()));
		}
	}
	
	// ================================================================================================
	// APPOINTMENT ID SUITE
	// ================================================================================================	
	@Nested
	@DisplayName ("Appointment ID Tests")
	class TestAppointmentID{
		// success cases tested within object suite
		@Test
		@DisplayName("Invalid ID - Null Throws Exception") 
		void testAppointmentIdIsNull() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment(null, futureDate, "Annual Physical Examination - fasting labs prior");
			});
		}
		
		@Test
		@DisplayName("Invalid ID - Exceeds 10 Characters Throws Exception")
		void testAppointmentIdTooLong() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("12345678911", futureDate, "Annual Physical Examination - fasting labs prior");
			});
		}
		
		@Test
		@DisplayName("Valid ID - Edge Case: < 10 characters allowed")
		void testAppointmentIdBoundary9CharsValid() {
			assertDoesNotThrow(() -> {
				new Appointment("123456789", futureDate, "Annual Physical Examination - fasting labs prior");
			});
		}
	}
	
	// ================================================================================================
	// APPOINTMENT DATE SUITE
	// ================================================================================================	
	@Nested
	@DisplayName ("Appointment Date Tests")
	class TestApptDate{
		@Test
		@DisplayName("Invalid Appt Date - Null Throws Exception") 
		void testAppointmentApptDateIsNull() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("9876543210", null, "Annual Physical Examination - fasting labs prior");
			});
		}
			
		@Test
		@DisplayName("Invalid Appt Date - Past Throws Exception")
		void testAppointmentDateInPast() throws ParseException {	
			// create instance of past date
			Date pastDate = formatter.parse("01/01/2020");
			// Assert IllegalArgumentException is thrown when date is in past
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("9876543210", pastDate, "Annual Physical Examination - fasting labs prior");
			});
		}
		
		@Test
		@DisplayName("Valid Date - Setter Updates Date Correctly")
		void testAppointmentSetDateValid() throws ParseException {
			// Step 1: instantiate new future appt date
			Date newFutureDate = formatter.parse("12/31/2026");
			// Step 2: use setApptDate to change appt date
			appointment.setApptDate(newFutureDate);
			// verify setter updates
			assertEquals(newFutureDate, appointment.getApptDate());
		}
	}
	
	// ================================================================================================
	// APPOINTMENT DESCRIPTION SUITE
	// ================================================================================================	
	@Nested
	@DisplayName ("Appointment Description Tests")
	class TestAppointmentDescription{
		@Test
		@DisplayName("Invalid Description - Null Throws Exception") 
		void testAppointmentDescriptionIsNull() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("9876543210", futureDate, null);
			});
		}
		
		@Test
		@DisplayName("Invalid Description - Too long (51 chars) Throws Exception")
		void testAppointmentDescriptionTooLong() {
			// declare 51 character string
			String desc51chars = "123456789012345678901234567890123456789012345678901";
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Appointment("9876543210", futureDate, desc51chars);
			});
		}
		
		@Test
		@DisplayName("Valid Description - Exactly 50 chars")
		void testAppointmentDescriptionBoundary50Chars() {
			// 50 chars string
			String desc50Chars = "12345678901234567890123456789012345678901234567890";
			assertDoesNotThrow(() -> {
				new Appointment("9876543210", futureDate, desc50Chars);
			});
		}
		
		@Test
		@DisplayName("Valid Description - Setter updates Description Correctly")
		void testAppointmentDescriptionValid() {
			// declare string for updated description
			String newDescription = "Updated description text under 50 chars."; // 40 characters
			// update using setter
			appointment.setApptDescription(newDescription);
			// check if equal
			assertEquals(newDescription, appointment.getDescription());
		}
	}
}
