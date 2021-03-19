package com.example.chat.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PatrolReqParamSaveBean {
    private String carId;
    private String hhId;
    private String leaderId;
    private String memberIds;
    private String equipIds;
    private String hhType;
    private String hhName;
    private String patrolType;

    @Generated(hash = 802632300)
    public PatrolReqParamSaveBean(String carId, String hhId, String leaderId,
            String memberIds, String equipIds, String hhType, String hhName,
            String patrolType) {
        this.carId = carId;
        this.hhId = hhId;
        this.leaderId = leaderId;
        this.memberIds = memberIds;
        this.equipIds = equipIds;
        this.hhType = hhType;
        this.hhName = hhName;
        this.patrolType = patrolType;
    }

    @Generated(hash = 2002559269)
    public PatrolReqParamSaveBean() {
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getHhId() {
        return hhId;
    }

    public void setHhId(String hhId) {
        this.hhId = hhId;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds;
    }

    public String getEquipIds() {
        return equipIds;
    }

    public void setEquipIds(String equipIds) {
        this.equipIds = equipIds;
    }

    public String getHhType() {
        return hhType;
    }

    public void setHhType(String hhType) {
        this.hhType = hhType;
    }

    public String getHhName() {
        return hhName;
    }

    public void setHhName(String hhName) {
        this.hhName = hhName;
    }

    public String getPatrolType() {
        return patrolType;
    }

    public void setPatrolType(String patrolType) {
        this.patrolType = patrolType;
    }
}
