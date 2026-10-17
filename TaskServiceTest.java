import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.*;

/*
 * JUnit testing of TaskService.java
 */

@DisplayName("Task Service Test Suite")
public class TaskServiceTest {
	// declare object variables for all methods to use
	private Task task;
	private TaskService service;
		
	@BeforeAll
	static void beginTesting() {
		System.out.println("Beginning JUnit testing...");
	}
		
	@AfterAll
	static void testingComplete() {
		System.out.println("...JUnit testing has completed.");
	}	
		
	@BeforeEach
	void testTaskServiceSetUp() {
		// clear or re-instantiate objects BEFORE each test run (fresh instances for each test)
		service = new TaskService();
		task = new Task("1234567891", "Module 4 Assignment", "Write unit tests to verify and validate code.");
		}
	
	@Test
	@DisplayName("Task Service Instantiation Check")
	void testTaskServiceInstantiation() {
		assertNotNull(service);
		assertNotNull(task);
	}
	// ================================================================================================
	// ADD TASK SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Add Task Tests")
	class AddTaskTests{
		@Test
		@DisplayName("Add Task - Success")
		void testAddTaskSuccess() {
			// verify its added without throwing an exception
			assertDoesNotThrow( () -> service.addTask(task));
		}
		
		@Test
		@DisplayName("Add Multiple Task - Unique IDs Success")
		void testAddMultipleTasksSuccess() {
			// instantiate a second task and unique ID
			Task task2 = new Task("9876543210", "Module 3 Assignment", "Introduction into Junit testing.");
			
			// already tested addTask, so add initial task first
			service.addTask(task);
			// verify error not thrown when task2, which has a unique taskId, is passed into addTask method
			assertDoesNotThrow( () -> service.addTask(task2));
		}
		
		@Test
		@DisplayName("Add Task - Null Object Throws Exception")
		void testAddTaskIsNullThrows() {
			// check if program throws error when task is null and passed into addTask method of service class
			assertThrows(IllegalArgumentException.class, () -> service.addTask(null));
		}
		
		@Test
		@DisplayName("Add Task - Duplicate ID Throws Exception")
		void testDuplicateIdThrows() {
			// check if program correctly throws error for duplicative taskIds
			// create another task with same id as task (initialized in set-up)
			Task task1 = new Task("1234567891", "Module 3 Assignment", "Introduction into unit testing.");
			
			// already tested addTask, so add initial task first 
			service.addTask(task);
			// verify error correctly thrown when task1, which has a duplicate taskId, is passed into addTask method
			assertThrows(IllegalArgumentException.class, () -> service.addTask(task1));
		}
	}
	
	// ================================================================================================
	// DELETE TASK SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Delete Task Tests")
	class testDeleteTask{
		@Test
		@DisplayName("Delete Task - Success and Verified Removed")
		void testDeleteTaskSuccess() {
			// add task to service
			service.addTask(task);
			// pass taskId from task object and verify it deletes without throwing error
			assertDoesNotThrow( () -> service.deleteTask(task.getTaskId()));
			// verify error thrown when trying to delete again
			assertThrows(IllegalArgumentException.class, () -> service.deleteTask(task.getTaskId()));
		}
				
		@Test
		@DisplayName("Delete Task - Null ID Throws Exception")
		void testDeleteTaskIdIsNullThrows() {
			// verify program throws error when task is null and passed into deleteTask method of service class
			assertThrows(IllegalArgumentException.class, () -> service.deleteTask(null));
		}
		
		@Test 
		@DisplayName("Delete Task - Non-Existent ID Throws Exception")
		void testDeleteTaskNotFoundThrows() {
			// verify exception thrown when trying to delete a taskId that was never entered
			assertThrows(IllegalArgumentException.class, () -> service.deleteTask("9999999999"));
		}	
	}
	
	// ================================================================================================
	// UPDATE TASK SUITE
	// ================================================================================================	
	@Nested
	@DisplayName("Update Task Tests")
	class testUpdateTask {
		@Test
		@DisplayName("Update Task - Success and verified Updated")
		void testUpdateTaskSuccess() {
			// add task to service 
			service.addTask(task);
			// pass taskId from task object and verify it updates name and description without throwing error
			assertDoesNotThrow( () -> {
				service.updateTask("1234567891", "Module 3 Assignment", "Introduction into unit testing.");
			});
			// verify name and description have been updated
			assertAll(() -> assertEquals("Module 3 Assignment", task.getName()),
					() -> assertEquals("Introduction into unit testing.", task.getDescription()));
		}
		
		@Test
		@DisplayName("Update Task  - Null Task ID Throws Exception")
		void testUpdateTaskIdNullThrows() {
			// verify program throws error when task is null and passed into updateTask method of service class
			assertThrows(IllegalArgumentException.class, () -> service.updateTask(null, "Module 4 Assignment", "Write unit tests to verify and validate code."));
		}
		
		@Test
		@DisplayName("Update Task - Non-Existent ID Throws Exception")
		void tesUpdateTaskNotFoundThrows() {
			service.addTask(task);
			// verify exception thrown when trying to update a taskId that was never entered
			assertThrows(IllegalArgumentException.class, () -> service.updateTask("9999999999", "Module 4 Assignment", "Write unit tests to verify and validate code."));
		}
	}
}
	
