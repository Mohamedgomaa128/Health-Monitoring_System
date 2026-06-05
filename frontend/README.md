# Health Monitoring Frontend

Modern Vue 3 + Vite dashboard for the Health-Monitoring System.

## Scripts

```bash
corepack npm install
corepack npm run dev
corepack npm run build
```

The dev server runs on `http://localhost:3000` by default.

## Data Flow

The dashboard calls `/api/v1/analytics/summary?from=...&to=...`.
If the backend endpoint is not ready yet, the Pinia store falls back to local sample analytics so the UI remains usable during modernization.
