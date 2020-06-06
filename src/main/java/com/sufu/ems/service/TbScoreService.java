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
    /**
     * @author sufu
     * @date 2020/6/3 下午1:17
     * @param student 要查询的学生
     * @return java.util.List<com.sufu.ems.entity.TbScore>
     * @description 根据学生查询成成绩信息
     **/
    public List<TbScore> selectByStudentNumber(TbStudent student){
        Example example = new Example(TbScore.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("studentNumber", student.getStudentNumber());
        return tbScoreMapper.selectByExample(example);
    }
}
