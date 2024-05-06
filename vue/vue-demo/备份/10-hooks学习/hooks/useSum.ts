import { ref, computed } from 'vue'

// 封装 求和 相关的数据
export default function () {
  let sum = ref(0)
  let bigSum = computed(() => {
    return sum.value * 10
  })
  function addSum() {
    sum.value += 1
  }

  // 向外部提供数据
  return {sum, addSum, bigSum}
}