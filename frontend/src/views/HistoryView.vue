<template>
  <section class="page-section glass-panel">
    <div class="section-heading">
      <div>
        <p>Historical analytics</p>
        <h2>Last sampled CPU trend</h2>
      </div>
      <span class="section-chip">{{ range.from }} to {{ range.to }}</span>
    </div>

    <div class="history-list">
      <article v-for="service in services" :key="service.id" class="history-item">
        <div>
          <h3>{{ service.name }}</h3>
          <span>Peak {{ service.peak }}</span>
        </div>
        <svg viewBox="0 0 240 52" preserveAspectRatio="none">
          <polyline :points="sparkline(service.history)" />
        </svg>
      </article>
    </div>
  </section>
</template>

<script setup>
import { useAnalytics } from '../composables/useAnalytics';

const { services, range } = useAnalytics();

function sparkline(points) {
  const max = Math.max(...points);
  const min = Math.min(...points);
  const rangeValue = max - min || 1;

  return points
    .map((point, index) => {
      const x = (index / (points.length - 1)) * 240;
      const y = 48 - ((point - min) / rangeValue) * 42;
      return `${x},${y}`;
    })
    .join(' ');
}
</script>
