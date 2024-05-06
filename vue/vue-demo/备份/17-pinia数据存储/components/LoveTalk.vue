<template>
  <div class="talk">
    <button @click="getLockTalk">获取一句话</button>
    <ul>
      <li v-for="talk in talkState.talkList" :key="talk.id">{{ talk.content }}</li>
    </ul>
  </div>
</template>

<script lang="ts">
export default {
  name: 'LoveTalk'
}
</script>

<script lang="ts" setup>
import { useTalkStore } from '@/store/talk'
import axios from 'axios'
import { nanoid } from 'nanoid'

// pinia 获取数据
let talkState = useTalkStore()

async function getLockTalk() {
  let {
    data: { content }
  } = await axios.get('https://api.uomg.com/api/rand.qinghua?format=json')
  console.log(content)
  talkState.talkList.unshift({
    id: nanoid(),
    content: content
  })
}
</script>

<style scoped>
.talk {
  background-color: orange;
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 0 10px;
}
</style>