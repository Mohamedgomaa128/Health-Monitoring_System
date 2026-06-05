<template>
  <section class="memory-chart glass-panel">
    <div class="section-heading">
      <div>
        <p>Memory and storage</p>
        <h2>RAM / Disk trend</h2>
      </div>
      <span class="section-chip">10 samples</span>
    </div>

    <svg viewBox="0 0 420 180" preserveAspectRatio="none" role="img" aria-label="RAM and disk utilization trend">
      <defs>
        <linearGradient id="ramFill" x1="0" x2="0" y1="0" y2="1">
          <stop offset="0%" stop-color="#38bdf8" stop-opacity="0.38" />
          <stop offset="100%" stop-color="#38bdf8" stop-opacity="0" />
        </linearGradient>
        <linearGradient id="diskFill" x1="0" x2="0" y1="0" y2="1">
          <stop offset="0%" stop-color="#f59e0b" stop-opacity="0.32" />
          <stop offset="100%" stop-color="#f59e0b" stop-opacity="0" />
        </linearGradient>
      </defs>
      <path class="memory-chart__area memory-chart__area--ram" :d="areaPath(ramSeries)" />
      <path class="memory-chart__line memory-chart__line--ram" :d="linePath(ramSeries)" />
      <path class="memory-chart__area memory-chart__area--disk" :d="areaPath(diskSeries)" />
      <path class="memory-chart__line memory-chart__line--disk" :d="linePath(diskSeries)" />
    </svg>

    <div class="memory-chart__legend">
      <span><i class="memory-chart__dot memory-chart__dot--ram" /> RAM {{ averageRam.toFixed(1) }}%</span>
      <span><i class="memory-chart__dot memory-chart__dot--disk" /> Disk {{ averageDisk.toFixed(1) }}%</span>
    </div>
  </section>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  services: { type: Array, required: true }
});

const ramSeries = computed(() => buildSeries('ram'));
const diskSeries = computed(() => buildSeries('disk'));
const averageRam = computed(() => average(props.services.map((service) => service.ram)));
const averageDisk = computed(() => average(props.services.map((service) => service.disk)));

function buildSeries(key) {
  return Array.from({ length: 10 }, (_, index) => {
    const values = props.services.map((service) => {
      const historyBias = service.history[index] - service.cpu;
      return Math.max(8, Math.min(98, service[key] + historyBias * 0.38));
    });
    return average(values);
  });
}

function average(values) {
  return values.reduce((total, value) => total + value, 0) / (values.length || 1);
}

function point(value, index, series) {
  const x = (index / (series.length - 1)) * 420;
  const y = 170 - (value / 100) * 150;
  return [x, y];
}

function linePath(series) {
  return series
    .map((value, index) => {
      const [x, y] = point(value, index, series);
      return `${index === 0 ? 'M' : 'L'} ${x} ${y}`;
    })
    .join(' ');
}

function areaPath(series) {
  return `${linePath(series)} L 420 170 L 0 170 Z`;
}
</script>
