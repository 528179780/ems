package com.sufu.ems.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_exam_class")
@Data
public class TbExamClass {
    /**
     * id
     */
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 考试班级编号
     */
    @Column(name = "class_number")
    private String classNumber;

    /**
     * 考试编号
     */
    @Column(name = "exam_number")
    private String examNumber;

    /**
     * 考试开始时间
     */
    @Column(name = "exam_begin")
    private Date examBegin;

    /**
     * 考试结束时间
     */
    @Column(name = "exam_end")
    private Date examEnd;

    /**
     * 考场位置
     */
    @Column(name = "exam_place")
    private String examPlace;

    /**
     * 考试专业编码
     */
    @Column(name = "major_number")
    private String majorNumber;

    /**
     * 考试专业名称
     */
    @Column(name = "major_name")
    private String majorName;

}