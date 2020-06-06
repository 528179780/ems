package com.sufu.ems.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Table(name = "tb_select_class")
public class TbSelectClass {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 课程id
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
     * 教师名字
     */
    @Column(name = "teacher_name")
    private String teacherName;

    /**
     * 课程总容量
     */
    @Column(name = "total_clesses")
    private Integer totalClesses;

    /**
     * 已选人数
     */
    @Column(name = "classes_left")
    private Integer classesLeft;

    /**
     * 可选专业编码列表，用逗号隔开的字符串 为空则所有专业都可选
     */
    @Column(name = "major_number")
    private String majorNumber;

    private List<String> majors;

    public List<String> setMajors() {
        this.majors = Arrays.asList(majorNumber.split(","));
        return majors;
    }

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
     * 获取课程id
     *
     * @return course_number - 课程id
     */
    public String getCourseNumber() {
        return courseNumber;
    }

    /**
     * 设置课程id
     *
     * @param courseNumber 课程id
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
     * 获取教师名字
     *
     * @return teacher_name - 教师名字
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 设置教师名字
     *
     * @param teacherName 教师名字
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * 获取课程总容量
     *
     * @return total_clesses - 课程总容量
     */
    public Integer getTotalClesses() {
        return totalClesses;
    }

    /**
     * 设置课程总容量
     *
     * @param totalClesses 课程总容量
     */
    public void setTotalClesses(Integer totalClesses) {
        this.totalClesses = totalClesses;
    }

    /**
     * 获取已选人数
     *
     * @return classes_left - 课程余量
     */
    public Integer getClassesLeft() {
        return classesLeft;
    }

    /**
     * 设置已选人数
     *
     * @param classesLeft 课程余量
     */
    public void setClassesLeft(Integer classesLeft) {
        this.classesLeft = classesLeft;
    }

    /**
     * 获取可选专业编码列表，用逗号隔开的字符串 为空则所有专业都可选
     *
     * @return major_number - 可选专业编码列表，用逗号隔开的字符串 为空则所有专业都可选
     */
    public String getMajorNumber() {
        return majorNumber;
    }

    /**
     * 设置可选专业编码列表，用逗号隔开的字符串 为空则所有专业都可选
     *
     * @param majorNumber 可选专业编码列表，用逗号隔开的字符串 为空则所有专业都可选
     */
    public void setMajorNumber(String majorNumber) {
        this.majorNumber = majorNumber;
    }
}