package com.sufu.ems.service;

import com.sufu.ems.dao.TbMajorMapper;
import com.sufu.ems.entity.TbMajor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/11 16:42
 * @description
 */
@Service
public class TbMajorService {
    @Autowired
    private TbMajorMapper tbMajorMapper;
    /**
     * @author sufu
     * @date 2020/5/11 16:44
     * @param
     * @return java.util.List<com.sufu.ems.entity.TbMajor>
     * @description 查询所有专业
     **/
    public List<TbMajor> selectAll(){
        return tbMajorMapper.selectAll();
    }
    public TbMajor selectByMajorNumber(String num){
        Example example = new Example(TbMajor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("majorNumber", num);
        return tbMajorMapper.selectOneByExample(example);
    }
    public void insert(TbMajor tbMajor){
        tbMajorMapper.insert(tbMajor);
    }
}
