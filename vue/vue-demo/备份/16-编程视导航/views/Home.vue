<template>
  <div class="home"><img src="http://www.atguigu.com/images/index_new/logo.png" alt="" /></div>
</template>

<script lang="ts">
export default {
  name: 'Home'
}
</script>

<script lang="ts" setup>
// 编程视路由, 实现需求: 在首页观看三秒之后跳转到新闻页面
// 生命周期函数: 当组件被挂载之后进行回调的函数
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()

onMounted(() => {
  setTimeout(() => {
    router.push({
      name: 'xinwen'
    })
  }, 3000)
})
</script>

<style scoped>
.home {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>