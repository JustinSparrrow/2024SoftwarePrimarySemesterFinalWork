<template>
	<view>
		<!-- 题目 -->
		<view class="paper_body_problem">
			<text>题目文本</text>
			<!-- 图片格式 -->
			<!-- <image src="" mode=""></image> -->
		</view>
		<!-- 作答选项 -->
		<view class="paper_body_select">
			<!-- 单选题 -->
			<!-- <view class="select">
				<radio-group @change="radioChange">
					<label class="uni-list-cell uni-list-cell-pd" v-for="(item, index) in items" :key="item.value">
						<view>
							<radio :value="item.value" :checked="index === current" />
						</view>
						<view>{{item.name}}</view>
						</label>
				</radio-group>
			</view> -->
			<!-- 多选题 -->
			<view class="select">
				<checkbox-group>
					<label>
						<checkbox value="cb" checked="true" />
						<text>a选项内容</text>
					</label>
					<label>
						<checkbox value="cb" />
						<text>b选项内容</text>
					</label>
					<label>
						<checkbox value="cb" />
						<text>c选项内容</text>
					</label>
					<label>
						<checkbox value="cb" />
						<text>d选项内容</text>
					</label>
						</checkbox-group>
			</view>
			<!-- 是非题 -->
			<!-- <view class="select">
				<view>
					<label class="radio"><radio value="r1" checked="true" />正确</label>
					<label class="radio"><radio value="r2" />错误</label>
				</view>
			</view> -->
			
		</view>
	</view>
</template>

<script>
	export default {
		name:"problem",
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">
.paper_body_problem{
	padding: 10px;
	font-size: 20px;
}
.paper_body_select{
	padding-bottom: 20px;
	display: flex;
	flex-direction: column;
}
.select{
	padding: 10px;
	font-size: 20px;
	display: flex;
	flex-direction: column;
}

</style>