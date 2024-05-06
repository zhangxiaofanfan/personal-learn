import App from './App'

// #ifndef VUE3
import Vue from 'vue'
import './uni.promisify.adaptor'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import { Button,Form,Message } from 'element-ui';
Vue.component(Button.name, Button);
Vue.component(Form.name, Form);
Vue.component(Message.name, Form);

Vue.use(ElementUI);
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
  ...App
})

app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif
