import { defineStore } from "pinia"

// pinia 数据存储
export const useUserStore = defineStore('user', {
  // 真正存储数据的地方
  state() {
      return {
        user: {
          user_id: '',
          user_name: ''
        }
      }
  }
})