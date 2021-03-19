package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class UserInfoBean {

    @Id(autoincrement = true)
    private Long id;
    @Index(unique = true)
    private int userId;
    private String userName;
    private String realName;
    private String organizationName;
    private String organizationCode;
    private String jobTitle;
    private String token;
    private String idCardNo;
    @Generated(hash = 150379356)
    public UserInfoBean(Long id, int userId, String userName, String realName,
            String organizationName, String organizationCode, String jobTitle,
            String token, String idCardNo) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.realName = realName;
        this.organizationName = organizationName;
        this.organizationCode = organizationCode;
        this.jobTitle = jobTitle;
        this.token = token;
        this.idCardNo = idCardNo;
    }
    @Generated(hash = 1818808915)
    public UserInfoBean() {
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
    public String getOrganizationName() {
        return this.organizationName;
    }
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    public String getOrganizationCode() {
        return this.organizationCode;
    }
    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
    public String getJobTitle() {
        return this.jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getIdCardNo() {
        return this.idCardNo;
    }
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

}
