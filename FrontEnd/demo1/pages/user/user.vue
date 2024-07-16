<template>
	<view class="container">
		<guidance></guidance>
		<!-- 用户信息 -->
		<view class="userinfo global">
			<image src="../../static/images/背景2.jpg"></image>
			<view>
				<view class="name box">{{ userData.username }}</view>
				<view class="box">注册时间: {{ userData.registerTime }}</view>
			</view>
		</view>
		<!-- 个人信息编辑区域 -->
		<view class="edit-section global">
			<form @submit.prevent="submitForm">
				<view>
					<label for="gender">性别：{{ userData.gender }}</label>
					<input type="text" id="gender" v-model="userData.gender" placeholder="修改性别" />
				</view>
				<view>
					<label for="age">年龄：{{ userData.age }}</label>
					<input type="number" id="age" v-model="userData.age" placeholder="修改年龄" />
				</view>
				<view>
					<label for="email">邮箱：{{ userData.email }}</label>
					<input type="email" id="email" v-model="userData.email" placeholder="修改邮箱" />
				</view>
				<view>
					<label for="phone">电话号码：{{ userData.phone }}</label>
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
					username: '',
					registerTime: '',
					gender: '',
					age: '',
					email: '',
					phone: ''
				}
			};
		},
		created() {
			this.fetchUserData();
		},
		methods: {
			fetchUserData() {
				// 从后端获取用户信息
				const token = localStorage.getItem('JWT');
				if (token) {
					uni.request({
						method: 'GET',
						url: 'http://localhost:81/User/userSelect', // 假设有一个后端接口可以获取用户信息
						header: {
							'Authorization': token,
							'Content-Type': 'application/json'
						},
						success: res => {
							if (res.data.success === 1) {
								this.userData = res.data.data[0]; // 假设返回的用户信息在data.data数组中
							} else {
								console.error('获取用户信息失败:', res.data.data);
							}
						},
						fail: err => {
							console.error('请求失败:', err);
						}
					});
				}
			},
			submitForm() {
				// 在此处添加保存用户信息的逻辑，可以通过 API 将修改后的数据提交给后端
				const token = localStorage.getItem('JWT');
				if (token) {
					uni.request({
						method: 'POST',
						url: 'http://localhost:81/User/userUpdate', // 假设有一个后端接口可以更新用户信息
						header: {
							'Authorization': token,
							'Content-Type': 'application/json'
						},
						data: this.userData,
						success: res => {
							if (res.data.success === 1) {
								uni.showToast({
									title: '保存成功',
									duration: 1000
								});
							} else {
								uni.showToast({
									title: '保存失败',
									icon: 'none',
									duration: 1000
								});
								console.error('保存用户信息失败:', res.data.data);
							}
						},
						fail: err => {
							console.error('请求失败:', err);
						}
					});
				}
			},
			changePassword() {
				// 在此处添加修改密码的逻辑，可能需要弹出对话框或导航到另一个页面
				console.log('修改密码');
				// 可以实现修改密码的功能，例如弹出对话框或者跳转到修改密码页面
			},
			logout() {
				// 在此处添加退出登录的逻辑，例如清空本地存储或跳转到登录页面
				localStorage.removeItem('JWT');
				uni.showToast({
					title: '退出登录',
					duration: 1000
				});
				uni.reLaunch({
					url: '/pages/login/login'
				});
			}
		}
	};
</script>

<style>
	.container {
		padding: 10px;
		background-image: url(../../static/images/restaurant.webp);
		background-size: cover;
		width: 100vw;
		height: 100vh;
	}

	.box {
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

	.submit button {
		width: 40%;
		/* 按钮宽度 */
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
		width: 30%;
		/* 按钮宽度 */
		height: 45px;
		border: none;
		border-radius: 10px;
		background-color: #3498db;
		color: #fff;
		font-size: 18px;
		cursor: pointer;
	}
</style>