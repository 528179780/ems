package com.sufu.ems.controller;

import com.sufu.ems.dto.BaseResult;
import com.sufu.ems.entity.SeUser;
import com.sufu.ems.entity.TbSelectClass;
import com.sufu.ems.entity.TbStudent;
import com.sufu.ems.exception.MajorNotMatchException;
import com.sufu.ems.exception.RepeatedOperationException;
import com.sufu.ems.exception.ResourceNotFindException;
import com.sufu.ems.exception.UserNotFindException;
import com.sufu.ems.service.TbSelectClassService;
import com.sufu.ems.service.TbStudentService;
import com.sufu.ems.utils.Constants;
import com.sufu.ems.utils.CurrentPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/15 16:37
 * @description 抢课Controller
 */
@RestController
@RequestMapping("/student")
public class CurriculaVariableController {
    @Autowired
    private TbStudentService tbStudentService;
    @Autowired
    private TbSelectClassService tbSelectClassService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    Logger logger = LoggerFactory.getLogger(CurriculaVariableController.class);

    /**
     * @author sufu
     * @date 2020/5/30 0:16
     * @param
     * @return void
     * @description 初始化的时候调用，将课程余量信息存到redis数据库
     **/
    @PostConstruct
    private void init() throws ResourceNotFindException {
        List<TbSelectClass> list = tbSelectClassService.selectAll();
        if(list.size()!=0){
            for (TbSelectClass selectClass : list) {
                stringRedisTemplate.opsForValue().set(Constants.ORDERKEY+selectClass.getId(), selectClass.getClassesLeft()+"");
            }
        }
        else
            throw new ResourceNotFindException("初始化待选课程失败，没有课程余量记录，请联系管理员");
        logger.info("初始化待选课程成功");
    }
    /**
     * @author sufu
     * @date 2020/6/4 上午12:50
     * @param classId 要选的select_class的id
     * @return com.sufu.ems.dto.BaseResult
     * @description 选课，具体业务在service里面
     **/
    @Transactional
    @PostMapping("/select/classes/{classId}")
    public BaseResult selectClass(@PathVariable("classId") int classId) throws ResourceNotFindException, UserNotFindException, RepeatedOperationException, MajorNotMatchException {
        SeUser seUser =CurrentPrincipal.getCurrentPrincipal();
        TbStudent student = tbStudentService.selectByStudentNumber(seUser.getUsername());
        return tbSelectClassService.selectClass(student, classId);
    }
    
    /**
     * @author sufu
     * @date 2020/5/30 0:20
     * @param
     * @return com.sufu.ems.dto.BaseResult
     * @description 根据学号查询待选课程
     **/
    @GetMapping("/select/classes")
    public BaseResult getClasses() throws Exception {
        SeUser seUser = CurrentPrincipal.getCurrentPrincipal();
        TbStudent student = tbStudentService.selectByStudentNumber(seUser.getUsername());
        if(student==null){
            throw new UserNotFindException();
        }
        List<TbSelectClass> classes = tbSelectClassService.getClasses(student);
        return BaseResult.success("请求成功",classes);
    }
}