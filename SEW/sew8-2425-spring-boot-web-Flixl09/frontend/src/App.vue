<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <nav class="navbar navbar-expand-lg bg-body-tertiary fixed-top">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Grocery Store</a>
        <div class="navbar-nav">
          <RouterLink to="/" class="nav-link">Home</RouterLink>
          <RouterLink to="/items" class="nav-link">Items</RouterLink>
        </div>
      </div>
    </nav>
  <RouterView />
</template>

<style>
</style>