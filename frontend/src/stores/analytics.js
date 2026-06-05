import { defineStore } from 'pinia';
import { computed, ref } from 'vue';
import { api } from '../utils/api';
import { formatDateInput, statusFromLoad } from '../utils/formatters';

const baselineServices = [
  {
    id: 'ticketing',
    name: 'Ticketing Service',
    cpu: 42.6,
    ram: 58.4,
    disk: 63.2,
    messages: 18420,
    trend: 4.8,
    peak: '14:00',
    history: [34, 39, 41, 46, 44, 49, 43, 42, 45, 43]
  },
  {
    id: 'payments',
    name: 'Payment Gateway',
    cpu: 73.8,
    ram: 69.3,
    disk: 52.1,
    messages: 12680,
    trend: -2.1,
    peak: '19:00',
    history: [62, 68, 71, 78, 76, 75, 74, 73, 72, 74]
  },
  {
    id: 'identity',
    name: 'Identity API',
    cpu: 88.9,
    ram: 82.7,
    disk: 71.5,
    messages: 24610,
    trend: 8.6,
    peak: '11:00',
    history: [69, 72, 77, 81, 84, 86, 90, 88, 91, 89]
  },
  {
    id: 'notifications',
    name: 'Notifications',
    cpu: 35.4,
    ram: 44.2,
    disk: 48.9,
    messages: 9360,
    trend: 1.3,
    peak: '09:00',
    history: [28, 31, 29, 33, 37, 35, 36, 34, 38, 35]
  }
];

function buildHeatmap(services) {
  return services.map((service, serviceIndex) => ({
    service: service.name,
    hours: Array.from({ length: 12 }, (_, hourIndex) => {
      const wave = Math.sin((hourIndex + serviceIndex) / 2) * 18;
      return Math.max(12, Math.min(98, Math.round(service.cpu + wave + hourIndex)));
    })
  }));
}

export const useAnalyticsStore = defineStore('analytics', () => {
  const range = ref({
    from: formatDateInput(new Date(Date.now() - 7 * 24 * 60 * 60 * 1000)),
    to: formatDateInput(new Date())
  });
  const services = ref(baselineServices.map((service) => ({
    ...service,
    status: statusFromLoad(service.cpu, service.ram)
  })));
  const loading = ref(false);
  const error = ref('');
  const lastUpdated = ref(new Date());

  const summary = computed(() => {
    const count = services.value.length || 1;
    const totals = services.value.reduce((acc, service) => {
      acc.cpu += service.cpu;
      acc.ram += service.ram;
      acc.disk += service.disk;
      acc.messages += service.messages;
      return acc;
    }, { cpu: 0, ram: 0, disk: 0, messages: 0 });

    return {
      cpu: totals.cpu / count,
      ram: totals.ram / count,
      disk: totals.disk / count,
      messages: totals.messages,
      activeServices: services.value.filter((service) => service.status !== 'critical').length
    };
  });

  const activity = computed(() => services.value
    .slice()
    .sort((a, b) => b.cpu - a.cpu)
    .map((service, index) => ({
      id: `${service.id}-${index}`,
      service: service.name,
      status: service.status,
      text: service.status === 'critical'
        ? 'Resource pressure requires attention'
        : service.status === 'warning'
          ? 'Load is elevated'
          : 'Metrics are stable',
      time: `${index + 2} min ago`
    })));

  const heatmap = computed(() => buildHeatmap(services.value));

  function setRange(nextRange) {
    range.value = nextRange;
  }

  async function refresh() {
    loading.value = true;
    error.value = '';

    try {
      const { data } = await api.get('/analytics/summary', {
        params: { from: range.value.from, to: range.value.to }
      });

      if (Array.isArray(data?.services)) {
        services.value = data.services.map((service) => ({
          ...service,
          status: service.status || statusFromLoad(service.cpu, service.ram)
        }));
      }
    } catch (requestError) {
      error.value = 'Using local analytics sample until the backend endpoint is ready.';
    } finally {
      lastUpdated.value = new Date();
      loading.value = false;
    }
  }

  return {
    range,
    services,
    loading,
    error,
    lastUpdated,
    summary,
    activity,
    heatmap,
    refresh,
    setRange
  };
});
