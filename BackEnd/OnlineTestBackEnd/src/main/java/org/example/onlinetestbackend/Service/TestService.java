package org.example.onlinetestbackend.Service;

import org.example.onlinetestbackend.Mapper.TestMapper;
import org.example.onlinetestbackend.pojo.PostQuestion;
import org.example.onlinetestbackend.pojo.StorageQuestion;
import org.example.onlinetestbackend.pojo.UserToQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestService {
    final int quantity=3;

    @Autowired
    TestMapper testMapper;

    public List<PostQuestion> paperExistCheck(int userid) {
        List<PostQuestion> questionsResult=new ArrayList<>();
        Comparator<PostQuestion> byqid=Comparator.comparing(PostQuestion::getQid);

        List<UserToQuestion> userToQuestions=testMapper.paperCheck(userid);
        if(!userToQuestions.isEmpty())                      //若上次试卷未提交
        {
            List<Integer>qids=new ArrayList<>();
            for(UserToQuestion userToQuestion:userToQuestions)qids.add(userToQuestion.getQid());
            for(StorageQuestion storageQuestion:testMapper.repostQuestions(qids))questionsResult.add(storageQuestion.postReady());
            questionsResult.sort(byqid);                    //排序后发送
            return questionsResult;
        }
        return null;
    }


    public List<PostQuestion> testEnter(int userid,String major) {
        List<PostQuestion> questionsResult=new ArrayList<>();
        Comparator<PostQuestion> byqid=Comparator.comparing(PostQuestion::getQid);
        String[] qmajor=major.split("/");

        List<StorageQuestion> storageQuestions =testMapper.qSelectBy2Major(qmajor[0],qmajor[1]);   //若不存在未提交的试卷
        Random random=new Random();                         //对题库进行抽取
        random.setSeed(System.currentTimeMillis());
        Collections.shuffle(storageQuestions,random);
        int count=0;
        while(count<quantity&&count< storageQuestions.size()) {
            questionsResult.add(storageQuestions.get(count++).postReady());
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
        float score=(int)(testMapper.questionCorrect(userid) * 1000.0f / quantity)/10.0f;   //按照回答正确的题目数量占比抽取题目总数百分制计算分数
        testMapper.testOver(userid);
        return score;
    }

}
