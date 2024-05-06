<template>
  <Person />
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
