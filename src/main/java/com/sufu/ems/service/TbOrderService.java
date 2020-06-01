package com.sufu.ems.service;

import com.sufu.ems.dao.TbOrderMapper;
import com.sufu.ems.entity.TbOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/30 0:26
 * @description
 */
@Service
public class TbOrderService {
    @Autowired
    private TbOrderMapper tbOrderMapper;
    public void saveOrder(TbOrder order){
        tbOrderMapper.insert(order);
    }
}
