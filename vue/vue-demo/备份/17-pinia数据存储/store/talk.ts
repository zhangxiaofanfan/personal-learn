import { defineStore } from "pinia";

export const useTalkStore = defineStore("talk", {
  // 真正存储数据的地方
  state() {
    return {
      talkList: [
        { id: "1", content: "生成数据1" },
        { id: "2", content: "生成数据2" },
        { id: "3", content: "生成数据3" },
      ],
    };
  },
});
