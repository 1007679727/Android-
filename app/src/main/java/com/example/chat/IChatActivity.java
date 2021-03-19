package com.example.chat;

import android.content.Context;


import java.util.List;

/**
 * Created by FHZ on 2018/9/5.
 */

public interface IChatActivity {
    boolean isActive();
    void onHistoryMsgLoad(List<HistoryMsg.DataBean.Row> historyMessageItems);
    Context getContext();
    void onServerResponse(List<HistoryMsg.DataBean.Row> historyMessageItems);
    void onServerResponse(HistoryMsg.DataBean.Row chatMessage);
    void sendMsg(HistoryMsg.DataBean.Row chatMessage);
    void setGroupInfo(List<String> custIds, List<String> realNameList, List<String> roleNames, GroupDetailData groupInfo);
    void onMsgIconLongClick(int position);
    void setCommander(String custId);
}
