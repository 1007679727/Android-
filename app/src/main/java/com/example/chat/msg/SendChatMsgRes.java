package com.example.chat.msg;

import com.example.chat.HistoryMsg;

public class SendChatMsgRes {

    /**
     * code : 0
     * data : {"content":"呵呵呵哒","contentLength":"","createTime":"2019-12-17 16:15:55","custId":"1","des":"","groupId":"4f3ab45636a444dba3453a8f7ccbe4b0","id":"15765705556843516633773","isDel":"","msgType":"1","specifyRecipients":""}
     * message : success
     * success : true
     */

    private int code;
    private HistoryMsg.DataBean.Row data;
    private String message;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HistoryMsg.DataBean.Row getData() {
        return data;
    }

    public void setData(HistoryMsg.DataBean.Row data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "SendChatMsgRes{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
