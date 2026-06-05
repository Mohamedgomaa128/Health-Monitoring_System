<template>
  <div class="dashboard-view">
    <p v-if="error" class="notice">{{ error }}</p>

    <section class="metrics-row">
      <MetricCard label="CPU Usage" :value="summary.cpu" :trend="3.4" :icon="Cpu" accent="#14b8a6" />
      <MetricCard label="RAM Usage" :value="summary.ram" :trend="-1.8" :icon="MemoryStick" accent="#38bdf8" />
      <MetricCard label="Disk Usage" :value="summary.disk" :trend="2.2" :icon="HardDrive" accent="#f59e0b" />
      <MetricCard
        label="Health Messages"
        :value="summary.messages"
        :trend="6.5"
        :icon="Radio"
        accent="#f43f5e"
        suffix=""
        :digits="0"
      />
    </section>

    <section class="dashboard-grid">
      <CpuGauge :value="summary.cpu" />
      <MemoryChart :services="services" />
      <ServiceGrid :services="services" />
      <ActivityFeed :items="activity" />
      <PeakTimeHeatmap :heatmap="heatmap" />
    </section>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { Cpu, HardDrive, MemoryStick, Radio } from 'lucide-vue-next';
import { useAnalytics } from '../composables/useAnalytics';
import ActivityFeed from '../components/dashboard/ActivityFeed.vue';
import CpuGauge from '../components/dashboard/CpuGauge.vue';
import MemoryChart from '../components/dashboard/MemoryChart.vue';
import MetricCard from '../components/dashboard/MetricCard.vue';
import PeakTimeHeatmap from '../components/dashboard/PeakTimeHeatmap.vue';
import ServiceGrid from '../components/dashboard/ServiceGrid.vue';

const { summary, services, activity, heatmap, error, refresh } = useAnalytics();

onMounted(refresh);
</script>
