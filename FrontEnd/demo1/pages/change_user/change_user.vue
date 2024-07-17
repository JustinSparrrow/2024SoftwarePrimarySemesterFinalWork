<template>
  <view class="container">
    <view class="form-card">
      <form @submit.prevent="updateUser">
        <view class="box">
          <input v-model="user.username" type="text" placeholder="用户名" required>
        </view>
        <view class="box">
          <input v-model="user.email" type="email" placeholder="邮箱" required>
        </view>
        <view class="box">
          <input v-model="user.phone" type="tel" placeholder="电话号码" required>
        </view>
        <view class="box">
          <input v-model="user.password" type="password" placeholder="密码" required>
        </view>
        <view class="actions">
          <button type="submit">保存</button>
        </view>
      </form>
      <p v-if="message">{{ message }}</p>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      user: {
        username: '',
        email: '',
        phone: '',
        password: '',
        admin: 0, // 默认值，可以根据需要进行设置
      },
      message: ''
    };
  },
  methods: {
    updateUser() {
      uni.request({
        url: 'http://localhost:81/User/userUpdate',
        method: 'POST',
		header:{
			"JWT":localStorage.getItem("JWT")
		},
        data: this.user,
        success: (res) => {
          this.message = '用户修改成功';
          this.clearForm();
        },
        fail: (err) => {
          this.message = '用户修改失败，请重试';
        }
      });
    },
    clearForm() {
      this.user = {
        username: '',
        email: '',
        phone: '',
        password: '',
        admin: 0,
      };
    }
  }
};
</script>

<style scoped>
.container {
  padding: 20px;
  background-image: url(../../static/images/restaurant.webp);
  background-size: cover;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.form-card {
  background: rgba(255, 255, 255, 0.8);
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 90%;
  max-width: 400px;
}

form {
  display: flex;
  flex-direction: column;
}

.box {
  margin-bottom: 15px;
}

input {
  width: 95%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

.actions {
  text-align: center;
}

.actions button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #3498db;
  color: #fff;
  font-size: 18px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.actions button:hover {
  background-color: #2980b9;
}

p {
  margin-top: 20px;
  color: green;
  text-align: center;
  font-size: 16px;
}
</style>