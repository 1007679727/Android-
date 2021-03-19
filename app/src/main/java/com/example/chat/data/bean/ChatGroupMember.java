package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ChatGroupMember {
    @Id(autoincrement = true)
    private Long _id;
    private String aliaCustId;
    private String custAliasName;
    private String groupId;
    private String id;
    @Generated(hash = 336553362)
    public ChatGroupMember(Long _id, String aliaCustId, String custAliasName,
            String groupId, String id) {
        this._id = _id;
        this.aliaCustId = aliaCustId;
        this.custAliasName = custAliasName;
        this.groupId = groupId;
        this.id = id;
    }
    @Generated(hash = 2056338917)
    public ChatGroupMember() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getAliaCustId() {
        return this.aliaCustId;
    }
    public void setAliaCustId(String aliaCustId) {
        this.aliaCustId = aliaCustId;
    }
    public String getCustAliasName() {
        return this.custAliasName;
    }
    public void setCustAliasName(String custAliasName) {
        this.custAliasName = custAliasName;
    }
    public String getGroupId() {
        return this.groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
