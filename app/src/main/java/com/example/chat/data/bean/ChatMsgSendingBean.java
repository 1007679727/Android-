package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ChatMsgSendingBean {

    @Id(autoincrement = true)
    private Long id;
    private String fileName;
    private String msgType;
    private String groupId;
    private String specifyRecipients;
    private String content;
    private String url;
    private String des;
    private String createTime;
    private int fileSize;
    private String custId;
    private String contentLength;
    private String fileType;
    private String fileId;
    private String fileDes;
    private String isDel;
    /**
     * 1：正在发送，2：发送失败
     */
    private int sending;
    @Generated(hash = 1534403658)
    public ChatMsgSendingBean(Long id, String fileName, String msgType,
            String groupId, String specifyRecipients, String content, String url,
            String des, String createTime, int fileSize, String custId,
            String contentLength, String fileType, String fileId, String fileDes,
            String isDel, int sending) {
        this.id = id;
        this.fileName = fileName;
        this.msgType = msgType;
        this.groupId = groupId;
        this.specifyRecipients = specifyRecipients;
        this.content = content;
        this.url = url;
        this.des = des;
        this.createTime = createTime;
        this.fileSize = fileSize;
        this.custId = custId;
        this.contentLength = contentLength;
        this.fileType = fileType;
        this.fileId = fileId;
        this.fileDes = fileDes;
        this.isDel = isDel;
        this.sending = sending;
    }
    @Generated(hash = 1434964116)
    public ChatMsgSendingBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getMsgType() {
        return this.msgType;
    }
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    public String getGroupId() {
        return this.groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getSpecifyRecipients() {
        return this.specifyRecipients;
    }
    public void setSpecifyRecipients(String specifyRecipients) {
        this.specifyRecipients = specifyRecipients;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public int getFileSize() {
        return this.fileSize;
    }
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    public String getCustId() {
        return this.custId;
    }
    public void setCustId(String custId) {
        this.custId = custId;
    }
    public String getContentLength() {
        return this.contentLength;
    }
    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }
    public String getFileType() {
        return this.fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getFileId() {
        return this.fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getFileDes() {
        return this.fileDes;
    }
    public void setFileDes(String fileDes) {
        this.fileDes = fileDes;
    }
    public String getIsDel() {
        return this.isDel;
    }
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
    public int getSending() {
        return this.sending;
    }
    public void setSending(int sending) {
        this.sending = sending;
    }
}
