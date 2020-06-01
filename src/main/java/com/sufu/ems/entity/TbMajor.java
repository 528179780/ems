package com.sufu.ems.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/5/11 20:56
 * @description 专业实体类
 */
@Table(name = "tb_major")
@Data
public class TbMajor {
    /**
     * id
     */
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 专业代码，4位数
     */
    @Column(name = "major_number")
    private String majorNumber;

    /**
     * 专业名称
     */
    @Column(name = "major_name")
    private String majorName;

    /**
     * 专业负责人姓名
     */
    @Column(name = "header_name")
    private String headerName;

    /**
     * 专业负责人id
     */
    @Column(name = "header_id")
    private String headerId;

    /**
     * 开课学院id
     */
    @Column(name = "college_name")
    private String collegeName;
}