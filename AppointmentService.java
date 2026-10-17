package appointmentservice;

import java.util.HashMap;
import java.util.Map;

/**
 *  Add and delete appointment objects.
 *  
 *  The appointment service shall be able to add appointments with a unique appointmentId.
 *  The appointment service shall be able to delete appointments per appointmentId.
 */
public class AppointmentService {
	// declare private Map to store Appointment objects in memory
	// Key: String (the contactID), Value: Appointment (the actual object)
	private final Map<String, Appointment> appointments = new HashMap<>();
		
	/* 
	* Add appointments with a unique ID.
	* 
	* @param unique ID
	*/
	public void addAppointment(Appointment appointment) {
		// check if incoming appointment object is null
		if (appointment == null) {
			throw new IllegalArgumentException("Appointment must not be null");
		}
		// check if appointment ID is already in map
		if (appointments.containsKey(appointment.getApptId())) {
			throw new IllegalArgumentException("Appointment ID must be unique");
		}
		// else condition: add to map
		appointments.put(appointment.getApptId(), appointment);
	}
	
	/*
	 * Delete appointments using unique appointment ID.
	 * 
	 * @param appointment object
	 */
	public void deleteAppointment(String apptId) {
		// check if incoming appointment object is null
		if (apptId == null) {
			throw new IllegalArgumentException("Appointment ID must not be null");
		}
		// check if appointment ID is NOT present in map
		if (!appointments.containsKey(apptId)) {
			throw new IllegalArgumentException("Appointment ID not found. Please try again.");
		}
		// else condition: unique ID found, proceed to deletion
		appointments.remove(apptId);
	}
}
