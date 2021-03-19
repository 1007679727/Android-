package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class LatestMsgBean {

    @Id(autoincrement = true)
    private Long id;

    private String groupId;
    private int count;
    private String createTime;
    private String createUser;
    private String groupName;
    private String isBrodacast;
    private String isDel;
    private String lastChatCreateTime;
    private String showFlag;
    private String lastChatContent;
    private String lastChatCustId;
    private String lastChatDes;
    private String lastChatId;
    private String lastChatMsgType;
    private String groupUserIds;
    private String groupUserAliasNames;
    private String groupUserAliasIds;
    private String showInChatGroup;
    @Generated(hash = 1630875776)
    public LatestMsgBean(Long id, String groupId, int count, String createTime,
            String createUser, String groupName, String isBrodacast, String isDel,
            String lastChatCreateTime, String showFlag, String lastChatContent,
            String lastChatCustId, String lastChatDes, String lastChatId,
            String lastChatMsgType, String groupUserIds, String groupUserAliasNames,
            String groupUserAliasIds, String showInChatGroup) {
        this.id = id;
        this.groupId = groupId;
        this.count = count;
        this.createTime = createTime;
        this.createUser = createUser;
        this.groupName = groupName;
        this.isBrodacast = isBrodacast;
        this.isDel = isDel;
        this.lastChatCreateTime = lastChatCreateTime;
        this.showFlag = showFlag;
        this.lastChatContent = lastChatContent;
        this.lastChatCustId = lastChatCustId;
        this.lastChatDes = lastChatDes;
        this.lastChatId = lastChatId;
        this.lastChatMsgType = lastChatMsgType;
        this.groupUserIds = groupUserIds;
        this.groupUserAliasNames = groupUserAliasNames;
        this.groupUserAliasIds = groupUserAliasIds;
        this.showInChatGroup = showInChatGroup;
    }
    @Generated(hash = 1495437006)
    public LatestMsgBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGroupId() {
        return this.groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getCreateUser() {
        return this.createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getIsBrodacast() {
        return this.isBrodacast;
    }
    public void setIsBrodacast(String isBrodacast) {
        this.isBrodacast = isBrodacast;
    }
    public String getIsDel() {
        return this.isDel;
    }
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
    public String getLastChatCreateTime() {
        return this.lastChatCreateTime;
    }
    public void setLastChatCreateTime(String lastChatCreateTime) {
        this.lastChatCreateTime = lastChatCreateTime;
    }
    public String getShowFlag() {
        return this.showFlag;
    }
    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }
    public String getLastChatContent() {
        return this.lastChatContent;
    }
    public void setLastChatContent(String lastChatContent) {
        this.lastChatContent = lastChatContent;
    }
    public String getLastChatCustId() {
        return this.lastChatCustId;
    }
    public void setLastChatCustId(String lastChatCustId) {
        this.lastChatCustId = lastChatCustId;
    }
    public String getLastChatDes() {
        return this.lastChatDes;
    }
    public void setLastChatDes(String lastChatDes) {
        this.lastChatDes = lastChatDes;
    }
    public String getLastChatId() {
        return this.lastChatId;
    }
    public void setLastChatId(String lastChatId) {
        this.lastChatId = lastChatId;
    }
    public String getLastChatMsgType() {
        return this.lastChatMsgType;
    }
    public void setLastChatMsgType(String lastChatMsgType) {
        this.lastChatMsgType = lastChatMsgType;
    }
    public String getGroupUserIds() {
        return this.groupUserIds;
    }
    public void setGroupUserIds(String groupUserIds) {
        this.groupUserIds = groupUserIds;
    }
    public String getGroupUserAliasNames() {
        return this.groupUserAliasNames;
    }
    public void setGroupUserAliasNames(String groupUserAliasNames) {
        this.groupUserAliasNames = groupUserAliasNames;
    }
    public String getGroupUserAliasIds() {
        return this.groupUserAliasIds;
    }
    public void setGroupUserAliasIds(String groupUserAliasIds) {
        this.groupUserAliasIds = groupUserAliasIds;
    }


    @Transient
    private boolean isMutilp;
    @Transient
    private boolean isSelect;

    public boolean isMutilp() {
        return isMutilp;
    }

    public void setMutilp(boolean mutilp) {
        isMutilp = mutilp;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
    public String getShowInChatGroup() {
        return this.showInChatGroup;
    }
    public void setShowInChatGroup(String showInChatGroup) {
        this.showInChatGroup = showInChatGroup;
    }
}
