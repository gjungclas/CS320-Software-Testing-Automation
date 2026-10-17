package appointmentservice;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/*
 * JUnit testing of AppointmentService.java
 */

@DisplayName("Appointment Service Test Suite")
public class AppointmentServiceTest {
	// declare object variables for all methods to use
	private Appointment appointment;
	private Date futureDate;
	private SimpleDateFormat formatter;
	private AppointmentService service;	
	
	@BeforeAll
	static void beginTesting() {
		System.out.println("Beginning JUnit testing...");
	}
		
	@AfterAll
	static void testingComplete() {
		System.out.println("...JUnit testing has completed.");
	}	
	
	@BeforeEach
	void testApptServiceSetUp() throws ParseException {
		// clear or re-instantiate objects BEFORE each test run (fresh instances for each test)
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		futureDate = formatter.parse("12/01/2026");
		appointment = new Appointment("1234567891", futureDate, "Annual Physical Examination - fasting labs prior");
		service = new AppointmentService();
	}
	
	// ================================================================================================
	// ADD APPOINTMENT SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Add Appointment Tests")
	class AddAppointmentTests{
		@Test
		@DisplayName("Add Appointment - Success")
		void testAddAppointmentSuccess() {
			// verify its added without throwing an exception
			assertDoesNotThrow(()->service.addAppointment(appointment));
		}
		
		@Test
		@DisplayName("Add Multiple Appointments - Unique IDs Success")
		void testAddMultipleAppointmentsSuccess() throws ParseException {
			// instantiate a second date and appointment
			Date secondDate = formatter.parse("12/15/2026");
			Appointment appt2 = new Appointment("9876543210", secondDate, "Follow-Up Appointment");
			
			// already tested addAppointment, so add initial appointment first 
			service.addAppointment(appointment);
			// verify error not thrown when appt2, which has a unique apptId, is passed into addAppointment method
			assertDoesNotThrow(()->service.addAppointment(appt2));
		}
		
		@Test
		@DisplayName("Add Appointment - Null Object Throws Exception")
		void testAddAppointmentIsNullThrows() {
			// check if service throws error when null appointment is passed into addAppoitnment method of service class
			assertThrows(IllegalArgumentException.class, () -> service.addAppointment(null));			
		}
		
		@Test
		@DisplayName("Add Appointment - Duplicate ID Throws Exception")
		void testDuplicateIdThrows() {
			// check if program correctly throws error for duplicative apptId
			// create another appointment with same id as appointment initialized in set up
			Appointment appt1 = new Appointment("1234567891", futureDate, "Well Child Check - update vaccines");
			
			// already tested addContact, so add initial appointment
			service.addAppointment(appointment);
			// verify error correctly thrown when appt1, which has a duplicate appointmentId, is passed into addAppointment method
			assertThrows(IllegalArgumentException.class, () -> service.addAppointment(appt1));
		}
	}
	
	// ================================================================================================
	// DELETE APPOINTMENT SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Delete Appointment Tests")
	class testDeleteAppointment{
		@Test
		@DisplayName("Delete Appointment - Success and Verified Removed")
		void testDeleteAppointmentSuccess() {
			// add appointment to service 
			service.addAppointment(appointment);
			// pass appointmentId from appointment object and verify it deletes without throwing error
			assertDoesNotThrow( () -> service.deleteAppointment(appointment.getApptId()));
			// verify error thrown when trying to delete again 
			assertThrows(IllegalArgumentException.class, ()-> service.deleteAppointment(appointment.getApptId()));
		}
		
		@Test
		@DisplayName("Delete Appointment - Null ID Throws Exception")
		void testDeleteAppointmentIdIsNullThrows() {
			// verify program throws error when appointment is null and passed into deleteAppointment method of service class
			assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment(null));
		}
		
		@Test
		@DisplayName("Delete Appointment - Non-Existent ID Throws Exception")
		void testDeleteAppointmentNotFoundThrows() {
			// verify exception thrown when trying to delete a appointmentId that was never entered
			assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("9999999999"));
		}
		
		
	}	
}
