<template>
	<scroll-view scroll-y="true" class="container">
		<!-- 导航栏 -->
		<guidance></guidance>
		<!-- 题库信息 -->
		<view class="on-item">
			<exampaper></exampaper>
			<exampaper></exampaper>
		</view>
		<!-- 专业选择 -->
		<view class="select">
			<picker mode="multiSelector" :range="multiArry" @change="bindMultiPickChange" :value="multiIndex">
				<view class="uni-input">
					<text>请选择你的专业：</text>
					{{ multiArry[0][multiIndex[0]] }}、{{ multiArry[1][multiIndex[1]] }}
				</view>
			</picker>
			<view class="start">
				<navigator url="/pages/test_page/test_page"><button class="start-button">开始考试</button></navigator>
			</view>
		</view>
	</scroll-view>
</template>

<script>
	export default {
		data() {
			return {
				multiArry: [
					['计算机科学与技术', '软件工程', '人工智能'],
					['通信工程', '数字媒体技术', '网络安全']
				],
				multiIndex: [0, 0]
			};
		},
		methods: {
			bindMultiPickChange(e) {
				console.log('picker发送选择改变，携带值为', e.detail.value);
				this.multiIndex = e.detail.value;
			}
		}
	}
</script>

<style lang="scss">
	.container {
		padding: 20px;
		background-image: url('../../static/images/restaurant.webp');
		background-size: cover;
		background-position: center;
		width: 100vw;
		height: 100vh;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.on-item {
		padding: 20px;
		display: flex;
		justify-content: space-around;
		flex-wrap: wrap;
	}

	.exampaper {
		width: 45%;
		margin: 10px;
		border: 1px solid #ccc;
		border-radius: 10px;
		overflow: hidden;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	}

	.select {
		padding: 20px;
		font-size: 20px;
		background-color: rgba(255, 255, 255, 0.8);
		border-radius: 10px;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		width: 80%;
		text-align: center;
	}

	.uni-input {
		margin: 10px 0;
		padding: 10px;
		font-size: 18px;
		color: #333;
		border: 1px solid #ccc;
		border-radius: 10px;
		background-color: #fff;
	}

	.start {
		margin-top: 20px;
	}

	.start-button {
		width: 50%;
		height: 50px;
		border: none;
		border-radius: 10px;
		background-color: #3498db;
		color: #fff;
		font-size: 18px;
		cursor: pointer;
	}

	.start-button:hover {
		background-color: #2980b9;
	}
</style>