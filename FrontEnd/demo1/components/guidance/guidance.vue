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
    
    <!-- 右侧 登录/用户头像 -->
    <view class="navbar-right">
      <navigator url="/pages/login/login">登录</navigator>
    </view>
  </view>
</template>

<script>
export default {
  name: "guidance",
  data() {
    return {
      isLoggedIn: false,
      userAvatar: '' // 假设头像路径存储在此
    };
  },
  created() {
    // 模拟获取登录状态和用户头像
    const user = JSON.parse(localStorage.getItem('user'));
    if (user) {
      this.isLoggedIn = true;
      this.userAvatar = user.avatar; // 设置用户头像
    }
  },
  methods: {
    goToUserProfile() {
      // 跳转到用户个人信息页面
      this.$router.push('/pages/user/user');
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