package com.sufu.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/7 1:02
 * @description
 */
@Controller
@RequestMapping(value = "student")
public class StudentController {
    @GetMapping({"/","index"})
    public String index(){
        return "/student/index";
    }
}
