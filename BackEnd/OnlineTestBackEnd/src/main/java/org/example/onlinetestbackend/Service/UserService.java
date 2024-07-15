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

    public String login(int userid, String password) {
        String encryption=DigestUtils.md5DigestAsHex(password.getBytes());                      //对密码加密
        User user=new User(userid,null,null,null,encryption,null);  //匹配用户密码是否正确
        List<User> searchResult=userMapper.userSelect(user);
        if( !searchResult.isEmpty())
        {
            Map<String,Object> map=new HashMap<>();             //将用户id，密码，管理员标记放入jwt令牌后送回
            map.put("userid",userid);
            map.put("password",encryption);
            map.put("admin",searchResult.get(0).getAdmin());
            return JWTUtils.generateToken(map);
        }
        return null;
    }

    public void userInsert(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));//对密码加密
        userMapper.userInsert(user);
    }

    public void userDelete(int[] userids) {
        userMapper.userDelete(userids);
    }

    public void userUpdate(User user) {
        if(user.getPassword()!=null)user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));//对密码加密
        System.out.println(user.getUserid()+"is changing:"+user.getEmail());
        userMapper.userUpdate(user);
    }

    public List<User> userSelect(User user){
        List<User> users=userMapper.userSelect(user);
        for(User userIns:users)userIns.setPassword("~password~ ^_^");    //让所有查询操作都无法得到加密后的密码
        return users;
    }
}
