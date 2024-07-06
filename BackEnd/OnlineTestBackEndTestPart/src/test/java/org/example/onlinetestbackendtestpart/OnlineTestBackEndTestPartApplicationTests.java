package org.example.onlinetestbackendtestpart;

import org.example.onlinetestbackendtestpart.Mapper.QuestionMapper;
import org.example.onlinetestbackendtestpart.pojo.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineTestBackEndTestPartApplicationTests {

    @Autowired
    QuestionMapper questionMapper;
    @Test
    void contextLoads() {
        System.out.println(questionMapper.selectQuestion(new Question()));
    }

}
