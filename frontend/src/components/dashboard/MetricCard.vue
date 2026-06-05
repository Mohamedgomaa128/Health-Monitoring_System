<template>
  <article class="metric-card glass-panel">
    <div class="metric-card__top">
      <div class="metric-card__icon" :style="{ '--accent': accent }">
        <component :is="icon" :size="20" />
      </div>
      <span class="metric-card__trend" :class="{ 'is-down': trend < 0 }">
        {{ trend > 0 ? '+' : '' }}{{ trend.toFixed(1) }}%
      </span>
    </div>
    <p class="metric-card__label">{{ label }}</p>
    <strong class="metric-card__value">
      <AnimatedCounter :value="value" :suffix="suffix" :digits="digits" />
    </strong>
    <div class="metric-card__bar" :aria-label="`${label} ${value}`">
      <span :style="{ width: `${Math.min(value, 100)}%`, background: accent }" />
    </div>
  </article>
</template>

<script setup>
import AnimatedCounter from '../common/AnimatedCounter.vue';

defineProps({
  label: { type: String, required: true },
  value: { type: Number, required: true },
  suffix: { type: String, default: '%' },
  digits: { type: Number, default: 1 },
  trend: { type: Number, default: 0 },
  accent: { type: String, default: '#14b8a6' },
  icon: { type: [Object, Function], required: true }
});
</script>
