package com.sufu.ems.entity;

import javax.persistence.*;
/**
 * @author sufu
 * @date 2020/5/29 22:37
 * @description 抢课订单类
 **/

@Table(name = "tb_order")
public class TbOrder {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 课程id
     */
    private Integer cid;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取课程id
     *
     * @return cid - 课程id
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置课程id
     *
     * @param cid 课程id
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }
}