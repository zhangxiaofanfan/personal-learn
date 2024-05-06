<template>
  <div class="person">
    <h1>姓名: {{ person.name }}</h1>
    <br />
    <h1>年龄: {{ person.age }}</h1>
    <br />
    <button @click="changeName">修改姓名</button>
    <button @click="changeAge">增加年龄</button>
    <button @click="changePerson">修改人物</button>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Person'
}
</script>

<script lang="ts" setup>
import { ref, watch } from 'vue'

let person = ref({ name: '张帆', age: 20 })

function changeName() {
  person.value.name += '~'
}

function changeAge() {
  person.value.age += 1
}

function changePerson() {
  person.value = { name: '小棉', age: 18 }
}

// 监视器, 情况4: 某一项数据
watch(
  () => person.value.name,
  (newValue, oldValue) => {
    console.log('person数据发生了变化', newValue, oldValue)
  }
)
</script>

<style>
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