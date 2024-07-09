package org.example.onlinetestbackendtestpart.Service;

import org.example.onlinetestbackendtestpart.Mapper.QuestionMapper;
import org.example.onlinetestbackendtestpart.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Component
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    public void insertQuestion(Question question, MultipartFile file) {
        if(file != null) {  //如果存在图片数据则生成uuid并存入本地存储空间，将存储地址存入数据库
            String id= UUID.randomUUID().toString();
            String path="D:\\College\\MySQL\\ServerFileData\\"+id+".jpg";
            try{
                file.transferTo(new File(path));
                question.setQimg(path);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        questionMapper.insertQuestion(question);
    }

    public void deleteQuestion(int[] id) {
        questionMapper.deleteQuestion(id);
    }

    public List<Question> selectQuestion(Question question) {
        return questionMapper.selectQuestion(question);
    }

    public void updateQuestion(Question question, MultipartFile file) {
        if(file!=null) {    //如果存在图片数据则生成uuid并存入本地存储空间，将存储地址存入数据库
            String id= UUID.randomUUID().toString();
            String path="D:\\College\\MySQL\\ServerFileData\\"+id+".jpg";
            try{
                file.transferTo(new File(path));
                question.setQimg(path);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        questionMapper.updateQuestion(question);
    }
}
