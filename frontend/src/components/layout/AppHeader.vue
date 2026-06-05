<template>
  <header class="app-header">
    <div>
      <p>Health Monitoring System</p>
      <h1>{{ title }}</h1>
    </div>

    <form class="date-filter" @submit.prevent="applyRange">
      <label>
        <span>From</span>
        <input v-model="draft.from" type="date" />
      </label>
      <label>
        <span>To</span>
        <input v-model="draft.to" type="date" />
      </label>
      <button type="submit" :disabled="loading">
        <RefreshCcw :size="16" />
        <span>{{ loading ? 'Loading' : 'Refresh' }}</span>
      </button>
    </form>
  </header>
</template>

<script setup>
import { computed, reactive, watch } from 'vue';
import { RefreshCcw } from 'lucide-vue-next';
import { useRoute } from 'vue-router';
import { useAnalytics } from '../../composables/useAnalytics';

const route = useRoute();
const { range, loading, refresh, setRange } = useAnalytics();

const draft = reactive({ ...range.value });
const title = computed(() => ({
  dashboard: 'Operations dashboard',
  services: 'Service inventory',
  history: 'Historical analytics'
})[route.name] || 'Operations dashboard');

watch(range, (nextRange) => Object.assign(draft, nextRange));

function applyRange() {
  setRange({ from: draft.from, to: draft.to });
  refresh();
}
</script>
