# RailFlow — August 14–26, 2026

## Goal

Ship a credible vertical slice by August 26. This is the first milestone of a larger portfolio project, not the final architecture.

## Aug 14 — Day 1

### Done
- AI foundation and context/tool-calling theory reviewed.
- RailFlow scope selected.
- Existing documentation refreshed.
- Train-consist problem from interview experience incorporated into the domain.

### Commit target
`Initialize RailFlow platform and train consist validation`

Build:
- Spring Boot project
- Train / Locomotive / Railcar model
- `POST /api/v1/trains/validate-consist`
- unit tests
- Swagger

## Aug 15
- Persist train composition with PostgreSQL.
- Add controller/service/repository boundaries.
- Add validation and error handling.

## Aug 16
- Add shipment + organization onboarding models.
- Create shipment API.
- Link shipment to train/consist.

## Aug 17
- Add integration interface.
- Implement one accessible provider or a faithful simulator behind the same adapter.
- Add OpenFeign.

## Aug 18
- Add Resilience4j.
- Test timeout, circuit-open and safe retry behavior.
- Add explicit `UNKNOWN` state for ambiguous external writes if applicable.

## Aug 19
- Add Kafka.
- Define telemetry/event schemas.
- Create sensor simulator.

## Aug 20
- Consume events.
- Maintain current asset state.
- Deduplicate using event IDs.

## Aug 21
- Add bogie/axle/wheel model.
- Generate deterministic health alerts.

## Aug 22
- Add shipment-to-train-to-asset correlation.
- Expose operations/status APIs.

## Aug 23
- Build small RAG corpus.
- Chunk/embed/store/retrieve with source metadata.

## Aug 24
- Add read-only AI tools.
- Add context builder.

## Aug 25
- Build AI investigation flow.
- Add structured output and basic evaluation set.
- Exercise at least one AI failure case.

## Aug 26

Demo:
1. Create a shipment/train.
2. Generate a sensor event.
3. Trigger an alert.
4. Correlate the alert to the train/railcar/shipment.
5. Ask the AI: `Why is shipment SH-1001 delayed?`
6. AI calls approved tools and retrieves relevant documentation.
7. Return an evidence-backed structured explanation.

## Parallel DSA track

Continue 1–2 linked-list problems per session. For each problem:
- derive the pattern,
- code it without copying,
- state time and space complexity,
- explain it as if in an interview.

## Daily shipping rule

Every day ends with a tangible artifact: code commit, test, design decision, integration experiment or evaluation result.
