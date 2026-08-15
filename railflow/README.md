# RailFlow — Digital Rail Freight & AI Operations Platform

RailFlow is a portfolio-scale reference implementation that connects freight workflows, train operations, asset health and AI-assisted operational investigation.

## Product shape

```text
Shipper
  -> onboarding -> shipment -> quote -> booking -> tracking
                                      |
                                      v
                                  Train / Consist
                                      |
                       Train -> Locomotive -> Railcar
                                              -> Bogie -> Axle -> Wheel
                                                               |
                                                        Sensor events
                                                               |
                                                          Kafka / Alerts
                                                               |
                                                     AI Operations Copilot

```

## Primary customer

Rail freight operations / train planning team. Secondary users include shippers/logistics teams and maintenance/asset teams.

## Current problem statement

RailFlow brings shipment status, train composition, asset state and equipment events into one application so an operations team can validate a consist, understand current state, identify exceptions and investigate the cause without jumping across unrelated systems.

## Why AI is here

The AI layer is for investigation and explanation. It uses approved tools and a small RAG knowledge base to answer questions such as “Why is this shipment delayed?” It does not own safety rules, authorization or source-of-truth business state.

## August 26, 2026 milestone

A working vertical slice: shipment/train -> integration boundary -> Kafka event -> asset state/alert -> RAG + tool calling -> structured LLM explanation.

## Stack

Java 17+, Spring Boot, PostgreSQL, OpenFeign, Resilience4j, Kafka, Redis where justified, Docker, AWS, OpenTelemetry, Python for AI/evaluation, LLM API, embeddings, vector store, RAG and tool calling.

Technologies are added because the system needs them, not to make the stack look bigger.

## Real vs simulated

Permissioned rail APIs will only be presented as real integrations if access is actually available. Sensor telemetry is synthetic for the portfolio build. Any simulator is clearly labeled.

## Docs

- `docs/domain-research.md`
- `docs/requirements.md`
- `docs/architecture.md`
- `docs/ai-design.md`
- `docs/integration-matrix.md`
- `docs/roadmap-to-aug-26.md`
