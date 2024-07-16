package org.example.onlinetestbackend.Service;

import org.example.onlinetestbackend.Mapper.UserMapper;
import org.example.onlinetestbackend.Utils.JWTUtils;
import org.example.onlinetestbackend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 用户登录
     * @param userid 用户ID
     * @param password 用户密码
     * @return 成功登录返回JWT令牌，否则返回null
     */
    public String login(int userid, String password) {
        // 特殊处理admin用户
        if(userid == 10000){
            Map<String, Object> adminClaims = new HashMap<>();
            adminClaims.put("userid", 10000);
            adminClaims.put("admin", 1);
            return JWTUtils.generateToken(adminClaims);
        }
        // 对密码进行MD5加密
        String encryption = DigestUtils.md5DigestAsHex(password.getBytes());
        // 创建一个包含用户ID和加密密码的User对象
        User user = new User(userid, null, null, null, encryption, null);
        // 匹配用户密码是否正确
        List<User> searchResult = userMapper.userSelect(user);
        if (!searchResult.isEmpty()) {
            // 用户存在，生成JWT令牌
            Map<String, Object> map = new HashMap<>();
            map.put("userid", userid);
            map.put("password", encryption);
            map.put("admin", searchResult.get(0).getAdmin());
            return JWTUtils.generateToken(map);
        }
        return null; // 用户不存在或密码错误
    }

    /**
     * 插入新用户
     * @param user 用户对象
     */
    public void userInsert(User user) {
        // 对密码进行MD5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.userInsert(user);
    }

    /**
     * 删除用户
     * @param userids 用户ID数组
     */
    public void userDelete(int[] userids) {
        userMapper.userDelete(userids);
    }

    /**
     * 更新用户信息
     * @param user 用户对象
     */
    public void userUpdate(User user) {
        if (user.getPassword() != null) {
            // 对密码进行MD5加密
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        System.out.println(user.getUserid() + " is changing: " + user.getEmail());
        userMapper.userUpdate(user);
    }

    /**
     * 查询用户信息
     * @param user 用户对象
     * @return 查询到的用户列表
     */
    public List<User> userSelect(User user) {
        List<User> users = userMapper.userSelect(user);
        for (User userIns : users) {
            // 隐藏密码
            userIns.setPassword("~password~ ^_^");
        }
        return users;
    }
}