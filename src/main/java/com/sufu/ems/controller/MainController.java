package com.sufu.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/4/24 21:58
 * @description
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String login(String name,String password){
        return "index";
    }
    @GetMapping("/admin/index")
    public String adminIndex(){
        return "admin/index";
    }
    @GetMapping("/user/index")
    public String userIndex(){
        return "user/index";
    }
}
