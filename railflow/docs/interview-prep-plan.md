# Staff/Lead FDE Interview Prep — 90-Day Plan

This extends `roadmap-to-aug-26.md` (the RailFlow vertical-slice sprint) into a full 90-day
interview-readiness plan. It exists because a project by itself doesn't get you a Staff/Lead
Forward Deployed Engineer offer — it has to be paired with DSA fluency, system design depth, and
a story you can defend under pressure. This doc is the connective tissue between "build RailFlow"
and "pass the loop."

Start date: Aug 18, 2026 (today, per the existing sprint = "add Resilience4j" day).
End date: ~Nov 16, 2026.

## Baseline check-in (Aug 18) — honest starting point

Self-reported baseline: LeetCode is currently a weak area overall; linked-list-style problems
are the one pattern that's solid; system design has not been started at all. This changes the
shape of Phase 1 below in three ways versus a generic plan:

1. **LeetCode restarts from true fundamentals, not mediums.** Jumping to Blind 75 mediums
   while still weak on basics is the #1 reason people plateau. Weeks 1–2 below are almost
   all easy/easy-medium arrays, strings, and hashmaps — the patterns that ~40% of all
   interview problems reduce to — before layering anything new on top of linked lists.
2. **System design starts at zero, on purpose.** No case studies yet — Week 1 is vocabulary
   and mental models only (client-server, HTTP, latency vs throughput, vertical vs horizontal
   scaling). You cannot skip this step and still sound coherent in Week 4's first case study.
3. **Linked lists become spaced-repetition maintenance, not new learning.** 1–2 linked-list
   problems every 5–7 days (not daily) is enough to keep the pattern sharp while LeetCode time
   goes toward the weaker areas.

Phase 1 below (Weeks 1–4) is rewritten week-by-week to reflect this. Phases 2–3 already assume
you've closed this gap, so revisit them once Phase 1 is actually done — don't rush ahead on
a fixed date if Week 2's fundamentals aren't solid yet; slipping the calendar by a week here is
cheaper than carrying a shaky foundation into mediums.

## What a Staff/Lead FDE loop actually tests

Most FDE loops (Palantir-style forward-deployed roles, applied-AI/solutions-architecture
Staff+ roles) are not a pure LeetCode gate. They weight four things, and Staff/Lead adds a
fifth:

1. **Coding** — usually medium-difficulty, pattern-based, correctness + communication under
   ambiguity. Rarely hard/competitive-programming style.
2. **System design** — given a messy, half-specified customer problem, can you scope it,
   name the tradeoffs, and design something operable (not just "diagram with boxes")?
3. **Project/portfolio deep dive** — you will be asked to defend a real system you built:
   why this architecture, what would break at 10x load, what you'd change knowing what you
   know now. RailFlow is built for exactly this round.
4. **Behavioral / "forward-deployed" scenario** — ambiguous customer requirements, pushback
   from a stakeholder, an integration partner who can't give you access (sound familiar —
   see `integration-matrix.md`), how you communicated a tradeoff to a non-technical audience.
5. **Staff/Lead differentiator** — technical leadership signal: did you *make the call* (build
   vs. buy, sync vs. async, LLM vs. deterministic logic), did you *de-risk* ambiguity for
   others, did you *mentor/influence* without formal authority. Interviewers listen for "I
   decided" and "here's what I'd do differently," not just "I built."

Everything below is organized so that RailFlow generates real material for rounds 3–5, not
just a coding sample.

## Weekly cadence (repeat every week, all 90 days)

Daily, in this order of priority when time is short:

| Block | Time | What |
|---|---|---|
| Theory | 30 min | Rotate: system design concept, distributed systems concept, or LLM/agents concept (see tracks below). Write 3–5 bullet notes — teaching yourself in writing is the point, not passive reading. |
| LeetCode | 30–45 min | 1–2 problems. Always say the pattern out loud before coding, then state time/space complexity after. Never look at a solution before a 20-minute honest attempt. |
| Project (RailFlow) | 45–90 min | Follow the daily shipping rule from `roadmap-to-aug-26.md`: one tangible artifact per day (commit, test, design decision, integration experiment, or eval result). |

Weekly (in addition to daily blocks):

- **1x full system design write-up** (60–90 min, untimed first, then timed once you've done ~4).
- **1x retro** (15 min, Sunday): what shipped, what's blocked, what pattern you keep missing in
  LeetCode, adjust next week's problem list accordingly.
- Starting Week 5: **1x mock interview** (coding, system design, or behavioral — rotate).

## Phase 1 — Weeks 1–4 (Aug 18 – Sep 14): finish the vertical slice + build fundamentals

Project: finish exactly what `roadmap-to-aug-26.md` already specifies (Resilience4j → Kafka →
asset/alert state → RAG → tool calling → structured LLM explanation), then in the remaining
days of the phase:

- Add **security basics** before any write-capable AI tool exists: authentication, org/tenant
  isolation on every query (multi-tenant leakage is a classic Staff-level design smell —
  `requirements.md` already flags this, so implement it, don't just document it).
  `application.yaml` currently has a plaintext DB password — externalize it (env var / secrets
  manager) now; this is a concrete, tellable "I caught this and fixed it" story.
- Add **OpenTelemetry** basics (trace ID propagation across the controller → service →
  Feign/Kafka path) so you have a real observability story, not just a claim in
  `architecture.md`.
- Write down, per major decision, a **1-paragraph ADR** (Architecture Decision Record) in
  `railflow/docs/adr/`: e.g. "why hexagonal architecture over layered," "why Feign+Resilience4j
  over a raw RestTemplate," "why the LLM never sees raw DB rows." These become your system
  design and behavioral answers verbatim — write them as you go, not the week before interviews.

### Phase 1, week by week

**LeetCode** (aim for correctness and a clean explanation over speed this phase — timing starts
in Phase 2):

| Week | Focus | Problems (illustrative, swap freely within the pattern) | Volume |
|---|---|---|---|
| 1 | Arrays & strings basics: iteration, in-place mutation, frequency counting | Two Sum, Contains Duplicate, Valid Anagram, Best Time to Buy/Sell Stock, Valid Parentheses | 8–10 |
| 2 | Hashmaps + two pointers | Group Anagrams, Top K Frequent Elements, Valid Palindrome, Two Sum II, 3Sum, Container With Most Water | 8–10 |
| 3 | Sliding window + binary search (new patterns) + 1 linked-list refresher | Longest Substring Without Repeating Characters, Minimum Window Substring (stretch), Binary Search, Search in Rotated Sorted Array; refresh: Reverse Linked List, Merge Two Sorted Lists | 8–10 |
| 4 | Stacks/queues + intro trees (BFS/DFS) + 1 linked-list refresher | Valid Parentheses variants, Min Stack, Invert Binary Tree, Maximum Depth of Binary Tree, Same Tree; refresh: Linked List Cycle, Merge K Sorted Lists (stretch) | 8–10 |

For every problem: name the pattern before coding, solve without looking at the solution for at
least 20 minutes, then state time/space complexity out loud as if to an interviewer. If stuck
past 20 minutes, read *just enough* of a hint to unblock (not the full solution), then redo it
unaided 2 days later. Keep a running list of "problems I had to redo" — that list is your
Phase-1 exit ticket; don't move to Phase 2 mediums until it's short.

**System design** (zero to first case study in 4 weeks):

| Week | Focus | Goal |
|---|---|---|
| 1 | Vocabulary and mental models: client-server model, DNS, HTTP/REST basics, latency vs throughput, vertical vs horizontal scaling, what "availability" and "durability" mean | Be able to define each term in one sentence, unaided, no case study yet |
| 2 | Data layer: SQL vs NoSQL tradeoffs, indexing basics, replication (leader/follower), what a cache is and why (read-through/write-through), CDN basics | Explain, from memory, why you'd choose Postgres vs a NoSQL store for a given access pattern |
| 3 | Scaling & messaging: load balancers, horizontal partitioning/sharding, queues vs pub/sub, at-least-once vs exactly-once delivery — deliberately mirrored against why RailFlow uses Kafka | Write 1 page connecting each concept to a concrete RailFlow decision |
| 4 | First case study, untimed, written not just discussed: **"design a URL shortener"** (the canonical first case study — small surface area, forces you to touch API design, data model, and one scaling decision) | Produce requirements → back-of-envelope estimate → API → data model → high-level diagram → one bottleneck + fix, on paper, even if it's rough |

Free resources that match this zero-to-one pace: the "System Design Primer" GitHub repo for
vocabulary, and ByteByteGo's free YouTube fundamentals videos (load balancing, caching,
databases) as your 30-min theory slot for weeks 1–3. Save Alex Xu's book chapters for Phase 2
once you have vocabulary to hang them on.

LLM/GenAI theory: prompt/context engineering, RAG pipeline mechanics, embeddings basics, why
tool-calling beats letting the model free-write SQL — all of which you're implementing directly
in `ai-design.md`, so theory and build reinforce each other this phase.

## Phase 2 — Weeks 5–8 (Sep 15 – Oct 12): harden the project + go deeper

Project — move RailFlow from "vertical slice" to "defensible under Staff-level questioning":

- **Evaluation harness**: automate the eval set from `ai-design.md` (retrieval relevance,
  tool-selection accuracy, grounded-answer rate, structured-output validity) so you can show a
  number, not a vibe, when asked "how do you know your AI feature works?"
- **Second AI use case** beyond Q&A — e.g. an agentic maintenance-recommendation flow that
  chains 2+ tool calls, or a proactive alert-summarization job. One extra use case proves the
  first wasn't a lucky demo.
- **Adversarial testing**: actually run the failure cases already listed in `ai-design.md`
  (prompt injection via retrieved docs, malformed output, conflicting tool results) and record
  what happened. This is one of the highest-signal things you can bring to an FDE interview,
  because most candidates only demo the happy path.
- **CI**: GitHub Actions running backend tests + (if added) a Python eval job on every PR.
- Cost/latency instrumentation on the AI path (tokens, $ per request, p50/p95 latency) — Staff
  FDEs get asked about unit economics of AI features, not just accuracy.

LeetCode: move to mediums covering trees/graphs (BFS/DFS), heaps, intervals, and basic DP
(1D and simple 2D). Start timing yourself (25–30 min per medium). Re-solve, from memory, any
problem you got wrong 5–7 days later — spaced repetition, not one-and-done.

System design: distributed systems depth — consistency models, idempotency and exactly-once
semantics (you already need this for Kafka consumer dedup and for `UNKNOWN`-state
reconciliation in `architecture.md` — connect the dots explicitly), rate limiting, circuit
breakers (Resilience4j, which you'll have just built), multi-tenant SaaS design. Do 1–2
case studies per week: "design a rate limiter," "design a notification system," and — the one
that doubles as project prep — "design RailFlow's AI layer to serve 1,000 concurrent ops users."

LLM/GenAI theory: agent architectures (ReAct/tool-loop patterns), guardrails and treating
retrieved content as untrusted input (already a stated principle in `ai-design.md` — go one
level deeper into *how* you enforce it), context-window/cost budgeting, and eval-driven
development for LLM features.

## Phase 3 — Weeks 9–13 (Oct 13 – Nov 16): interview-readiness sprint

Project: stop adding scope. Polish instead — README with an architecture diagram and a 2-minute
demo script (the Aug 26 demo script in `roadmap-to-aug-26.md` is your base), record a short
Loom/video walkthrough, write a short public write-up ("what I'd do differently at 10x scale" /
"where the LLM boundary is and why"). A written narrative is what turns a repo into an
interview asset.

LeetCode: fill gaps only — pull your retro notes from Phases 1–2, identify your 2–3 weakest
patterns, and drill only those plus timed mixed sets (4 random mediums under 90 minutes,
simulating a real loop).

System design: 2 timed mock designs/week with a friend, mentor, or by recording yourself
narrating out loud. Focus on the structure interviewers grade: requirements → scale
estimation → API/data model → high-level design → deep dive → failure modes/tradeoffs.

Behavioral / Staff signal: write out 6–8 STAR stories mapped directly to real decisions you
made on RailFlow, e.g.:

- Build vs. buy: choosing an adapter over Railinc's permissioned API (`integration-matrix.md`).
- A reliability tradeoff: why writes aren't blindly retried and what `UNKNOWN` + reconciliation
  means (`architecture.md`).
- An AI safety boundary you enforced: "the LLM never bypasses backend authorization."
- A failure you deliberately induced and fixed (from the adversarial testing in Phase 2).

Mocks: at least one full-loop mock (coding + design + behavioral back to back) in the final two
weeks to build stamina, not just skill.

## Weekly self-review checklist (use every Sunday)

- [ ] Did I ship a tangible artifact every day this week (per the daily shipping rule)?
- [ ] Can I explain today's project change in under 60 seconds, including *why*, out loud?
- [ ] Which LeetCode pattern did I get wrong, and is it scheduled for a redo in 5–7 days?
- [ ] Did I write (not just read) one system design case study this week?
- [ ] Did I record one new ADR / decision note for the interview-story bank?
- [ ] What's the single biggest risk to next week's plan, and what's the mitigation?

## Reference reading (pull from as your 30-min theory slot, don't binge)

- System design: "System Design Primer" (GitHub, free) + ByteByteGo fundamentals videos for
  Weeks 1–3 vocabulary; *System Design Interview* Vol. 1–2 (Alex Xu) once you hit Week 4's
  first case study and through Phase 2; *Designing Data-Intensive Applications* (Kleppmann) in
  Phase 2 for the depth a Staff-level answer needs.
- LLM/agents: your own `ai-design.md` is already the right shape — treat vendor docs
  (Anthropic/OpenAI tool-use guides, RAG evaluation write-ups) as supplementary, not primary.
- LeetCode: start from an easy-heavy fundamentals set (Week 1–2 table above) before moving to
  NeetCode 150 / Blind 75 mediums in Phase 2; add company-tagged sets only in Phase 3 if you
  know target companies.
