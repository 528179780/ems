package com.sufu.ems.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_exam_student")
@Data
public class TbExamStudent {
    /**
     * id
     */
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 考试编号
     */
    @Column(name = "exam_number")
    private String examNumber;

    /**
     * 学号
     */
    @Column(name = "student_number")
    private String studentNumber;

    /**
     * 姓名
     */
    @Column(name = "student_name")
    private String studentName;

    /**
     * 开始时间
     */
    @Column(name = "exam_begin")
    private Date examBegin;

    /**
     * 结束时间
     */
    @Column(name = "exam_end")
    private Date examEnd;

    /**
     * 考场位置
     */
    @Column(name = "exam_place")
    private String examPlace;

    /**
     * 考试专业编号
     */
    @Column(name = "major_number")
    private String majorNumber;

    /**
     * 考试专业名字
     */
    @Column(name = "major_name")
    private String majorName;
}