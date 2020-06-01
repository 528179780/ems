package com.sufu.ems.controller;

import com.sufu.ems.exception.ResourceNotFindException;
import com.sufu.ems.exception.UserNotFindException;
import com.sufu.ems.dto.BaseResult;
import com.sufu.ems.entity.TbExam;
import com.sufu.ems.entity.TbScore;
import com.sufu.ems.entity.TbStudent;
import com.sufu.ems.service.TbExamService;
import com.sufu.ems.service.TbScoreService;
import com.sufu.ems.service.TbStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * @description 查询所有学生需要管理员权限
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
     * @param studentNumber 学号
     * @return com.sufu.ems.dto.BaseResult
     * @description 查询自己的考试
     **/
    @GetMapping("/exams")
    @PreAuthorize("principal.username.equals(#studentNumber)")//限制只能查询自己的信息
    public BaseResult selectExamByStudentNumber(String studentNumber) throws Exception {
        TbStudent student = tbStudentService.selectByStudentNumber(studentNumber);
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
     * @param studentNumber 学号
     * @return com.sufu.ems.dto.BaseResult
     * @description 查询自己的成绩
     **/
    @GetMapping("/scores")
    @PreAuthorize("principal.username.equals(#studentNumber)")
    public BaseResult selectScoreByStudentNumber(String studentNumber) throws Exception {
        TbStudent student = tbStudentService.selectByStudentNumber(studentNumber);
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
     * @param studentNumber 学号
     * @return com.sufu.ems.dto.BaseResult
     * @description 查询学生信息
     **/
    @GetMapping("/info")
    @PreAuthorize("principal.username.equals(#studentNumber)")
    public BaseResult selectStudentByNumber(String studentNumber) throws UserNotFindException {
        TbStudent student = tbStudentService.selectByStudentNumber(studentNumber);
        //如果根据学号无法获得学生对象，则系统出现异常，抛出异常
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
