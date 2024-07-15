<template>
  <view class="container">
    <!-- 筛选框 -->
    <view class="filter-box">
      <text>筛选:<input v-model="filterKeyword" type="text" placeholder="输入关键词筛选"></text>
      <button @click="filterUsers">筛选</button>
    </view>

    <!-- 用户列表 -->
    <view class="user-list">
      <view v-for="(user, index) in filteredUsers" :key="user.id" class="user-item">
        <!-- 添加选项框 -->
       <checkbox-group>
            <checkbox :value="user.id"></checkbox>
        </checkbox-group> 
       	<text><strong>姓名：</strong>{{ user.name }}</text>
		<text><strong>年龄：</strong>{{ user.age }}</text>
		<text><strong>性别：</strong>{{ user.sex }}</text>
        <text><strong>邮箱：</strong>{{ user.email }}</text>
		<text><strong>电话：</strong>{{ user.phone }}</text>
        <!-- 其他用户信息根据需求展示-->
		<view class="actions">
		  <navigator url="/pages/change_user/change_user"> <button @tap="editQuestion(question)">编辑</button></navigator>
		  <button @tap="deleteQuestion(question)">删除</button>
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
    // 总页数
    totalPages() {
      return Math.ceil(this.filteredUsers.length / this.pageSize);
    }
  },
  methods: {
    // 筛选用户
    filterUsers() {
      // 这里可以根据筛选条件重新获取用户列表，或直接在前端进行筛选
      // 示例中仅在 computed 的 filteredUsers 中处理
    },
    // 全选
    selectAll() {
      this.selectedUsers = this.filteredUsers.map(user => user.id);
    },
    // 删除选中用户
    deleteSelected() {
      // 在实际项目中，根据 this.selectedUsers 中的 id 执行删除操作
      // 这里仅清空已选中用户列表作为示例
      this.selectedUsers = [];
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
    // 模拟获取用户数据
    // 在实际项目中可以通过接口获取数据，并初始化到 this.users 中
    this.users = [
      { id: 1, name: 'User A', age:'18', sex:'男', email: 'userA@example.com', phone:'13344556677' },
      { id: 2, name: 'User B', age:'17', sex:'女', email: 'userB@example.com', phone:'11223344556' },
      // 其他用户数据
    ];
  },
  onLaunch(){
  	let token=localStorage.getItem("JWT")
  	if(!token){
  		uni.reLaunch({
  			url:'/pages/login/login'
  		})
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

.filter-box {
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
}

.filter-box button{
	background-color: #85C8C5;
}

.actions {
  margin-bottom: 10px;
}
.actions button{
	background-color: #3aacf8;
}

.user-item {
  margin-bottom: 5px;
  display: flex;
  justify-content: space-around;
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
.pagination {
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
