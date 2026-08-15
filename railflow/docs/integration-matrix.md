# RailFlow — Integration Matrix

The integration strategy is intentionally honest about access. A provider being documented does not mean we have permission to use its production API.

| Integration | Why we need it | Access assumption | Implementation | First use |
|---|---|---|---|---|
| Railinc RailSight / related Railinc APIs | railcar movement, event history, shipment/ETA context | permissioned; credentials may be required | OpenFeign adapter | after core |
| Open/public rail data | reference infrastructure / development context | public or registration-based depending on source | REST client | optional |
| Rate/booking provider | quote/booking workflow | sandbox/developer access if available | OpenFeign | MVP |
| Weather API | optional operational context | developer/public API | REST client | later |
| Notification API | shipment/alert notifications | developer account or local adapter | REST client | later |
| Sensor simulator | wayside telemetry | our code | Kafka producer | MVP |
| LLM provider | AI generation | developer API | Python/HTTP | MVP |
| Vector store | RAG retrieval | local container initially | Python client | MVP |

## Railinc

Railinc documents real asset-tracking and related rail APIs, but access is not universally public.

Sources:
- https://public.railinc.com/developers/asset-tracking
- https://public.railinc.com/support/accessing-railinc-products

We therefore implement a provider adapter:

```text
RailincClient / SimulatorClient
          |
      adapter
          |
Normalized RailFlow model
```

The application runs without Railinc credentials.

## Feign and Resilience4j

OpenFeign is the preferred pattern for stable external business APIs. Spring's modern `RestClient` will be used for at least one simpler integration so we understand both approaches.

Resilience4j policies will include timeouts, circuit breaking and selective retries. The project will explicitly test why an operation is or is not retryable.

## The non-negotiable rule

Never represent a simulator as a real railroad API. The README and documentation will identify simulated integrations clearly.
