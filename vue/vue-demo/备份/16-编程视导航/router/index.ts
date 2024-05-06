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
          component: Detail,
          // 第一种写法: 将路由收到的params作为 props 传参给子组件
          props: true,
          // 第二种写法, 函数写法, 自己决定将什么传递给子组件
          // props(route) {
          //   return route.query
          // }
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