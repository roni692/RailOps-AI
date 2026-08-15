# RailFlow — Architecture

## Architecture position

The core application is a normal business system. AI sits beside it, not above it as an authority.

```text
Web / API / Chat
       |
   Spring Boot
       |
  +----+---------------------+
  |                          |
Domain modules             AI layer
  |                          |
  |                  +-------+-------+
  |                  |       |       |
  |                 RAG    Tools  Context
  |                  |       |       |
  +--------+---------+-------+-------+
           |                 |
      PostgreSQL             LLM
           |
       Domain state

Sensor simulator -> Kafka -> event consumers -> asset state / alerts

External providers -> adapters / Feign -> normalized domain model
```

## Start modular, not microservice-heavy

For the first milestone we use one Spring Boot application with clear modules/packages:

- `shipment`
- `train`
- `asset`
- `integration`
- `event`
- `ai`
- `security`

We split services only when there is a real reason: load profile, isolation, ownership or deployment needs.

## Core domain boundaries

### Train operations
Owns train composition, locomotives, railcars and consist validation.

### Asset health
Owns bogie/axle/wheel state, sensor events, alerts and maintenance references.

### Shipment
Owns shipper, cargo, quote, booking and shipment lifecycle.

### Integration
Owns external provider clients and translation to RailFlow domain objects.

### AI operations
Owns context construction, retrieval, tool orchestration, model calls and evaluation. It does not own business truth.

## Data

**PostgreSQL:** transaction/domain state.

**Kafka:** sensor telemetry and domain event transport.

**Redis:** only where justified for hot state, idempotency or short-lived caches.

**Vector store:** maintenance/operations knowledge used by RAG.

**Object storage:** source documents and large artifacts.

## External API pattern

```text
Provider response
      |
Provider DTO
      |
Adapter / Feign client
      |
Normalized RailFlow model
      |
Core domain
```

Provider DTOs should not leak into the domain.

## Resilience model

- GET/status calls: normally retryable within bounds.
- CREATE/booking/write calls: do not blindly retry.
- Use idempotency keys where supported.
- If a write times out after submission, enter `UNKNOWN` and reconcile.
- Circuit breakers protect the application from repeatedly calling an unhealthy dependency.

## Train consist service

`TrainConsistService` is deliberately deterministic:

```text
request
  -> calculate length
  -> calculate trailing weight
  -> calculate available pull capacity
  -> apply configured constraints
  -> result + reason
```

No LLM involvement.

## AI execution boundary

```text
user
  -> auth
  -> authorized tool set
  -> tool calls / RAG
  -> context builder
  -> LLM
  -> schema validation
  -> application/business validation
  -> response
```

The LLM never bypasses backend authorization.
