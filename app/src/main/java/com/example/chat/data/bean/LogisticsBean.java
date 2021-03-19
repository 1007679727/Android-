package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class LogisticsBean {
    @Id(autoincrement = true)
    private Long id;
    @Index(unique = true)
    private String groupId;
    private String taskName;
    @Generated(hash = 174853375)
    public LogisticsBean(Long id, String groupId, String taskName) {
        this.id = id;
        this.groupId = groupId;
        this.taskName = taskName;
    }
    @Generated(hash = 1526887395)
    public LogisticsBean() {
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
    public String getTaskName() {
        return this.taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

}
