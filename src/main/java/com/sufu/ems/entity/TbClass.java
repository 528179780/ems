package com.sufu.ems.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
/**
 * @author sufu
 * @date 2020/5/15 16:48
 * @description 班级实体类
 **/

@Table(name = "tb_class")
@Data
public class TbClass {
    /**
     * id
     */
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 班级号，2位
     */
    @Column(name = "class_number")
    private String classNumber;

    /**
     * 班级学生人数
     */
    @Column(name = "student_number")
    private Integer studentNumber;

    /**
     * 班导师姓名
     */
    @Column(name = "header_name")
    private String headerName;
}