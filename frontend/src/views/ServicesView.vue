<template>
  <section class="page-section glass-panel">
    <div class="section-heading">
      <div>
        <p>Service inventory</p>
        <h2>Tracked microservices</h2>
      </div>
    </div>

    <div class="service-table">
      <div class="service-table__row service-table__row--head">
        <span>Service</span>
        <span>Status</span>
        <span>CPU</span>
        <span>RAM</span>
        <span>Messages</span>
        <span>Peak</span>
      </div>
      <div v-for="service in services" :key="service.id" class="service-table__row">
        <strong>{{ service.name }}</strong>
        <StatusBadge :status="service.status" />
        <span>{{ service.cpu.toFixed(1) }}%</span>
        <span>{{ service.ram.toFixed(1) }}%</span>
        <span>{{ formatNumber(service.messages) }}</span>
        <span>{{ service.peak }}</span>
      </div>
    </div>
  </section>
</template>

<script setup>
import StatusBadge from '../components/common/StatusBadge.vue';
import { useAnalytics } from '../composables/useAnalytics';
import { formatNumber } from '../utils/formatters';

const { services } = useAnalytics();
</script>
