<template>
  <span>{{ displayValue }}</span>
</template>

<script setup>
import { computed, onMounted, ref, watch } from 'vue';

const props = defineProps({
  value: { type: Number, required: true },
  suffix: { type: String, default: '' },
  digits: { type: Number, default: 1 }
});

const current = ref(0);

const displayValue = computed(() => `${current.value.toFixed(props.digits)}${props.suffix}`);

function animateTo(target) {
  const start = current.value;
  const startTime = performance.now();
  const duration = 650;

  function tick(now) {
    const progress = Math.min((now - startTime) / duration, 1);
    const eased = 1 - Math.pow(1 - progress, 3);
    current.value = start + (target - start) * eased;

    if (progress < 1) requestAnimationFrame(tick);
  }

  requestAnimationFrame(tick);
}

onMounted(() => animateTo(props.value));
watch(() => props.value, animateTo);
</script>
