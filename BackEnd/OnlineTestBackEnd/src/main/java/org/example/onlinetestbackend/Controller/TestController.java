package org.example.onlinetestbackend.Controller;

import org.example.onlinetestbackend.Service.TestService;
import org.example.onlinetestbackend.pojo.PostQuestion;
import org.example.onlinetestbackend.pojo.Result;
import org.example.onlinetestbackend.pojo.UserToQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("/Test/paperExistCheck")
    public Result paperExistCheck(int userid) {
        try{
            List<PostQuestion> resultQuestion=testService.paperExistCheck(userid);
            if(resultQuestion==null)return new Result(0,"不存在已创建的试卷");
            return new Result(1,resultQuestion);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/Test/testEnter")
    public Result testEnter(int userid,String major) {
        try{
            return new Result(1,testService.testEnter(userid,major));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/Test/answerSave")
    public Result answerSave(UserToQuestion userToQuestion) {
        try{
            testService.answerSave(userToQuestion);
            return new Result(1);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/Test/paperSubmit")
    public Result paperSubmit(int userid) {
        try{
            return new Result(1,testService.getGrade(userid));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }
}
