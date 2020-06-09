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

    /**
     * @param tbTeacher 要查询的教师
     * @return java.util.List<java.lang.String>
     * @author sufu
     * @date 2020/6/9 上午11:53
     * @description 根据教师查询所带班级
     **/
    public List<String> getClassNumbers(TbTeacher tbTeacher) {
        Example example = new Example(TbCourseClass.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teacherNumber", tbTeacher.getTeacherNumber());
        TbCourseClass tbCourseClass = tbCourseClassMapper.selectOneByExample(example);
        String classNumber = tbCourseClass.getClassNumber();
        return Arrays.asList(classNumber.split(","));
    }


    /**
     * @author sufu
     * @date 2020/6/9 上午11:57
     * @param tbTeacher 教师
     * @param classNumber 班级号
     * @return com.sufu.ems.entity.TbCourseClass
     * @description 根据教师和班级号查询该课程信息
     **/
    public TbCourseClass getClasses(TbTeacher tbTeacher, String classNumber) {
        Example example = new Example(TbCourseClass.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teacherNumber",tbTeacher.getTeacherNumber());
        criteria.andEqualTo("classNumber", classNumber);
        return tbCourseClassMapper.selectOneByExample(example);
    }

}
