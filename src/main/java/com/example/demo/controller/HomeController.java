package com.example.demo.controller;

import com.example.demo.constants.RequestUrl;
import com.example.demo.constants.TemplatePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", RequestUrl.HOME})
    public String home(){
        return TemplatePath.HOME;
    }

    @GetMapping(RequestUrl.USER)
    public String user(){
        return TemplatePath.USER;
    }
}
