# Module 5 — Spring Core and Maven
## DN 5.0 Deep Skilling - Java FSE

Both projects model the same running example from the hands-on doc: a
**Library Management** application with `BookService` and `BookRepository`.

---

## ✅ Mandatory Exercises
- **Exercise 1:** Configuring a Basic Spring Application
- **Exercise 2:** Implementing Dependency Injection
- **Exercise 4:** Creating and Configuring a Maven Project

## 📚 All Exercises

| # | Topic | Where |
|---|-------|-------|
| 1 ⭐ | Configuring a Basic Spring Application | `LibraryManagement` — `applicationContext.xml` + `LibraryManagementApplication.java` |
| 2 ⭐ | Implementing Dependency Injection (setter) | `LibraryManagement` — `applicationContext.xml` + `LibraryManagementApplication.java` |
| 3 | Logging with Spring AOP | `LibraryManagement` — `applicationContext-aop.xml` + `LoggingAspect.java` + `AopLoggingDemo.java` |
| 4 ⭐ | Creating and Configuring a Maven Project | `LibraryManagement/pom.xml` |
| 5 | Configuring the Spring IoC Container | `LibraryManagement` — `applicationContext.xml` (same as 1/2 — one central config) |
| 6 | Configuring Beans with Annotations | `LibraryManagement` — `applicationContext-annotations.xml` + `AnnotationConfigDemo.java` |
| 7 | Constructor and Setter Injection | `LibraryManagement` — `applicationContext-constructor.xml` + `ConstructorSetterInjectionDemo.java` |
| 8 | Basic AOP with Spring | `LibraryManagement` — `applicationContext-aop.xml` + `LoggingAspect.java` + `AopLoggingDemo.java` |
| 9 | Creating a Spring Boot Application | `LibraryManagementBoot` (separate project) |

Exercises 1, 2 and 5 all point at the same `applicationContext.xml` because
the doc's steps for each are the same central bean config — there's nothing
to duplicate.

---

## 🚀 How to Run

### Prerequisites
- JDK 17+
- Maven 3.8+

### `LibraryManagement` (Exercises 1, 2, 3, 4, 5, 6, 7, 8 — classic Spring Framework, XML + annotations)
```bash
cd Module5_SpringCoreMaven/LibraryManagement

# Exercises 1, 2 & 5 — basic config + setter injection + IoC container
mvn compile exec:java -Dexec.mainClass=com.library.LibraryManagementApplication

# Exercise 6 — annotation-driven config (@Service/@Repository + component-scan)
mvn compile exec:java -Dexec.mainClass=com.library.AnnotationConfigDemo

# Exercise 7 — constructor injection vs setter injection, side by side
mvn compile exec:java -Dexec.mainClass=com.library.ConstructorSetterInjectionDemo

# Exercises 3 & 8 — AOP logging aspect (watch the console for [AOP] lines)
mvn compile exec:java -Dexec.mainClass=com.library.AopLoggingDemo
```

### `LibraryManagementBoot` (Exercise 9 — Spring Boot, Spring Web, Spring Data JPA, H2)
```bash
cd Module5_SpringCoreMaven/LibraryManagementBoot
mvn spring-boot:run
```
Then hit the REST API:
```bash
curl http://localhost:8080/api/books
curl http://localhost:8080/api/books/1
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"isbn":"978-1491950357","title":"Building Microservices","author":"Sam Newman"}'
```
H2 console (in-memory DB browser): `http://localhost:8080/h2-console` — JDBC URL `jdbc:h2:mem:librarydb`, user `sa`, no password.

---

## 💡 Key Concepts

| Concept | What it does |
|---------|--------------|
| IoC Container (`ApplicationContext`) | Creates, wires and manages the lifecycle of your beans instead of you calling `new` everywhere |
| `<bean>` | Declares a bean by hand in XML: id, class, and how to wire its dependencies |
| Setter injection (`<property>`) | Spring builds the object with a no-arg constructor, then calls the setter |
| Constructor injection (`<constructor-arg>`) | Spring passes dependencies straight into the constructor — the object is never in a half-built state |
| `@Component` / `@Service` / `@Repository` | Marks a class to be auto-discovered and registered as a bean |
| `<context:component-scan>` | Tells Spring which package to scan for `@Component`-style annotations |
| `@Autowired` | Tells Spring to inject a matching bean automatically |
| AOP (Aspect-Oriented Programming) | Lets you add cross-cutting behavior (logging, timing, security) without touching the target class's code |
| `@Aspect` / `@Around` / `@Before` / `@After` | Defines an aspect and the advice types that run around/before/after matched method calls |
| Pointcut expression (`execution(* com.library.service..*(..))`) | Selects *which* methods an aspect applies to |
| `<aop:aspectj-autoproxy/>` | Turns on proxy-based weaving so `@Aspect` classes actually intercept calls |
| Spring Boot | Convention-over-configuration: starters + auto-configuration mean no XML, an embedded server, and a single `main()` to run everything |
| `spring-boot-starter-data-jpa` | Pulls in Hibernate + Spring Data JPA so a repository interface is all you need for CRUD |
| `JpaRepository<T, ID>` | Extend it and get `save`, `findAll`, `findById`, `deleteById`, etc. with zero implementation code |
