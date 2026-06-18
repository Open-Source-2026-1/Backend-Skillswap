# Backend-Skillswap

# SkillSwap Platform - Backend API

Backend de la plataforma SkillSwap desarrollado con Spring Boot, siguiendo arquitectura DDD (Domain-Driven Design).

## 🚀 URLs

- **Backend desplegado:** [https://backend-skillswap-production-746f.up.railway.app](https://backend-skillswap-production-746f.up.railway.app)
- **Swagger UI:** [https://backend-skillswap-production-746f.up.railway.app/swagger-ui/index.html](https://backend-skillswap-production-746f.up.railway.app/swagger-ui/index.html)
- **Repositorio GitHub:** [https://github.com/Open-Source-2026-1/Backend-Skillswap](https://github.com/Open-Source-2026-1/Backend-Skillswap)

## 🛠️ Stack Tecnológico

- Java 26
- Spring Boot 4.0.7
- PostgreSQL (Railway)
- Spring Data JPA + Hibernate
- SpringDoc OpenAPI (Swagger)
- Maven

## 📦 Bounded Contexts implementados

| BC | Estado | Tablas |
|---|---|---|
| Workspace | ✅ Completado | tutoring_sessions, messages |
| Reputation | ✅ Completado | reviews |
| Moderation | ✅ Completado | reports, sanctions |
| Discovery | 🔄 En progreso | tutors, tutor_skills |
| Learning & Assessment | 🔄 En progreso | quizzes, quiz_questions, quiz_attempts |
| Payments & Wallet | 🔄 En progreso | donations, wallets |

---

## 🧪 Ejemplos de prueba en Swagger

### 📌 Bounded Context: Workspace

#### Flujo completo de una sesión de tutoría

**Paso 1 — Crear sesión** `POST /api/v1/tutoring-sessions`
```json
{
  "learnerId": 100,
  "tutorId": 200,
  "topic": "Cálculo II - Integrales por sustitución",
  "message": "Hola, necesito ayuda con integrales dobles para mi parcial",
  "studentLevel": "intermedio",
  "scheduledAt": "2026-06-20T10:00:00"
}
```
✅ Respuesta esperada: `201 Created` con el objeto de la sesión y `status: "PENDING"`

---

**Paso 2 — Tutor acepta la sesión** `PATCH /api/v1/tutoring-sessions/{id}/status`

Reemplaza `{id}` con el id devuelto en el Paso 1.
```json
{
  "status": "SCHEDULED"
}
```
✅ Respuesta esperada: `200 OK` con `status: "SCHEDULED"`

---

**Paso 3 — Enviar mensaje** `POST /api/v1/messages`

Reemplaza `sessionId` con el id devuelto en el Paso 1.
```json
{
  "content": "Hola tutor, tengo dudas con la regla de sustitución trigonométrica",
  "senderId": 100,
  "sessionId": 1
}
```
✅ Respuesta esperada: `201 Created` con el mensaje y `sentAt` automático

---

**Paso 4 — Sesión completada** `PATCH /api/v1/tutoring-sessions/{id}/status`
```json
{
  "status": "COMPLETED"
}
```
✅ Respuesta esperada: `200 OK` con `status: "COMPLETED"`

---

**Paso 5 — Consultar sesiones del learner** `GET /api/v1/tutoring-sessions/learner/100`

Sin body. ✅ Respuesta esperada: `200 OK` con lista de sesiones del learner 100.

---

**⚠️ Validación de negocio — learner y tutor no pueden ser la misma persona**

`POST /api/v1/tutoring-sessions`
```json
{
  "learnerId": 100,
  "tutorId": 100,
  "topic": "Prueba inválida",
  "message": "Test",
  "studentLevel": "básico",
  "scheduledAt": "2026-06-20T10:00:00"
}
```
❌ Respuesta esperada: `400 Bad Request` con `"Learner and tutor cannot be the same person"`

---

### 📌 Bounded Context: Reputation

#### Flujo de reseñas

**Paso 1 — Crear review** `POST /api/v1/reviews`
```json
{
  "tutorId": 200,
  "learnerId": 100,
  "learnerName": "Jazmín Torres",
  "rating": 5.0,
  "comment": "Excelente tutor, explica muy claro y con paciencia",
  "sessionId": 1,
  "tutorReply": ""
}
```
✅ Respuesta esperada: `201 Created` con la review y `createdAt` automático

---

**Paso 2 — Tutor responde la review** `PUT /api/v1/reviews/{id}`

Reemplaza `{id}` con el id devuelto en el Paso 1.
```json
{
  "rating": 5.0,
  "comment": "Excelente tutor, explica muy claro y con paciencia",
  "tutorReply": "Gracias Jazmín, fue un placer ayudarte con el tema"
}
```
✅ Respuesta esperada: `200 OK` con `tutorReply` actualizado

---

**Paso 3 — Ver reviews de un tutor** `GET /api/v1/reviews/tutor/200`

Sin body. ✅ Respuesta esperada: `200 OK` con lista de reviews del tutor 200.

---

**⚠️ Validación de negocio — tutor y learner no pueden ser la misma persona**

`POST /api/v1/reviews`
```json
{
  "tutorId": 100,
  "learnerId": 100,
  "learnerName": "Jazmín Torres",
  "rating": 5.0,
  "comment": "Me autoevalúo",
  "sessionId": 1,
  "tutorReply": ""
}
```
❌ Respuesta esperada: `400 Bad Request` con `"Tutor and learner cannot be the same person"`

---

### 📌 Bounded Context: Moderation

### Flujo completo de Moderation

**Paso 1 — Crear reporte** `POST /api/v1/reports`
```json
{
  "reporterUserId": 100,
  "reportedUserId": 300,
  "reason": "Lenguaje ofensivo durante la sesión",
  "status": "pending"
}
```
✅ Respuesta esperada: `201 Created` con `status: "pending"` y `closed: false`

---

**Paso 2 — Crear sanción** `POST /api/v1/sanctions`

Reemplaza `reportId` con el id devuelto en el Paso 1.
```json
{
  "reportId": 1,
  "sanctionedUserId": 300,
  "type": "warning",
  "description": "Primera advertencia por lenguaje ofensivo",
  "durationDays": 7
}
```
✅ Respuesta esperada: `201 Created` con la sanción creada

---

**Paso 3 — Cerrar reporte** `PATCH /api/v1/reports/{id}/close`

Reemplaza `{id}` con el id del reporte. Sin body.

✅ Respuesta esperada: `200 OK` con `status: "resolved"` y `closed: true`

---

**Paso 4 — Ver sanciones de un usuario** `GET /api/v1/sanctions/by-user/300`

Sin body. ✅ Respuesta esperada: `200 OK` con lista de sanciones del usuario 300.

---

**⚠️ Validación de negocio — no puedes reportarte a ti mismo**

`POST /api/v1/reports`
```json
{
  "reporterUserId": 100,
  "reportedUserId": 100,
  "reason": "Prueba inválida",
  "status": "pending"
}
```

❌ Respuesta esperada: `400 Bad Request` con `"Reporter and reported user cannot be the same person"`

---

## 📁 Estructura del proyecto

```
src/main/java/pe/edu/upc/skillswap/platform/
├── shared/
│   └── domain/model/aggregates/
│       ├── AbstractDomainAggregateRoot
│       └── AuditableAbstractAggregateRoot
├── workspace/
│   ├── application/internal/commandservices/
│   ├── application/internal/queryservices/
│   ├── domain/model/aggregates/
│   ├── domain/model/commands/
│   ├── domain/model/queries/
│   ├── domain/services/
│   ├── infrastructure/persistence/jpa/repositories/
│   └── interfaces/rest/
├── reputation/
├── moderation/
├── discovery/
├── learning/
└── payments/
```


