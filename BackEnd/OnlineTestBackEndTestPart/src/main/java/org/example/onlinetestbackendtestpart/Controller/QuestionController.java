package org.example.onlinetestbackendtestpart.Controller;

import org.example.onlinetestbackendtestpart.Service.QuestionService;
import org.example.onlinetestbackendtestpart.pojo.StorageQuestion;
import org.example.onlinetestbackendtestpart.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @RequestMapping("/qInsert")
    public Result qInsert(StorageQuestion storageQuestion, @RequestParam(name = "img",required = false)MultipartFile file) {
        try{
            questionService.insertQuestion(storageQuestion,file);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
        return new Result(1);
    }

    @RequestMapping("/qDelete")
    public Result qDelete(int[] qid) {
        try{
            questionService.deleteQuestion(qid);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
        return new Result(1);
    }

    @RequestMapping("/qSelect")
    public Result qSelect(StorageQuestion storageQuestion) {
        try{
        return new Result(1, questionService.selectQuestion(storageQuestion));
        } catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }

    @RequestMapping("/qUpdate")
    public Result qUpdate(StorageQuestion storageQuestion, @RequestParam(name = "img",required = false)MultipartFile file) {
        try{
            questionService.updateQuestion(storageQuestion,file);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
        return new Result(1);
    }
}
