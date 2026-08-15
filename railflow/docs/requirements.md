# RailFlow — Requirements

## Product goal

Build a digital rail freight and operations platform that connects the shipment lifecycle to train composition, asset state and operational exceptions. Add an AI operations layer that helps people investigate those exceptions using approved data and documents.

## Primary persona

**Rail freight operations / train planning team.**

They need a current answer to questions such as:
- Can this consist be built within configured constraints?
- What is the current state of a train or railcar?
- Which shipment is affected by an equipment issue?
- Which alerts need action?
- Why is a shipment late?

## Secondary personas

### Shipper / logistics operations
- onboard an organization
- create a shipment request
- obtain a quote
- book a move where an integration is available
- track status and ETA

### Maintenance / asset operations
- view asset health events
- inspect recent telemetry
- see maintenance history
- investigate an alert

### Customer service / control room
- answer shipment-status questions
- understand exceptions quickly

## Core domain

```text
Organization
  └── User / Role
       └── Shipment
            └── Train
                 ├── Locomotive
                 └── Railcar
                      └── Bogie
                           └── Axle
                                └── Wheel
                                     └── SensorEvent
```

## MVP workflows

### A. Train consist validation

Input:
- locomotives and their configured pull capacity/length
- railcars and their configured length/weight
- train length limit
- trailing-weight / pulling-capacity limits

Output:
- total length
- trailing weight
- available locomotive pull capacity
- valid/invalid
- reasons

This is deterministic domain logic.

### B. Shipper onboarding

Capture organization, locations, users/roles and a basic cargo/shipping profile. Use synthetic data for the portfolio build.

### C. Shipment request / quote / booking

Capture origin, destination, commodity, weight, railcar requirements and dates. Normalize quote/booking responses from an external provider or simulator.

### D. Event and asset monitoring

Consume events such as:
- `train.detected`
- `axle.detected`
- `wheel.impact.detected`
- `bearing.temperature.recorded`
- `train.entered.zone`
- `train.exited.zone`

Maintain current state and generate deterministic alerts.

### E. AI operations assistant

Questions such as:
- Why is shipment SH-1001 delayed?
- What is happening with Train 842?
- What alerts are active on railcar RC-102?
- Which maintenance procedure applies to this alert?

AI may investigate; the backend remains authoritative.

## Non-functional requirements

### Reliability
External calls require timeouts. Retries are allowed only when the operation is safe or protected by idempotency. Ambiguous writes enter an `UNKNOWN` state and are reconciled.

### Security
Authentication, role-based access, tenant/organization isolation, least-privilege tool access and audit logging are required before sensitive write tools are introduced.

### Observability
Capture API latency/errors, trace IDs, Kafka consumer lag, event processing latency and AI/tool timing.

### AI quality
Track retrieval relevance, tool selection, grounded response rate, citation correctness, structured-output validity and fallback behavior.

## August 26 definition of done

A demonstrable vertical slice exists:

`train/shipment -> external integration boundary -> Kafka event -> asset state/alert -> RAG + tool calling -> LLM explanation`

Anything beyond that is later scope.
