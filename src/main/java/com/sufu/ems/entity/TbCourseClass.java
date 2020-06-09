package com.sufu.ems.entity;

import javax.persistence.*;

/**
 * @author sufu
 * @date 2020/5/15 16:48
 * @description 按班级查询的课程实体类
 **/
@Table(name = "tb_course_class")
public class TbCourseClass {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 课程编号
     */
    @Column(name = "course_number")
    private String courseNumber;

    /**
     * 课程名字
     */
    @Column(name = "course_name")
    private String courseName;

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
     * 班级编号，以‘，’隔开的字符串
     */
    @Column(name = "class_number")
    private String classNumber;

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
     * 获取课程编号
     *
     * @return course_number - 课程编号
     */
    public String getCourseNumber() {
        return courseNumber;
    }

    /**
     * 设置课程编号
     *
     * @param courseNumber 课程编号
     */
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    /**
     * 获取课程名字
     *
     * @return course_name - 课程名字
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 设置课程名字
     *
     * @param courseName 课程名字
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 获取教师工号
     *
     * @return teacher_number - 教师工号
     */
    public String getTeacherNumber() {
        return teacherNumber;
    }

    /**
     * 设置教师工号
     *
     * @param teacherNumber 教师工号
     */
    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    /**
     * 获取教师姓名
     *
     * @return teacher_name - 教师姓名
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 设置教师姓名
     *
     * @param teacherName 教师姓名
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * 获取班级编号
     *
     * @return class_number - 班级编号，以‘，’隔开的字符串
     */
    public String getClassNumber() {
        return classNumber;
    }

    /**
     * 设置班级编号
     *
     * @param classNumber 班级编号，以‘，’隔开的字符串
     */
    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}