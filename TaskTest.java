import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.*;

/*
 * Class used to test Task.java
 */
public class TaskTest {
	// Declare object variable for all methods to use
	private Task task;
	
	@BeforeAll
	static void beginTesting() {
		System.out.println("Beginning JUnit testing...");
	}
	
	@AfterAll
	static void testingComplete() {
		System.out.println("...JUnit testing has completed.");
	}
	
	// instantiate a new object BEFORE each test runs
	@BeforeEach
	void testTaskSetUp() {
		task = new Task("1234567891", "Module 4 Assignment", "Write unit tests to verify and validate code.");
	}
	
	// ================================================================================================
	// TASK OBJECT SUITE
	// ================================================================================================		
	@Nested
	@DisplayName("Task Object Tests")
	class TestAppointmentObject{
		@Test
		@DisplayName ("Task Class Tests")
		void testTaskClass() {
			// verify object attributes were correctly set
			assertAll(()-> assertEquals("1234567891", task.getTaskId()),
					() -> assertEquals("Module 4 Assignment", task.getName()),
					() -> assertEquals("Write unit tests to verify and validate code.", task.getDescription()));
		}
		
		@Test
		@DisplayName("Invalid Task Object - Null Throws Exception")
		void testTaskObjectNullThrows() {
			// check if error thrown if object is null
			assertNotNull(task);
		}
		
		@Test
		@DisplayName ("Invalid Task Attribute - Null Throws Exception")
		void testTaskAttributesNullThrows() {
			// verify object attributes were correctly set
			assertAll(()-> assertNotNull(task.getTaskId()),
					() -> assertNotNull(task.getName()),
					() -> assertNotNull(task.getDescription()));
		}
	}
	
	// ================================================================================================
	// TASK ID SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Task Id Tests")
	class testTaskId {
		// success cases tested within object suite
		@Test
		@DisplayName("Invalid ID - Null Throws Exception") 
		void testTaskIdIsNull() {
			// test if error is correctly thrown when taskId is null
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task(null, "Module 4 Assignment", "Write unit tests to verify and validate code.");			
			});
		}
		
		@Test
		@DisplayName("Invalid ID - Exceeds 10 Characters Throws Exception") 
		void testTaskIdTooLong() {
			// test if error is correctly thrown when taskID is > 10 characters
			// set taskId to 11 characters (edge case)
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("12345678911", "Module 4 Assignment", "Write unit tests to verify and validate code.");
			});
		}
		
		@Test
		@DisplayName("Valid ID - Edge Case : < 10 Characters allowed")
		void testTaskBoundary9CharsValid() {
			assertDoesNotThrow(() -> {
				new Task("123456789", "Module 4 Assignment", "Write unit tests to verify and validate code.");
			});
		}
	}	
	
	// ================================================================================================
	// TASK NAME SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Task Name Tests")
	class TestTaskName{
		@Test
		@DisplayName("Invalid Task Name - Null Throws Exception") 
		void testTaskNameIsNull() {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("9876543210", null, "Write unit tests to verify and validate code.");
			});
		}
		
		@Test
		@DisplayName("Invalid Task Name - Exceeds 20 Characters Throws Exception") 
		void testTaskNameIsTooLong() {
			// declare string with 21 characters (edge case)
			String name21Chars ="Module 4 Assignmenttt";
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("9876543210", name21Chars, "Write unit tests to verify and validate code.");
			});
		}
		@Test
		@DisplayName ("Valid Task Name - Exactly 20 chars")
		void testTaskNameBoundary20Chars() {
			// 20 chars string
			String name20Chars = "Module 4 Assignmentt";
			assertDoesNotThrow(() -> {
				new Task("9876543210", name20Chars, "Write unit tests to verify and validate code.");
			});
		}
		
		@Test
		@DisplayName("Valid Task Name - Setter updates Task Name Correctly")
		void testContactFirstNameValid() {
			// declare string for updated task name
			String newTaskName = "Module 3 Assignment";
			// update using setter
			task.setName(newTaskName);
			// check if equal
			assertEquals(newTaskName, task.getName());
		}
	}
	
	// ================================================================================================
	// TASK DESCRIPTION SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Task Description Tests")
	class TestTaskDescription{
		@Test
		@DisplayName("Invalid Description - Null Throws Exception") 
		void taskTestDescriptionIsNull() {
			// test if error is correctly thrown when description is null
			// set description to null
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("9876543210", "Module 4 Assignment", null);
			});
		}
		
		@Test
		@DisplayName("Invalid Description- Exceeds 50 Characters Throws Exception") 
		void taskTestDescriptionTooLong() {
			// declare string > 50 characters
			String description51Chars = "Write unit tests to verify and validate code.......";			
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				new Task("9876543210", "Module 4 Assignment", description51Chars);
			});
		}
		
		@Test
		@DisplayName("Valid Description - Setter updates Description Correctly")
		void testTaskDescriptionValid() {
			// declare string for updated description
			String newDescription = "Introduction into Junit testing.";
			// update using setter
			task.setDescription(newDescription);
			//check if equal
			assertEquals(newDescription, task.getDescription());
		}
	}
}
