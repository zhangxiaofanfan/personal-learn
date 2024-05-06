<template>
  <div class="person">
    姓: <input type="text" v-model="firstName" /> <br />
    名: <input type="text" v-model="lastName" /> <br />
    全名: {{ fullName }}
    <button @click="changeFullName">修改全名为li-si</button>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Person'
}
</script>

<script lang="ts" setup>
import { ref, computed } from 'vue'

let firstName = ref('张')
let lastName = ref('帆')
// 这样写是只读
// let fullName = computed(() => {
//   return firstName.value + '-' + lastName.value
// })

// 通过设置get和set方法来修改计算属性的值
let fullName = computed({
  get() {
    return firstName.value + '-' + lastName.value
  },
  set(val) {
    const [str1, str2] = val.split('-')
    firstName.value = str1
    lastName.value = str2
  }
})

function changeFullName() {
  fullName.value = 'li-si'
}
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