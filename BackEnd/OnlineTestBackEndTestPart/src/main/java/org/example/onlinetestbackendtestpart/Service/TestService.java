package org.example.onlinetestbackendtestpart.Service;

import org.example.onlinetestbackendtestpart.Mapper.TestMapper;
import org.example.onlinetestbackendtestpart.pojo.PostQuestion;
import org.example.onlinetestbackendtestpart.pojo.Question;
import org.example.onlinetestbackendtestpart.pojo.UserToQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.timer.Timer;
import java.util.*;

@Component
public class TestService {
    final int quantity=3;

    @Autowired
    TestMapper testMapper;


    public List<PostQuestion> enterTest(int userid) {
        List<PostQuestion> questionsResult=new ArrayList<>();
        Comparator<PostQuestion> byqid=Comparator.comparing(PostQuestion::getQid);

        List<UserToQuestion> userToQuestions=testMapper.paperCheck(userid);
        if(!userToQuestions.isEmpty())                      //若上次试卷未提交
        {
            List<Integer>qids=new ArrayList<>();
            for(UserToQuestion userToQuestion:userToQuestions)qids.add(userToQuestion.getQid());
            questionsResult=testMapper.repostQuestions(qids);
            questionsResult.sort(byqid);                    //排序后发送
            return questionsResult;
        }

        List<Question> questions=testMapper.qSelectAll();   //若不存在未提交的试卷
        Random random=new Random();                         //对题库进行抽取
        random.setSeed(System.currentTimeMillis());
        Collections.shuffle(questions,random);
        int count=0;
        while(count<quantity) {
            questionsResult.add(new PostQuestion(questions.get(count++)));
        }
        testMapper.paperCreate(userid,questionsResult);
        questionsResult.sort(byqid);                        //排序后发送
        return questionsResult;
    }

    public void answerSave(UserToQuestion userToQuestion) {
        testMapper.answerSave(userToQuestion.getRid(), userToQuestion.getUseranswer());
    }

    public float getGrade(int userid)
    {
        return (int)(testMapper.questionCorrect(userid) * 1000.0f / quantity)/10.0f;
    }

}
