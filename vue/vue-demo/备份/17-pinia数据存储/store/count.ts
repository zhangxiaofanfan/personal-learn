import { defineStore } from "pinia"

// pinia 数据存储
export const useConutStore = defineStore('count', {
  // actions 里放置一个一个的方法, 用于相应组件中的 动作
  actions: {
    
  },
  // 真正存储数据的地方
  state() {
      return {
        sum: 6
      }
  },
  getters: {
    bigSum:state => state.sum * 10
  }
})