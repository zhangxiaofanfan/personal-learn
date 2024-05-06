// 创建一个路由器, 并暴露出去
// 第一步: 引入 createRouter
import {createRouter, createWebHistory} from 'vue-router'

// 引入路由组件
import Chat from '@/components/Chat.vue'
import AddUser from '@/components/AddUser.vue'
import AddGroup from '@/components/AddGroup.vue'

// 第二步: 创建路由器
const router = createRouter({
  history: createWebHistory(), // 路由器的工作模式
  routes: [
    {
      name: 'chat',
      path: '/',
      component: Chat
    },
    {
      name: 'chat',
      path: '/chat',
      component: Chat
    },
    {
      name: 'add-user',
      path: '/add-user',
      component: AddUser
    },
    {
      name: 'add-group',
      path: '/add-group',
      component: AddGroup
    },
  ]
})

// 第三步: 暴露出路由规则
export default router