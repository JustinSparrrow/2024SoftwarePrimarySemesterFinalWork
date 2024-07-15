<!-- 题库管理 -->
<template>
  <view class="container">
    <!-- 筛选属性栏 -->
    <view class="attributes">
		<view class="details">
      <text>题型：</text>
           <select v-model="selectedType" @change="filterQuestions"> <!-- 题型筛选 -->
             <option value="">全部</option>
             <option value="单选题">单选题</option>
             <option value="多选题">多选题</option>
             <option value="填空题">填空题</option>
           </select>
           <text>科目：</text>
           <select v-model="selectedSubject" @change="filterQuestions"> <!-- 科目筛选 -->
             <option value="">全部</option>
             <option value="数学">数学</option>
             <option value="语文">语文</option>
             <option value="英语">英语</option>
           </select>
		   </view>
		   <view class="add">
		   	<navigator url="/pages/add_problem/add_problem"><button>新增</button></navigator>
		   </view>
    </view>

    <!-- 题目列表 -->
    <view class="question-list">
      <view v-for="question in paginatedQuestions" :key="question.id" class="question">
		<view class="question-details">
			 <!-- 添加选项框 -->
			<checkbox-group>
	            <checkbox :value="question.id"></checkbox>
			</checkbox-group>
                 <view class="question-information">
                 	<text><strong>题目：</strong>{{ question.title }}</text>
                 	<text><strong>题型：</strong>{{ question.type }}</text>
                 	<text><strong>科目：</strong>{{ question.subject }}</text>
                 </view>
                  <view class="actions">
                    <button @tap="editQuestion(question)">编辑</button>
                    <button @tap="deleteQuestion(question)">删除</button>
                  </view>
		</view>
      </view>
    </view>

	<view class="down">
		<!-- 全选按钮 -->
		    <view class="select-all">
		      <checkbox-group>
		        <checkbox @change="selectAll($event.target.checked)">全选</checkbox>
		      </checkbox-group>
		    </view>
		
		 <!-- 删除选中按钮 -->
		    <view class="delete-selected">
		      <button @tap="deleteSelected">删除选中</button>
		    </view>
	</view>

    <!-- 分页控件 -->
    <view class="pagination">
      	<button @tap="prevPage" :disabled="currentPage === 1">上一页</button>
      <text>{{ currentPage }} / {{ totalPages }}</text>
      <button @tap="nextPage" :disabled="currentPage === totalPages">下一页</button>
    </view>
  </view>
</template>


<script>
export default {
  data() {
    return {
      questions: [], // 题目数组
      filteredQuestions: [], // 筛选后的题目数组
      selectedType: '', // 选择的题型
      selectedSubject: '' ,// 选择的科目
	  currentPage: 1, // 当前页码
	  pageSize: 3, // 每页显示的题目数量
	  selectedQuestionIds: [] // 已选中题目的ID数组
    };
  },
  mounted() {
    // 模拟获取题目数据，实际应用中需替换为真实接口调用
    this.questions = [
      { id: 1, title: '问题1', type: '单选题', subject: '数学' },
      { id: 2, title: '问题2', type: '多选题', subject: '语文' },
      { id: 3, title: '问题3', type: '填空题', subject: '英语' },
      // 更多题目数据
    ];
    this.filteredQuestions = this.questions; // 初始加载显示所有题目
  },
  computed: {
    paginatedQuestions() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      return this.filteredQuestions.slice(startIndex, startIndex + this.pageSize);
    },
    totalPages() {
      return Math.ceil(this.filteredQuestions.length / this.pageSize);
    }
  },
  methods: {
    filterQuestions() {
      // 根据选择的题型和科目进行筛选
      this.filteredQuestions = this.questions.filter(question => {
        const typeMatch = this.selectedType ? question.type === this.selectedType : true;
        const subjectMatch = this.selectedSubject ? question.subject === this.selectedSubject : true;
        return typeMatch && subjectMatch;
      });
    },
	selectAll(checked) {
	      if (checked) {
	        this.selectedQuestionIds = this.filteredQuestions.map(question => question.id);
	      } else {
	        this.selectedQuestionIds = [];
	      }
	    },
	deleteSelected() {
	      this.filteredQuestions = this.filteredQuestions.filter(question => !this.selectedQuestionIds.includes(question.id));
	      this.questions = this.questions.filter(question => !this.selectedQuestionIds.includes(question.id));
	      this.selectedQuestionIds = [];
	    },
    editQuestion(question) {
      console.log('编辑题目：', question);
    },
    deleteQuestion(question) {
      console.log('删除题目：', question);
    },
	onPageChange(page) {
	    this.currentPage = page;
	  },
	nextPage() {
	    if (this.currentPage < this.totalPages) {
	      this.currentPage++;
	    }
	  },
	prevPage() {
	    if (this.currentPage > 1) {
	      this.currentPage--;
	    }
	  }
  }
};
</script>

<style scoped>
.container {
	padding: 10px;
	background-image: url(../../static/images/restaurant.webp);
	background-size: cover;
	width: 100vw;
	height: 100vh;
}

.attributes {
	display: flex;
	justify-content: space-between;
	padding-right: 30px;
}

.details{
	display: flex;
	align-items: center;
	margin-bottom: 10px;
}
.add button{
	width: 100%; /* 按钮宽度 */
	height: 30px;
	border: none;
	border-radius: 10px;
	background-color: #3aacf8;
	color: #fff;
	font-size: 14px;
	cursor: pointer;
}

.attributes select{
	background-color: #85C8C5;
	padding: 5px;
}

.question-list {
  margin-top: 10px;
}

.question {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
}
.question-details{
	 display: grid;
	  grid-template-rows: auto;
}
.actions {
  margin-top: 10px;
  display: flex;
  justify-content: space-around;
}

.actions button{
	width: 25%; /* 按钮宽度 */
	height: 40px;
	border: none;
	border-radius: 10px;
	background-color: #3498db;
	color: #fff;
	font-size: 18px;
	cursor: pointer;
}
.down{
	display: flex;
	justify-content: space-between;
	padding-right: 40px;
}
.delete-selected button{
	width: 100%; /* 按钮宽度 */
	height: 40px;
	border: none;
	border-radius: 10px;
	background-color: #3498db;
	color: #fff;
	font-size: 18px;
	cursor: pointer;
}
.pagination{
	display: flex;
	justify-content: space-around;
}
.pagination button{
	width: 15%; /* 按钮宽度 */
	height: 40px;
	border: none;
	border-radius: 10px;
	background-color: #3aacf8;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
}
</style>
