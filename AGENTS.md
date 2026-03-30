# AGENTS.md — Guidance for AI coding agents

Purpose: short, actionable notes for an AI to be productive in the SmartQuark codebase.

High-level summary
- SmartQuark is a Kotlin + Quarkus template application (microservice-style) using Quarkus extensions
  for REST/GraphQL (smallrye-graphql), Hibernate ORM with Panache, and YAML configuration.
- Main runtime entry: `src/main/kotlin/net/littlelite/smartquark/Main.kt` (annotated @QuarkusMain).
- Business logic sits in `service` (e.g. `PersonService.kt`). Persistence uses Panache DAOs in `dao` and
  entity models in `model` with DTO conversions in `dto`.

Key files and directories
- `build.gradle.kts` — Kotlin/Gradle build, Quarkus plugin, Kotlin 2.x, Java 21 toolchain.
- `src/main/resources/application.yaml` — central runtime configuration (datasource, logging, quarkus features).
- `src/main/kotlin/net/littlelite/smartquark/` — main code
  - `service/` — application service layer (transactional boundaries live here, see `PersonService.kt`).
  - `graphql/` — GraphQL resources exposing queries (`PersonResource.kt`, `PhoneResource.kt`).
  - `dao/`, `model/`, `dto/` — standard persistence / mapping pattern.
  - `controller/` — web controllers (e.g. `HomeController.kt` serves OpenAPI UI and home page).
- `src/main/docker/Dockerfile.jvm` and `Dockerfile.native` — container packaging conventions.
- `build-native.sh`, `build-docker-native-example.sh`, `run-native-example.sh` — helper scripts for native build/run.

Big-picture architecture & patterns (what to infer from code)
- Layering: controller/resource (GraphQL) -> service (transactional, validation) -> DAO -> entity model.
  Example: GraphQL query `allPersons` calls `PersonService.getAllPersons()` -> `PersonDAO.listAll()`.
- Transactions: service methods annotated with `@Transactional` (see `PersonService.patchPerson`, `putPerson`).
  Mutation/update logic is implemented in the service layer; DAOs are used for retrieval/persistence.
- DTO usage: DTOs provide mapping to/from entities (see `PersonDTO.fromPerson` and `toPerson`). Services accept/return DTOs.
- Phones relationship: Phone entities are owned/managed through Person (addPhone/removePhone patterns in `PersonService`).

Build / run / debug workflows (concrete commands)
- Development (hot reload / dev UI):
  - gradle quarkusDev
  - Dev UI: http://localhost:8080/q/dev/ (available in dev mode). See `README.md`.
- Package JVM runnable jar:
  - gradle quarkusBuild -Dquarkus.package.type=uber-jar
  - Result: `build/quarkus-app/quarkus-run.jar` or `build/*-runner.jar` depending on package type.
  - Run: java -jar build/quarkus-app/quarkus-run.jar (Dockerfile.jvm expects `build/quarkus-app` layout).
- Container (JVM):
  - gradle quarkusBuild
  - docker build -f src/main/docker/Dockerfile.jvm -t smartquark .
  - docker run -p 8080:8080 smartquark
- Native image (requires GraalVM Java 17+ and native-image):
  - Set GRAALVM_HOME and install native-image (see README.md).
  - ./build-native.sh or `./build-native` (project has helper scripts). Use `run-native-example.sh` to run.
- Tests: standard Gradle test tasks (uses Quarkus JUnit5)
  - ./gradlew test

Project-specific conventions and gotchas for agents
- Kotlin/Quarkus versions: build uses Kotlin 2.x and Java 21 toolchain — when editing Gradle/Kotlin code match versions in `build.gradle.kts`.
- Files referenced by config: application.yaml sets `quarkus.native.native-image-xmx: 4g` — native builds expect >=4g memory.
- Docker files expect Quarkus output layout under `build/quarkus-app` (Dockerfile.jvm copies that exact layout).
- Logging: `application.yaml` sets console log formatter; tests run with a specific log manager system property (see `build.gradle.kts` tasks.withType<Test>`).
- Database handling: Quarkus Dev Services will spin up Postgres if Docker is available. Default datasource is postgresql in YAML.
  - If running native or containers, ensure external Postgres coordinates are provided — see `run-native-example.sh`.

Integration points & external dependencies
- Quarkus extensions (declared in `build.gradle.kts`) — primary integrations:
  - quarkus-hibernate-orm-panache-kotlin (Panache entities/DAOs)
  - quarkus-jdbc-postgresql and quarkus-jdbc-h2 (runtime/test DB)
  - smallrye-graphql (GraphQL endpoints)
  - quarkus-smallrye-openapi (OpenAPI/Swagger UI)
- Docker and GraalVM native-image tooling are required for native/container workflows.

Examples of code patterns agents can edit safely
- Adding a new GraphQL query: mirror `PersonResource.kt` pattern — inject service, add `@get:Query` property or method returning DTOs.
- Changing transactional semantics: update `@Transactional` methods in `service/` only — DAOs assume entity manager is managed.
- DTO -> Entity mapping: use existing `PersonDTO.toPerson()` and `fromPerson()` helpers to preserve mapping conventions.
- Jakarta Bean Validation (code pattern):
  - Use `jakarta.validation.constraints` on DTOs to validate at the boundary: annotate Kotlin constructor properties with `@field:` targets (e.g. `@field:NotBlank`, `@field:Min(0)`).
  - For nested/collection DTOs, prefer type-use validation: `Set<@Valid PhoneDTO>` so element constraints are validated.
  - Add `@Valid` on controller/resource method parameters so Quarkus runs validation before service logic.
  - Keep services free of basic input checks (remove `IllegalArgumentException` guards); services should assume boundary-validated inputs and perform only business-specific validations.
  - Representative files to inspect/edit: `build.gradle.kts` (add `io.quarkus:quarkus-hibernate-validator`), `src/main/kotlin/.../dto/*`, controller/resource files (add `@Valid`), and `service/*` (remove manual checks).

Testing and verification notes
- Run the test suite after changes: `./gradlew clean test`. The build includes the validator and unit/integration tests should pass.
- When testing manually, invalid payloads sent to controller endpoints should return HTTP 400 with a Quarkus validation payload describing the constraint violations.
- If you have tests that previously expected `IllegalArgumentException` from services, update them to assert controller 400 responses or adapt unit tests to provide valid inputs when calling services directly.

Quick navigation pointers (files to open first)
- `build.gradle.kts` — dependencies, Kotlin/Java versions, gradle plugin config.
- `application.yaml` — configuration that alters runtime behavior (datasource, logs, native settings).
- `src/main/kotlin/net/littlelite/smartquark/service/PersonService.kt` — core service examples: transactions, validation, phone management.
- `src/main/kotlin/net/littlelite/smartquark/graphql/PersonResource.kt` — GraphQL exposure pattern.

When to run tests or builds locally
- If you change DTO/entity or persistence code, run `./gradlew test` and `gradle quarkusDev` to validate runtime interactions.
- For native changes, expect long native-image builds and need for GraalVM; use CI or helper scripts when available.

If you need to modify CI or add agent rules
- No existing agent instruction files were found (no `.github/copilot-instructions.md`, `AGENT.md`, or `AGENTS.md` present). Place new agent rules here in root `AGENTS.md`.

Contact / provenance
- Author and contact appear in `application.yaml` openapi contact and file headers (Alessio Saltarin). Use repository-level README for context.

Limitations
- This guidance is derived from static inspection of the repository; runtime behavior (Dev Services, environment variables) may alter flows — run dev mode to verify.

