package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class PatrolGroupBean {

    @Id(autoincrement = true)
    private Long id;
    @Index(unique = true)
    private int hhId;
    private String hhmc;
    @Generated(hash = 1400218156)
    public PatrolGroupBean(Long id, int hhId, String hhmc) {
        this.id = id;
        this.hhId = hhId;
        this.hhmc = hhmc;
    }
    @Generated(hash = 1725896631)
    public PatrolGroupBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getHhId() {
        return this.hhId;
    }
    public void setHhId(int hhId) {
        this.hhId = hhId;
    }
    public String getHhmc() {
        return this.hhmc;
    }
    public void setHhmc(String hhmc) {
        this.hhmc = hhmc;
    }
}
