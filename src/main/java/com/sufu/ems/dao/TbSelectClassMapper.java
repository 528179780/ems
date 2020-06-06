package com.sufu.ems.dao;

import com.sufu.ems.entity.TbSelectClass;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.utils.MyMapper;

public interface TbSelectClassMapper extends MyMapper<TbSelectClass> {
    @Update("update tb_select_class set classes_left=classes_left-1 where id=#{id}")
    void decreaseClassLeft(@Param("id")int selectClassId);
    @Update("update tb_select_class set classes_left=classes_left+1 where id=#{id}")
    void increaseClassLeft(@Param("id")int selectClassId);
}