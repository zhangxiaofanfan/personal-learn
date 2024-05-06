<template>
  <div class="person">
    sum为: {{ sum }}
    <button @click="changeSum">求和+1</button>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Person'
}
</script>

<script lang="ts" setup>
import { ref, watch } from 'vue'

let sum = ref(0)

function changeSum() {
  sum.value += 1
}

// 监视器, 情况1: 监视【ref】定义的【基本类型】数据
// 监视器, 当求和参数 sum 大于10时停止监视
const stopWatch = watch(sum, (newValue, oldValue) => {
  console.log('watch', newValue, oldValue)
  if (newValue >= 10) {
    stopWatch()
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