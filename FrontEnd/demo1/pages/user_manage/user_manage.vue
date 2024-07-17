<script>
	export default {
		data() {
			return {
				users: [], // 用户列表数据
				currentPage: 1, // 当前页码
				pageSize: 5, // 每页显示条数
				selectedUsers: [], // 已选中用户的 id 数组
				filterKeyword: '', // 筛选关键词
				allSelected: false // 全选状态
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
							"JWT": localStorage.getItem("JWT")
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
				this.currentPage = 1; // 重新筛选后从第一页开始显示
				// 只要修改了 filterKeyword， filteredUsers 计算属性会自动更新
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
							"JWT": localStorage.getItem("JWT")
						},
						data: JSON.stringify({
							ids: this.selectedUsers.map(String) // 转换为字符串数组
						}),
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
							"JWT": localStorage.getItem("JWT")
						},
						data: JSON.stringify({
							ids: [String(userId)] // 转换为字符串数组
						}),
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