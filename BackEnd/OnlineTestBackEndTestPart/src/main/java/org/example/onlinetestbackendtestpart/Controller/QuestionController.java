package org.example.onlinetestbackendtestpart.Controller;

import org.example.onlinetestbackendtestpart.Mapper.QuestionMapper;
import org.example.onlinetestbackendtestpart.pojo.Question;
import org.example.onlinetestbackendtestpart.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    QuestionMapper questionMapper;

    @RequestMapping("/qinsert")
    public String qinsert(Question question) {
        try{
            questionMapper.insertQuestion(question);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0).toString();
        }
        return new Result(1).toString();
    }

    @RequestMapping("/qdelete")
    public String qdelete(String[] qid) {
        try{
            questionMapper.deleteQuestion(qid);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0).toString();
        }
        return new Result(1).toString();
    }

    @RequestMapping("/qselect")
    public String qselect(Question question) {
        return questionMapper.selectQuestion(question).toString();
    }


}
