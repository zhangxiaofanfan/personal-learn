// 创建一个路由器, 并暴露出去
// 第一步: 引入 createRouter
import {createRouter, createWebHistory} from 'vue-router'
import About from '@/views/About.vue'
import Home from '@/views/Home.vue'
import News from '@/views/News.vue'
import Detail from '@/views/Detail.vue'

// 第二步: 创建路由器
const router = createRouter({
  history: createWebHistory(), // 路由器的工作模式
  routes: [
    {
      name: 'shouye',
      path: '/about',
      component: About
    },
    {
      name: 'xinwen',
      path: '/news',
      component: News,
      children: [
        {
          name: 'xiangqing',
          path: 'detail/:id/:title/:content',
          component: Detail
        }
      ]
    },
    {
      name: 'zhuye',
      path: '/home',
      component: Home
    },
  ]
})

// 第三步: 暴露出路由规则
export default router