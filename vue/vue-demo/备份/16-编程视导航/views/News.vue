<template>
  <div class="news">
    <ul>
      <!-- 展示区域 -->
      <li v-for="news in newsList" :key="news.id">
        <button @click="showNewsDetail(news)">点击查看详情</button>
        <router-link
          :to="{
            name: 'xiangqing',
            params: {
              id: news.id,
              title: news.title,
              content: news.content
            }
          }"
        >
          {{ news.title }}
        </router-link>
      </li>
    </ul>
    <!-- 展示区 -->
    <div class="news-content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'News'
}
</script>

<script lang="ts" setup>
import { reactive } from 'vue'
import { RouterView, useRouter } from 'vue-router'
import { RouterLink } from 'vue-router'

const newsList = reactive([
  { id: '1', title: '很好的抗癌食物', content: '西蓝花' },
  { id: '2', title: '如何一夜暴富', content: '买彩票' },
  { id: '3', title: '震惊, 万万没想到', content: '明天是周一' },
  { id: '4', title: '好消息, 好消息', content: '没有好消息' }
])

const router = useRouter()
function showNewsDetail(news) {
  router.push({
    name: 'xiangqing',
    params: {
      id: news.id,
      title: news.title,
      content: news.content
    }
  })
}
</script>

<style scoped>
.news {
  padding: 0 20px;
  display: flex;
  justify-content: space-between;
  height: 100%;
}
.news ul {
  margin-top: 30px;
  list-style: none;
  padding-left: 10px;
}
.news li > a {
  font-size: 18px;
  line-height: 40px;
  text-decoration: none;
  color: #64967e;
  text-shadow: 0 0 1px rgb(0, 84, 0);
}
.news-content {
  width: 70%;
  height: 90%;
  border: 1px solid;
  margin-top: 20px;
  border-radius: 10px;
}
</style>