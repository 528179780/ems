package com.sufu.ems.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "tb_classroom")
@Data
public class TbClassroom {
    /**
     * id
     */
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 教室编号
     */
    @Column(name = "classroom_number")
    private String classroomNumber;

    /**
     * 教室位置
     */
    @Column(name = "classroom_place")
    private String classroomPlace;

    /**
     * 容纳人数
     */
    @Column(name = "student_count")
    private Integer studentCount;
}