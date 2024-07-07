package org.example.onlinetestbackendtestpart.Controller;

import org.example.onlinetestbackendtestpart.Mapper.TestMapper;
import org.example.onlinetestbackendtestpart.Service.TestService;
import org.example.onlinetestbackendtestpart.pojo.Result;
import org.example.onlinetestbackendtestpart.pojo.UserToQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("/entertest")
    public Result entertest(int userid) {
        try{
            return new Result(1,testService.enterTest(userid));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }
}
