import { createApp } from 'vue'
import App from './App.vue'

// 引入路由器
import router from './router'

// 引入 element-plus 组件
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'

// 引入 pinia
import { createPinia } from 'pinia'
const pinia = createPinia()

// 创建应用
var app = createApp(App)

// 使用 element-plus
app.use(ElementPlus)
// 使用路由器
app.use(router)
// 使用 pinia
app.use(pinia)
// 进行挂载操作
app.mount('#app')
