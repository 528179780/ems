package com.sufu.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/7 0:56
 * @description
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    @GetMapping({"/","index"})
    public String index(){
        return "/admin/index";
    }
}
