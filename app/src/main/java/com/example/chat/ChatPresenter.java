package com.example.chat;

import android.annotation.SuppressLint;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;


import com.example.chat.data.DbManager;
import com.example.chat.msg.HistoryMsg;
import com.example.chat.msg.SendChatMsgRes;
import com.example.chat.msg.UploadMsgFileRes;
import com.example.chat.request.CommonRequest;
import com.example.chat.request.RequestListener;
import com.example.chat.util.Constant;
import com.example.chat.util.DateUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by FHZ on 2018/9/5.
 */

public class ChatPresenter implements IChatPresenter {
    final private String TAG = ChatPresenter.class.getSimpleName();
    private IChatActivity chatActivity;
    private static HistoryMsg historyMsg = new HistoryMsg();

    public ChatPresenter(IChatActivity chatActivity) {
        this.chatActivity = chatActivity;
        HistoryMsg.DataBean dataBean = new HistoryMsg.DataBean();
        ArrayList arrayList = new ArrayList();
        dataBean.setRows(arrayList);
        historyMsg.setData(dataBean);
    }

    @Override
    public void loadHistoryMsg(String groupId) {
//        Map<String, String> params = new HashMap<>();
//        params.put("groupId", groupId);
//        params.put("custId", DbManager.getInstance().getDaoInstant(chatActivity.getContext().getApplicationContext()).getUserInfoBeanDao().queryBuilder().list().get(0).getUserId() + "");
//        params.put("pageIndex", "1");
//        params.put("pageSize", "0");
//        CommonRequest.getInstance().post(Constant.IP_COMMAND + "chat/chat/findChatRecordsOfPageByTime", params, "loadHistoryMsg", HistoryMsg.class, new RequestListener() {
//            @Override
//            public void onSuccess(Object o) {
//                HistoryMsg historyMsg = (HistoryMsg) o;
//                Flowable.just(0)
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .subscribe(integer -> {
//                            if (historyMsg.isSuccess()) {
//                                if (chatActivity.isActive()) {
//                                    chatActivity.onHistoryMsgLoad(historyMsg.getData().getRows());
//                                }
//                            }
//                        });
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        });
        if (chatActivity.isActive()) {
            chatActivity.onHistoryMsgLoad(historyMsg.getData().getRows());
        }
    }

    private boolean compareTime(String userCreateTime, String create_time) {
        Long userTime = DateUtils.formatTime(userCreateTime);
        Long msgTime = DateUtils.formatTime(create_time);
        if (userTime != null && msgTime != null) {
            return userTime - msgTime > 0 ? false : true;
        }
        return false;
    }

    @Override
    public void loadGroupDetails(String groupId) {
//        String url = new StringBuilder(Constant.reqAddrWithPre).append("getGroupDetail?groupId=").append(groupId).toString();//.append("&custId=").append(SharedPreferencesUtils.getInstance().getString("userId", null)).toString();
//        CommonRequest.getInstance().get(url,"", GroupDetailRes.class, new RequestListener() {
//            @Override
//            public void onSuccess(Object o) {
//                GroupDetailRes groupDetailRes = (GroupDetailRes) o;
//                if (groupDetailRes != null && groupDetailRes.getCode() == 0) {
//                    String CUST_ID = groupDetailRes.getData().getCUST_ID();
//                    String real_name = groupDetailRes.getData().getReal_name();
//                    List<String> custIds = new ArrayList<>(Arrays.asList(CUST_ID.split(",")));
//                    List<String> realNames = new ArrayList<>(Arrays.asList(real_name.split(",")));
//                    List<String> roleNames = new ArrayList<>(Arrays.asList(groupDetailRes.getData().getRole_name().split(",")));
////                    ArrayList<String> custIdList = new ArrayList<>(custIds);
////                    ArrayList<String> realNameList = new ArrayList<>(realNames);
//                    String userId = SharedPreferencesUtils.getInstance().getString("userId", null);
//                    realNames.remove(custIds.indexOf(userId));
//                    custIds.remove(userId);
//                    chatActivity.setGroupInfo(custIds, realNames, roleNames, groupDetailRes.getData());
//                    FhzzLog.d(TAG, custIds.toString());
//                }
//            }
//
//            @Override
//            public void onError() {
//
//            }
//
//
//        });
    }

    @Override
    public void uploadChatMsg(HistoryMsg.DataBean.Row chatMessage) {
//        CommonRequest.getInstance().post(Constant.IP_COMMAND+ "chat/chat/sendChatRecords",chatMessage,"uploadChatMsg", SendChatMsgRes.class,new RequestListener(){
//            @Override
//            public void onSuccess(Object o) {
//                SendChatMsgRes sendChatMsgRes = (SendChatMsgRes) o;
//                if (sendChatMsgRes != null && sendChatMsgRes.isSuccess()) {
//                    Flowable.just(0)
//                            .subscribeOn(AndroidSchedulers.mainThread())
//                            .subscribe(integer -> {
//                                if (chatActivity.isActive()) {
//                                    chatActivity.sendMsg(sendChatMsgRes.getData());
//                                }
//                            });
//
//                }
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        });
        List<HistoryMsg.DataBean.Row> list = historyMsg.getData().getRows();
        chatMessage.setCreateTime(DateUtils.formatTime(System.currentTimeMillis()));
        list.add(chatMessage);
        HistoryMsg.DataBean bean = new HistoryMsg.DataBean();
        bean.setRows(list);
        historyMsg.setData(bean);
        if (chatActivity.isActive()) {
            chatActivity.sendMsg(chatMessage);
//            chatActivity.onServerResponse(list);
        }
    }

    @Override
    public void uploadFile(Map<String, String> params, File file) {

//        CommonRequest.getInstance().upload(Constant.IP_COMMAND + "chat/chat/uploadChatFile", params, file,"uploadData","uploadFile", UploadMsgFileRes.class, new RequestListener() {
//            @Override
//            public void onSuccess(Object o) {
//                UploadMsgFileRes uploadMsgFileRes = (UploadMsgFileRes) o;
//
//                HistoryMsg.DataBean.Row chatMessage=new HistoryMsg.DataBean.Row();
//
//                chatMessage.setContent(uploadMsgFileRes.getData().getChatRecord().getContent());
//                chatMessage.setContentLength(uploadMsgFileRes.getData().getChatRecord().getContentLength());
//                chatMessage.setDes(uploadMsgFileRes.getData().getChatRecord().getDes());
//                chatMessage.setMsgType(uploadMsgFileRes.getData().getChatRecord().getMsgType());
//                chatMessage.setGroupId(uploadMsgFileRes.getData().getChatRecord().getGroupId());
//                chatMessage.setCustId(uploadMsgFileRes.getData().getChatRecord().getCustId());
//                chatMessage.setCreateTime(uploadMsgFileRes.getData().getChatRecord().getCreateTime());
//                chatMessage.setUrl(uploadMsgFileRes.getData().getChatFile().getUrl());
//                chatMessage.setFileDes(uploadMsgFileRes.getData().getChatFile().getDes());
//                chatMessage.setFileName(uploadMsgFileRes.getData().getChatFile().getFilename());
//                chatMessage.setFileSize(uploadMsgFileRes.getData().getChatFile().getFileSize());
//                chatMessage.setFileType(uploadMsgFileRes.getData().getChatFile().getFiletype());
//                chatMessage.setId(uploadMsgFileRes.getData().getChatRecord().getId());
//
//                Flowable.just(0)
//                        .subscribeOn(AndroidSchedulers.mainThread())
//                        .subscribe(integer -> {
//                            if (uploadMsgFileRes.isSuccess()) {
//                                if (chatActivity.isActive()) {
//                                    chatActivity.sendMsg(chatMessage);
//                                }
//                            }
//                        });
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        });

        HistoryMsg.DataBean.Row chatMessage=new HistoryMsg.DataBean.Row();
        UploadMsgFileRes.DataBean bean = new UploadMsgFileRes.DataBean();
//                bean.setChatFile(new UploadMsgFileRes.DataBean.ChatFileBean().setUrl(););
//        new UploadMsgFileRes().setData();
        if (chatActivity.isActive()) {
            chatActivity.sendMsg(chatMessage);
        }
    }

    @Override
    public void updateChatRecordsState(String groupId) {
//        CommonRequest commonRequest = new CommonRequest();
//        String url = new StringBuilder(Constant.reqAddrWithPre).append("updateChatRecordsStateByGroup.do?groupId=").append(groupId)
//                .append("&custId=").append(SharedPreferencesUtils.getInstance().getString("userId", null)).toString();
//        commonRequest.requestGet(url, FhzzResponse.class, new CommonRequest.OnRequestCompletedListener() {
//            @Override
//            public void success(Object o) {
//
//            }
//
//            @Override
//            public void failed(Object o) {
//
//            }
//        });
    }

    @Override
    public void startVoiceRecord(MediaRecorder mediaRecorder, File soundFile) {
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setOutputFile(soundFile.getAbsolutePath());
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Timer timer=new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                FhzzLog.d(TAG,"采样的声音："+ mediaRecorder.getMaxAmplitude());
//            }
//        },100);
    }


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x0001:
                    chatActivity.onServerResponse((List<HistoryMsg.DataBean.Row>) msg.obj);
                    break;
                case 0:
                    chatActivity.onServerResponse((HistoryMsg.DataBean.Row) msg.obj);
                    break;
            }
        }
    };

    @Override
    public void serverResponse(String groupId, HistoryMsg.DataBean.Row chatMessage, List<HistoryMsg.DataBean.Row> historyMessageItems) {
        handler.obtainMessage(0, chatMessage).sendToTarget();
        updateChatRecordsState(groupId);
    }

}
