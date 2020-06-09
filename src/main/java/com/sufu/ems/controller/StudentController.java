package com.sufu.ems.controller;

import com.sufu.ems.entity.*;
import com.sufu.ems.exception.UserNotFindException;
import com.sufu.ems.dto.BaseResult;
import com.sufu.ems.service.TbExamService;
import com.sufu.ems.service.TbScoreService;
import com.sufu.ems.service.TbSelectClassService;
import com.sufu.ems.service.TbStudentService;
import com.sufu.ems.utils.CurrentPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/7 1:02
 * @description 学生信息相关类
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private TbStudentService tbStudentService;
    @Autowired
    private TbExamService tbExamService;
    @Autowired
    private TbScoreService tbScoreService;
    @Autowired
    private TbSelectClassService tbSelectClassService;
    @GetMapping({"/","index"})
    public ModelAndView index(){
        return new ModelAndView("student/index");
    }
    @GetMapping("/select")
    public ModelAndView toSelectCoursePage(){
        return new ModelAndView("select");
    }
    /**
     * @author sufu
     * @date 2020/5/13 16:30
     * @param
     * @return com.sufu.ems.dto.BaseResult
     * @description 查询所有学生
     **/
    @GetMapping("students")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public BaseResult selectAll(){
        List<TbStudent> students = tbStudentService.selectAll();
        Map<String,Object> remap = new HashMap<>();
        remap.put("students", students);
        return BaseResult.success("成功",remap);
    }
    /**
     * @author sufu
     * @date 2020/5/13 9:44
     * @param
     * @return com.sufu.ems.dto.BaseResult
     * @description 查询自己的考试
     **/
    @GetMapping("/exams")
    public String selectExamByStudentNumber(Model model) throws Exception {
        SeUser seUser =CurrentPrincipal.getCurrentPrincipal();
        TbStudent student = tbStudentService.selectByStudentNumber(seUser.getUsername());
        if(student == null){
            model.addAttribute("error", "没有找到该用户！系统异常");
            return "student/exam";
        }
        List<TbExam> exams = tbExamService.selectExams(student);
        if(exams == null||exams.size()==0){
            model.addAttribute("error", "没有考试信息");
            return "student/exam";
        }
        model.addAttribute("exams",exams);
        return "student/exam";
    }
    /**
     * @author sufu
     * @date 2020/5/13 16:58
     * @param
     * @return com.sufu.ems.dto.BaseResult
     * @description 查询自己的成绩
     **/
    @GetMapping("/scores")
    public String getScores(Model model) throws Exception {
        SeUser seUser =CurrentPrincipal.getCurrentPrincipal();
        //查询当前用户的成绩
        TbStudent student = tbStudentService.selectByStudentNumber(seUser.getUsername());
        //如果根据学号无法获得学生对象，则系统出现异常，抛出异常
        if(student == null){
            model.addAttribute("error", "没有找到该用户！系统异常");
            return "student/score";
        }
        List<TbScore> scores = tbScoreService.selectByStudentNumber(student);
        //若无法获取成绩信息，则出现异常，时间不对或者出现异常
        if(scores == null||scores.size()==0){
            model.addAttribute("error", "没有成绩信息！");
            return "student/score";
        }
        model.addAttribute("scores", scores);
        return "student/score";
    }
    /**
     * @author sufu
     * @date 2020/5/13 17:13
     * @param
     * @return com.sufu.ems.dto.BaseResult
     * @description 查询当前登陆学生的信息
     **/
    @GetMapping("/info")
    public String selectStudentByNumber(Model model) throws UserNotFindException {
        SeUser seUser =CurrentPrincipal.getCurrentPrincipal();
        TbStudent student = tbStudentService.selectByStudentNumber(seUser.getUsername());
        //如果根据学号无法获得学生对象，则系统出现异常，抛出异常,一般不会出现此异常，除非数据库中 SpringSecurity中的用户列表和学生信息布不匹配
        if(student == null){
            model.addAttribute("error", "没有找到学生信息，系统异常！");
            return "student/info";
        }
        model.addAttribute("info", student);
        return "student/info";
    }
    /**
     * @author sufu
     * @date 2020/6/9 上午10:55
     * @param model
     * @return java.lang.String
     * @description 跳转到选课页面的方法
     **/
    @GetMapping("to/select/classes")
    public String selectClass(Model model){
        SeUser seUser = CurrentPrincipal.getCurrentPrincipal();
        TbStudent student = tbStudentService.selectByStudentNumber(seUser.getUsername());
        //如果根据学号无法获得学生对象，则系统出现异常，抛出异常,一般不会出现此异常，除非数据库中 SpringSecurity中的用户列表和学生信息布不匹配
        if(student == null){
            model.addAttribute("error", "没有找到学生信息，系统异常！");
            return "student/info";
        }
        List<TbCourseStudent> list =tbSelectClassService.getSelectedClasses(student);
        model.addAttribute("selectedClasses",list);
        return "student/select";
    }
}
