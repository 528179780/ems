package com.sufu.ems.controller;

import com.sufu.ems.dto.BaseResult;
import com.sufu.ems.entity.TbStudent;
import com.sufu.ems.service.TbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/7 1:02
 * @description
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    TbStudentService tbStudentService;
    @GetMapping({"/","index"})
    public ModelAndView index(){
        return new ModelAndView("/student/index");
    }
    @GetMapping("students")
    @PreAuthorize("hasAuthority('admin')")
    public BaseResult selectAll(){
        List<TbStudent> students = tbStudentService.selectAll();
        Map<String,Object> remap = new HashMap<>();
        remap.put("students", students);
        return BaseResult.success("成功",remap);
    }
}
