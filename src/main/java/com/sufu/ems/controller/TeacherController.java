package com.sufu.ems.controller;

import com.sufu.ems.entity.*;
import com.sufu.ems.service.TbCourseClassService;
import com.sufu.ems.service.TbStudentService;
import com.sufu.ems.service.TbTeacherService;
import com.sufu.ems.utils.CurrentPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/7 1:03
 * @description
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    private TbCourseClassService tbCourseClassService;
    @Autowired
    private TbTeacherService tbTeacherService;
    @Autowired
    private TbStudentService tbStudentService;

    @GetMapping({"/","/index"})
    public ModelAndView index(){
        return new ModelAndView("/teacher/index");
    }
    /**
     * @author sufu
     * @date 2020/6/8 下午6:30
     * @return java.lang.String
     * @description
     **/
    @GetMapping("to/classes")
    public String toSelectClasses(Model model){
        SeUser seUser = CurrentPrincipal.getCurrentPrincipal();
        TbTeacher teacher = tbTeacherService.selectTeacherByTeacherNumber(seUser.getUsername());
        if(teacher == null){
            model.addAttribute("error", "没有找到教师信息，系统异常！");
            return "teacher/classes";
        }
        List<String> classNumbers = tbCourseClassService.getClassNumbers(teacher);
        if(classNumbers==null||classNumbers.size()==0){
            model.addAttribute("error", "该老师无授课班级");
            return "teacher/classes";
        }
        //string类型的list
        model.addAttribute("classes", classNumbers);
        return "teacher/classes";
    }
    /**
     * @author sufu
     * @date 2020/6/9 下午12:03
     * @param model
     * @param classNumber 课程编号
     * @return java.lang.String
     * @description
     **/
    @GetMapping("/to/update/scores")
    public String updateScores(Model model,String classNumber){
        SeUser seUser = CurrentPrincipal.getCurrentPrincipal();
        List<TbStudent> tbStudents = tbStudentService.selectByClass(classNumber);
        TbTeacher tbTeacher = tbTeacherService.selectTeacherByTeacherNumber(seUser.getUsername());
        TbCourseClass tbCourseClass = tbCourseClassService.getClasses(tbTeacher,classNumber);
        List<TbScore> scoreList = new ArrayList<>();
        TbScore score;
        for (TbStudent tbStudent : tbStudents) {
            score = new TbScore(tbStudent, tbCourseClass);
            scoreList.add(score);
        }
        model.addAttribute("scores", scoreList);
        return "/teacher/scores";
    }
}