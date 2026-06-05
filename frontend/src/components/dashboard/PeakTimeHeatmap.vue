<template>
  <section class="heatmap glass-panel">
    <div class="section-heading">
      <div>
        <p>Peak analysis</p>
        <h2>Resource intensity by hour</h2>
      </div>
    </div>

    <div class="heatmap__grid">
      <span class="heatmap__corner" />
      <span v-for="hour in hours" :key="hour" class="heatmap__hour">{{ hour }}</span>
      <template v-for="row in heatmap" :key="row.service">
        <span class="heatmap__service">{{ row.service }}</span>
        <span
          v-for="(value, index) in row.hours"
          :key="`${row.service}-${index}`"
          class="heatmap__cell"
          :style="{ '--level': value / 100 }"
          :title="`${row.service}: ${value}% at ${hours[index]}`"
        />
      </template>
    </div>
  </section>
</template>

<script setup>
defineProps({
  heatmap: { type: Array, required: true }
});

const hours = ['00', '02', '04', '06', '08', '10', '12', '14', '16', '18', '20', '22'];
</script>
