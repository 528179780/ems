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
    @GetMapping({"/","index"})
    public String main(){
        return "index";
    }
}
