package org.example.onlinetestbackendtestpart.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.onlinetestbackendtestpart.pojo.PostQuestion;
import org.example.onlinetestbackendtestpart.pojo.Question;
import org.example.onlinetestbackendtestpart.pojo.UserToQuestion;

import java.util.List;

@Mapper
public interface TestMapper {
    public List<UserToQuestion> paperCheck(int userid);
    public List<PostQuestion> repostQuestions(List<Integer> qids);
    public List<Question> qSelectAll();
    public void paperCreate(int userid,List<PostQuestion> questions);
    public void answerSave(int rid,String useranswer);
    public int questionCorrect(int userid);
}
