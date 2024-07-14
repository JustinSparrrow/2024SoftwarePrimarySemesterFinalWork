package org.example.onlinetestbackend.Service;

import org.example.onlinetestbackend.Mapper.QuestionMapper;
import org.example.onlinetestbackend.pojo.StorageQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    final String path="/root/MySQLServer";
    public void insertQuestion(StorageQuestion storageQuestion, MultipartFile file) {
        if(file != null) {  //如果存在图片数据则生成uuid并存入本地存储空间，将存储地址存入数据库
            String id= UUID.randomUUID().toString();
            try{
                file.transferTo(new File(path+id+".jpg"));
                storageQuestion.setQimg(path+id+".jpg");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        questionMapper.insertQuestion(storageQuestion);
    }

    public void deleteQuestion(int[] ids) {
        for(int id:ids) {   //删除对应的图片文件
            StorageQuestion storageQuestion =new StorageQuestion(id,null,null,null,null,null);
            File file=new File(questionMapper.selectQuestion(storageQuestion).get(0).getQimg());
            file.delete();
        }
        questionMapper.deleteQuestion(ids);
    }

    public List<StorageQuestion> selectQuestion(StorageQuestion storageQuestion) {
        return questionMapper.selectQuestion(storageQuestion);
    }

    public void updateQuestion(StorageQuestion storageQuestion, MultipartFile file) {
        if(file!=null) {    //如果存在图片数据则生成uuid并存入本地存储空间，将存储地址存入数据库
            String id= UUID.randomUUID().toString();
            try{
                file.transferTo(new File(path+id+".jpg"));
                storageQuestion.setQimg(path);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        questionMapper.updateQuestion(storageQuestion);
    }
}
