package org.example.onlinetestbackendtestpart.Controller;

import org.example.onlinetestbackendtestpart.Mapper.QuestionMapper;
import org.example.onlinetestbackendtestpart.Service.QuestionService;
import org.example.onlinetestbackendtestpart.pojo.Question;
import org.example.onlinetestbackendtestpart.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @CrossOrigin(origins = "")
    @RequestMapping("/qinsert")
    public Result qinsert(Question question, @RequestParam(name = "img",required = false)MultipartFile file) {
        try{
            questionService.insertQuestion(question,file);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
        return new Result(1);
    }

    @CrossOrigin(origins = "")
    @RequestMapping("/qdelete")
    public Result qdelete(int[] qid) {
        try{
            questionService.deleteQuestion(qid);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
        return new Result(1);
    }

    @CrossOrigin(origins = "")
    @RequestMapping("/qselect")
    public Result qselect(Question question) {
        try{
        return new Result(1, questionService.selectQuestion(question));
        } catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }

    @CrossOrigin(origins = "")
    @RequestMapping("/qupdate")
    public Result qupdate(Question question,@RequestParam(name = "img",required = false)MultipartFile file) {
        try{
            questionService.updateQuestion(question,file);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
        return new Result(1);
    }
}
