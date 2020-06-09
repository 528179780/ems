package com.sufu.ems.service;

import com.sufu.ems.dao.TbStudentMapper;
import com.sufu.ems.entity.TbStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/8 23:33
 * @description student 管理类
 */
@Service
public class TbStudentService {
    @Autowired
    private TbStudentMapper tbStudentMapper;

    /**
     * @author sufu
     * @date 2020/5/9 18:14
     * @param
     * @return java.util.List<com.sufu.ems.entity.TbStudent>
     * @description 查询全部数据
     **/
    public List<TbStudent> selectAll(){
        return tbStudentMapper.selectAll();
    }

    /**
     * @author sufu
     * @date 2020/5/9 18:29
     * @param id 要查询的学生id
     * @return com.sufu.ems.entity.TbStudent
     * @description 根据id查询学生信息
     **/
    public TbStudent selectById(int id){
        return tbStudentMapper.selectByPrimaryKey(id);
    }

    /**
     * @author sufu
     * @date 2020/5/9 18:30
     * @param student 要插入的学生 id可以为空
     * @return void
     * @description 插入一条学生数据
     **/
    public void insert(TbStudent student){
        tbStudentMapper.insert(student);
    }
    /**
     * @author sufu
     * @date 2020/5/10 16:41
     * @param num 要查询的学生学号
     * @return com.sufu.ems.entity.TbStudent
     * @description 根据学号查询
     **/
    public TbStudent selectByStudentNumber(String num){
        Example example = new Example(TbStudent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("studentNumber", num);
        return tbStudentMapper.selectOneByExample(example);
    }
    /**
     * @author sufu
     * @date 2020/6/9 上午11:41
     * @param classNumber 班级号
     * @return java.util.List<com.sufu.ems.entity.TbStudent>
     * @description 根据班级查询学生列表
     **/
    public List<TbStudent> selectByClass(String classNumber){
        Example example = new Example(TbStudent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("studentClass", classNumber);
        return tbStudentMapper.selectByExample(example);
    }
}
