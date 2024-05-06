<template>
  <div class="person">
    <ul>
      <li v-for="person in persons" :key="person.id">{{ person.id }}-{{ person.name }}-{{ person.age }}</li>
    </ul>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Person'
}
</script>

<script lang="ts" setup>
import { Persons } from '@/types'
import { defineProps, withDefaults } from 'vue'
// 1.只进行数据接收
// defineProps(['persons'])
// 2. 接收 + 限制类型
// defineProps<{ persons: Persons }>()
// 3. 接收 + 限制类型 + 限制必要性 + 指定默认值
withDefaults(defineProps<{ persons: Persons }>(), {
  persons: () => [{ id: '0', name: '张帆', age: 10 }]
})
</script>

<style scoped>
/* 写css使用 */
.person {
  background-color: skyblue;
  box-shadow: 0 0 10px;
  border-radius: 10px;
  padding: 20px;
}
button {
  margin: 0 5px;
}
</style>