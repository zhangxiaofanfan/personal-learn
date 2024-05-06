<template>
  <div class="person">
    <h1>需求: 水温达到60度或高度达到80厘米时发送请求</h1>
    <h2>水温: {{ temp }} C</h2>
    <h2>高度: {{ height }} cm</h2>
    <button @click="changeTemp">水温+10</button>
    <button @click="changeHeight">高度+10</button>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Person'
}
</script>

<script lang="ts" setup>
import { ref, watchEffect } from 'vue'

let temp = ref(10)
let height = ref(0)

function changeTemp() {
  temp.value += 10
}
function changeHeight() {
  height.value += 10
}

// 方式一: 使用watch实现需求内容
// watch([temp, height], (value) => {
//   const [temp, height] = value
//   console.log(temp, height)
//   if (temp >= 60 || height >= 80) {
//     console.log('发送请求')
//   }
// })

// 方式二: 使用watchEffect实现需求内容
watchEffect(() => {
  console.log(temp.value, height.value)
  if (temp.value >= 60 || height.value >= 80) {
    console.log('发送请求')
  }
})
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