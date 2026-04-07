# DSA Question Manager

A production-ready Spring Boot backend for managing Data Structures & Algorithms (DSA) questions. Organize topics, track progress, store solutions, add notes, and tag questions for efficient revision.

---

## Tech Stack

- Java 17
- Spring Boot 3.2
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- MapStruct
- Swagger / OpenAPI (springdoc)
- Maven

---

## Project Structure

```
dsa-question-manager/
├── src/main/java/com/dsa/manager/
│   ├── config/          # JPA Auditing, OpenAPI config
│   ├── controller/      # REST API controllers
│   ├── dto/             # Request and Response DTOs
│   ├── entity/          # JPA entities + enums
│   ├── exception/       # Global exception handling
│   ├── mapper/          # MapStruct mappers
│   ├── repository/      # Spring Data JPA repositories
│   └── service/         # Business logic
└── src/main/resources/
    └── application.yml  # App configuration
```

---

## Database Design

### Entities

| Entity   | Key Fields                                                                 |
|----------|----------------------------------------------------------------------------|
| Topic    | id, name (unique), createdAt, updatedAt                                    |
| Question | id, title, description, difficulty, status, isFavorite, deleted, topic_id |
| Answer   | id, code, explanation, question_id                                         |
| Note     | id, content, question_id                                                   |
| Tag      | id, name (unique)                                                          |

### Relationships

- Topic → Questions: One-to-Many
- Question → Answers: One-to-Many
- Question → Notes: One-to-Many
- Question ↔ Tags: Many-to-Many (`question_tags` join table)

### Enums

**Difficulty:** `EASY` | `MEDIUM` | `HARD`

**Status:** `NOT_STARTED` | `IN_PROGRESS` | `COMPLETED`

---

## Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8+

---

## Getting Started

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd dsa-question-manager
```

### 2. Configure the database

Update `src/main/resources/application.yml` with your MySQL credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/dsa_manager?createDatabaseIfNotExist=true
    username: your_username
    password: your_password
```

The database `dsa_manager` will be created automatically on first run.

### 3. Build the project

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

The server starts at `http://localhost:8080`.

---

## API Documentation

Swagger UI is available at:

```
http://localhost:8080/swagger-ui.html
```

Raw OpenAPI spec:

```
http://localhost:8080/api-docs
```

---

## API Reference

### Topics

| Method | Endpoint                     | Description                        |
|--------|------------------------------|------------------------------------|
| GET    | /topics                      | Get all topics                     |
| GET    | /topics/{id}                 | Get topic by ID                    |
| POST   | /topics                      | Create a new topic                 |
| PUT    | /topics/{id}                 | Update a topic                     |
| DELETE | /topics/{id}                 | Delete a topic                     |
| GET    | /topics/{id}/questions       | Get all questions under a topic    |

### Questions

| Method | Endpoint                     | Description                                      |
|--------|------------------------------|--------------------------------------------------|
| GET    | /questions                   | Get all questions (paginated + sortable)         |
| GET    | /questions/{id}              | Get question by ID                               |
| POST   | /questions                   | Create a new question                            |
| PUT    | /questions/{id}              | Update a question                                |
| DELETE | /questions/{id}              | Soft delete a question                           |
| GET    | /questions/search            | Search by topic, difficulty, or keyword          |

#### Search Query Parameters

```
GET /questions/search?topic=1&difficulty=EASY&keyword=two+pointers&page=0&size=10
```

All parameters are optional and combinable.

### Answers

| Method | Endpoint                              | Description                  |
|--------|---------------------------------------|------------------------------|
| GET    | /questions/{questionId}/answers       | Get answers for a question   |
| POST   | /questions/{questionId}/answers       | Add an answer                |
| DELETE | /questions/{questionId}/answers/{id}  | Delete an answer             |

### Notes

| Method | Endpoint                              | Description                  |
|--------|---------------------------------------|------------------------------|
| GET    | /questions/{questionId}/notes         | Get notes for a question     |
| POST   | /questions/{questionId}/notes         | Add a note                   |
| DELETE | /questions/{questionId}/notes/{id}    | Delete a note                |

### Tags

| Method | Endpoint    | Description       |
|--------|-------------|-------------------|
| GET    | /tags        | Get all tags      |
| POST   | /tags        | Create a tag      |
| DELETE | /tags/{id}   | Delete a tag      |

---

## Sample Requests

### Create a Topic

```json
POST /topics
{
  "name": "Two Pointers"
}
```

### Create a Question

```json
POST /questions
{
  "title": "Container With Most Water",
  "description": "Find two lines that together with the x-axis form a container with the most water.",
  "difficulty": "MEDIUM",
  "status": "NOT_STARTED",
  "isFavorite": false,
  "topicId": 1,
  "tagIds": [1, 2]
}
```

### Add an Answer

```json
POST /questions/1/answers
{
  "code": "public int maxArea(int[] height) { ... }",
  "explanation": "Use two pointers from both ends, move the shorter one inward."
}
```

### Add a Note

```json
POST /questions/1/notes
{
  "content": "Remember to move the pointer with the smaller height."
}
```

---

## Features

- Full CRUD for topics, questions, answers, notes, and tags
- Soft delete for questions (data is retained, not permanently removed)
- Pagination and sorting on question listing
- Multi-filter search (topic + difficulty + keyword combined)
- Progress tracking per question (NOT_STARTED / IN_PROGRESS / COMPLETED)
- Favorite marking on questions
- Many-to-Many tagging (Important, Revision, Must-Do, etc.)
- Audit fields (`createdAt`, `updatedAt`) on all entities
- Global exception handling with structured error responses
- Input validation with descriptive field-level error messages
- DTOs for all API inputs and outputs (no entity exposure)
- MapStruct for zero-boilerplate DTO mapping
