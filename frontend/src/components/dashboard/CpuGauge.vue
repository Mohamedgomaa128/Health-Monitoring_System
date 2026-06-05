<template>
  <section class="cpu-gauge glass-panel">
    <div class="section-heading">
      <div>
        <p>Cluster Load</p>
        <h2>CPU pressure</h2>
      </div>
      <StatusBadge :status="status" />
    </div>

    <div class="cpu-gauge__dial">
      <svg viewBox="0 0 140 140" role="img" aria-label="Average CPU gauge">
        <circle class="cpu-gauge__track" cx="70" cy="70" r="56" />
        <circle class="cpu-gauge__progress" cx="70" cy="70" r="56" :style="gaugeStyle" />
      </svg>
      <div class="cpu-gauge__value">
        <AnimatedCounter :value="value" suffix="%" />
        <span>Average CPU</span>
      </div>
    </div>
  </section>
</template>

<script setup>
import { computed } from 'vue';
import AnimatedCounter from '../common/AnimatedCounter.vue';
import StatusBadge from '../common/StatusBadge.vue';

const props = defineProps({
  value: { type: Number, required: true }
});

const status = computed(() => props.value > 82 ? 'critical' : props.value > 68 ? 'warning' : 'online');
const gaugeStyle = computed(() => ({
  '--dash': `${Math.min(props.value, 100) * 3.52} 352`
}));
</script>
