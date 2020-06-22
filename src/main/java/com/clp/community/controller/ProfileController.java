package com.clp.community.controller;

import com.clp.community.dto.QuestionDTO;
import com.clp.community.mode.Question;
import com.clp.community.mode.User;
import com.clp.community.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "pn",defaultValue = "1") Integer pn,
                          HttpServletRequest request,
                          Model model){

        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            return "redirect:/";
        }

        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        } else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PageHelper.startPage(pn,5);
        List<Question> questionListByUserId = questionService.list(user.getId());//user.getId()
        PageInfo PageInfoByUserId = new PageInfo(questionListByUserId);

        //封装所查用户id的信息
        List<QuestionDTO> questionDTOListByUserId = questionService.setQuestionDTOByUserId(questionListByUserId);
        PageInfoByUserId.setList(questionDTOListByUserId);
        model.addAttribute("pageInfo",PageInfoByUserId);
        return "profile";
    }
}
