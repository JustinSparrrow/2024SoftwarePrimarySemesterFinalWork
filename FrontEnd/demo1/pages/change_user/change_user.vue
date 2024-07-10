<!-- 试题添加 -->
<template>
  <view class="container">
    <form @submit.prevent="addQuestion">
     <view class="box">
     	 <input v-model="user.name" type="text" placeholder="姓名" required>
     </view>
      <view class="box">
      	<input  v-model="user.age" type="text" placeholder="年龄" required>
      </view>
     <view class="box">
     	 <input v-model="user.sex" type="text" placeholder="性别" required>
     </view>
      <view class="box">
      	<input  v-model="user.email" type="text" placeholder="邮箱" required>
      </view>
     <view class="box">
     	 <input v-model="user.phone" type="text" placeholder="电话号码" required>
     </view>

    </form>
	<view class="actions">
		<button type="submit">保存</button>
	</view>
    <p v-if="message">{{ message }}</p>
  </view>
</template>

<script>
export default {
  data() {
    return {
      user: {
        name: '',
        age: '',
        sex: '',
        email: '',
        phone: '',
      },
      message: ''
    };
  },
  methods: {
    addQuestion() {
      uni.request({
        url: 'https://your-backend-url/addQuestion',
        method: 'POST',
        data: this.question,
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
      this.question = {
        name: '',
        age: '',
        sex: '',
        email: '',
        phone: '',
      };
    }
  }
};
</script>

<style scoped>
.container {
	padding: 10px;
	background-image: url(../../images/restaurant.webp);
	background-size: cover;
	width: 100vw;
	height: 100vh;
}
form {
  display: flex;
  flex-direction: column;
}
.box{
	border: 1px solid #ccc;
}
input{
  margin-bottom: 10px;
  padding: 5px;
}
.actions{
	padding: 10px;
}
.actions button{
  width: 30%; /* 按钮宽度 */
  height: 45px;
  border: none;
  border-radius: 10px;
  background-color: #3498db;
  color: #fff;
  font-size: 18px;
  cursor: pointer;
}
p {
  margin-top: 10px;
  color: green;
}
</style>
