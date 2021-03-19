package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class PatrolMemberBean {

    @Id(autoincrement = true)
    private Long id;
    @Index(unique = true)
    private int userId;
    private int hhId;
    private String groupName;
    private String realName;
    private int reportId;
    private String reportTime;
    private String telNum;
    private int type;
    private String userName;
    @Generated(hash = 1907825834)
    public PatrolMemberBean(Long id, int userId, int hhId, String groupName,
            String realName, int reportId, String reportTime, String telNum,
            int type, String userName) {
        this.id = id;
        this.userId = userId;
        this.hhId = hhId;
        this.groupName = groupName;
        this.realName = realName;
        this.reportId = reportId;
        this.reportTime = reportTime;
        this.telNum = telNum;
        this.type = type;
        this.userName = userName;
    }
    @Generated(hash = 759341111)
    public PatrolMemberBean() {
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
    public int getHhId() {
        return this.hhId;
    }
    public void setHhId(int hhId) {
        this.hhId = hhId;
    }
    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getRealName() {
        return this.realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public int getReportId() {
        return this.reportId;
    }
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
    public String getReportTime() {
        return this.reportTime;
    }
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
    public String getTelNum() {
        return this.telNum;
    }
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
