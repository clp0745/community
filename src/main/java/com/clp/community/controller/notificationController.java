package com.clp.community.controller;

import com.clp.community.dto.NotificationDTO;
import com.clp.community.dto.QuestionDTO;
import com.clp.community.enums.NotificationTypeEnum;
import com.clp.community.model.Notification;
import com.clp.community.model.Question;
import com.clp.community.model.User;
import com.clp.community.service.NotificationService;
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
public class notificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id,
                          HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            return "redirect:/";
        }

        NotificationDTO notificationDTO = notificationService.read(id,user);

        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
            || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()){
            return "redirect:/question/" + notificationDTO.getOuterId();
        } else {
            return "redirect:/";
        }


    }
}

