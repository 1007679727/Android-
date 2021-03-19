package com.example.chat;

import android.media.MediaRecorder;

import com.example.chat.msg.HistoryMsg;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by FHZ on 2018/9/5.
 */

public interface IChatPresenter {
    void loadHistoryMsg(String groupId);
    void loadGroupDetails(String groupId);
	void uploadChatMsg(HistoryMsg.DataBean.Row chatMessage);
    void serverResponse(String groupId, HistoryMsg.DataBean.Row chatMessage, List<HistoryMsg.DataBean.Row> historyMsgList);
    void uploadFile(Map<String, String> params, File file);
    void updateChatRecordsState(String groupId);
    void startVoiceRecord(MediaRecorder mediaRecorder, File soundFile);
//    ChatMessage parseToChatMessage(ChatMsgTextOrEmoticons chatMsgTextOrEmoticons, ChatMessage chatMessage);
}
