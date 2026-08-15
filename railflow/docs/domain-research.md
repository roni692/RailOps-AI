# RailFlow — Domain Research Notes

This is a working research log, not a railroad-industry textbook. The purpose is to keep the product grounded while we build.

## What we know from public sources

### Railinc: shipment, asset and equipment data are real integration domains
Railinc documents APIs and products covering railcar/asset tracking, movement and event history, shipment-related data, ETA, equipment information and equipment-health workflows. Access to a number of Railinc products is permissioned and may require an AAR reporting mark or Railinc Company ID.

Sources:
- https://public.railinc.com/developers/asset-tracking
- https://public.railinc.com/support/accessing-railinc-products
- https://public.railinc.com/developers

**Project decision:** use a provider adapter. If credentials are available later, a Railinc integration can sit behind that adapter. Until then, the same contract will run against a simulator/mock provider.

### Wayside condition monitoring is a real railway practice
The February 2026 review in *Railway Engineering Science* surveys wayside condition monitoring for rolling stock. It covers wheel-impact detection, hot axle box/bearing monitoring, acoustic detection, weighing and other sensing approaches, including defects such as wheel flats, bearing faults, hunting, overloading and unbalanced loads.

Source:
- https://link.springer.com/article/10.1007/s40534-025-00423-2

**Project decision:** our telemetry model will include train/railcar/bogie/axle/wheel identity plus measurements such as temperature, impact and vibration. The sensor stream itself will be synthetic.

### Train composition is a real operational constraint
RailFlow also models locomotive and railcar characteristics because train planning involves constraints such as total consist length, trailing weight and locomotive pulling capacity. The exact rules vary by railroad and route; we will treat those as configurable rules rather than pretending there is one universal formula.

**Project decision:** `TrainConsistService` will calculate and validate a configurable set of constraints. It will not make safety-critical dispatch decisions.

## What we are deliberately not claiming

- RailFlow is not a copy of BNSF, Union Pacific, CSX or another railroad's internal platform.
- We do not have production railroad telemetry.
- A documented API is not automatically a public/free API. Permissioned integrations are represented honestly.
- Synthetic sensor events are simulation data, not real detector readings.
- The LLM is not a safety authority.

## Research rule for the build

We research when the answer changes a design decision. We do not spend weeks reconstructing proprietary railroad workflows before shipping code.
