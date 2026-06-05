<template>
  <div class="app-shell">
    <AppSidebar />
    <main class="app-main">
      <AppHeader />
      <RouterView />
    </main>
  </div>
</template>

<script setup>
import AppHeader from './AppHeader.vue';
import AppSidebar from './AppSidebar.vue';
</script>
