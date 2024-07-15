<template>
  <scroll-view scroll-y="true" class="container">
    <!-- 导航栏 -->
    <guidance></guidance>
    <!-- 欢迎使用在线考试系统！ -->
    <view class="main-wrapper">
      <view class="box">
        <text>欢迎使用在线考试系统！</text>
      </view>
      <!-- 轮播图 -->
      <view class="uni-margin-wrap">
        <swiper class="swiper" circular autoplay interval="5000">
          <swiper-item>
            <image src="../../static/images/背景1.jpg" mode="aspectFill"></image>
          </swiper-item>
          <swiper-item>
            <image src="../../static/images/背景2.jpg" mode="aspectFill"></image>
          </swiper-item>
          <swiper-item>
            <image src="../../static/images/背景3.jpg" mode="aspectFill"></image>
          </swiper-item>
        </swiper>
      </view>
      <!-- 公告栏和团队成员介绍 -->
      <view class="main-content">
        <!-- 左侧公告栏 -->
        <view class="announcement-section">
          <view class="section-title">公告栏</view>
          <view class="announcement">
            <text>公告1：系统升级通知</text><br>
            <text>公告2：期末考试安排</text><br>
            <text>公告3：假期安排</text>
          </view>
        </view>
        <!-- 右侧时间和团队成员 -->
        <view class="right-content">
          <!-- 当前时间 -->
          <view class="time-section">
            <view class="section-title">当前时间</view>
            <view class="time">
              <text>{{ currentTime }}</text>
            </view>
          </view>
          <!-- 团队成员 -->
          <view class="team-section">
            <view class="section-title">团队成员</view>
            <view class="team">
              <view class="team-member" v-for="member in teamMembers" :key="member.name">
                <image :src="member.image" mode="aspectFill" class="team-image"></image>
                <text>{{ member.name }}</text>
              </view>
            </view>
          </view>
        </view>
      </view>
    </view>
  </scroll-view>
</template>

<script>
import guidance from '../../components/guidance/guidance.vue';

export default {
  name: 'Home',
  components: {
    guidance
  },
  data() {
    return {
      currentTime: '',
      teamMembers: [
        { name: '马嘉昱', image: '../../static/images/member1.jpg' },
        { name: '詹锦烨', image: '../../static/images/member2.jpg' },
        { name: '孙哲伟', image: '../../static/images/member3.jpg' },
        { name: '李相龙', image: '../../static/images/member4.jpg' },
        { name: '夏星', image: '../../static/images/member5.jpg' },
      ]
    };
  },
  created() {
    this.updateTime();
    setInterval(this.updateTime, 1000);
  },
  methods: {
    updateTime() {
      const now = new Date();
      this.currentTime = now.toLocaleString();
    }
  }
};
</script>

<style lang="scss">
.container {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: cover; /* 垂直居中 */
  padding: 10px;
  background-image: url(../../static/images/首页背景.png);
  background-size: cover; /* 保持图片原始大小 */
  background-position: center center;
  width: 100vw;
  height: 100vh;
  overflow-y: auto;
}

.main-wrapper {
  max-width: 1200px; /* 设置最大宽度 */
  width: auto; /* 固定宽度 */
  height: auto;
  align-self: center;
  background-repeat: repeat; /* 重复背景图片 */
  background-image: url(../../static/images/restaurant.webp);
  background-size: contain;
  background-position: center center;
  border-radius: 20px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  margin-top: 40rpx;
  margin-left: auto;
  margin-right: auto;
  opacity: 0.8; /* 设置透明度 */
}

.swiper {
  height: 400px;
  border-radius: 20px;
  overflow: hidden;
  margin-bottom: 20px;
}

.swiper image {
  width: 100%;
  height: 100%;
}

.box {
  display: flex;
  justify-content: center;
  padding: 30px;
  font-size: 40px;
  font-weight: bold;
  color: #185ABD;
}

.main-content {
  display: flex;
  justify-content: space-between;
  padding: 20px;
}

.announcement-section, .time-section, .team-section {
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 20px;
  padding: 20px;
  margin: 10px;
  flex: 1;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.announcement-section {
  margin-right: 10px;
}

.right-content {
  display: flex;
  flex-direction: column;
  flex: 1;
}

.section-title {
  font-size: 30px;
  font-weight: bold;
  color: #333;
  margin-bottom: 10px;
}

.announcement, .time, .team {
  padding: 10px;
  word-wrap: break-word; /* 确保文本自动换行 */
}

.team {
  display: flex;
  flex-wrap: wrap;
}

.team-member {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 10px;
}

.team-image {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-bottom: 10px;
}

/* 导航栏样式 */
.navbar {
  display: flex;
  justify-content: space-between; /* 两端对齐 */
  align-items: center;
  background-color: #333;
  padding: 10px 20px;
  color: white;
}

.navbar-left {
  display: flex;
  align-items: center;
}

.logo {
  height: 70rpx;
  width: 70rpx;
  margin-right: 20px;
}

.navbar-right {
  display: flex;
  align-items: center;
}

.avatar {
  height: 40px;
  width: 40px;
  border-radius: 50%;
  cursor: pointer;
}

a {
  color: white;
  text-decoration: none;
  margin: 0 10px;
}

a:hover {
  text-decoration: underline;
}
</style>