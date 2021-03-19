package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class ContactBean {
    @Id(autoincrement = true)
    private Long id;
    @Index(unique = true)
    private int userId;
    private String userName;
    private String realName;
    private String isOnline;
    private String jobTitle;
    private String groupName;
    private int hhId;
    private int stealth;
    private String orgName;
    private String orgCode;
    private String shortOrgName;
    private String parentOrgName;
    private String shortParentOrgName;
    @Generated(hash = 819975450)
    public ContactBean(Long id, int userId, String userName, String realName,
            String isOnline, String jobTitle, String groupName, int hhId,
            int stealth, String orgName, String orgCode, String shortOrgName,
            String parentOrgName, String shortParentOrgName) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.realName = realName;
        this.isOnline = isOnline;
        this.jobTitle = jobTitle;
        this.groupName = groupName;
        this.hhId = hhId;
        this.stealth = stealth;
        this.orgName = orgName;
        this.orgCode = orgCode;
        this.shortOrgName = shortOrgName;
        this.parentOrgName = parentOrgName;
        this.shortParentOrgName = shortParentOrgName;
    }
    @Generated(hash = 1283900925)
    public ContactBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getRealName() {
        return this.realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getIsOnline() {
        return this.isOnline;
    }
    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }
    public String getJobTitle() {
        return this.jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public int getHhId() {
        return this.hhId;
    }
    public void setHhId(int hhId) {
        this.hhId = hhId;
    }
    public int getStealth() {
        return this.stealth;
    }
    public void setStealth(int stealth) {
        this.stealth = stealth;
    }
    public String getOrgName() {
        return this.orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getOrgCode() {
        return this.orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    public String getShortOrgName() {
        return this.shortOrgName;
    }
    public void setShortOrgName(String shortOrgName) {
        this.shortOrgName = shortOrgName;
    }
    public String getParentOrgName() {
        return this.parentOrgName;
    }
    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }
    public String getShortParentOrgName() {
        return this.shortParentOrgName;
    }
    public void setShortParentOrgName(String shortParentOrgName) {
        this.shortParentOrgName = shortParentOrgName;
    }
}
