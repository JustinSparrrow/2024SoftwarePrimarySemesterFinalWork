package org.example.onlinetestbackendtestpart.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.onlinetestbackendtestpart.pojo.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {
    public void insertQuestion(Question question);
    public void deleteQuestion(int[] qids);
    public List<Question> selectQuestion(Question question);
    public void updateQuestion(Question question);
}
