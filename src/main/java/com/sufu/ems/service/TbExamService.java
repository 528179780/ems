package com.sufu.ems.service;

import com.sufu.ems.dao.TbExamClassMapper;
import com.sufu.ems.dao.TbExamStudentMapper;
import com.sufu.ems.dao.TbStudentMapper;
import com.sufu.ems.entity.TbExam;
import com.sufu.ems.entity.TbExamClass;
import com.sufu.ems.entity.TbExamStudent;
import com.sufu.ems.entity.TbStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/11 23:06
 * @description
 */
@Service
public class TbExamService {
    @Autowired
    TbExamClassMapper examClassMapper;
    @Autowired
    TbExamStudentMapper examStudentMapper;
    public List<TbExam> selectExams(TbStudent student){
        List<TbExam> examList = new ArrayList<>();
        //根据专业查询考试
        Example classExample = new Example(TbExamClass.class);
        Example.Criteria classCriteria = classExample.createCriteria();
        classCriteria.andEqualTo("majorNumber",student.getStudentMajor());
        classCriteria.andEqualTo("classNumber", student.getStudentClass());
        List<TbExamClass> classExamList= examClassMapper.selectByExample(classExample);
        examList.addAll(classExamList);
        //根据学号查询考试
        Example studentExample = new Example(TbExamStudent.class);
        Example.Criteria studentCriteria = studentExample.createCriteria();
        studentCriteria.andEqualTo("studentNumber",student.getStudentNumber());
        List<TbExamStudent> studentExamList= examStudentMapper.selectByExample(studentExample);
        examList.addAll(studentExamList);
        return examList;
    }
}
