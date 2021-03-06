package com.clp.community.advice;

import com.clp.community.dto.ResultDTO;
import com.clp.community.exception.CustomizeErrorCode;
import com.clp.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    Object handle(Throwable ex, Model model, HttpServletRequest request){

        String contentType = request.getContentType();
        if ("application/json".equals(contentType)){
            //返回JSON
            if (ex instanceof CustomizeException){
                return ResultDTO.errorOf((CustomizeException) ex);
            } else {
                return ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
        } else{
            //错误页面跳转
            if (ex instanceof CustomizeException){
                model.addAttribute("message",ex.getMessage());
            } else {
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }

            return new ModelAndView("error");
        }


    }
}
