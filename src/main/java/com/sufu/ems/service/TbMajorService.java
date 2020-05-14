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
    /**
     * @author sufu
     * @date 2020/5/13 10:15
     * @param num 要查找的专业的编码
     * @return com.sufu.ems.entity.TbMajor
     * @description 根据专业编码查找专业
     **/
    public TbMajor selectByMajorNumber(String num){
        Example example = new Example(TbMajor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("majorNumber", num);
        return tbMajorMapper.selectOneByExample(example);
    }/**
     * @author sufu
     * @date 2020/5/13 10:15
     * @param tbMajor 要增加的专业
      * @return void
     * @description 增加一个专业
     **/
    public void insert(TbMajor tbMajor){
        tbMajorMapper.insert(tbMajor);
    }
}
