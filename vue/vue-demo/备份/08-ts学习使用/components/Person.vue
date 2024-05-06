<template>
  <div class="person">
    <h1>编号: {{ person.id }}</h1>
    <h1>姓名: {{ person.name }}</h1>
    <h1>年龄: {{ person.age }}</h1>
    <br />
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
import { type PersonInter, type Persons } from '@/types'

let person: PersonInter = {
  id: '1',
  name: '张帆',
  age: 20
}

let persons: Persons = [
  { id: '1', name: '张帆1', age: 20 },
  { id: '2', name: '张帆2', age: 20 },
  { id: '3', name: '张帆3', age: 20 }
]
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