package com.sufu.ems.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "tb_score")
@Data
public class TbScore {
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
     * 课程名字
     */
    @Column(name = "course_name")
    private String courseName;

    /**
     * 学号
     */
    @Column(name = "student_number")
    private String studentNumber;

    /**
     * 学生名字
     */
    @Column(name = "student_name")
    private String studentName;

}