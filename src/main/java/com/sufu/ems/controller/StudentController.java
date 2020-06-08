package com.sufu.ems.controller;

import com.sufu.ems.entity.SeUser;
import com.sufu.ems.exception.ResourceNotFindException;
import com.sufu.ems.exception.UserNotFindException;
import com.sufu.ems.dto.BaseResult;
import com.sufu.ems.entity.TbExam;
import com.sufu.ems.entity.TbScore;
import com.sufu.ems.entity.TbStudent;
import com.sufu.ems.service.TbExamService;
import com.sufu.ems.service.TbScoreService;
import com.sufu.ems.service.TbStudentService;
import com.sufu.ems.utils.CurrentPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/7 1:02
 * @description 学生信息相关类
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private TbStudentService tbStudentService;
    @Autowired
    private TbExamService tbExamService;
    @Autowired
    private TbScoreService tbScoreService;
    @GetMapping({"/","index"})
    public ModelAndView index(){
        return new ModelAndView("student/index");
    }
    @GetMapping("/score")
    public ModelAndView toScorePage(){
        return new ModelAndView("student/score");
    }
    @GetMapping("/exam")
    public ModelAndView toExamPage(){
        return new ModelAndView("student/exam");
    }
    @GetMapping("/select")
    public ModelAndView toSelectCoursePage(){
        return new ModelAndView("student/select-courses");
    }
    /**
     * @author sufu
     * @date 2020/5/13 16:30
     * @param
     * @return com.sufu.ems.dto.BaseResult
     * @description 查询所有学生
     **/
    @GetMapping("students")
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
    public BaseResult selectExamByStudentNumber() throws Exception {
        SeUser seUser =CurrentPrincipal.getCurrentPrincipal();
        TbStudent student = tbStudentService.selectByStudentNumber(seUser.getUsername());
        if(student == null){
            throw new UserNotFindException();
        }
        List<TbExam> exams = tbExamService.selectExams(student);
        if(exams == null||exams.size()==0){
            throw new ResourceNotFindException("没有找到成绩");
        }
        Map<String,Object> remap = new HashMap<>();
        remap.put("exams", exams);
        return BaseResult.success("成功",remap);
    }
    /**
     * @author sufu
     * @date 2020/5/13 16:58
     * @param
     * @return com.sufu.ems.dto.BaseResult
     * @description 查询自己的成绩
     **/
    @GetMapping("/scores")
    public BaseResult getScores() throws Exception {
        SeUser seUser =CurrentPrincipal.getCurrentPrincipal();
        //查询当前用户的成绩
        TbStudent student = tbStudentService.selectByStudentNumber(seUser.getUsername());
        //如果根据学号无法获得学生对象，则系统出现异常，抛出异常
        if(student == null){
            throw new UserNotFindException();
        }
        List<TbScore> scores = tbScoreService.selectByStudentNumber(student);
        //若无法获取成绩信息，则出现异常，时间不对或者出现异常
        if(scores == null||scores.size()==0){
            throw new ResourceNotFindException("没有找到成绩");
        }
        Map<String,Object> remap = new HashMap<>();
        remap.put("scores", scores);
        return BaseResult.success("成功", remap);
    }
    /**
     * @author sufu
     * @date 2020/5/13 17:13
     * @param
     * @return com.sufu.ems.dto.BaseResult
     * @description 查询当前登陆学生的信息
     **/
    @GetMapping("/info")
    public BaseResult selectStudentByNumber() throws UserNotFindException {
        SeUser seUser =CurrentPrincipal.getCurrentPrincipal();
        TbStudent student = tbStudentService.selectByStudentNumber(seUser.getUsername());
        //如果根据学号无法获得学生对象，则系统出现异常，抛出异常,一般不会出现此异常，除非数据库中 SpringSecurity中的用户列表和学生信息布不匹配
        if(student == null){
            throw new UserNotFindException();
        }
        BaseResult success = BaseResult.success();
        Map<String,Object> remap = new HashMap<>();
        remap.put("info", student);
        success.setData(remap);
        return success;
    }
}
