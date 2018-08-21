package com.zwh.mvp.bean;

import com.zwh.mvp.library.base.entity.BaseEntity;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 16:15
 */

public class Data extends BaseEntity{


    /**
     * account : admin
     * createTime : 2018-08-14 14:43:32
     * dataFlag : 1
     * entryTime : 2018-08-14
     * id : 1
     * lastVisitTime : 2018-08-21 15:22:12
     * name : 管理员
     * password : 123456
     * role : 超级管理员
     * roleType : 1
     * salary : 0.0
     * token : a3c414c28d8f42039d06379bdf5ab187
     */

    private String account;
    private String createTime;
    private int dataFlag;
    private String entryTime;
    private int id;
    private String lastVisitTime;
    private String name;
    private String password;
    private String role;
    private int roleType;
    private double salary;
    private String token;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(int dataFlag) {
        this.dataFlag = dataFlag;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(String lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
