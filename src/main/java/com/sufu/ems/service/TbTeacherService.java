package com.sufu.ems.service;

import com.sufu.ems.dao.TbTeacherMapper;
import com.sufu.ems.entity.TbTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/14 11:31
 * @description
 */
@Service
public class TbTeacherService {
    @Autowired
    private TbTeacherMapper tbTeacherMapper;
    public TbTeacher selectTeacherByTeacherNumber(String number){
        Example example = new Example(TbTeacher.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teacherNumber", number);
        return tbTeacherMapper.selectOneByExample(example);
    }
}
