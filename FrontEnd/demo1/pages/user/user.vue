<!-- 个人信息 -->
<template>
  <view class="container">
	  <guidance></guidance>
    <!-- 用户信息 -->
    <view class="userinfo global">
      <image src="../../images/背景2.jpg"></image>
      <view>
        <view class="name box">张三</view>
        <view class="box">注册时间:2023.6.20</view>
      </view>
    </view>
    <!-- 个人信息编辑区域 -->
    <view class="edit-section global">
      <form @submit.prevent="submitForm">
        <view>
          <label for="gender">性别：原性别</label>
          <input type="text" id="gender" v-model="userData.gender" placeholder="修改性别"/>
        </view>
        <view>
          <label for="age">年龄：原年龄</label>
          <input type="number" id="age" v-model="userData.age"  placeholder="修改年龄"/>
        </view>
        <view>
          <label for="email">邮箱：原邮箱</label>
          <input type="email" id="email" v-model="userData.email" placeholder="修改邮箱" />
        </view>
        <view>
          <label for="phone">电话号码：原电话号码</label>
          <input type="tel" id="phone" v-model="userData.phone" placeholder="修改电话号码" />
        </view>
        <view class="submit">
          <button type="submit">保存</button>
        </view>
      </form>
    </view>
    <!-- 导航列表 -->
    <view class="nav global">
      <button type="button" @click="changePassword">修改密码</button>
      <button type="button" @click="logout">退出登录</button>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      userData: {
        gender: '', // 从后端获取的用户性别信息
        age: '', // 从后端获取的用户年龄信息
        email: '', // 从后端获取的用户邮箱信息
        phone: '' // 从后端获取的用户电话号码信息
      }
    };
  },
  methods: {
    submitForm() {
      // 在此处添加保存用户信息的逻辑，可以通过 API 将修改后的数据提交给后端
      // 示例中使用 this.userData 中的数据
      console.log('保存用户信息:', this.userData);
      // 可以调用后端接口进行保存操作，这里只是示例，实际需要根据项目需求处理
    },
    changePassword() {
      // 在此处添加修改密码的逻辑，可能需要弹出对话框或导航到另一个页面
      console.log('修改密码');
      // 可以实现修改密码的功能，例如弹出对话框或者跳转到修改密码页面
    },
    logout() {
      // 在此处添加退出登录的逻辑，例如清空本地存储或跳转到登录页面
      console.log('退出登录');
    }
  }
};
</script>

<style>
.container {
 padding: 10px;
 background-image: url(../../images/restaurant.webp);
 background-size: cover;
 width: 100vw;
 height: 100vh;
}
.box{
	padding-right: 30px;
}
.global {
  border: 1px solid #ccc;
  padding: 20px;
  box-sizing: border-box;
}
.userinfo {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.userinfo image {
  width: 100px;
  height: 100px;
  border-radius: 50%;
}
.name {
  font-size: 20px;
  font-weight: bold;
}
.edit-section {
  margin: 10px auto;
}
.edit-section form {
  display: grid;
  grid-template-columns: 1fr 3fr;
  gap: 10px;
}
.submit button{
	width: 40%; /* 按钮宽度 */
	height: 45px;
	border: none;
	border-radius: 10px;
	background-color: #3498db;
	color: #fff;
	font-size: 18px;
	cursor: pointer;
}
.nav {
  margin: 10px auto;
  display: flex;
}
.nav button {
 width: 30%; /* 按钮宽度 */
 height: 45px;
 border: none;
 border-radius: 10px;
 background-color: #3498db;
 color: #fff;
 font-size: 18px;
 cursor: pointer;
}
</style>
