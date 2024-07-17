<template>
	<view class="container">
		<!-- 筛选框 -->
		<view class="filter-box">
			<text>筛选:<input v-model="filterKeyword" type="text" placeholder="输入关键词筛选"></text>
			<button @click="filterUsers">筛选</button>
		</view>

		<!-- 用户列表 -->
		<view class="user-list">
			<view v-for="(user, index) in paginatedUsers" :key="user.id" class="user-item">
				<!-- 添加选项框 -->
				<checkbox-group v-model="selectedUsers">
					<checkbox :value="user.id"></checkbox>
				</checkbox-group>
				<text><strong>姓名：</strong>{{ user.name }}</text>
				<text><strong>年龄：</strong>{{ user.age }}</text>
				<text><strong>性别：</strong>{{ user.sex }}</text>
				<text><strong>邮箱：</strong>{{ user.email }}</text>
				<text><strong>电话：</strong>{{ user.phone }}</text>
				<!-- 其他用户信息根据需求展示-->
				<view class="actions">
					<navigator url="/pages/change_user/change_user"> <button @tap="editUser(user)">编辑</button>
					</navigator>
					<button @tap="deleteUser(user.id)">删除</button>
				</view>
			</view>
		</view>

		<view class="down">
			<!-- 全选按钮 -->
			<view class="select-all">
				<checkbox-group v-model="allSelected">
					<checkbox @change="selectAll($event.target.checked)">全选</checkbox>
				</checkbox-group>
			</view>

			<!-- 删除选中按钮 -->
			<view class="delete-selected">
				<button @tap="deleteSelected">删除选中</button>
			</view>
		</view>

		<!-- 分页 -->
		<view class="pagination">
			<button @click="previousPage" :disabled="currentPage === 1">上一页</button>
			<text>{{ currentPage }} / {{ totalPages }}</text>
			<button @click="nextPage" :disabled="currentPage === totalPages">下一页</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				users: [], // 用户列表数据
				currentPage: 1, // 当前页码
				pageSize: 5, // 每页显示条数
				selectedUsers: [], // 已选中用户的 id 数组
				filterKeyword: '', // 筛选关键词
			};
		},
		computed: {
			// 根据筛选关键词过滤后的用户列表
			filteredUsers() {
				if (!this.filterKeyword) {
					return this.users;
				} else {
					const keyword = this.filterKeyword.toLowerCase();
					return this.users.filter(user =>
						user.name.toLowerCase().includes(keyword) || user.email.toLowerCase().includes(keyword)
					);
				}
			},
			// 分页后的用户列表
			paginatedUsers() {
				const start = (this.currentPage - 1) * this.pageSize;
				const end = start + this.pageSize;
				return this.filteredUsers.slice(start, end);
			},
			// 总页数
			totalPages() {
				return Math.ceil(this.filteredUsers.length / this.pageSize);
			}
		},
		methods: {
			// 从后端获取用户数据
			fetchUsers() {
				const token = localStorage.getItem('JWT');
				if (token) {
					uni.request({
						method: 'GET',
						url: 'http://localhost:81/User/userSelect',
						header: {
							'Authorization': token,
							'Content-Type': 'application/json',
							"JWT":localStorage.getItem("JWT")
						},
						success: (res) => {
							if (res.data.success === 1) {
								this.users = res.data.data;
							} else {
								console.error('获取用户列表失败:', res.data.data);
							}
						},
						fail: (err) => {
							console.error('请求失败:', err);
						}
					});
				}
			},
			// 筛选用户
			filterUsers() {
				// 此方法会重新计算 `filteredUsers` 的值，因为 `filterKeyword` 发生了变化
			},
			// 全选
			selectAll(checked) {
				if (checked) {
					this.selectedUsers = this.paginatedUsers.map(user => user.id);
				} else {
					this.selectedUsers = [];
				}
			},
			// 删除选中用户
			deleteSelected() {
				const token = localStorage.getItem('JWT');
				if (token && this.selectedUsers.length > 0) {
					uni.request({
						method: 'POST',
						url: 'http://localhost:81/User/userDelete',
						header: {
							'Authorization': token,
							'Content-Type': 'application/json',
							"JWT":localStorage.getItem("JWT")
						},
						data: {
							ids: this.selectedUsers
						},
						success: (res) => {
							if (res.data.success === 1) {
								this.users = this.users.filter(
									user => !this.selectedUsers.includes(user.id)
								);
								this.selectedUsers = [];
							} else {
								console.error('删除用户失败:', res.data.data);
							}
						},
						fail: (err) => {
							console.error('请求失败:', err);
						}
					});
				}
			},
			// 删除单个用户
			deleteUser(userId) {
				const token = localStorage.getItem('JWT');
				if (token) {
					uni.request({
						method: 'POST',
						url: 'http://localhost:81/User/userDelete',
						header: {
							'Authorization': token,
							'Content-Type': 'application/json',
							"JWT":localStorage.getItem("JWT")
						},
						data: {
							ids: [userId]
						},
						success: (res) => {
							if (res.data.success === 1) {
								this.users = this.users.filter(user => user.id !== userId);
							} else {
								console.error('删除用户失败:', res.data.data);
							}
						},
						fail: (err) => {
							console.error('请求失败:', err);
						}
					});
				}
			},
			// 上一页
			previousPage() {
				if (this.currentPage > 1) {
					this.currentPage--;
				}
			},
			// 下一页
			nextPage() {
				if (this.currentPage < this.totalPages) {
					this.currentPage++;
				}
			}
		},
		created() {
			this.fetchUsers(); // 获取用户数据
		},
		onLaunch() {
			let token = localStorage.getItem("JWT");
			if (!token) {
				uni.reLaunch({
					url: '/pages/login/login'
				});
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
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}

	.filter-box {
		margin-bottom: 10px;
		display: flex;
		align-items: center;
	}

	.filter-box input {
		margin-left: 10px;
		padding: 5px;
		border: 1px solid #ccc;
		border-radius: 5px;
	}

	.filter-box button {
		background-color: #85C8C5;
		border: none;
		padding: 5px 10px;
		border-radius: 5px;
		cursor: pointer;
		margin-left: 10px;
	}

	.filter-box button:hover {
		background-color: #72b0ab;
	}

	.user-list {
		flex: 1;
		overflow-y: auto;
	}

	.user-item {
		border: 1px solid #ccc;
		padding: 10px;
		margin-bottom: 10px;
		background-color: rgba(255, 255, 255, 0.8);
		border-radius: 10px;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.user-info {
		flex: 1;
		margin-left: 10px;
		display: flex;
		flex-direction: column;
	}

	.actions {
		display: flex;
		flex-direction: column;
	}

	.actions button {
		width: 60px;
		height: 30px;
		border: none;
		border-radius: 10px;
		background-color: #3498db;
		color: #fff;
		font-size: 14px;
		cursor: pointer;
		margin-top: 5px;
	}

	.actions button:hover {
		background-color: #2980b9;
	}

	.down {
		display: flex;
		justify-content: space-between;
		margin-top: 10px;
	}

	.select-all {
		display: flex;
		align-items: center;
	}

	.delete-selected button {
		width: 100px;
		height: 30px;
		border: none;
		border-radius: 10px;
		background-color: #e74c3c;
		color: #fff;
		font-size: 14px;
		cursor: pointer;
	}

	.delete-selected button:hover {
		background-color: #c0392b;
	}

	.pagination {
		display: flex;
		justify-content: space-around;
		margin-top: 10px;
	}

	.pagination button {
		width: 80px;
		height: 30px;
		border: none;
		border-radius: 10px;

		background-color: #3aacf8;
		color: #fff;
		font-size: 14px;
		cursor: pointer;
	}

	.pagination button:hover {
		background-color: #2a8cd6;
	}
</style>