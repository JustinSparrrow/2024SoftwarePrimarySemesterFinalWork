package org.example.onlinetestbackendtestpart;

import org.example.onlinetestbackendtestpart.Controller.TestController;
import org.example.onlinetestbackendtestpart.Mapper.QuestionMapper;
import org.example.onlinetestbackendtestpart.Mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineTestBackEndTestPartApplicationTests {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    private TestController testController;

    @Test
    void contextLoads() {
        System.out.println(testController.testEnter(2,"english/math"));
    }

}
