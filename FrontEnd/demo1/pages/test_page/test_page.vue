<template>
	<view class="container">
		<view v-if="isAdmin" class="admin-section">
			<view class="section">
				<view class="section-title">添加新题目</view>
				<view class="input-group">
					<label for="questionText">题目文本</label>
					<input v-model="newQuestionText" type="text" id="questionText" placeholder="请输入题目文本">
				</view>
				<view class="input-group">
					<label for="questionOptions">题目选项（用逗号分隔）</label>
					<input v-model="newQuestionOptions" type="text" id="questionOptions" placeholder="请输入选项">
				</view>
				<view class="input-group">
					<label for="questionAnswer">正确答案</label>
					<input v-model="newQuestionAnswer" type="text" id="questionAnswer" placeholder="请输入正确答案">
				</view>
				<view class="input-group">
					<label for="questionImage">上传图片</label>
					<input type="file" @change="handleFileUpload">
				</view>
				<button @click="insertQuestion()">添加题目</button>
			</view>

			<view class="section">
				<view class="section-title">删除题目</view>
				<view class="input-group">
					<label for="deleteQuestionId">题目ID</label>
					<input v-model="deleteQuestionId" type="number" id="deleteQuestionId" placeholder="请输入要删除的题目ID">
				</view>
				<button @click="deleteQuestion()">删除题目</button>
			</view>

			<view class="section">
				<view class="section-title">更新题目</view>
				<view class="input-group">
					<label for="updateQuestionId">题目ID</label>
					<input v-model="updateQuestionId" type="number" id="updateQuestionId" placeholder="请输入要更新的题目ID">
				</view>
				<view class="input-group">
					<label for="updateQuestionText">题目文本</label>
					<input v-model="updateQuestionText" type="text" id="updateQuestionText" placeholder="请输入新的题目文本">
				</view>
				<view class="input-group">
					<label for="updateQuestionOptions">题目选项（用逗号分隔）</label>
					<input v-model="updateQuestionOptions" type="text" id="updateQuestionOptions" placeholder="请输入新的选项">
				</view>
				<view class="input-group">
					<label for="updateQuestionAnswer">正确答案</label>
					<input v-model="updateQuestionAnswer" type="text" id="updateQuestionAnswer" placeholder="请输入新的正确答案">
				</view>
				<view class="input-group">
					<label for="updateQuestionImage">上传图片</label>
					<input type="file" @change="handleUpdateFileUpload">
				</view>
				<button @click="updateQuestion()">更新题目</button>
			</view>
		</view>

		<view v-else class="user-section">
			<view class="question-section" v-if="currentQuestion">
				<view class="question-text">
					题目{{ currentQuestionIndex + 1 }}/{{ questions.length }}：{{ currentQuestion.text }}
				</view>
				<view class="options">
					<view v-for="(option, index) in currentQuestion.options" :key="index">
						<input type="checkbox" :value="option" v-model="userAnswers[currentQuestionIndex]">{{ option }}
					</view>
				</view>
			</view>
			<view class="navigation-buttons">
				<button @click="prevQuestion()" :disabled="currentQuestionIndex === 0">上一题</button>
				<button @click="nextQuestion()" :disabled="currentQuestionIndex === questions.length - 1">下一题</button>
				<button @click="submitExam()" :disabled="!isAllAnswered">提交试卷</button>
			</view>
		</view>
	</view>
</template>

<script>
	import Fly from 'flyio/dist/npm/fly';
	export default {
		data() {
			return {
				isAdmin: false,
				newQuestionText: '',
				newQuestionOptions: '',
				newQuestionAnswer: '',
				newQuestionImage: null,
				deleteQuestionId: '',
				updateQuestionId: '',
				updateQuestionText: '',
				updateQuestionOptions: '',
				updateQuestionAnswer: '',
				updateQuestionImage: null,
				questions: [],
				currentQuestionIndex: 0,
				userAnswers: [],
				currentQuestion: null,
				totalQuestions: 0,
				isAllAnswered: false,
			};
		},
		created() {
			this.checkUserRole();
			this.fetchQuestions();
		},
		methods: {
			checkUserRole() {
				const userid = localStorage.getItem('userid');
				this.isAdmin = userid && userid === '1000000';
			},
			fetchQuestions() {
				var formData = new FormData();
				formData.append("userid",parseInt(localStorage.getItem("userId")))
				let fly = new Fly;
				fly.config.headers={
					JWT:localStorage.getItem("JWT")
				}
				fly.post('http://localhost:81/Test/paperFetch',formData)
					.then(res => {
						if (res.data.success === 1) {
							this.questions = res.data.data;
							this.userAnswers = new Array(this.questions.length).fill(null);
							this.totalQuestions = this.questions.length;
							this.currentQuestion = this.questions[this.currentQuestionIndex];
						} else {
							uni.showToast({
								title: '获取题目失败',
								icon: 'none',
								duration: 2000
							});
						}
					})
					.catch(err => {
						console.error(err);
						uni.showToast({
							title: '获取题目失败',
							icon: 'none',
							duration: 2000
						});
					});
			},
			handleFileUpload(event) {
				this.newQuestionImage = event.target.files[0];
			},
			handleUpdateFileUpload(event) {
				this.updateQuestionImage = event.target.files[0];
			},
			insertQuestion() {
				const formData = new FormData();
				formData.append('text', this.newQuestionText);
				formData.append('options', this.newQuestionOptions);
				formData.append('answer', this.newQuestionAnswer);
				if (this.newQuestionImage) {
					formData.append('img', this.newQuestionImage);
				}

				const fly = new Fly;
				fly.config.headers={
					JWT:localStorage.getItem("JWT")
				}
				fly.post('http://localhost:81/Question/qInsert', formData)
					.then(res => {
						if (res.data.success === 1) {
							uni.showToast({
								title: '添加题目成功',
								icon: 'none',
								duration: 2000
							});
							this.fetchQuestions();
						} else {
							uni.showToast({
								title: '添加题目失败',
								icon: 'none',
								duration: 2000
							});
						}
					})
					.catch(err => {
						console.error(err);
						uni.showToast({
							title: '添加题目失败',
							icon: 'none',
							duration: 2000
						});
					});
			},
			deleteQuestion() {
				const fly = new Fly;
				fly.config.headers={
					JWT:localStorage.getItem("JWT")
				}
				fly.post('http://localhost:81/Question/qDelete', {
						qid: this.deleteQuestionId
					})
					.then(res => {
						if (res.data.success === 1) {
							uni.showToast({
								title: '删除题目成功',
								icon: 'none',
								duration: 2000
							});
							this.fetchQuestions();
						} else {
							uni.showToast({
								title: '删除题目失败',
								icon: 'none',
								duration: 2000
							});
						}
					})
					.catch(err => {
						console.error(err);
						uni.showToast({
							title: '删除题目失败',
							icon: 'none',
							duration: 2000
						});
					});
			},
			updateQuestion() {
				const formData = new FormData();
				formData.append('id', this.updateQuestionId);
				formData.append('text', this.updateQuestionText);
				formData.append('options', this.updateQuestionOptions);
				formData.append('answer', this.updateQuestionAnswer);
				if (this.updateQuestionImage) {
					formData.append('img', this.updateQuestionImage);
				}

				const fly = new Fly;
				fly.config.headers={
					JWT:localStorage.getItem("JWT")
				}
				fly.post('http://localhost:81/Question/qUpdate', formData)
					.then(res => {
						if (res.data.success === 1) {
							uni.showToast({
								title: '更新题目成功',
								icon: 'none',
								duration: 2000
							});
							this.fetchQuestions();
						} else {
							uni.showToast({
								title: '更新题目失败',
								icon: 'none',
								duration: 2000
							});
						}
					})
					.catch(err => {
						console.error(err);
						uni.showToast({
							title: '更新题目失败',
							icon: 'none',
							duration: 2000
						});
					});
			},
			prevQuestion() {
				if (this.currentQuestionIndex > 0) {
					this.currentQuestionIndex--;
					this.currentQuestion = this.questions[this.currentQuestionIndex];
				}
			},
			nextQuestion() {
				if (this.currentQuestionIndex < this.questions.length - 1) {
					this.currentQuestionIndex++;
					this.currentQuestion = this.questions[this.currentQuestionIndex];
				}
			},
			submitExam() {
				if (this.isAllAnswered) {
					const fly = new Fly;
					const userId = localStorage.getItem('userid');
					const userAnswers = this.userAnswers.map((answer, index) => ({
						qid: this.questions[index].id,
						answer,
					}));
					
					fly.config.headers={
						JWT:localStorage.getItem("JWT")
					}
					fly.post('http://localhost:81/Test/paperSubmit', {
							userid: userId,
							answers: userAnswers
						},)
						.then(res => {
							if (res.data.success === 1) {
								uni.showToast({
									title: '提交成功',
									icon: 'none',
									duration: 2000
								});
							} else {
								uni.showToast({
									title: '提交失败',
									icon: 'none',
									duration: 2000
								});
							}
						})
						.catch(err => {
							console.error(err);
							uni.showToast({
								title: '提交失败',
								icon: 'none',
								duration: 2000
							});
						});
				} else {
					uni.showToast({
						title: '请回答所有题目后提交',
						icon: 'none',
						duration: 2000
					});
				}
			},
			checkIfAllAnswered() {
				this.isAllAnswered = this.userAnswers.every(answer => answer !== null);
			}
		},
		watch: {
			userAnswers: {
				deep: true,
				handler() {
					this.checkIfAllAnswered();
				}
			}
		}
	};
</script>

<style lang="scss">
	.container {
		padding: 10px;
		background-image: url(../../static/images/restaurant.webp);
		background-size: cover;
		width: 100vw;
		height: 100vh;
	}

	.admin-section,
	.user-section {
		padding: 20px;
	}

	.section {
		margin-bottom: 20px;
	}

	.section-title {
		font-size: 24px;
		font-weight: bold;
		margin-bottom: 10px;
	}

	.input-group {
		margin-bottom: 10px;
	}

	input[type="text"],
	input[type="number"],
	input[type="file"],
	input[type="email"],
	input[type="tel"] {
		width: 100%;
		padding: 8px;
		box-sizing: border-box;
	}

	button {
		width: 100%;
		padding: 10px;
		background-color: #3498db;
		color: #fff;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		transition: background-color 0.3s ease;
	}

	button:hover {
		background-color: #2980b9;
	}

	.question-section {
		padding: 20px;
		background-color: rgba(255, 255, 255, 0.8);
		border-radius: 10px;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	}

	.question-text {
		font-size: 20px;
		font-weight: bold;
		margin-bottom: 10px;
	}

	.options {
		margin-bottom: 20px;
	}

	.navigation-buttons {
		display: flex;
		justify-content: space-between;
	}

	.navigation-buttons button {
		width: 30%;
	}
</style>