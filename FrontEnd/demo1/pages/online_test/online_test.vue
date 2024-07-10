<template>
  <view class="container">
    <!-- 导航栏 -->
    <guidance></guidance>
    <!-- 题库信息 -->
    <view class="on-item">
      <exampaper></exampaper>
      <exampaper></exampaper>
    </view>
    <!-- 专业选择 -->
    <view class="select">
      <picker mode="multiSelector" :range="multiArry" @change="bindMultiPickChange" :value="multiIndex">
        <view class="uni-input">
			<text>请选择你的专业：</text>
          {{ multiArry[0][multiIndex[0]] }}、{{ multiArry[1][multiIndex[1]] }}
        </view>
      </picker>
	  <view class="start">
	  	<navigator url="/pages/test_page/test_page"><button>开始考试</button></navigator>
	  </view>
    </view> 
  
  
  </view>
</template>

<script>
  export default {
    data() {
      return {
        multiArry: [
          ['计算机科学与技术', '软件工程','人工智能'],
          ['通信工程', '数字媒体技术','网络安全']
        ],
        multiIndex: [0, 0]
      };
    },
    methods: {
      bindMultiPickChange(e) {
        console.log('picker发送选择改变，携带值为', e.detail.value);
        this.multiIndex = e.detail.value;
      }
    }
  }
</script>

<style lang="scss">
.container {
  padding: 10px;
  background-image: url('../../images/restaurant.webp');
  background-size: cover;
  width: 100vw; 
  height: 100vh;
}
.select{
	padding: 10px;
	font-size: 20px;
}
.select button{
	width: 30%; /* 按钮宽度 */
	height: 45px;
	border: none;
	border-radius: 10px;
	background-color: #3498db;
	color: #fff;
	font-size: 18px;
	cursor: pointer;
}
.on-item {
	padding: 10px;
    display: flex;
	justify-content: space-around;
}
</style>
