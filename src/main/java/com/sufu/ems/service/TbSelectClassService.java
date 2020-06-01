package com.sufu.ems.service;

import com.sufu.ems.dao.TbCourseStudentMapper;
import com.sufu.ems.dao.TbSelectClassMapper;
import com.sufu.ems.dto.BaseResult;
import com.sufu.ems.entity.TbClassNumber;
import com.sufu.ems.entity.TbCourseStudent;
import com.sufu.ems.entity.TbSelectClass;
import com.sufu.ems.entity.TbStudent;
import com.sufu.ems.exception.NoClassNeedSelectException;
import com.sufu.ems.exception.ResourceNotFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/15 16:38
 * @description
 */
@Service
public class TbSelectClassService {
    @Autowired
    private TbSelectClassMapper tbSelectClassMapper;
    @Autowired
    private TbCourseStudentMapper tbCourseStudentMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    TbClassNumberService tbClassNumberService;
    /**
     * @author sufu
     * @date 2020/5/15 17:30
     * @param student 要查询的学生
     * @return java.util.List<com.sufu.ems.entity.TbSelectClass>
     * @description 根据学生查询选课
     **/
    public List<TbSelectClass> getClasses(TbStudent student) throws NoClassNeedSelectException {
        //查询已选课程
        Example example = new Example(TbCourseStudent.class);
        Example.Criteria criteria1 = example.createCriteria();
        criteria1.andEqualTo("studentNumber", student.getStudentNumber());
        List<TbCourseStudent> selectedCoursesList = tbCourseStudentMapper.selectByExample(example);
        List<TbSelectClass> tbSelectClasses;
        //已选课程
        if(selectedCoursesList.size() != 0){
            Example example1 = new Example(TbSelectClass.class);
            Example.Criteria criteria = example1.createCriteria();
            for (TbCourseStudent tbCourseStudent : selectedCoursesList) {
                criteria.andNotEqualTo("courseNumber",tbCourseStudent.getCourseNumber());
            }
            tbSelectClasses = tbSelectClassMapper.selectByExample(example1);
        }
        else {
            tbSelectClasses = tbSelectClassMapper.selectAll();
        }
        if(tbSelectClasses.size()!=0){
            for (TbSelectClass tbSelectClass : tbSelectClasses) {
                if(tbSelectClass.getMajorNumber()!=null){
                    //课程有专业限定
                    tbSelectClass.setMajors(Arrays.asList(tbSelectClass.getMajorNumber().split(",")));
                }
            }
        }
        else{
            throw new NoClassNeedSelectException();
        }
        return tbSelectClasses;
    }

    public BaseResult selectClass(TbStudent student,int classId) throws ResourceNotFindException {
        TbClassNumber numberOfClassesByClassId = tbClassNumberService.getNumberOfClassesByClassId(classId);
        if(numberOfClassesByClassId.getClassLeft()<0){
            throw new ResourceNotFindException("课程已经选完！");
        }
        Example example = new Example(TbSelectClass.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("classId", classId);
        TbSelectClass selectClass = tbSelectClassMapper.selectOneByExample(example);
        TbCourseStudent tbCourseStudent = new TbCourseStudent();
        tbCourseStudent.setCourseName(selectClass.getCourseName());
        tbCourseStudent.setCourseNumber(selectClass.getCourseNumber());
        tbCourseStudent.setTeacherName(selectClass.getTeacherName());
        tbCourseStudent.setTeacherNumber(selectClass.getTeacherNumber());
        tbCourseStudent.setStudentName(student.getStudentName());
        tbCourseStudent.setStudentNumber(student.getStudentNumber());
        tbCourseStudentMapper.insert(tbCourseStudent);
        return null;
    }
}
