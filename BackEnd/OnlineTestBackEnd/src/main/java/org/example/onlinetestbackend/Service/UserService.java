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
        String encryption=DigestUtils.md5DigestAsHex(password.getBytes());
        User user=new User(userid,null,null,null,encryption,null);
        User searchResult=userMapper.userSelect(user).get(0);
        if( searchResult!=null)
        {
            Map<String,Object> map=new HashMap<>();
            map.put("userid",userid);
            map.put("password",encryption);
            map.put("admin",searchResult.getAdmin());
            return JWTUtils.generateToken(map);
        }
        return null;
    }

    public void userInsert(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.userInsert(user);
    }

    public void userDelete(int[] userids) {
        userMapper.userDelete(userids);
    }

    public void userUpdate(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.userUpdate(user);
    }

    public List<User> userSelect(User user){
        List<User> users=userMapper.userSelect(user);
        for(User userIns:users)userIns.setPassword("不可以偷看哦~ ^_^");
        return users;
    }
}
