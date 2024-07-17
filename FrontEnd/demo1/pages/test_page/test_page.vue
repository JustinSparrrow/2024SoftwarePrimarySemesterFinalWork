<!-- <template>
	<view class="container">
		<view class="user-section">
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
				<button @click="submitExam()" :disabled="isAllAnswered">提交试卷</button>
			</view>
		</view>
	</view>
</template> -->

<template>
	<div id="app">
		<div class="container">
			<div class="user-section">
				<div class="question-section" v-if="currentQuestion">
					<div class="question-text">
						题目{{ currentQuestionIndex + 1 }}/{{ questions.length }}：{{ currentQuestion.qcontent.split('/')[0] }}
					</div>
					<div class="options">
						<!-- 是非题 -->
						<div v-if="currentQuestion.qtype === 0" class="option-container">
							<button @click="selectAnswer('A', currentQuestionIndex)">是</button>
							<button @click="selectAnswer('B', currentQuestionIndex)">否</button>
						</div>
						<!-- 单选题 -->
						<div v-if="currentQuestion.qtype === 1" class="option-container">
							<button v-for="(option, index) in currentQuestion.qcontent.split('/').slice(1)" :key="index"
								@click="selectAnswer(option, currentQuestionIndex)">
								{{ option }}
							</button>
						</div>
						<!-- 多选题 -->
						<div v-if="currentQuestion.qtype === 2" class="option-container">
							<button v-for="(option, index) in currentQuestion.qcontent.split('/').slice(1)" :key="index"
								@click="toggleAnswer(option, currentQuestionIndex)">
								{{ option }}
							</button>
						</div>
					</div>
				</div>
				<div class="navigation-buttons">
					<button @click="prevQuestion" :disabled="currentQuestionIndex === 0">上一题</button>
					<button @click="nextQuestion" :disabled="currentQuestionIndex === questions.length - 1">下一题</button>
					<button @click="submitExam" :disabled="!isAllAnswered">提交试卷</button>
				</div>
			</div>
		</div>
	</div>
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
				isAllAnswered: true,
			};
		},
		created() {
			this.checkUserRole();
			this.fetchQuestions();
		},
		methods: {
			checkUserRole() {
				const userid = localStorage.getItem('userid');
			},
			fetchQuestions() {
				var formData = new FormData();
				formData.append("userid", parseInt(localStorage.getItem("userId")))
				let fly = new Fly;
				fly.config.headers = {
					JWT: localStorage.getItem("JWT")
				}
				fly.post('http://localhost:81/Test/paperFetch', formData)
					.then(res => {
						if (res.data.success === 1) {
							console.log(res.data.data);
							this.questions = res.data.data.map(q => ({
								...q,
								options: q.qcontent.split('/').slice(1) // 分割题目选项
							}));
							this.userAnswers = new Array(this.questions.length).fill(null);
							this.totalQuestions = this.questions.length;
							this.currentQuestion = this.questions[this.currentQuestionIndex];
						} else {
							alert('获取题目失败');
						}
					})
					.catch(err => {
						console.error(err);
						alert('获取题目失败');
					});
			},
			prevQuestion() {
				if (this.currentQuestionIndex > 0) {
					this.saveAnswer(this.currentQuestionIndex);
					this.currentQuestionIndex--;
					this.currentQuestion = this.questions[this.currentQuestionIndex];
				}
			},
			nextQuestion() {
				if (this.currentQuestionIndex < this.questions.length - 1) {
					this.saveAnswer(this.currentQuestionIndex);
					this.currentQuestionIndex++;
					this.currentQuestion = this.questions[this.currentQuestionIndex];
				}
			},

			selectAnswer(answer, index) {
				this.$set(this.userAnswers, index, answer);
				this.saveAnswer(index);
			},
			toggleAnswer(option, index) {
				if (!Array.isArray(this.userAnswers[index])) {
					this.$set(this.userAnswers, index, []);
				}
				const answers = this.userAnswers[index];
				const answerIndex = answers.indexOf(option);
				if (answerIndex === -1) {
					answers.push(option);
				} else {
					answers.splice(answerIndex, 1);
				}
				this.$set(this.userAnswers, index, answers);
				this.saveAnswer(index);
			},
			saveAnswer(index) {
				const answer = this.userAnswers[index];
				if (answer !== null) {
					const userId = parseInt(localStorage.getItem('userid'));
					const questionId = this.questions[index].qid;
					const formData = new FormData();
					formData.append("userid", userId);
					formData.append("qid", questionId);
					formData.append("answer", Array.isArray(answer) ? answer.join('') : answer);
					const fly = new Fly;
					fly.config.headers = {
						JWT: localStorage.getItem("JWT")
					};
					fly.post('http://localhost:81/Test/answerSave', formData)
						.then(res => {
							if (res.data.success === 1) {
								console.log('答案保存成功');
							} else {
								console.log('答案保存失败');
							}
						})
						.catch(err => {
							console.error(err);
							console.log('答案保存失败');
						});
				} else {
					console.log('请先选择答案');
				}
			},

			submitExam() {
				if (true) {
					this.saveAnswer(this.currentQuestionIndex);
					const fly = new Fly;
					const userId = localStorage.getItem('userid');
					const userAnswers = this.userAnswers.map((answer, index) => ({
						qid: this.questions[index].id,
						answer,
					}));
					var formData = new FormData();
					formData.append("userid", localStorage.getItem("userId"))
					fly.config.headers = {
						JWT: localStorage.getItem("JWT")
					}
					fly.post('http://localhost:81/Test/paperSubmit', formData, )
						.then(res => {
							if (res.data.success === 1) {
								var score = res.data.data
								console.log(score)
								alert("提交成功，您的分数是" + score.toString())
								uni.navigateTo({
									url: '/pages/index/index',
								})
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

// <style lang="scss">
	// 	.container {
	// 		padding: 10px;
	// 		background-image: url(../../static/images/restaurant.webp);
	// 		background-size: cover;
	// 		width: 100vw;
	// 		height: 100vh;
	// 	}

	// 	.admin-section,
	// 	.user-section {
	// 		padding: 20px;
	// 	}

	// 	.section {
	// 		margin-bottom: 20px;
	// 	}

	// 	.section-title {
	// 		font-size: 24px;
	// 		font-weight: bold;
	// 		margin-bottom: 10px;
	// 	}

	// 	.input-group {
	// 		margin-bottom: 10px;
	// 	}

	// 	input[type="text"],
	// 	input[type="number"],
	// 	input[type="file"],
	// 	input[type="email"],
	// 	input[type="tel"] {
	// 		width: 100%;
	// 		padding: 8px;
	// 		box-sizing: border-box;
	// 	}

	// 	button {
	// 		width: 100%;
	// 		padding: 10px;
	// 		background-color: #3498db;
	// 		color: #fff;
	// 		border: none;
	// 		border-radius: 5px;
	// 		cursor: pointer;
	// 		transition: background-color 0.3s ease;
	// 	}

	// 	button:hover {
	// 		background-color: #2980b9;
	// 	}

	// 	.question-section {
	// 		padding: 20px;
	// 		background-color: rgba(255, 255, 255, 0.8);
	// 		border-radius: 10px;
	// 		box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	// 	}

	// 	.question-text {
	// 		font-size: 20px;
	// 		font-weight: bold;
	// 		margin-bottom: 10px;
	// 	}

	// 	.options {
	// 		margin-bottom: 20px;
	// 	}

	// 	.navigation-buttons {
	// 		display: flex;
	// 		justify-content: space-between;
	// 	}

	// 	.navigation-buttons button {
	// 		width: 30%;
	// 	}
	// 
</style>
<style scoped>
	.container {
		padding: 10px;
		background-image: url(../../static/images/restaurant.webp);
		background-size: cover;
		width: 100vw;
		height: 100vh;
	}

	.user-section {
		padding: 20px;
	}

	.question-section {
		padding: 20px;
		background-color: rgba(255, 255, 255, 0.8);
		border-radius: 10px;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		margin-bottom: 20px;
	}

	.question-text {
		font-size: 20px;
		font-weight: bold;
		margin-bottom: 10px;
	}

	.options {
		margin-bottom: 20px;
	}

	.option-container {
		display: flex;
		flex-direction: column;
	}

	.option-box {
		display: flex;
		align-items: center;
		margin-bottom: 10px;
	}

	button {
		padding: 10px 20px;
		margin: 5px 0;
		border: none;
		border-radius: 5px;
		background-color: #f0f0f0;
		cursor: pointer;
	}

	button:hover {
		background-color: #ddd;
	}

	.navigation-buttons {
		display: flex;
		justify-content: space-between;
	}

	.navigation-buttons button {
		width: 30%;
	}
</style>