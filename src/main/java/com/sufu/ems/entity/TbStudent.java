package com.sufu.ems.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/11 20:56
 * @description 学生实体类
 */
@Table(name = "tb_student")
@Data
public class TbStudent {
    /**
     * id
     */
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学生学号
     */
    @Column(name = "student_number")
    private String studentNumber;

    /**
     * 学生姓名
     */
    @Column(name = "student_name")
    private String studentName;

    /**
     * 学生性别
     */
    @Column(name = "student_sex")
    private String studentSex;

    /**
     * 学生生日
     */
    @Column(name = "student_birthday")
    private Date studentBirthday;

    /**
     * 学生年级
     */
    @Column(name = "student_grade")
    private String studentGrade;

    /**
     * 学生班级
     */
    @Column(name = "student_class")
    private String studentClass;

    /**
     * 学生层次(本、专科)
     */
    @Column(name = "student_level")
    private String studentLevel;

    /**
     * 学生专业编号
     */
    @Column(name = "student_major")
    private String studentMajor;

}