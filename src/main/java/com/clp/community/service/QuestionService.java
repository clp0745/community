package com.clp.community.service;

import com.clp.community.dto.QuestionDTO;
import com.clp.community.exception.CustomizeErrorCode;
import com.clp.community.exception.CustomizeException;
import com.clp.community.mapper.QuestionMapper;
import com.clp.community.mapper.UserMapper;
import com.clp.community.model.Question;
import com.clp.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<Question> list() {
        return questionMapper.list();
    }

    public List<QuestionDTO> setQuestionDTO(List<Question> questions) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//将question属性拷贝到questionDTO对象上
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public List<Question> list(Integer userId) {
        return questionMapper.listByUserId(userId);
    }

    public List<QuestionDTO> setQuestionDTOByUserId(List<Question> questions) {
        List<QuestionDTO> questionDTOListByUserId = new ArrayList<>();
        for (Question question : questions){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//将question属性拷贝到questionDTO对象上
            questionDTO.setUser(user);
            questionDTOListByUserId.add(questionDTO);
        }
        return questionDTOListByUserId;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null){
            //創建
            questionMapper.create(question);
        }else {
            //更新
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }

    public void incView(Integer id) {
        Question question = questionMapper.getById(id);
        Question updateQuestion = new Question();
        System.out.println(question.getViewCount());
        updateQuestion.setViewCount(question.getViewCount() + 1);
        questionMapper.update(updateQuestion);
    }
}
