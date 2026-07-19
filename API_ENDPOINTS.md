# Student Study Planner — API Endpoints

Base URL: `http://localhost:8080`

All endpoints except those under `/api/auth` require a JWT Bearer token, obtained from `/api/auth/register` or `/api/auth/login`.

Attach it as a header on every other request:
```
Authorization: Bearer <your-token>
```

---

## Auth

### Register
`POST /api/auth/register`

```json
{
    "email": "test@example.com",
    "password": "password123",
    "fullName": "Test User"
}
```

Returns:
```json
{ "token": "<jwt>" }
```

### Login
`POST /api/auth/login`

```json
{
    "email": "test@example.com",
    "password": "password123"
}
```

Returns:
```json
{ "token": "<jwt>" }
```

---

## Courses

### Get my courses
`GET /api/courses`

### Create course
`POST /api/courses`

```json
{ "name": "Calculus II" }
```

### Delete course
`DELETE /api/courses/{id}`

---

## Assignments

### Get assignments for a course
`GET /api/assignments/course/{courseId}`

### Create assignment
`POST /api/assignments/course/{courseId}`

```json
{
    "title": "Homework 1",
    "description": "Chapter 3 problems",
    "dueDate": "2026-08-01"
}
```

### Update assignment
`PUT /api/assignments/{id}`

```json
{
    "title": "Homework 1 - Updated",
    "description": "Chapter 3 and 4 problems",
    "dueDate": "2026-08-05",
    "completed": true
}
```

### Delete assignment
`DELETE /api/assignments/{id}`

---

## Exams

### Get exams for a course
`GET /api/exam/course/{courseId}`

### Create exam
`POST /api/exam/course/{courseId}`

```json
{
    "title": "Midterm",
    "topic": "Derivatives and Integrals",
    "examDate": "2026-08-15"
}
```

### Update exam
`PUT /api/exam/{id}`

```json
{
    "title": "Midterm - Rescheduled",
    "topic": "Derivatives and Integrals",
    "examDate": "2026-08-20"
}
```

### Delete exam
`DELETE /api/exam/{id}`

---

## Grades

### Get grades for a course
`GET /api/grade/course/{courseId}`

### Create grade
`POST /api/grade/course/{courseId}`

```json
{
    "type": "Midterm",
    "score": 87.5,
    "dateRecorded": "2026-08-16"
}
```

### Update grade
`PUT /api/grade/{id}`

```json
{
    "type": "Midterm",
    "score": 92.0,
    "dateRecorded": "2026-08-16"
}
```

### Delete grade
`DELETE /api/grade/{id}`

---

## Study Sessions

These are tied to the logged-in user via the JWT — no course ID needed in the URL.

### Get my study sessions
`GET /api/study-sessions`

### Create study session
`POST /api/study-sessions`

```json
{
    "subject": "Calculus review",
    "startTime": "2026-08-10T14:00:00",
    "endTime": "2026-08-10T16:00:00",
    "notes": "Focus on integration by parts"
}
```

### Update study session
`PUT /api/study-sessions/{id}`

```json
{
    "subject": "Calculus review - extended",
    "startTime": "2026-08-10T14:00:00",
    "endTime": "2026-08-10T17:00:00",
    "notes": "Focus on integration by parts and series"
}
```

### Delete study session
`DELETE /api/study-sessions/{id}`

---

## Interactive docs

Full interactive API documentation (Swagger UI) is available while the app is running at:

```
http://localhost:8080/swagger-ui/index.html
```
