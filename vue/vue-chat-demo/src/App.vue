<template>
  <div class="app">
    <Header />
    <!-- 导航区 -->
    <div class="navigate">
      <RouterLink :to="{ name: 'chat' }" active-class="active">群聊</RouterLink>
      <RouterLink :to="{ name: 'add-user' }" active-class="active">新增好友</RouterLink>
      <RouterLink :to="{ name: 'add-group' }" active-class="active">加入群聊</RouterLink>
    </div>
    <!-- 展示区 -->
    <div class="main-content">
      <RouterView></RouterView>
    </div>
  </div>
</template>

<!-- <script lang="ts">
// 写 JS 或 TS 使用
export default {
  name: 'App' // 组件名称
}
</script> -->

<script lang="ts" setup>
import Header from '@/components/Header.vue'
import { RouterView } from 'vue-router'
import { RouterLink } from 'vue-router'
</script>

<style>
/* App */
.navigate {
  display: flex;
  justify-content: space-around;
  margin: 0 100px;
}
.navigate a {
  display: block;
  text-align: center;
  width: 90px;
  height: 40px;
  line-height: 40px;
  border-radius: 10px;
  background-color: gray;
  text-decoration: none;
  color: white;
  font-size: 14px;
  letter-spacing: 5px;
}
.navigate a.active {
  background-color: #94967e;
  color: #ffc268;
  font-weight: 900;
  text-shadow: 0 0 1px black;
  font-family: 微软雅黑;
}
.main-content {
  margin: 0 auto;
  margin-top: 30px;
  border-radius: 10px;
  width: 90%;
  height: 400px;
  border: 1px solid;
}
</style>