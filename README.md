# Backend-Skillswap
## Moderation Bounded Context

### API Documentation
Once running, access the interactive documentation at:
- **Swagger UI:** `http://localhost:8097/swagger-ui/index.html`
- **OpenAPI JSON:** `http://localhost:8097/v3/api-docs`

### JSON de entrada - Crear Report

```json
{
  "reporterUserId": 1,
  "reportedUserId": 2,
  "reason": "string",
  "status": "pending"
}
```

### JSON de entrada - Crear Sanction

```json
{
  "reportId": 1,
  "sanctionedUserId": 2,
  "type": "string",
  "description": "string",
  "durationDays": 0
}
```
