package org.example.onlinetestbackend.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.onlinetestbackend.pojo.PostQuestion;
import org.example.onlinetestbackend.pojo.StorageQuestion;
import org.example.onlinetestbackend.pojo.UserToQuestion;

import java.util.List;

@Mapper
public interface TestMapper {
    public List<UserToQuestion> paperCheck(int userid);
    public List<StorageQuestion> repostQuestions(List<Integer> qids);
    public List<StorageQuestion> qSelectBy2Major(String major1, String major2);
    public void paperCreate(int userid,List<PostQuestion> questions);
    public void answerSave(int rid,String useranswer);
    public int questionCorrect(int userid);
    public void testOver(int userid);
}
