<template>
	<view class="container">
		 <!--考试日期和考试时长-->
		    <view class="paper_sub_title_first">
		      <text>考试日期：{{currentDate | date-format('YYYY-MM-DD')}}</text>
			  <text>考试时间：60分钟</text>
		    </view>
			<!-- 试卷问题及答题区域 -->
			<problem></problem>
				<!-- 上下题切换 -->
				<view class="paper_body_button">
							<button>上一题</button>
							<button>下一题</button>
							<button>提交试卷</button>
				</view>
			</view>
			
	</view>
</template>

<script>
	export default {
		data() {
			return {
				currentDate: new Date(),
				
			}
		},
		methods: {
			filters: {
			    dateFormat: function (value) {
			      if (!value) return '';
			      const date = new Date(value);
			      const year = date.getFullYear();
			      const month = (date.getMonth() + 1).toString().padStart(2, '0');
			      const day = date.getDate().toString().padStart(2, '0');
			      return `${year}-${month}-${day}`;
			    }
			  }
			}
	}
</script>

<style lang="scss">
.container {
 padding: 10px;
 background-image: url(../../images/restaurant.webp);
 background-size: cover;
 width: 100vw;
 height: 100vh;
}
.paper_sub_title_first{
	padding-bottom: 10px;
	padding-right: 35px;
	display: flex;
	justify-content: space-between;
}


.paper_body_button{
	display: flex;
	justify-content: space-between;
	padding-right: 10px;
}
.paper_body_button button{
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
