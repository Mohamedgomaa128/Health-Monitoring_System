export function formatPercent(value, digits = 1) {
  return `${Number(value).toFixed(digits)}%`;
}

export function formatNumber(value) {
  return new Intl.NumberFormat('en-US').format(value);
}

export function formatDateInput(date) {
  return new Date(date).toISOString().slice(0, 10);
}

export function statusFromLoad(cpu, ram) {
  if (cpu >= 85 || ram >= 88) return 'critical';
  if (cpu >= 70 || ram >= 75) return 'warning';
  return 'online';
}
