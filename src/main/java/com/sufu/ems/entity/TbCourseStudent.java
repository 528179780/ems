package com.sufu.ems.entity;

import javax.persistence.*;

@Table(name = "tb_course_student")
public class TbCourseStudent {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 这里的id是select_class类的id
     **/
    @Column(name = "class_id")
    private Integer classId;

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
     * 教师名字
     */
    @Column(name = "teacher_name")
    private String teacherName;

    /**
     * 学号
     */
    @Column(name = "student_number")
    private String studentNumber;

    /**
     * 学生姓名
     */
    @Column(name = "student_name")
    private String studentName;

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
     * @return class_id
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * @param classId
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
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
     * 获取学生姓名
     *
     * @return student_name - 学生姓名
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * 设置学生姓名
     *
     * @param studentName 学生姓名
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public TbCourseStudent(TbSelectClass selectClass,TbStudent student) {
        this.classId = selectClass.getId();
        this.courseName = selectClass.getCourseName();
        this.courseNumber = selectClass.getCourseNumber();
        this.studentName = student.getStudentName();
        this.studentNumber = student.getStudentNumber();
        this.teacherName = selectClass.getTeacherName();
        this.teacherNumber = selectClass.getTeacherNumber();
    }
}