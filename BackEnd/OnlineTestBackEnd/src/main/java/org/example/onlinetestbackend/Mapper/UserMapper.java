package org.example.onlinetestbackend.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.onlinetestbackend.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {
    public void userInsert(User user);
    public void userDelete(int[] userids);
    public void userUpdate(User user);
    public List<User> userSelect(User user);
}
