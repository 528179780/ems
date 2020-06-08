package com.sufu.ems.service;

import com.sufu.ems.dao.TbCourseStudentMapper;
import com.sufu.ems.dao.TbSelectClassMapper;
import com.sufu.ems.dto.BaseResult;
import com.sufu.ems.entity.TbCourseStudent;
import com.sufu.ems.entity.TbSelectClass;
import com.sufu.ems.entity.TbStudent;
import com.sufu.ems.exception.*;
import com.sufu.ems.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    static Logger logger = LoggerFactory.getLogger(TbSelectClass.class);

    public List<TbSelectClass> selectAll(){
        return tbSelectClassMapper.selectAll();
    }

    /**
     * @author sufu
     * @date 2020/5/15 17:30
     * @param student 要查询的学生
     * @return java.util.List<com.sufu.ems.entity.TbSelectClass>
     * @description 根据学生查询待选课程
     **/
    public List<TbSelectClass> getClasses(TbStudent student) throws ResourceNotFindException {
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
                    tbSelectClass.setMajors();//更新课程专业限定信息
                }
            }
        }
        else{
            throw new ResourceNotFindException("没有课程要选");
        }
        return tbSelectClasses;
    }

    /**
     * @author sufu
     * @date 2020/6/3 下午1:20
     * @param student 学生
     * @param classId 课程id
     * @return com.sufu.ems.dto.BaseResult
     * @description 根据学号选课
     **/
    @Transactional
    public BaseResult selectClass(TbStudent student,int classId) throws ResourceNotFindException, UserNotFindException, RepeatedOperationException, MajorNotMatchException {
        if(student == null){
            throw new UserNotFindException("要查询的学生为空");
        }else {
            String parameter =stringRedisTemplate.opsForValue().get(Constants.ORDERKEY+classId);
            int count ;
            if(parameter!=null){
                //获得余量字符串
                count = Integer.parseInt(parameter);
            }else {
                throw new ResourceNotFindException("没有课程余量记录，请联系管理员");
            }
            if(count <= 0){
                throw new ResourceNotFindException("课已经选完！");
            }else {
                //选课
                TbSelectClass selectClass = tbSelectClassMapper.selectByPrimaryKey(classId);
                if(selectClass==null){
                    throw new ResourceNotFindException("没有该选课");
                }
                if(selectClass.getClassesLeft()<=0){
                    throw new ResourceNotFindException("课程已经选完！");
                }
                List<String> majors = selectClass.setMajors();//更新
                if(null!=majors&& majors.size()>0){
                    logger.info("该课程有专业限定："+majors.toString());
                    if(!majors.contains(student.getStudentMajor())){
                        throw new MajorNotMatchException("你的专业无法选择此课程");
                    }
                }
                Example selectedClassesExample = new Example(TbCourseStudent.class);
                Example.Criteria selectedClassesCriteria = selectedClassesExample.createCriteria();
                selectedClassesCriteria.andEqualTo("courseNumber",selectClass.getCourseNumber());
                List<TbCourseStudent> tbCourseStudents = tbCourseStudentMapper.selectByExample(selectedClassesExample);
                if(tbCourseStudents!=null&&tbCourseStudents.size()>0){
                    throw new RepeatedOperationException("已经选了该课，如要重选，请先退选");
                }
                TbCourseStudent tbCourseStudent = new TbCourseStudent(selectClass,student);//根据学生和选课信息创建一条选课记录
                tbCourseStudentMapper.insert(tbCourseStudent);
                stringRedisTemplate.opsForValue().decrement(Constants.ORDERKEY+classId);//减redis库存
                tbSelectClassMapper.decreaseClassLeft(classId);//减少mysql数据库库存
                return BaseResult.success("选课成功");
            }
        }
    }

    /**
     * @author sufu
     * @date 2020/6/3 下午5:17
     * @param student 要退选的学生
     * @param classId 退选的课程id
     * @return com.sufu.ems.dto.BaseResult
     * @description 退选课程
     **/
    @Transactional
    public BaseResult unselectClass(TbStudent student,int classId) throws UserNotFindException, ResourceNotFindException {
        if(student == null){
            throw new UserNotFindException("要查询的学生为空");
        }else {
            Example example = new Example(TbCourseStudent.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("classId", classId);
            TbCourseStudent tbCourseStudent = tbCourseStudentMapper.selectOneByExample(example);
            if(tbCourseStudent == null){
                throw new ResourceNotFindException("没有要退选的课程");
            }
            tbCourseStudentMapper.delete(tbCourseStudent);//删除该选课记录
            stringRedisTemplate.opsForValue().increment(Constants.ORDERKEY+classId);

            tbSelectClassMapper.increaseClassLeft(classId);
        }
        return null;
    }
}
