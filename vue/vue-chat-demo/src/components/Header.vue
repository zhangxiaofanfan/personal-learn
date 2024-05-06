<template>
  <div class="header">
    <h2 class="title">当前用户: {{ cur_username }}</h2>
  </div>
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue'
import { useUserStore } from '@/store/user'

// pinia 获取数据
let cur_user = useUserStore()

// 通过设置get和set方法来修改计算属性的值
let cur_username = computed(() => {
  if (cur_user.user.user_id != '') {
    return cur_user.user.user_name
  }
  return '用户未登录'
})
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