package com.sufu.ems.entity;

import javax.persistence.*;

/**
 * @author sufu
 * @date 2020/5/29 22:36
 * @description 课程余量类
 **/
@Table(name = "tb_class_number")
public class TbClassNumber {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 课程id
     */
    @Column(name = "class_id")
    private Integer classId;

    /**
     * 课程余量
     */
    @Column(name = "class_left")
    private Integer classLeft;

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
     * 获取课程id
     *
     * @return class_id - 课程id
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * 设置课程id
     *
     * @param classId 课程id
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * 获取课程余量
     *
     * @return class_left - 课程余量
     */
    public Integer getClassLeft() {
        return classLeft;
    }

    /**
     * 设置课程余量
     *
     * @param classLeft 课程余量
     */
    public void setClassLeft(Integer classLeft) {
        this.classLeft = classLeft;
    }
}