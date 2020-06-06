package com.sufu.ems.controller;

import com.sufu.ems.dto.BaseResult;
import com.sufu.ems.entity.TbSelectClass;
import com.sufu.ems.entity.TbStudent;
import com.sufu.ems.exception.MajorNotMatchException;
import com.sufu.ems.exception.RepeatedOperationException;
import com.sufu.ems.exception.ResourceNotFindException;
import com.sufu.ems.exception.UserNotFindException;
import com.sufu.ems.service.TbSelectClassService;
import com.sufu.ems.service.TbStudentService;
import com.sufu.ems.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * @param studentNumber 学号
     * @param classId 要选的select_class的id
     * @return com.sufu.ems.dto.BaseResult
     * @description 选课，具体业务在service里面
     **/
    @Transactional
    @PreAuthorize("principal.username.equals(#studentNumber)")//限制只能给自己选课
    @PostMapping(Constants.APIPREFIXV1+"/select/classes/{classId}")
    public BaseResult selectClass(String studentNumber,@PathVariable("classId") int classId) throws ResourceNotFindException, UserNotFindException, RepeatedOperationException, MajorNotMatchException {
        TbStudent student = tbStudentService.selectByStudentNumber(studentNumber);
        return tbSelectClassService.selectClass(student, classId);
    }

    /**
     * @author sufu
     * @date 2020/5/30 0:20
     * @param studentNumber 学号
     * @return com.sufu.ems.dto.BaseResult
     * @description 根据学号查询待选课程
     **/
    @GetMapping(Constants.APIPREFIXV1+"/select/classes")
    @PreAuthorize("principal.username.equals(#studentNumber)")//限制只能查询自己的信息
    public BaseResult getClasses(String studentNumber) throws Exception {
        TbStudent student = tbStudentService.selectByStudentNumber(studentNumber);
        if(student==null){
            throw new UserNotFindException();
        }
        List<TbSelectClass> classes = tbSelectClassService.getClasses(student);
        return BaseResult.success("请求成功",classes);
    }
}
