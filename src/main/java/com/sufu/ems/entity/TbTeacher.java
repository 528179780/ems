package com.sufu.ems.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/11 20:56
 * @description 教师实体类
 */
@Table(name = "tb_teacher")
@Data
public class TbTeacher {
    /**
     * id
     */
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 教师工号
     */
    @Column(name = "teacher_number")
    private String teacherNumber;

    /**
     * 教师姓名
     */
    @Column(name = "teacher_name")
    private String teacherName;

    /**
     * 教师性别
     */
    @Column(name = "teacher_sex")
    private String teacherSex;

    /**
     * 教师学历
     */
    @Column(name = "teacher_educational_background")
    private String teacherEducationalBackground;
}