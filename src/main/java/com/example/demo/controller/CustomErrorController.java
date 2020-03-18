package com.example.demo.controller;

import com.example.demo.constants.RequestUrl;
import com.example.demo.constants.TemplatePath;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    /**
     * エラーページのパスを返す。
     *
     * @return エラーページのパス
     */
    @Override
    public String getErrorPath() {
        return RequestUrl.ERROR;
    }

    @RequestMapping(RequestUrl.ERROR)
    public String error(HttpServletRequest req) {

        Object statusCode = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (statusCode != null && statusCode.toString().equals("404")) {
            return TemplatePath.NOT_FOUND;
        }

        return getErrorPath();

    }
}
