package org.example.onlinetestbackend.Controller;

import org.example.onlinetestbackend.Service.UserService;
import org.example.onlinetestbackend.pojo.Result;
import org.example.onlinetestbackend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestBody User user) {
        try{
            String jwt=userService.login(user.getUserid(),user.getPassword());
            if(jwt!=null)return new Result(1,jwt);
            return new Result(0,"ID或密码错误");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/User/userInsert")
    public Result userInsert(User user) {
        try{
            userService.userInsert(user);
            return new Result(1);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/User/userDelete")
    public Result userDelete(int[] userid) {
        try{
            userService.userDelete(userid);
            return new Result(1);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/User/userUpdate")
    public Result userUpdate(User user) {
        try{
            userService.userUpdate(user);
            return new Result(1);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/User/userSelect")
    public Result userSelect(User user) {
        try{
            return new Result(1,userService.userSelect(user));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }
}
