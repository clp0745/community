package com.clp.community.controller;

import com.clp.community.dto.QuestionDTO;
import com.clp.community.mapper.UserMapper;
import com.clp.community.mode.Question;
import com.clp.community.mode.User;
import com.clp.community.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(name = "pn",defaultValue = "1") Integer pn,
                          HttpServletRequest request,
                          Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length!=0)
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }

        PageHelper.startPage(pn,5);

        List<Question> questionList = questionService.list();
        //封装了详细的分页信息 ，包括我们查询出来的数据
        PageInfo pageInfo = new PageInfo(questionList);

        //封装用户信息
        List<QuestionDTO> questionDTOList = questionService.setQuestionDTO(questionList);

        pageInfo.setList(questionDTOList);

        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

}
