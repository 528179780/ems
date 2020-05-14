package com.sufu.ems.service;

import com.sufu.ems.dao.TbScoreMapper;
import com.sufu.ems.entity.TbScore;
import com.sufu.ems.entity.TbStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/13 15:31
 * @description
 */
@Service
public class TbScoreService {
    @Autowired
    private TbScoreMapper tbScoreMapper;
    public List<TbScore> selectByStudentNumber(TbStudent student){
        Example example = new Example(TbScore.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("studentNumber", student.getStudentNumber());
        return tbScoreMapper.selectByExample(example);
    }
}
