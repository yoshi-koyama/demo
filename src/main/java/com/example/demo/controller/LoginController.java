package com.example.demo.controller;

import com.example.demo.constants.RequestUrl;
import com.example.demo.constants.TemplatePath;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @ModelAttribute
    public LoginForm setUp(){
        return new LoginForm();
    }


    @RequestMapping(RequestUrl.LOGIN)
    public String login(@AuthenticationPrincipal User user){

        if(user != null){
            return "redirect:" + RequestUrl.HOME;
        }
        return TemplatePath.LOGIN;
    }
}
