package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class Message {

    @Id(autoincrement = true)
    private Long id;
    private String content;
    private int type;
    private String time;
    private String receiver;
    private String sender;
    @Generated(hash = 351510202)
    public Message(Long id, String content, int type, String time, String receiver,
            String sender) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.time = time;
        this.receiver = receiver;
        this.sender = sender;
    }
    @Generated(hash = 637306882)
    public Message() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getReceiver() {
        return this.receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getSender() {
        return this.sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", time='" + time + '\'' +
                ", receiver='" + receiver + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
