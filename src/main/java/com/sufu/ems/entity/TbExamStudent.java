package com.sufu.ems.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/11 20:56
 * @description 考试类的实体类
 */
@Table(name = "tb_exam_student")
public class TbExamStudent extends TbExam{
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 考试科目
     */
    @Column(name = "course_name")
    private String courseName;

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
     * 获取考试科目
     *
     * @return course_name - 考试科目
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 设置考试科目
     *
     * @param courseName 考试科目
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 获取学号
     *
     * @return student_number - 学号
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * 设置学号
     *
     * @param studentNumber 学号
     */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * 获取姓名
     *
     * @return student_name - 姓名
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * 设置姓名
     *
     * @param studentName 姓名
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * 获取开始时间
     *
     * @return exam_begin - 开始时间
     */
    public Date getExamBegin() {
        return examBegin;
    }

    /**
     * 设置开始时间
     *
     * @param examBegin 开始时间
     */
    public void setExamBegin(Date examBegin) {
        this.examBegin = examBegin;
    }

    /**
     * 获取结束时间
     *
     * @return exam_end - 结束时间
     */
    public Date getExamEnd() {
        return examEnd;
    }

    /**
     * 设置结束时间
     *
     * @param examEnd 结束时间
     */
    public void setExamEnd(Date examEnd) {
        this.examEnd = examEnd;
    }

    /**
     * 获取考场位置
     *
     * @return exam_place - 考场位置
     */
    public String getExamPlace() {
        return examPlace;
    }

    /**
     * 设置考场位置
     *
     * @param examPlace 考场位置
     */
    public void setExamPlace(String examPlace) {
        this.examPlace = examPlace;
    }

    /**
     * 获取考试专业编号
     *
     * @return major_number - 考试专业编号
     */
    public String getMajorNumber() {
        return majorNumber;
    }

    /**
     * 设置考试专业编号
     *
     * @param majorNumber 考试专业编号
     */
    public void setMajorNumber(String majorNumber) {
        this.majorNumber = majorNumber;
    }

    /**
     * 获取考试专业名字
     *
     * @return major_name - 考试专业名字
     */
    public String getMajorName() {
        return majorName;
    }

    /**
     * 设置考试专业名字
     *
     * @param majorName 考试专业名字
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "TbExamStudent{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", studentName='" + studentName + '\'' +
                ", examBegin=" + examBegin +
                ", examEnd=" + examEnd +
                ", examPlace='" + examPlace + '\'' +
                ", majorNumber='" + majorNumber + '\'' +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}