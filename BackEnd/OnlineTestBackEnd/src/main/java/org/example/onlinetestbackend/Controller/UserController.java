package org.example.onlinetestbackend.Controller;

import org.example.onlinetestbackend.Service.UserService;
import org.example.onlinetestbackend.pojo.Result;
import org.example.onlinetestbackend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param user 用户对象，包含用户ID和密码
     * @return 登录结果，成功返回1和JWT，失败返回0和错误信息
     */
    @RequestMapping("/login")
    public Result login(User user) {
        try {
            String jwt = userService.login(user.getUserid(), user.getPassword());
            if (jwt != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("token", jwt);
                response.put("admin", user.getUserid() == 10000 ? 1:0);
                return new Result(1, jwt);
            }
            return new Result(0, "ID或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0, "系统错误");
        }
    }

    /**
     * 新用户注册
     * @param user 用户对象，包含用户ID和密码
     * @return 注册结果，成功返回1和JWT，失败返回0和错误信息
     */
    @RequestMapping("/register")
    public Result register(@RequestBody User user) {
        try {
            userService.userInsert(user);
            return new Result(1, "注册成功");
        } catch (Exception e){
            e.printStackTrace();
            return new Result(0, "注册失败");
        }
    }

    /**
     * 插入新用户
     * @param user 用户对象，包含用户信息
     * @return 插入结果，成功返回1，失败返回0
     */
    @RequestMapping("/User/userInsert")
    public Result userInsert(User user) {
        try {
            userService.userInsert(user);
            return new Result(1);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0);
        }
    }

    /**
     * 删除用户
     * @param userid 用户ID数组
     * @return 删除结果，成功返回1，失败返回0
     */
    @RequestMapping("/User/userDelete")
    public Result userDelete(int[] userid) {
        try {
            userService.userDelete(userid);
            return new Result(1);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0);
        }
    }

    /**
     * 更新用户信息
     * @param user 用户对象，包含更新后的用户信息
     * @return 更新结果，成功返回1，失败返回0
     */
    @RequestMapping("/User/userUpdate")
    public Result userUpdate(User user) {
        try {
            userService.userUpdate(user);
            return new Result(1);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0);
        }
    }

    /**
     * 查询用户信息
     * @param user 用户对象，包含查询条件
     * @return 查询结果，成功返回1和用户信息，失败返回0
     */
    @RequestMapping("/User/userSelect")
    public Result userSelect(User user) {
        try {
            return new Result(1, userService.userSelect(user));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(0);
        }
    }
}