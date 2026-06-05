import { storeToRefs } from 'pinia';
import { useAnalyticsStore } from '../stores/analytics';

export function useAnalytics() {
  const store = useAnalyticsStore();
  const state = storeToRefs(store);

  return {
    ...state,
    refresh: store.refresh,
    setRange: store.setRange
  };
}
