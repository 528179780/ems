package com.sufu.ems.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/11 20:56
 * @description 选课实体类 也是选课记录实体类
 */
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
    @Column(name = "total_number")
    private Integer totalNumber;

    /**
     * 已选人数
     */
    @Column(name = "selected_number")
    private Integer selectedNumber;

    /**
     * 可选专业编码，用逗号隔开的字符串
     */
    @Column(name = "major_number")
    private String majorNumber;

    public List<String> getMajors() {
        return majors;
    }

    public void setMajors(List<String> majors) {
        this.majors = majors;
    }

    /*
     * 可选专业编码列表
     */
    private List<String> majors;

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
     * @return total_number - 课程总容量
     */
    public Integer getTotalNumber() {
        return totalNumber;
    }

    /**
     * 设置课程总容量
     *
     * @param totalNumber 课程总容量
     */
    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    /**
     * 获取已选人数
     *
     * @return selected_number - 已选人数
     */
    public Integer getSelectedNumber() {
        return selectedNumber;
    }

    /**
     * 设置已选人数
     *
     * @param selectedNumber 已选人数
     */
    public void setSelectedNumber(Integer selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    /**
     * 获取可选专业编码列表，用逗号隔开的字符串
     *
     * @return major_number - 可选专业编码列表，用逗号隔开的字符串
     */
    public String getMajorNumber() {
        return majorNumber;
    }

    /**
     * 设置可选专业编码列表，用逗号隔开的字符串
     *
     * @param majorNumber 可选专业编码列表，用逗号隔开的字符串
     */
    public void setMajorNumber(String majorNumber) {
        this.majorNumber = majorNumber;
    }
}