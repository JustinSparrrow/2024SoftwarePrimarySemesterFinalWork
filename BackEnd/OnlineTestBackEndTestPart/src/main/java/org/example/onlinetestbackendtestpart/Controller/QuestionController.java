package org.example.onlinetestbackendtestpart.Controller;

import org.example.onlinetestbackendtestpart.Service.QuestionService;
import org.example.onlinetestbackendtestpart.pojo.StorageQuestion;
import org.example.onlinetestbackendtestpart.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @CrossOrigin(origins = "")
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

    @CrossOrigin(origins = "")
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

    @CrossOrigin(origins = "")
    @RequestMapping("/qSelect")
    public Result qSelect(StorageQuestion storageQuestion) {
        try{
        return new Result(1, questionService.selectQuestion(storageQuestion));
        } catch (Exception e){
            e.printStackTrace();
            return new Result(0);
        }
    }

    @CrossOrigin(origins = "")
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
