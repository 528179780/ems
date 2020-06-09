package com.sufu.ems.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/11 20:56
 * @description 考试类的实体类
 */
@Table(name = "tb_exam_class")
public class TbExamClass extends TbExam{
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
     * 考试班级编号
     */
    @Column(name = "class_number")
    private String classNumber;

    /**
     * 考试开始时间
     */
    @Column(name = "exam_begin")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date examBegin;

    /**
     * 考试结束时间
     */
    @Column(name = "exam_end")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
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
     * 获取考试班级编号
     *
     * @return class_number - 考试班级编号
     */
    public String getClassNumber() {
        return classNumber;
    }

    /**
     * 设置考试班级编号
     *
     * @param classNumber 考试班级编号
     */
    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    /**
     * 获取考试开始时间
     *
     * @return exam_begin - 考试开始时间
     */
    public Date getExamBegin() {
        return examBegin;
    }

    /**
     * 设置考试开始时间
     *
     * @param examBegin 考试开始时间
     */
    public void setExamBegin(Date examBegin) {
        this.examBegin = examBegin;
    }

    /**
     * 获取考试结束时间
     *
     * @return exam_end - 考试结束时间
     */
    public Date getExamEnd() {
        return examEnd;
    }

    /**
     * 设置考试结束时间
     *
     * @param examEnd 考试结束时间
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
     * 获取考试专业编码
     *
     * @return major_number - 考试专业编码
     */
    public String getMajorNumber() {
        return majorNumber;
    }

    /**
     * 设置考试专业编码
     *
     * @param majorNumber 考试专业编码
     */
    public void setMajorNumber(String majorNumber) {
        this.majorNumber = majorNumber;
    }

    /**
     * 获取考试专业名称
     *
     * @return major_name - 考试专业名称
     */
    public String getMajorName() {
        return majorName;
    }

    /**
     * 设置考试专业名称
     *
     * @param majorName 考试专业名称
     */
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "TbExamClass{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", classNumber='" + classNumber + '\'' +
                ", examBegin=" + examBegin +
                ", examEnd=" + examEnd +
                ", examPlace='" + examPlace + '\'' +
                ", majorNumber='" + majorNumber + '\'' +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}