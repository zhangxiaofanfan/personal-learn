<template>
  <div class="header">
    <h2 class="title">Vue 路由测试</h2>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Header'
}
</script>

<script lang="ts" setup>
</script>

<style>
/* App */
.header {
  text-align: center;
  word-spacing: 5px;
  margin: 30px 0;
  height: 70px;
  line-height: 70px;
  background-image: linear-gradient(45deg, gray, white);
  border-radius: 10px;
  box-shadow: 0 0 2px;
  font-size: 20px;
}
</style>