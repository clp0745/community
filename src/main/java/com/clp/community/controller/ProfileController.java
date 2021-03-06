package com.clp.community.controller;

import com.clp.community.dto.NotificationDTO;
import com.clp.community.dto.QuestionDTO;
import com.clp.community.model.Notification;
import com.clp.community.model.Question;
import com.clp.community.model.User;
import com.clp.community.service.NotificationService;
import com.clp.community.service.QuestionService;
import com.github.pagehelper.Page;
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

    @Autowired
    private NotificationService notificationService;

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

            /*分页查询我的问题*/
            PageHelper.startPage(pn,5);
            List<Question> questionListByUserId = questionService.list(user.getId());//user.getId()
            PageInfo PageInfoByUserId = new PageInfo(questionListByUserId);

            //封装所查用户id的信息
            List<QuestionDTO> questionDTOListByUserId = questionService.setQuestionDTO(questionListByUserId);
            PageInfoByUserId.setList(questionDTOListByUserId);
            model.addAttribute("pageInfo",PageInfoByUserId);

        } else if ("replies".equals(action)){

            /*分页查询最新回复*/
            PageHelper.startPage(pn,5);
            List<Notification> notificationList = notificationService.list(user.getId());
            PageInfo notificationPageInfo = new PageInfo(notificationList);

            //封装所查回复的信息
            List<NotificationDTO> notificationDTOList = notificationService.setNotificationDTO(notificationList);
            notificationPageInfo.setList(notificationDTOList);

            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            model.addAttribute("pageInfo",notificationPageInfo);

        }
        return "profile";
    }
}
