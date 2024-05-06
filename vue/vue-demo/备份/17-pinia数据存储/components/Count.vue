<template>
  <div class="count">
    <h2>当前求和为: {{ countState.sum }}</h2>
    <select v-model.number="n">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <button @click="addCount">加</button>
    <button @click="subCount">减</button>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Count'
}
</script>

<script lang="ts" setup>
import { ref } from 'vue'
import { useConutStore } from '@/store/count'

let countState = useConutStore()
let n = ref(1)

function addCount() {
  // 第一种修改方式: 直接通过数据项进行修改
  // countState.sum += n.value
  // 第二种修改方式: 直接通过对象的形式对数据进行修改
  countState.$patch({
    sum: countState.sum + n.value
  })
}
function subCount() {
  countState.sum -= n.value
}
</script>

<style scoped>
.count {
  background-color: skyblue;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 0 10px;
}
select,
button {
  margin: 0 5px;
  height: 25px;
}
</style>