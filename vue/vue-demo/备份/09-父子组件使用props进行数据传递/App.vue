<template>
  <ul>
    <li v-for="person in persons" :key="person.id">{{ person.id }}-{{ person.name }}-{{ person.age }}</li>
  </ul>
  <button @click="changeFirstPersonAge">增加第一个人年龄</button>
  <Person a="哈哈" :persons="persons" />
</template>

<script lang="ts">
import Person from './components/Person.vue'

// 写 JS 或 TS 使用
export default {
  name: 'App', // 组件名称
  components: {
    Person
  }
}
</script>

<script lang="ts" setup>
import { reactive } from 'vue'
import { Persons } from '@/types'
let persons = reactive<Persons>([
  { id: '1', name: '张帆1', age: 20 },
  { id: '2', name: '张帆2', age: 21 },
  { id: '3', name: '张帆3', age: 22 }
])
function changeFirstPersonAge() {
  persons[0].age += 1
}
</script>
