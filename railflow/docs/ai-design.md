# RailFlow — AI Design

## Purpose

The AI layer is an operations copilot. Its job is to reduce the time needed to understand a shipment, train, asset or maintenance exception.

It is not a train-control system and not the source of truth for safety constraints.

## First AI use case

> "Why is shipment SH-1001 delayed?"

The assistant can retrieve:
- shipment status
- train status
- railcar state
- active sensor alerts
- maintenance history
- relevant operating/maintenance documentation

Then it produces a structured explanation with evidence.

## RAG

Initial corpus:
- synthetic maintenance procedures
- synthetic equipment guides
- public documents that we are permitted to use for development

Pipeline:

`documents -> chunks -> embeddings -> vector store -> retrieval -> metadata filtering -> context -> LLM`

RAG is for external knowledge/documents. It does not replace live system-of-record APIs.

## Tool calling

Initial read-only tools:
- `getShipment`
- `getTrainState`
- `getRailcarStatus`
- `getActiveAlerts`
- `getMaintenanceHistory`
- `searchMaintenanceDocs`

Later write tools require explicit authorization and, for high-risk actions, human confirmation.

## Context engineering

The context builder selects only what is relevant and authorized for the request:
- system instructions
- user request
- recent state/events
- selected tool results
- retrieved source material
- conversation state where needed

We do not dump an entire database or event log into every prompt.

## Structured output

AI results consumed by the application use a schema. Example:

```json
{
  "severity": "HIGH",
  "summary": "Wheel impact is above the configured threshold.",
  "evidence": ["EVT-1009", "MR-44"],
  "recommendedNextStep": "Open maintenance investigation"
}
```

The application validates both the schema and the business meaning.

## Security

Do not use a prompt as the security boundary. Authorization happens before data is exposed to the model. Retrieved content is treated as untrusted data, not as a higher-priority instruction.

## Evaluation

Create a small fixed test set and measure:
- retrieval relevance
- tool-selection accuracy
- grounded-answer rate
- evidence/citation correctness
- structured-output validity
- refusal/fallback correctness
- latency and approximate token cost

## AI failure handling

Test:
- model timeout
- model/provider error
- tool timeout
- vector-store failure
- malformed output
- irrelevant retrieval
- conflicting tool results
- prompt injection in retrieved documents

A failed AI path should result in a safe, explicit fallback—not a confident guess.
