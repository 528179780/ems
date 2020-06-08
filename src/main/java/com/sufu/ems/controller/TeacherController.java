package com.sufu.ems.controller;

import com.sufu.ems.entity.SeUser;
import com.sufu.ems.exception.ResourceNotFindException;
import com.sufu.ems.exception.UserNotFindException;
import com.sufu.ems.dto.BaseResult;
import com.sufu.ems.entity.TbTeacher;
import com.sufu.ems.service.TbCourseClassService;
import com.sufu.ems.service.TbTeacherService;
import com.sufu.ems.utils.CurrentPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/7 1:03
 * @description
 */
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    private TbCourseClassService tbCourseClassService;
    @Autowired
    private TbTeacherService tbTeacherService;
    @GetMapping({"/","/index"})
    public ModelAndView index(){
        return new ModelAndView("/teacher/index");
    }
    @GetMapping("/classes")
    public BaseResult selectClasses(String teacherNumber) throws UserNotFindException, ResourceNotFindException {
        SeUser seUser = CurrentPrincipal.getCurrentPrincipal();
        TbTeacher teacher = tbTeacherService.selectTeacherByTeacherNumber(seUser.getUsername());
        if(teacher == null){
            throw new UserNotFindException("未找到该教师，系统异常");
        }
        List<String> classNumbers = tbCourseClassService.getClassNumbers(teacher);
        if(classNumbers==null||classNumbers.size()==0){
            throw new ResourceNotFindException("该老师无授课班级，请联系管理员");
        }
        return BaseResult.success("成功", classNumbers);
    }
}