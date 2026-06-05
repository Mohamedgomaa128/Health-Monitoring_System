<template>
  <section class="service-grid glass-panel">
    <div class="section-heading">
      <div>
        <p>Microservices</p>
        <h2>Live service state</h2>
      </div>
      <span class="section-chip">{{ services.length }} tracked</span>
    </div>

    <div class="service-grid__items">
      <article v-for="service in services" :key="service.id" class="service-card">
        <div class="service-card__header">
          <h3>{{ service.name }}</h3>
          <StatusBadge :status="service.status" />
        </div>
        <div class="service-card__metrics">
          <span>CPU <strong>{{ service.cpu.toFixed(1) }}%</strong></span>
          <span>RAM <strong>{{ service.ram.toFixed(1) }}%</strong></span>
          <span>Disk <strong>{{ service.disk.toFixed(1) }}%</strong></span>
        </div>
        <svg class="sparkline" viewBox="0 0 120 32" preserveAspectRatio="none">
          <polyline :points="sparkline(service.history)" />
        </svg>
      </article>
    </div>
  </section>
</template>

<script setup>
import StatusBadge from '../common/StatusBadge.vue';

defineProps({
  services: { type: Array, required: true }
});

function sparkline(points) {
  const max = Math.max(...points);
  const min = Math.min(...points);
  const range = max - min || 1;

  return points
    .map((point, index) => {
      const x = (index / (points.length - 1)) * 120;
      const y = 30 - ((point - min) / range) * 26;
      return `${x},${y}`;
    })
    .join(' ');
}
</script>
