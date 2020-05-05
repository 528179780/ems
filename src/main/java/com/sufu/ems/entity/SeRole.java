package com.sufu.ems.entity;

import javax.persistence.*;

@Table(name = "se_role")
public class SeRole {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色中文名
     */
    @Column(name = "nameZh")
    private String namezh;

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
     * 获取角色名
     *
     * @return name - 角色名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名
     *
     * @param name 角色名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色中文名
     *
     * @return nameZh - 角色中文名
     */
    public String getNamezh() {
        return namezh;
    }

    /**
     * 设置角色中文名
     *
     * @param namezh 角色中文名
     */
    public void setNamezh(String namezh) {
        this.namezh = namezh;
    }

    @Override
    public String toString() {
        return "SeRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", namezh='" + namezh + '\'' +
                '}';
    }
}