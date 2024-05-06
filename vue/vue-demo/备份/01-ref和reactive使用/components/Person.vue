<template>
  <div class="person">
    <h2>姓名: {{ name }}</h2>
    <h2>年龄: {{ age }}</h2>
    <button @click="updateName">修改名称</button>
    <button @click="updateAge">修改年龄</button>
    <button @click="showTel">查看联系方式</button>
    <br />
    <h2>一辆{{ car.name }}价值{{ car.price }}万元</h2>
    <button @click="changePrice">修改汽车价格</button>
    <br />
    <ul>
      <li v-for="g in games" :key="g.id">{{ g.name }}</li>
    </ul>
    <button @click="changeFirstGameName">修改游戏名称</button>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Person'
}
</script>

<script lang="ts" setup>
import { ref } from 'vue'
import { reactive } from 'vue'

// 数据区域
let name = ref('张帆')
let age = ref(18)
let tel = '138-8888-8888'
let car = reactive({
  name: '奔驰',
  price: 100
})
let games = reactive([
  { id: 1, name: '王者荣耀' },
  { id: 2, name: '刺激战场' },
  { id: 3, name: '英雄联盟' }
])

// 方法区域
function updateName() {
  name.value = '小棉'
}
function updateAge() {
  age.value += 1
}
function showTel() {
  alert(tel)
}
function changePrice() {
  car.price += 1
}
function changeFirstGameName() {
  games[0].name = '更新之后的游戏'
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