<template>
  <view class="content">
    <el-form label-width="85px">
      <!-- 选择在线用户 -->
      <el-form-item label="聊天用户">
        <el-select v-model="textMessage.receiver" placeholder="请选择聊天用户" @change="selectOnlineUser">
          <el-option
            v-for="(user, index) in onlineUserList"
            :key="index"
            :label="user.username"
            :value="user.userId"
          ></el-option>
        </el-select>
      </el-form-item>

      <!-- 聊天内容区域 -->
      <el-form-item label="聊天内容">
        <el-input v-model="textMessage.msg"></el-input>
      </el-form-item>

      <!-- 发送按钮 -->
      <el-form-item>
        <el-button type="primary" @click="sendMsg">发送</el-button>
        <el-button type="primary" @click="getOnlineUserList">刷新在线用户</el-button>
      </el-form-item>
    </el-form>
  </view>
  <!-- 历史记录 -->
  <view class="historyContainer">
    <el-row class="infinite-list" style="overflow: hidden" v-for="(history, index) in historyChatList" :key="index">
      <el-col
        class="infinite-list-item"
        :span="4"
        :style="{ backgroundColor: history.bgc, float: history.float }"
        :xs="4"
        :sm="6"
        :md="8"
        :lg="9"
        :xl="11"
      >
        {{ history.username }}: {{ history.msg }}
      </el-col>
    </el-row>
  </view>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import { useUserStore } from '@/store/user'
import { ElMessage } from 'element-plus'
import axios from 'axios'

// pinia 获取数据
let cur_user = useUserStore()
//websocket操作对象
var webSocket = null
//当前用户Id
var userId = ''
//当前用户要发送的消息
var msg = '发送的消息'
//要发送消息的用户
var receiver = ''
//在线用户列表
var onlineUserList = ref([
  // {
  //   'userId': '4HdMcp',
  //   'username': 'Edge'
  // },
])
//历史消息
var historyChatList = reactive([
  // {
  //   "userId": '',
  //   'msg': '',
  //   'bgc': 'white',
  // }
])
//TextMessage
var textMessage = reactive({ userId: '', receiver: '', msg: '测试数据', type: 'TEXT' })
//RegisterMessage
var registerMessage = { userId: '', type: 'REGISTER' }
//HeaterMessage
var heartMessage = { userId: '', type: 'HEARTBEAT' }
// 发送心跳请求定时器事件
let heartBeatEvent = null

onMounted(() => {
  console.log('onMounted 被调用了')
  webSocket = new WebSocket('ws://localhost:19999/websocket')
  initWebSocket()
  //随机获取一个UserId
  userId = generateRandomString(6)
  //设置注册包、心跳包、TextMessage的UserId
  registerMessage.userId = userId
  heartMessage.userId = userId
  textMessage.userId = userId
  cur_user.user.user_id = userId
  cur_user.user.user_name = userId
  console.log(`当前UserId: ${userId}`)
  console.log('注册包: {} --- 心跳包: {}', registerMessage, heartMessage)
  // uni.setNavigationBarTitle({
  //   title: `您的UserId：${userId}`
  // })
})

function onOpen() {
  //向服务器发送注册消息
  registerMessage.userId = userId
  webSocket.send(JSON.stringify(registerMessage))
  console.log('WebSocket连接成功, 状态码: ', webSocket.readyState)

  //获取所有的在线用户
  getOnlineUserList()

  //发送心跳包
  heartBeatEvent = setInterval(() => {
    webSocket.send(JSON.stringify(heartMessage))
    console.log('心跳')
  }, 3000)
}

/**
 * 将发送的消息存入历史记录
 * @param userId
 * @param msg
 * @param type
 */
function pushHistory({ userId, msg, type }) {
  //如果type不是TEXT类型，不用存入历史记录
  if (type !== 'TEXT') {
    return
  }

  // debugger
  let history = {
    userId,
    username: '',
    msg,
    bgc: '',
    float: ''
  }
  if (history.userId === textMessage.userId) {
    history.bgc = '#95ec69'
    history.username = `发给 ${textMessage.userId}`
    history.float = 'right'
  } else {
    history.bgc = 'white'
    history.username = history.userId
    history.float = 'left'
  }
  historyChatList.push(history)
  // load()
}

function onMessage(event) {
  console.log('WebSocket时间为 --> ', event)
  console.log('WebSocket收到消息 --> ', event.data)

  //提醒用户上线
  if (event.data.includes('上线')) {
    ElMessage({
      message: event.data,
      type: 'success'
    })
    //刷新在线用户数据
    getOnlineUserList()
    return
  }

  //提醒用户下线
  if (event.data.includes('下线')) {
    ElMessage({
      message: event.data,
      type: 'error'
    })
    //刷新在线用户数据
    getOnlineUserList()
    return
  }

  //发送了TextMessage之后服务端的响应 --> "userId：消息"
  let datas = event.data.split('：')
  pushHistory({
    userId: datas[0],
    msg: datas[1],
    type: 'TEXT'
  })
}

function onError() {
  console.log('WebSocket连接错误，状态码：', webSocket.readyState)
  clearInterval(heartBeatEvent)
}

function onClose() {
  console.log('WebSocket连接关闭，状态码：', webSocket.readyState)
  clearInterval(heartBeatEvent)
}

function initWebSocket() {
  // 连接成功
  webSocket.onopen = onOpen
  // 收到消息的回调
  webSocket.onmessage = onMessage
  // 连接错误
  webSocket.onerror = onError
  // 连接关闭的回调
  webSocket.onclose = onClose
}

//发送消息
function sendMsg() {
  let msg = JSON.stringify(textMessage)
  webSocket.send(msg)
  pushHistory(textMessage)
  // console.log(msg)
}
//选择聊天用户
function selectOnlineUser(userId) {
  console.log('选择用户时间被触发了, 用户id: ', userId)
  textMessage.receiver = userId
}

/**
 * 获取所有的在线用户, 不显示当前UserId，只显示别的在线的UserId
 */
function getOnlineUserList() {
  axios.get('http://localhost:8999/user/online/list').then(({ data }) => {
    onlineUserList.value = data.data
    for (var i = 0; i < onlineUserList.value.length; i++) {
      if (onlineUserList.value[i].userId === userId) {
        let user = onlineUserList.value.splice(i, 1)
        break
      }
    }
  })
}

function scrollToBottom() {
  let container = document.querySelector('.historyContainer')
  // let container = $refs.historyContainer
  container.scrollTop = container.scrollHeight
}

function load(event) {
  event.$nextTick(() => {
    scrollToBottom()
  })
}

//生成一个指定长度的字符串
function generateRandomString(length) {
  var result = ''
  var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789'
  for (var i = 0; i < length; i++) {
    result += characters.charAt(Math.floor(Math.random() * characters.length))
  }
  return result
}
</script>

<style>
.historyContainer {
  height: 500px;
  overflow: auto;
  background-color: #f5f5f5;
}

.infinite-list-item {
  border-radius: 15px;
  margin: 10px 0;
  padding: 10px;
  display: inline-block;
  list-style: none;
  max-width: 400px;
}
</style>