<template>
  <ul class="news-list">
    <li>编号: {{ query.id }}</li>
    <li>标题: {{ query.title }}</li>
    <li>内容: {{ query.content }}</li>
  </ul>
</template>

<script lang="ts">
export default {
  name: 'Detail'
}
</script>

<script lang="ts" setup>
import { useRoute } from 'vue-router'
import { toRefs } from 'vue'

const ronte = useRoute()
// 避免结构出来的 query 参数失去响应式性能
const { query } = toRefs(ronte)
</script>

<style scoped>
.news-list {
  list-style: none;
  padding-left: 20px;
}
.news-list > li {
  line-height: 30px;
}
</style>