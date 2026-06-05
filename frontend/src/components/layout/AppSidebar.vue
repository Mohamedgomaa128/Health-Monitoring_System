<template>
  <aside class="app-sidebar">
    <RouterLink class="app-sidebar__brand" to="/" aria-label="Health Monitoring">
      <Activity :size="24" />
    </RouterLink>

    <nav class="app-sidebar__nav" aria-label="Main navigation">
      <RouterLink v-for="item in items" :key="item.to" :to="item.to" :title="item.label">
        <component :is="item.icon" :size="20" />
      </RouterLink>
    </nav>
  </aside>
</template>

<script setup>
import { Activity, BarChart3, Clock3, Server } from 'lucide-vue-next';

const items = [
  { to: '/', label: 'Dashboard', icon: BarChart3 },
  { to: '/services', label: 'Services', icon: Server },
  { to: '/history', label: 'History', icon: Clock3 }
];
</script>
