<template>
  <view class="container">
	     <!-- 导航栏 -->
	    <guidance></guidance>
    <!-- 轮播图 -->
    <view class="uni-margin-wrap">
      <swiper class="swiper" circular autoplay interval="3000">
        <swiper-item>
          <image src="../../images/背景1.jpg" mode="aspectFill"></image>
        </swiper-item>
        <swiper-item>
          <image src="../../images/背景2.jpg" mode="aspectFill"></image>
        </swiper-item>
        <swiper-item>
          <image src="../../images/背景3.jpg" mode="aspectFill"></image>
        </swiper-item>
      </swiper>
    </view>
	<!-- 欢迎使用在线考试系统！ -->
	<view class="box">
		<text>欢迎使用在线考试系统！</text>
	</view>
   
  </view>
</template>

<script>
export default {
  data() {
    return {
      currentTime: ''
    };
  },
  
  
};
</script>

<style>
.container {
  padding: 10px;
  background-image: url(../../images/restaurant.webp);
  background-size: cover;
  background-position: center center; /* 可选，控制背景图片位置 */
  background-repeat: repeat; /* 可选，控制背景图片重复 */
  width: 100vw;
  height: 100vh;
}

.swiper {
  height: 280px;
}
.swiper image {
  width: 100%;
  height: 100%;
}
.container {
  padding: 10px;
}
.box{
	display: flex;
	justify-content: space-around;
	padding-top: 30px;
	padding:10px;
	font-size: 40px;
	font-style: italic;
	color:#185ABD;
}

</style>
