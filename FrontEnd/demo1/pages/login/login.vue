<!-- 登录 -->
<template>
	<view class="container">
		<view class="box">
			<text space="emsp"><p>欢迎登陆</p></text>
		</view>
		<view class="input-group">
			<text>用户：</text><input type="text" placeholder="请输入用户名" v-model:value="id">
		</view>
		<view class="input-group">
			<text>密码：</text><input type="password" placeholder="请输入密码" v-model:value="psw">
		</view>
		<view class="button-group" >
			<button type="default">注册</button>
			<button type="default" @click="login()">登录</button>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			id:null,
			psw:null
		}
	},
	methods: {
		login() {
			uni.request({
				method:'POST',
				url:"http://localhost:81/login",
				header:{'Content-Type':'application/json'},
				data:{
					userid:parseInt(this.id),
					password:this.psw
				},
				success:res=>{
					console.log(res)
					if(res.data.success==1)
					{localStorage.setItem("JWT",res.data.data)
					uni.showToast({
						title:'成功登录',
						duration:1000
					})
					uni.navigateTo({
						url:'/pages/index/index'
					})}
					else{
						uni.showToast({
							title:'登录失败',
							icon:'none',
							duration:1000
						})
					}
				},
				fail: () => {
					console.log(this.url)
				}
			})
		}
	},
}
</script>

<style lang="scss">
.container {
	background-image: url(../../static/images/背景7.jpg);
	background-size: cover;
	background-position: center center;
	padding: 10px;
	width: 100vw;
	height: 100vh;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.box {
	font-size: 24px;
	text-align: center;
	margin-bottom: 20px;
	color: #fff;
	text-shadow: 1px 1px 2px rgba(0,0,0,0.6); /* 添加文字阴影增强可读性 */
}

.input-group {
	display: flex;
	align-items: center;
	margin-bottom: 10px;
}

.input-group text {
	width: 80px; /* 文本宽度 */
	color: #fff;
}

.input-group input {
	flex: 1;
	height: 45px;
	margin-left: 10px; /* 输入框和文本之间的间距 */
	border: 1px solid #ccc;
	border-radius: 10px;
	padding-left: 10px;
	padding-right: 10px;
	font-size: 16px;
}

.button-group {
	display: flex;
	justify-content: space-around; /* 按钮等间距排列 */
	width: 100%;
	margin-top: 20px; /* 调整按钮组与上方元素的间距 */
}

.button-group button {
	width: 30%; /* 按钮宽度 */
	height: 45px;
	border: none;
	border-radius: 10px;
	background-color: #3498db;
	color: #fff;
	font-size: 18px;
	cursor: pointer;
}

button:hover {
	background-color: #2980b9;
}

</style>
