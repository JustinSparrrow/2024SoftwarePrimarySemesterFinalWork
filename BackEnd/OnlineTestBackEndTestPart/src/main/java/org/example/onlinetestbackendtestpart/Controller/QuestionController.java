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
    public Result qinsert(Question question) {
        try{
            questionMapper.insertQuestion(question);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
        return new Result(1);
    }

    @RequestMapping("/qdelete")
    public Result qdelete(int[] qid) {
        try{
            questionMapper.deleteQuestion(qid);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
        return new Result(1);
    }

    @RequestMapping("/qselect")
    public Result qselect(Question question) {
        return new Result(1, questionMapper.selectQuestion(question));
    }

    @RequestMapping("/qupdate")
    public Result qupdate(Question question) {
        try{
            questionMapper.updateQuestion(question);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
        return new Result(1);
    }
}
