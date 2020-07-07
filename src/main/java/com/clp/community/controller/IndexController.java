package com.clp.community.controller;

import com.clp.community.dto.QuestionDTO;
import com.clp.community.model.Question;
import com.clp.community.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(name = "pn",defaultValue = "1") Integer pn,
                        @RequestParam(name = "search",required = false) String search,
                        Model model){

        PageHelper.startPage(pn,5);

        List<Question> questionList = questionService.list(search);
        //封装了详细的分页信息 ，包括我们查询出来的数据
        PageInfo pageInfo = new PageInfo(questionList);

        //封装用户信息
        List<QuestionDTO> questionDTOList = questionService.setQuestionDTO(questionList);

        pageInfo.setList(questionDTOList);

        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

}
