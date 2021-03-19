package com.example.chat.msg;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class IMMessage implements Parcelable {
    private String content;
    private int type;
    private String time;
    private List<String> receiver;
    private List<String> recOrg;
    private String sender;
    private boolean broadcast;

    public IMMessage(){

    }

    protected IMMessage(Parcel in) {
        content = in.readString();
        type = in.readInt();
        time = in.readString();
        receiver = in.createStringArrayList();
        recOrg = in.createStringArrayList();
        sender = in.readString();
        broadcast = in.readByte() != 0;
    }

    public static final Creator<IMMessage> CREATOR = new Creator<IMMessage>() {
        @Override
        public IMMessage createFromParcel(Parcel in) {
            return new IMMessage(in);
        }

        @Override
        public IMMessage[] newArray(int size) {
            return new IMMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(content);
        parcel.writeInt(type);
        parcel.writeString(time);
        parcel.writeStringList(receiver);
        parcel.writeStringList(recOrg);
        parcel.writeString(sender);
        parcel.writeByte((byte) (broadcast ? 1 : 0));
    }

    public List<String> getRecOrg() {
        return recOrg;
    }

    public void setRecOrg(List<String> recOrg) {
        this.recOrg = recOrg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public static Creator<IMMessage> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "IMMessage{" +
                "content='" + content + '\'' +
                ", type=" + type +
                ", time='" + time + '\'' +
                ", receiver=" + receiver +
                ", recOrg=" + recOrg +
                ", sender='" + sender + '\'' +
                ", broadcast=" + broadcast +
                '}';
    }
}
