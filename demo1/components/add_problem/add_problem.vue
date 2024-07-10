<template>
  <view class="container">
    <form @submit.prevent="addQuestion">
		<select v-model="question.type" required>
		  <option value="single">单选题</option>
		  <option value="multiple">多选题</option>
		  <option value="truefalse">是非题</option>
		</select>
     <view class="box">
     	 <input v-if="question.type !== 'truefalse'" v-model="question.title" type="text" placeholder="题目内容" required>
     </view>
      <view class="box">
      	<input v-if="question.type !== 'truefalse'" v-model="question.optionA" type="text" placeholder="选项A" required>
      </view>
     <view class="box">
     	 <input v-if="question.type !== 'truefalse'" v-model="question.optionB" type="text" placeholder="选项B" required>
     </view>
      <view class="box">
      	<input v-if="question.type !== 'truefalse'" v-model="question.optionC" type="text" placeholder="选项C" required>
      </view>
     <view class="box">
     	 <input v-if="question.type !== 'truefalse'" v-model="question.optionD" type="text" placeholder="选项D" required>
     </view>
	 <view class="box">
	 	<input v-if="question.type === 'truefalse'" v-model="question.title" type="text" placeholder="是非题仅输入题目内容" required>
	 </view>
	  <view class="box">
	  	<input v-if="question.type !== 'truefalse'" v-model="question.answer" type="text" placeholder="答案（A/B/C/D）" required>
	  </view>
      <view class="box">
      	<input v-if="question.type === 'truefalse'" v-model="question.answer" type="text" placeholder="答案（true/false）" required>
      </view>
	  <view class="answer">
      	<text>答案解析:</text>
      	<textarea name="answer" id="answer" cols="30" rows="10"></textarea>
		 <button type="submit">提交</button>
      </view>
    </form>
    <p v-if="message">{{ message }}</p>
  </view>
</template>

<script>
export default {
  data() {
    return {
      question: {
        title: '',
        optionA: '',
        optionB: '',
        optionC: '',
        optionD: '',
        answer: '',
        type: 'single' // 默认题型为单选题
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
          this.message = '试题添加成功';
          this.clearForm();
        },
        fail: (err) => {
          this.message = '试题添加失败，请重试';
        }
      });
    },
    clearForm() {
      this.question = {
        title: '',
        optionA: '',
        optionB: '',
        optionC: '',
        optionD: '',
        answer: '',
        type: 'single'
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
select{
	background-color: #85C8C5;
	margin-bottom: 10px;
	padding: 5px;
}
.container button{
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
.answer{
	padding: 10px;
	display: flex;
	justify-content: space-around;
}
.answer textarea{
	border: 1px solid #ccc;
}
</style>
