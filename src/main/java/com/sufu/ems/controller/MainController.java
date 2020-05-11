package com.sufu.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/4/24 21:58
 * @description
 */
@Controller
public class MainController {
    @GetMapping({"/","index"})
    public String main(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("error/500")
    public String error500(){
        return "error/500";
    }
}
