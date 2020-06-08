package com.sufu.ems.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/11 20:56
 * @description 考试类的父类
 */
public class TbExam {
    private Integer id;
    private String courseName;
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    private Date examBegin;
    private Date examEnd;
    private String examPlace;
    private String majorName;

    public Date getExamBegin() {
        return examBegin;
    }

    public void setExamBegin(Date examBegin) {
        this.examBegin = examBegin;
    }

    public Date getExamEnd() {
        return examEnd;
    }

    public void setExamEnd(Date examEnd) {
        this.examEnd = examEnd;
    }

    public String getExamPlace() {
        return examPlace;
    }

    public void setExamPlace(String examPlace) {
        this.examPlace = examPlace;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "TbExam{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", examBegin=" + examBegin +
                ", examEnd=" + examEnd +
                ", examPlace='" + examPlace + '\'' +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}
