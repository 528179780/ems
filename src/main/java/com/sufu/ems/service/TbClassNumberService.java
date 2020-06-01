package com.sufu.ems.service;

import com.sufu.ems.dao.TbClassNumberMapper;
import com.sufu.ems.entity.TbClassNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/30 0:01
 * @description
 */
@Service
public class TbClassNumberService {
    @Autowired
    private TbClassNumberMapper tbClassNumberMapper;
    public List<TbClassNumber> selectAll(){
        return tbClassNumberMapper.selectAll();
    }
    public TbClassNumber getNumberOfClassesByClassId(int classId){
        Example example = new Example(TbClassNumber.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cId", classId);
        return tbClassNumberMapper.selectOneByExample(example);
    }
    public int decreaseNumber(int classId){
        return 0;
    }
}
