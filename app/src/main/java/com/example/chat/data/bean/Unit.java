package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class Unit {
    @Id(autoincrement = true)
    private Long id;

    /**
     * createTime : 2019-08-14 11:22:55
     * description :
     * grade : null
     * id : 59
     * isFinal : null
     * latitude : null
     * longitude : null
     * organizationCode : 123
     * organizationName : 武汉测试
     * pid : 55
     * status : null
     * type : 1
     * updateTime : 2019-08-14 11:22:55
     */

    private String createTime;
    private String description;
    private String grade;
    @Index(unique = true)
    private int organizationId;
    private String isFinal;
    private String latitude;
    private String longitude;
    private String organizationCode;
    private String organizationName;
    private int pid;
    private String status;
    private int type;
    private String updateTime;
    @Generated(hash = 784682547)
    public Unit(Long id, String createTime, String description, String grade,
            int organizationId, String isFinal, String latitude, String longitude,
            String organizationCode, String organizationName, int pid,
            String status, int type, String updateTime) {
        this.id = id;
        this.createTime = createTime;
        this.description = description;
        this.grade = grade;
        this.organizationId = organizationId;
        this.isFinal = isFinal;
        this.latitude = latitude;
        this.longitude = longitude;
        this.organizationCode = organizationCode;
        this.organizationName = organizationName;
        this.pid = pid;
        this.status = status;
        this.type = type;
        this.updateTime = updateTime;
    }
    @Generated(hash = 1236212320)
    public Unit() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getGrade() {
        return this.grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public int getOrganizationId() {
        return this.organizationId;
    }
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
    public String getIsFinal() {
        return this.isFinal;
    }
    public void setIsFinal(String isFinal) {
        this.isFinal = isFinal;
    }
    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return this.longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getOrganizationCode() {
        return this.organizationCode;
    }
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
    public String getOrganizationName() {
        return this.organizationName;
    }
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    public int getPid() {
        return this.pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


}
