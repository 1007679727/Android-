package com.example.chat.msg;

/**
 * Created by fhzz on 2018/9/14.
 */

public class GroupDetailData {
    private String GROUP_NAME;
    private String role_name;
    private String CUST_ID;
    private String CREATE_USER;
    private String role_id;
    private String user_name;
    private String real_name;
    private String ID;
    private String CREATE_TIME;
    private String user_create_time;
    private String archivesId;

    public String getGROUP_NAME() {
        return GROUP_NAME;
    }

    public void setGROUP_NAME(String GROUP_NAME) {
        this.GROUP_NAME = GROUP_NAME;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getCUST_ID() {
        return CUST_ID;
    }

    public void setCUST_ID(String CUST_ID) {
        this.CUST_ID = CUST_ID;
    }

    public String getCREATE_USER() {
        return CREATE_USER;
    }

    public void setCREATE_USER(String CREATE_USER) {
        this.CREATE_USER = CREATE_USER;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getUser_create_time() {
        return user_create_time;
    }

    public void setUser_create_time(String user_create_time) {
        this.user_create_time = user_create_time;
    }

    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId;
    }

    @Override
    public String toString() {
        return "GroupDetailData{" +
                "GROUP_NAME='" + GROUP_NAME + '\'' +
                ", role_name='" + role_name + '\'' +
                ", CUST_ID='" + CUST_ID + '\'' +
                ", CREATE_USER='" + CREATE_USER + '\'' +
                ", role_id='" + role_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", real_name='" + real_name + '\'' +
                ", ID='" + ID + '\'' +
                ", CREATE_TIME='" + CREATE_TIME + '\'' +
                ", user_create_time='" + user_create_time + '\'' +
                ", archivesId='" + archivesId + '\'' +
                '}';
    }
}
