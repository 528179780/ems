package com.sufu.ems.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/11 20:56
 * @description 成绩实体类
 */
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

    /**
     * 学年如：2019-2020
     */
    @Column(name = "study_year")
    private String studyYear;

    /**
     * 学期如：1
     */
    @Column(name = "exam_term")
    private Integer examTerm;

    /**
     * 分数 百分制
     */
    @Column(name = "exam_mark")
    private Integer examMark;

    public TbScore(int id,String courseName, String studentNumber, String studentName, String studyYear, Integer examTerm, Integer examMark) {
        this.id = id;
        this.courseName = courseName;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studyYear = studyYear;
        this.examTerm = examTerm;
        this.examMark = examMark;
    }

    public TbScore(TbStudent student, TbCourseClass tbCourseClass) {
        this.studentNumber = student.getStudentNumber();
        this.studentName = student.getStudentName();
        this.courseName = tbCourseClass.getCourseName();
    }
}