import { reactive } from 'vue'
import axios from 'axios'

// 封装和狗相关的所有数据
export default function () {
  let dogList = reactive(['https://images.dog.ceo/breeds/pembroke/n02113023_1571.jpg'])

  async function addDog() {
    let result = await axios.get('https://dog.ceo/api/breed/pembroke/images/random')
    console.log(result.data.message)
    dogList.push(result.data.message)
  }

  // 向外部提供数据
  return {dogList, addDog}
}