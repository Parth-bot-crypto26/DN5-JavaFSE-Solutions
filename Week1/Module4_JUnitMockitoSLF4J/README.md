# Module 4 — TDD using JUnit5 & Mockito, SLF4J Logging
## DN 5.0 Deep Skilling - Java FSE

---

## ✅ Mandatory Exercises
- **JUnit Exercise 3:** Assertions in JUnit
- **JUnit Exercise 4:** Arrange-Act-Assert (AAA) Pattern, Test Fixtures, Setup & Teardown
- **Mockito Exercise 1:** Mocking and Stubbing
- **Mockito Exercise 2:** Verifying Interactions
- **SLF4J Exercise 1:** Logging Error Messages and Warning Levels

## 📚 All Exercises

### JUnit 5
| # | Topic | File |
|---|-------|------|
| 1 | Setting Up JUnit | `junit/Exercise1_SettingUpJUnitTest.java` |
| 2 | Writing Basic JUnit Tests | `junit/Exercise2_BasicTestsTest.java` |
| 3 ⭐ | Assertions in JUnit | `junit/Exercise3_AssertionsTest.java` |
| 4 ⭐ | AAA Pattern, Fixtures, Setup/Teardown | `junit/Exercise4_AAAPatternTest.java` |

### Mockito
| # | Topic | File |
|---|-------|------|
| 1 ⭐ | Mocking and Stubbing | `mockito/Exercise1_MockingStubbingTest.java` |
| 2 ⭐ | Verifying Interactions | `mockito/Exercise2_VerifyingInteractionsTest.java` |
| 3 | Argument Matching | `mockito/Exercise3_ArgumentMatchingTest.java` |
| 4 | Handling Void Methods | `mockito/Exercise4_VoidMethodsTest.java` |
| 5 | Multiple Returns on Consecutive Calls | `mockito/Exercise5_MultipleReturnsTest.java` |
| 6 | Verifying Interaction Order | `mockito/Exercise6_InteractionOrderTest.java` |
| 7 | Void Methods that Throw Exceptions | `mockito/Exercise7_VoidMethodsExceptionTest.java` |

### SLF4J Logging
| # | Topic | File |
|---|-------|------|
| 1 ⭐ | Error/Warning level logging | `slf4j/LoggingExample.java` |
| 2 | Parameterized Logging | `slf4j/ParameterizedLoggingExample.java` |
| 3 | Different Appenders (console + file) | `slf4j/AppenderExample.java` |

---

## 🚀 How to Run

Unlike Modules 1-3, JUnit 5 and Mockito are real third-party libraries, so this
module is a proper **Maven** project instead of plain `javac`/`java`.

### Prerequisites
- JDK 17+
- Maven 3.8+

### Run every test (all exercises)
```bash
cd Module4_JUnitMockitoSLF4J
mvn test
```

### Run just the mandatory tests
```bash
mvn -Dtest=Exercise3_AssertionsTest,Exercise4_AAAPatternTest,Exercise1_MockingStubbingTest,Exercise2_VerifyingInteractionsTest test
```

### Run the SLF4J logging demos (they have a `main` method, not a test)
```bash
mvn compile exec:java -Dexec.mainClass=com.dn.module4.slf4j.LoggingExample
mvn compile exec:java -Dexec.mainClass=com.dn.module4.slf4j.ParameterizedLoggingExample
mvn compile exec:java -Dexec.mainClass=com.dn.module4.slf4j.AppenderExample
```
(If `exec:java` isn't available, just run the class's `main` method from your IDE —
Logback is on the classpath via `pom.xml` either way.)

---

## 💡 Key Concepts

| Concept | What it does |
|---------|--------------|
| `@Test` | Marks a method as a JUnit 5 test case |
| `@BeforeEach` / `@AfterEach` | Runs before/after **every** test — used to set up and tear down fixtures |
| `assertEquals/assertTrue/assertAll/...` | JUnit 5 assertion library (`org.junit.jupiter.api.Assertions`) |
| Arrange-Act-Assert (AAA) | Structure every test in three clear blocks: set up data, call the code, check the result |
| `Mockito.mock(Class)` | Creates a fake implementation of a dependency so real code isn't invoked |
| `when(...).thenReturn(...)` | Stubs a mock's method to return a canned value |
| `verify(mock).method(...)` | Confirms a method was actually called on the mock, with the given arguments |
| `InOrder` | Verifies a sequence of calls happened in a specific order |
| `doThrow(...).when(mock).voidMethod()` | Stubs a **void** method to throw an exception (can't use `when(...)` for void methods) |
| SLF4J `Logger`/`LoggerFactory` | A logging *facade* — your code logs against this interface; Logback is the actual implementation underneath |
| `logback.xml` | Configures **appenders** (where logs go — console, file, etc.) and the root log level |
| Parameterized logging (`"{}"`) | `logger.info("User {} logged in", user)` avoids string concatenation and is skipped entirely if the level is disabled |
