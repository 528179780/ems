package com.sufu.ems.controller;

import com.sufu.ems.dto.BaseResult;
import com.sufu.ems.entity.TbClassNumber;
import com.sufu.ems.entity.TbOrder;
import com.sufu.ems.entity.TbSelectClass;
import com.sufu.ems.entity.TbStudent;
import com.sufu.ems.exception.ResourceNotFindException;
import com.sufu.ems.exception.UserNotFindException;
import com.sufu.ems.service.TbClassNumberService;
import com.sufu.ems.service.TbOrderService;
import com.sufu.ems.service.TbSelectClassService;
import com.sufu.ems.service.TbStudentService;
import com.sufu.ems.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

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
    @Autowired
    private TbClassNumberService tbClassNumberService;
    @Autowired
    private TbOrderService tbOrderService;

    /**
     * @author sufu
     * @date 2020/5/30 0:16
     * @param
     * @return void
     * @description 初始化的时候调用，将课程余量信息存到redis数据库
     **/
    @PostConstruct
    private void init() throws ResourceNotFindException {
        List<TbClassNumber> list = tbClassNumberService.selectAll();
        if(list.size()!=0){
            for (TbClassNumber tbClassNumber : list) {
                stringRedisTemplate.opsForValue().set(Constants.ORDERKEY+tbClassNumber.getClassId(), tbClassNumber.getClassLeft()+"");
            }
        }
        else
            throw new ResourceNotFindException("初始化待选课程失败，没有课程余量记录，请联系管理员");
    }
    @Transactional
    @PostMapping("/{classId}")
    public BaseResult selectClass(String studentNumber,@PathVariable("classId") int classId) throws UserNotFindException {

        return null;
    }


    /**
     * @author sufu
     * @date 2020/5/30 0:20
     * @param studentNumber 学号
     * @return com.sufu.ems.dto.BaseResult
     * @description 根据学号查询待选课程
     **/
    @GetMapping("/select/classes")
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
