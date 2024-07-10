<template>
	<view class="exampaper">
		<view class="pic">
			<image src="../../images/背景4.jpg" mode="aspectFill"></image>
		</view>
		<view class="text">
			<view class="title">
				<view class="t">
					试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名试卷名
				</view>
			</view>
			<view class="info">
				<text>发布者：马老师</text>
				<text>2024.7.7</text>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		name:"exampaper",
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">
.exampaper{
	height: 240px;
	width: 400px;
	border: 1px solid #ffffff;
	display:flex;
	.pic{
		height: 400px;
		width:200px;
		image{
			width:100%;
			heght:100%;
		}
	}
	.text{
		border: 1px solid #ccc;
		flex: 1;
		padding-left:20px;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		.title{
			font-size: 24px;
			color: #333;
		}
		.t{
			 display: -webkit-box; /* 使用WebKit的弹性盒模型 */
			  -webkit-line-clamp: 2; /* 限制显示的文本行数 */
			  -webkit-box-orient: vertical; /* 设置盒模型的方向为垂直 */
			  overflow: hidden; /* 隐藏超出容器的部分 */
			  text-overflow: ellipsis; /* 显示省略号 */
		}
		.info{
			font-size: 18px;
			color: #999;
			text{
				padding-top: 300px;
				padding-right: 30px;
			}
		}
	}
}
</style>