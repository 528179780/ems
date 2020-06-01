package com.sufu.ems.service;

import com.sufu.ems.dao.TbCourseClassMapper;
import com.sufu.ems.entity.TbCourseClass;
import com.sufu.ems.entity.TbTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/14 11:16
 * @description 课程与班级对应关系表的service
 */
@Service
public class TbCourseClassService {
    @Autowired
    private TbCourseClassMapper tbCourseClassMapper;
    public List<String> getClassNumbers(TbTeacher tbTeacher){
        Example example = new Example(TbCourseClass.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teacherNumber",tbTeacher.getTeacherNumber());
        TbCourseClass tbCourseClass = tbCourseClassMapper.selectOneByExample(example);
        String classNumber = tbCourseClass.getClassNumber();
        return Arrays.asList(classNumber.split(","));
    }
}
