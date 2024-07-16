<template>
  <view class="navbar">
    <!-- 左侧 Logo 和导航链接 -->
    <view class="navbar-left">
      <image src="../../static/images/logo.png" alt="Logo" class="logo">
      </image>
      <navigator url="/pages/index/index">首页</navigator>
      <navigator url="/pages/online_test/online_test">在线考试</navigator>
      <navigator url="/pages/manager/manager">题库管理</navigator>
      <navigator url="/pages/user_manage/user_manage">用户管理</navigator>
    </view>
    
    <!-- 右侧 登录/个人信息管理 -->
    <view class="navbar-right">
      <navigator v-if="!isLoggedIn" url="/pages/login/login">登录</navigator>
      <navigator v-else url="/pages/user/user">个人信息管理</navigator>
    </view>
  </view>
</template>

<script>
export default {
  name: "guidance",
  data() {
    return {
      isLoggedIn: false,
	  isAdmin: false
    };
  },
  created() {
    // 模拟获取登录状态和用户头像
    const token = localStorage.getItem('JWT');
    if (token) {
      // 检查JWT令牌的有效性，可以发送请求到后端验证
      try {
        const payload = JSON.parse(atob(token.split('.')[1])); // 使用 Base64 解码 JWT 的 payload 部分
        this.isLoggedIn = true;
        this.isAdmin = payload.isAdmin === 1;
      } catch (e) {
        console.error('Error parsing token from localStorage: ', e);
      }
    }
  }
}
</script>

<style lang="scss">
.navbar {
  display: flex;
  justify-content: space-between;
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

.navbar-left navigator {
  margin: 0 15px; /* 调整间距 */
  color: white;
  text-decoration: none;
}

.navbar-right {
  display: flex;
  align-items: center;
  margin-right: 3%;
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