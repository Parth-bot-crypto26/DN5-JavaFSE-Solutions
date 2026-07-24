# Module 8 — Microservices with Spring Boot 3 and Spring Cloud
## DN 5.0 Deep Skilling - Java FSE

Five independent Spring Boot services under one Maven reactor, matching the
bank-themed hands-on scenario: split a monolithic "get account balance" /
"get loan details" service into real microservices, register them with a
Eureka discovery server, then route to one of them through a Spring Cloud
API Gateway.

---

## ✅ Mandatory Exercises
- **Creating Microservices for account and loan**
- **Create Eureka Discovery Server and register microservices**

## 📚 All Services

| Service | Port | Purpose |
|---|---|---|
| `eureka-server` ⭐ | 8761 | Service registry — every other service registers itself here |
| `account-service` ⭐ | 8080 | `GET /accounts/{number}` — dummy savings-account details |
| `loan-service` ⭐ | 8081 | `GET /loans/{number}` — dummy car-loan details |
| `greet-service` | 8082 | `GET /greet` → `"Hello World"` — simplest possible service, used for the gateway walkthrough |
| `api-gateway` | 9090 | Spring Cloud Gateway routing to any registered service, plus a `LogFilter` that logs every request |

`account-service` and `loan-service` cover the mandatory exercise
("Creating Microservices for account and loan") on their own — each is
exactly the independent, single-purpose Spring Boot project the doc asks
for. `greet-service` + `api-gateway` layer the (also mandatory) Eureka +
gateway routing on top using an even simpler example, which is how the doc
itself introduces the gateway before wiring it up to account/loan.

---

## 🚀 How to Run

### Prerequisites
- JDK 17+
- Maven 3.8+

Build everything once from the root (the reactor pom builds all five modules in order):
```bash
cd Module8_Microservices
mvn clean package
```

Then start each service in **its own terminal**, in this order:

```bash
# 1. Registry first - everything else needs this running to register against
cd eureka-server && mvn spring-boot:run
```
Wait for it to fully start, then open http://localhost:8761 — "Instances
currently registered with Eureka" should be empty.

```bash
# 2 & 3. The two business services
cd account-service && mvn spring-boot:run
cd loan-service && mvn spring-boot:run
```
Refresh http://localhost:8761 — `ACCOUNT-SERVICE` and `LOAN-SERVICE` should
now be listed.

```bash
curl http://localhost:8080/accounts/00987987973432
# {"number":"00987987973432","type":"savings","balance":234343.0}

curl http://localhost:8081/loans/H00987987972342
# {"number":"H00987987972342","type":"car","loan":400000.0,"emi":3258.0,"tenure":18}
```

```bash
# 4 & 5. Greet service + the gateway in front of it
cd greet-service && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
```
Refresh http://localhost:8761 again — `GREET-SERVICE` and `API-GATEWAY`
should both be listed.

```bash
curl http://localhost:9090/greet-service/greet
# Hello World
```
Watch the `api-gateway` console — `LogFilter` prints the method and full
URI of every request that passes through it, e.g.:
```
Incoming request: GET http://localhost:9090/greet-service/greet
```

The same discovery-locator routing works for account/loan too, once they're
registered — e.g. `curl http://localhost:9090/account-service/accounts/123`.

---

## 💡 Key Concepts

| Concept | What it does |
|---------|--------------|
| Monolith vs. microservices | One codebase/deployment doing everything vs. many small, independently deployable services — a bug or overload in one no longer takes the rest down with it |
| Maven reactor (parent `pom.xml` with `<modules>`) | Lets `mvn clean package` build every service in one command while each still has its own independent `pom.xml`/artifact |
| Eureka Server (`@EnableEurekaServer`) | A registry that other services register themselves into and look each other up from, instead of hardcoding hostnames/ports |
| Eureka Client (`@EnableDiscoveryClient`) | Tells a service to register itself with the configured Eureka server on startup, under `spring.application.name` |
| `server.port` | Each service needs its own port when run on the same machine — hence account (8080), loan (8081), greet (8082), gateway (9090), eureka (8761) |
| Spring Cloud Gateway | A reverse proxy that routes incoming requests to the right backend service — here, entirely auto-configured from the Eureka registry via the discovery locator |
| `spring.cloud.gateway.discovery.locator.enabled` | Skips writing manual route config — the gateway builds a route for every service it sees in Eureka |
| `GlobalFilter` | Gateway-level middleware that runs on **every** routed request, regardless of which service it's headed to — used here just to log method + URI |
| Spring Cloud BOM (`spring-cloud-dependencies`) | A version-locked bill of materials imported via `dependencyManagement` so every Spring Cloud starter across all five modules resolves to compatible versions |
