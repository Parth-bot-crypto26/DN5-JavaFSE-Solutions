# Module 7 — Spring REST using Spring Boot 3
## DN 5.0 Deep Skilling - Java FSE

A single Spring Boot project (`spring-learn`) covering Spring Core XML config,
RESTful web services, MockMVC testing, and JWT-secured endpoints.

---

## ✅ Mandatory Exercises
- **Hands on 1:** Create a Spring Web Project using Maven
- **Hands on 4:** Spring Core – Load Country from Spring Configuration XML
- **Hello World RESTful Web Service**
- **REST - Country Web Service**
- **REST - Get country based on country code**
- **JWT-handson: Create authentication service that returns JWT**

## 📚 All Exercises

| # | Topic | Where |
|---|-------|-------|
| 1 ⭐ | Create a Spring Web Project using Maven | `pom.xml`, `SpringLearnApplication.java` |
| 2 | Load `SimpleDateFormat` from Spring Configuration XML | `date-format.xml` + `displayDate()` |
| 3 | Incorporate Logging | `application.properties` + `LOGGER` throughout |
| 4 ⭐ | Load Country from Spring Configuration XML | `country.xml`, `Country.java` + `displayCountry()` |
| 5 | Singleton Scope vs Prototype Scope | `country` vs `countryPrototype` beans in `country.xml` |
| 6 | Load list of countries from Spring Configuration XML | `countryList` bean + `displayCountries()` |
| — ⭐ | Hello World RESTful Web Service | `HelloController` (`GET /hello`) |
| — ⭐ | REST - Country Web Service | `CountryController.getCountryIndia()` (`GET /country`) |
| — | REST - Get all countries | `CountryController.getAllCountries()` (`GET /countries`) |
| — ⭐ | REST - Get country based on country code | `CountryController.getCountry()` (`GET /countries/{code}`) |
| — | REST - Get country exceptional scenario | `CountryNotFoundException` (`@ResponseStatus 404`) |
| — | MockMVC - Test get country service | `SpringLearnApplicationTests` |
| — | Employee/Department REST (doc3, all hands-on) | `EmployeeController`/`DepartmentController` + DAO/Service layers |
| — ⭐ | JWT: authentication service that returns a token | `AuthenticationController` (`GET /authenticate`) |
| — | Securing services + roles + JWT authorization filter | `SecurityConfig`, `JwtUtil`, `JwtAuthorizationFilter` |

### A note on the JWT implementation
The original hands-on doc is written against **Spring Boot 2 / Spring
Security 5** (`WebSecurityConfigurerAdapter`, `javax.servlet`, JJWT 0.9.0's
`setSubject()`/`signWith(alg, string)` API) — none of which exist anymore in
Spring Boot 3. This module reproduces the exact same three-step flow
(secure everything → issue a JWT from `/authenticate` → validate it on every
other request) using current APIs:

| Then (doc, Boot 2 / Security 5) | Now (this project, Boot 3 / Security 6) |
|---|---|
| `class SecurityConfig extends WebSecurityConfigurerAdapter` | `@Bean SecurityFilterChain securityFilterChain(HttpSecurity http)` |
| `configure(AuthenticationManagerBuilder auth)` | `@Bean UserDetailsService userDetailsService(...)` using `InMemoryUserDetailsManager` |
| `class JwtAuthorizationFilter extends BasicAuthenticationFilter` (`javax.servlet`) | `class JwtAuthorizationFilter extends OncePerRequestFilter` (`jakarta.servlet`) |
| `Jwts.builder().setSubject(user)...signWith(SignatureAlgorithm.HS256, "secretkey")` | `Jwts.builder().subject(user)...signWith(signingKey)` (JJWT 0.12.x, `Keys.hmacShaKeyFor(...)`) |

---

## 🚀 How to Run

### Prerequisites
- JDK 17+
- Maven 3.8+

```bash
cd Module7_SpringREST/spring-learn
mvn spring-boot:run
```
The app starts on **port 8083** (set in `application.properties`, matching the doc's sample requests).

### Try the public flow first
```bash
curl http://localhost:8083/hello
```

### Everything else needs auth
Every other endpoint requires either HTTP Basic or a Bearer JWT (per the
`SecurityConfig` rules). Two in-memory users are seeded: `admin` / `pwd`
(ROLE_ADMIN) and `user` / `pwd` (ROLE_USER).

**Get a token:**
```bash
curl -s -u user:pwd http://localhost:8083/authenticate
# {"token":"eyJhbGciOiJIUzI1NiJ9....."}
```

**Use the token on any other endpoint:**
```bash
TOKEN=$(curl -s -u user:pwd http://localhost:8083/authenticate | grep -o '"token":"[^"]*' | cut -d'"' -f4)

curl -H "Authorization: Bearer $TOKEN" http://localhost:8083/country
curl -H "Authorization: Bearer $TOKEN" http://localhost:8083/countries
curl -H "Authorization: Bearer $TOKEN" http://localhost:8083/countries/in
curl -H "Authorization: Bearer $TOKEN" http://localhost:8083/countries/az   # -> 404 Country not found
curl -H "Authorization: Bearer $TOKEN" http://localhost:8083/employees
curl -H "Authorization: Bearer $TOKEN" http://localhost:8083/departments
```

**Or skip the token and use HTTP Basic directly** (it still works — the
filter chain keeps `httpBasic()` alongside the JWT filter):
```bash
curl -u user:pwd http://localhost:8083/countries
```

### Run the MockMVC tests
```bash
mvn test
```

### On startup
`SpringLearnApplication` also runs `displayDate()` / `displayCountry()` /
`displayCountries()` once via `CommandLineRunner` — watch the console (debug
logging is on) to see the Hands-on 2/4/5/6 output, including the
singleton-vs-prototype constructor-call proof.

---

## 💡 Key Concepts

| Concept | What it does |
|---------|--------------|
| `ClassPathXmlApplicationContext` | Loads a standalone Spring context from an XML file — used here for `date-format.xml`, which nothing else in the app needs |
| `@ImportResource` | Merges beans from one or more XML files straight into a Spring Boot app's own `ApplicationContext`, so `@Autowired`/`@Qualifier` can reach them |
| Singleton scope (default) | One shared bean instance for the whole container — the constructor runs once no matter how many times you call `getBean()` |
| Prototype scope (`scope="prototype"`) | A brand-new instance (and constructor call) every time `getBean()` is called |
| `@ResponseStatus` | Maps an exception class straight to an HTTP status code, with no `try/catch` needed in the controller |
| `@PathVariable` | Binds a `{placeholder}` segment of the URL to a method parameter |
| MockMvc | Simulates HTTP requests against your controllers in a test, without starting a real server |
| `@WithMockUser` | Stands in for a real login in a `@SpringBootTest` — sets up a fake authenticated user with the given role |
| HTTP Basic Authentication | Credentials sent as a Base64-encoded `Authorization: Basic ...` header on every request — simple, but not encrypted, just encoded |
| JWT (JSON Web Token) | A signed, self-contained token (header + payload + signature) proving who you are without the server needing to store session state |
| `OncePerRequestFilter` | A servlet filter guaranteed to run exactly once per request — the modern home for custom auth logic like JWT validation |
| `SecurityFilterChain` | Spring Security 6's replacement for `WebSecurityConfigurerAdapter` — a single `@Bean` that declares the entire security configuration |
| `Keys.hmacShaKeyFor(bytes)` | Builds a proper `SecretKey` object for HMAC signing (HS256 needs ≥256 bits) instead of passing a raw string |
