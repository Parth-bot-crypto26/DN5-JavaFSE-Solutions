# Module 6 — Spring Data JPA with Spring Boot, Hibernate
## DN 5.0 Deep Skilling - Java FSE

A single Spring Boot project (`orm-learn`) that walks through the `country`
quick-start, then a full `employee` / `department` / `skill` payroll schema
for O/R mapping, HQL and native queries — matching the three hands-on docs.

H2 (in-memory) is used in place of MySQL so the whole module runs with zero
external database setup; everything else (entities, repositories, HQL) is
unchanged from the original exercises.

---

## ✅ Mandatory Exercises
- **Hands on 1:** Spring Data JPA - Quick Example
- **Hands on 4:** Difference between JPA, Hibernate and Spring Data JPA *(see below)*

### Difference between JPA, Hibernate and Spring Data JPA

| | What it is |
|---|---|
| **JPA** (Java Persistence API) | A *specification* (JSR 338) for persisting, reading, and managing Java objects. It has no implementation of its own. |
| **Hibernate** | An ORM tool that *implements* the JPA specification (one of several available implementations). |
| **Spring Data JPA** | Doesn't implement JPA itself — it's a further layer of abstraction *over* a JPA provider like Hibernate, removing boilerplate code and managing transactions for you. |

Compare the code needed to add an employee, Hibernate vs. Spring Data JPA:

```java
// Hibernate
public Integer addEmployee(Employee employee) {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;
    try {
        tx = session.beginTransaction();
        employeeID = (Integer) session.save(employee);
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
    return employeeID;
}
```

```java
// Spring Data JPA
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

@Autowired
private EmployeeRepository employeeRepository;

@Transactional
public void addEmployee(Employee employee) {
    employeeRepository.save(employee);
}
```
Same outcome, but Spring Data JPA removes the manual session/transaction
management entirely — `JpaRepository` and `@Transactional` handle it.

---

## 📚 All Exercises

| # | Topic | Where |
|---|-------|-------|
| 1 ⭐ | Spring Data JPA - Quick Example | `Country` entity/repository/service + `testGetAllCountries()` |
| 4 ⭐ | JPA vs Hibernate vs Spring Data JPA | README section above |
| 5 | Implement services for managing Country | `CountryService` (find/add/update/delete/search) |
| 6 | Find a country based on country code | `CountryService.findCountryByCode()` + `CountryNotFoundException` |
| 7 | Add a new country | `CountryService.addCountry()` |
| — | Update a country based on code | `CountryService.updateCountry()` |
| — | Delete a country based on code | `CountryService.deleteCountry()` |
| — | Query Methods (contains/sorted/starts-with) | `CountryRepository` |
| — | O/R Mapping: `@ManyToOne` (Employee → Department) | `Employee.department`, `DepartmentService` |
| — | O/R Mapping: `@OneToMany` (Department → Employees) | `Department.employeeList` |
| — | O/R Mapping: `@ManyToMany` (Employee ↔ Skill) | `Employee.skillList`, `Skill.employeeList`, `employee_skill` join table |
| — | HQL: permanent employees with fetch joins | `EmployeeRepository.getAllPermanentEmployees()` |
| — | HQL: aggregate function (average salary) | `EmployeeRepository.getAverageSalary()` / `getAverageSalaryByDepartment()` |
| — | Native Query | `EmployeeRepository.getAllEmployeesNative()` |

---

## 🚀 How to Run

### Prerequisites
- JDK 17+
- Maven 3.8+

```bash
cd Module6_SpringDataJPA/orm-learn
mvn spring-boot:run
```

All twelve `test*()` methods in `OrmLearnApplication` run automatically on
startup (via `CommandLineRunner`) and log their results. Watch the console —
`logging.level.org.hibernate.SQL=trace` is on, so you'll see the actual SQL
Hibernate generates for every step, including:
- the single fetch-joined query for permanent employees (vs. the N+1 queries
  you'd get without `fetch` in the HQL)
- the `LEFT OUTER JOIN` Hibernate adds automatically for the `@ManyToOne`
  Department fetch
- the native query running verbatim against the `employee` table

### Data
`src/main/resources/data.sql` seeds:
- the full ~249-country list from the hands-on doc's script
- 3 departments, 4 skills, 5 employees, and their `employee_skill` join rows

---

## 💡 Key Concepts

| Concept | What it does |
|---------|--------------|
| `@Entity` / `@Table` | Marks a class as a persistent entity and maps it to a table |
| `@Id` / `@GeneratedValue` | Marks the primary key; `GenerationType.IDENTITY` lets the DB auto-increment it |
| `@Column` | Maps a field to a specific column name |
| `JpaRepository<T, ID>` | Extend it and get `save`, `findAll`, `findById`, `deleteById`, etc. for free |
| Query Methods | Spring derives the query from the method name itself — `findByNameContaining`, `findByNameStartingWith`, no SQL/HQL needed |
| `ddl-auto` | `create` wipes and recreates tables; `validate` only checks they match; `update` adds missing tables/columns; `create-drop` tears down after use |
| `@ManyToOne` / `@JoinColumn` | The "many" side owns the foreign key column and the relationship |
| `@OneToMany(mappedBy=...)` | The inverse side of a `@ManyToOne` — `mappedBy` points back at the owning field |
| `@ManyToMany` / `@JoinTable` | Models a many-to-many relationship via a join table; one side owns it, the other uses `mappedBy` |
| `FetchType.EAGER` vs `LAZY` | Eager loads the related data immediately; Lazy loads it only when accessed (and throws `LazyInitializationException` outside an open session) |
| HQL (Hibernate Query Language) | Queries against **entity classes and fields**, not table/column names — `SELECT e FROM Employee e WHERE e.permanent = true` |
| `fetch` in HQL joins | `join` alone only filters; `join fetch` actually populates the related entity in the same query, avoiding extra round-trips |
| `@Query(nativeQuery = true)` | Runs a literal SQL string instead of HQL — useful as an escape hatch, but sacrifices database portability |
| `@Param` | Binds a Java method parameter to a named (`:name`) placeholder in a `@Query` |
| `@Transactional` | Wraps a service method in a database transaction, and (for JPA) keeps the persistence context/session open for the method's duration |
