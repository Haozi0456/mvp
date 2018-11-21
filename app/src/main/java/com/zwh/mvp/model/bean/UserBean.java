package com.zwh.mvp.model.bean;

import com.zwh.mvp.library.base.model.bean.BaseBean;

/**
 * @author Zhaohao
 * @Description:
 * @Date 2018/08/20 16:15
 */

public class UserBean extends BaseBean {


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
    private String email;
    private int fromType;
    private int id;
    private String lastTime;
    private int money;
    private String name;
    private String openId;
    private int operatorId;
    private String phone;
    private int score;
    private int sex;
    private int status;
    private String token;
    private int totalScore;
    private int type;
    private int valid;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFromType() {
        return fromType;
    }

    public void setFromType(int fromType) {
        this.fromType = fromType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
