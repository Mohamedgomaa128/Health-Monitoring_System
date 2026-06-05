<template>
  <span class="status-badge" :class="`status-badge--${status}`">
    <span class="status-badge__dot" />
    {{ label }}
  </span>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  status: { type: String, default: 'online' }
});

const label = computed(() => ({
  online: 'Online',
  warning: 'Warning',
  critical: 'Critical'
})[props.status] || 'Online');
</script>
