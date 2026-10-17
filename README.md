# Software Testing and Quality Assurance Portfolio
**Course:** CS 320: Software Testing and Automation  
**Focus:** Mobile Application Back-End Services & Unit Testing  

## Project Overview

This repository showcases back-end service development and automated unit testing workflows developed for Grand Strand Systems. The included codebase and documentation demonstrate the application of unit testing, equivalence partitioning, boundary value analysis, exception validation, and coverage verification within a Java environment using JUnit 5 and EclEmma.

### Repository Files
* **`Contact.java`**: Domain model implementing strict data validation for contact attributes (10-character unique ID, 10-character maximum names, exact 10-digit phone number, and 30-character maximum address).
* **`ContactService.java`**: In-memory service layer managing contact objects through internal map storage, duplicate ID prevention, deletion logic, and field updates.
* **`ContactTest.java`**: JUnit 5 test suite validating boundary constraints, constructor logic, and field mutators.
* **`ContactServiceTest.java`**: JUnit 5 nested test suite verifying service-level operations, exception propagation, and memory map behavior.
* **`Project 2 Summary and Reflections Report.docx`**: The formal report submitted to leadership analyzing unit testing methodologies, code coverage metrics, reviewer bias mitigation, and strategies for avoiding technical debt.
* **`Project 2 Summary and Reflections Report.pdf`**: The PDF version of the summary and reflections report for viewing directly in GitHub.

---

## Technical Soundness and Efficiency Highlights

### 1. State Mutation and Assertion Verification
To verify technical soundness, test cases assert that state mutations persist accurately in memory without throwing unhandled exceptions:

```java
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
```

### 2. Isolated Exception Assertion
Negative test paths verify that invalid actions (such as attempting to insert duplicate identifiers) immediately throw an `IllegalArgumentException` via targeted lambda assertions:

```java
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
```

### 3. Test Harness Efficiency and Lifecycle Management
To avoid test pollution and eliminate redundant instantiation overhead across test cases, test suites centralize fixture creation within `@BeforeEach` setup methods:

```java
@BeforeEach
void testTaskServiceSetUp() {
    // clear or re-instantiate objects BEFORE each test run (fresh instances for each test)
    service = new TaskService();
    task = new Task("1234567891", "Module 4 Assignment", "Write unit tests to verify and validate code.");
}
```

## Professional Reflections

### How can I ensure that my code, program, or software is functional and secure?
Ensuring that software is functional and secure requires an uncompromising, test-driven approach that begins at the data model level[cite: 1]. Functional reliability is verified by combining Equivalence Partitioning and Boundary Value Analysis to rigorously exercise both nominal execution paths and extreme edge boundaries[cite: 1]. Security and data integrity are enforced by incorporating strict defensive programming inside domain constructors and mutator methods—guaranteeing that `null` references, malformed inputs, or out-of-bounds strings fail fast by throwing an `IllegalArgumentException` before invalid data can mutate application state[cite: 1]. Furthermore, pairing automated unit tests with coverage tools like EclEmma ensures that 100% of conditional branches, duplicate checks, and exception handlers are systematically evaluated, preventing unexpected runtime escapements in production[cite: 1].

### How do I interpret user needs and incorporate them into a program?
Interpreting user needs requires analyzing functional requirement specifications and translating business rules into explicit, enforceable software constraints[cite: 1]. Instead of relying on developer assumptions or happy-path interactions, requirements must be decomposed into exact structural limits—such as fixed string length thresholds, non-null guarantees, and unique key requirements[cite: 1]. These business constraints serve as the specification contract implemented directly within class constructors, setter methods, and service validation routines[cite: 1]. By authoring test suites that map directly back to these requirements, developers can verify that each user requirement is programmatically fulfilled and consistently enforced across all service layers[cite: 1].

### How do I approach designing software?
My approach to designing software centers on modularity, clear separation of concerns, and defensive architecture[cite: 1]. Applications are structured by decoupling domain data models (e.g., `Contact`) from the service coordination layer (e.g., `ContactService`), allowing state management and business logic to evolve independently[cite: 1]. In addition, I build software with testability in mind from the ground up, utilizing JUnit 5 lifecycle annotations (`@BeforeEach`, `@Nested`) to maintain clean, isolated, and repeatable test execution[cite: 1]. This design approach limits reviewer bias by separating developer logic from testing assertions, curbs technical debt through continuous refactoring, and ensures the codebase remains maintainable, scalable, and resilient over time[cite: 1].
