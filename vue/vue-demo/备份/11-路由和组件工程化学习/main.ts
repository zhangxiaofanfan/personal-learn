// 引入 createApp 用于创建应用
import {createApp} from 'vue'
// 引入 App 根组件
import App from './App.vue'
// 引入路由器
import router from './router'

// 创建一个应用
createApp(App)
// 使用路由器
.use(router)
// 挂载整个容器到app应用中
.mount("#app")