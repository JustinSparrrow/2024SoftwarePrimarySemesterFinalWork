<template>
	<view class>
		<!-- 导航栏 -->
		<scroll-view scroll-x class="nav-scroll">
		  <view class="nav-item">
		    <navigator url="/pages/login/login">登陆页面</navigator>
		  </view>
		  <view class="nav-item">
		    <navigator url="/pages/index/index">首页</navigator>
		  </view>
		  <view class="nav-item">
		    <navigator url="/pages/online_test/online_test">在线考试</navigator>
		  </view>
		  <view class="nav-item">
		    <navigator url="/pages/user/user">个人信息管理</navigator>
		  </view>
		  <view class="nav-item">
		    <navigator url="/pages/manager/manager">题库管理</navigator>
		  </view>
		  <view class="nav-item">
		    <navigator url="/pages/user_manage/user_manage">用户管理</navigator>
		  </view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		name:"guidance",
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">
.nav-scroll {
  height: 70px; /* 设置合适的高度 */
  width: 100%;
  background-color: #85C8C5;
  white-space: nowrap;
  overflow-x: auto; /* 横向滚动 */
  padding-bottom: 10px; /* 底部留白 */
}

.nav-item {
  font-size: 40rpx; /* 调整字体大小 */
  display: inline-block;
  text-align: center;
  margin-bottom: 20px;
  color: #333;
  text-shadow: 1px 1px 1px rgba(0,0,0,0.6); /* 添加文字阴影增强可读性 */
  line-height: 100px; /* 垂直居中 */
  padding: 0 30px;
}
</style>