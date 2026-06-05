<template>
  <section class="activity-feed glass-panel">
    <div class="section-heading">
      <div>
        <p>Events</p>
        <h2>Activity feed</h2>
      </div>
    </div>

    <ul>
      <li v-for="event in items" :key="event.id">
        <StatusBadge :status="event.status" />
        <div>
          <strong>{{ event.service }}</strong>
          <span>{{ event.text }}</span>
        </div>
        <time>{{ event.time }}</time>
      </li>
    </ul>
  </section>
</template>

<script setup>
import StatusBadge from '../common/StatusBadge.vue';

defineProps({
  items: { type: Array, required: true }
});
</script>
