package org.example.onlinetestbackend.Controller;

import org.example.onlinetestbackend.Service.QuestionService;
import org.example.onlinetestbackend.pojo.StorageQuestion;
import org.example.onlinetestbackend.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @RequestMapping("/Question/qInsert")
    public Result qInsert(StorageQuestion storageQuestion, @RequestParam(name = "img",required = false)MultipartFile file) {
        try{
            questionService.insertQuestion(storageQuestion,file);
            return new Result(1);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/Question/qDelete")
    public Result qDelete(int[] qid) {
        try{
            questionService.deleteQuestion(qid);
            return new Result(1);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/Question/qSelect")
    public Result qSelect(StorageQuestion storageQuestion) {
        try{
        return new Result(1, questionService.selectQuestion(storageQuestion));
        } catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/Question/qUpdate")
    public Result qUpdate(StorageQuestion storageQuestion, @RequestParam(name = "img",required = false)MultipartFile file) {
        try{
            questionService.updateQuestion(storageQuestion,file);
            return new Result(1);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }
}
