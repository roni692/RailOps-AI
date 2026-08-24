# Bracket

An original, static local prototype for a paid-ranking discovery board. It is intentionally not a copy of Outbid's brand, interface, content, or rules.

## Run locally

```bash
cd bidboard
python3 -m http.server 4173
```

Open `http://localhost:4173`.

The claim flow is a browser-only preview: listings persist in `localStorage` and no money is collected.

## Production path

Do not connect the client-side claim form directly to a payment provider. A production implementation needs:

1. A server-side database for listings and payments.
2. Stripe Checkout created from a server endpoint.
3. A signed Stripe webhook that records completed payments idempotently.
4. An atomic transaction that updates the listing amount and rank.
5. Authentication or signed email management links, URL verification, moderation, rate limits, and audit logs.

For a Hostinger VPS, deploy behind Caddy or Nginx with TLS and run the application/database with Docker Compose. The static prototype can be served directly by the web server, but the payment and ranking logic must move to a backend before accepting real payments.
