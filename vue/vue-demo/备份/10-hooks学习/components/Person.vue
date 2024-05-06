<template>
  <div class="person">
    <h1>和为: {{ sum }}, 放大10倍后: {{ bigSum }}</h1>
    <button @click="addSum">点我和+1</button>
    <br />
    <img v-for="(dog, index) in dogList" :src="dog" :key="index" />
    <br />
    <button @click="addDog">再来一只小狗</button>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Person'
}
</script>

<script lang="ts" setup>
import useSum from '@/hooks/useSum'
import useDog from '@/hooks/useDog'

const { sum, addSum, bigSum } = useSum()
const { dogList, addDog } = useDog()
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
img {
  height: 100px;
  margin-right: 10px;
}
</style>