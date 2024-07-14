package org.example.onlinetestbackend.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.onlinetestbackend.pojo.StorageQuestion;

import java.util.List;

@Mapper
public interface QuestionMapper {
    public void insertQuestion(StorageQuestion storageQuestion);
    public void deleteQuestion(int[] qids);
    public List<StorageQuestion> selectQuestion(StorageQuestion storageQuestion);
    public void updateQuestion(StorageQuestion storageQuestion);
}
