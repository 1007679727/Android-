package com.example.chat;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.chat.adapter.EmojiGridViewAdapter;
import com.example.chat.adapter.HistoryMsgRecyclerViewAdapter;
import com.example.chat.msg.GroupDetailData;
import com.example.chat.msg.HistoryMsg;
import com.example.chat.msg.IMMessage;
import com.example.chat.util.SharedPreferencesUtils;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FHZ on 2018/8/6.
 */

public class ChatActivity extends BaseActivity implements IChatActivity,
        View.OnLayoutChangeListener,
        AdapterView.OnItemClickListener,
        View.OnTouchListener,
        MsgEditText.MsgEditTextListener {

    private final int PICK_PICTURE = 1;
    private final int PICK_VIDEO = 2;
    private final int PICK_MEMBER = 3;
    private final int UPDATE_NAME = 4;
    private final int SHOOT_PICTURE=5;
    private final int SHOOT_VIDEO=6;
    private final int PICK_MEMBER_RESULT = 0x0001;
    public static final int MSG_TYPE_TEXT = 0x0001;
    public static final int MSG_TYPE_AUDIO = 0x0002;
    public static final int MSG_TYPE_VIDEO = 0x0003;
    public static final int MSG_TYPE_EMOTION = 0x0004;
    public static final int MSG_TYPE_IMAGE = 0x0005;
    public static final int MSG_TYPE_FILE = 0x0006;
    public static final int MSG_TYPE_AUDIO_CALL = 0x0007;
    public static final int MSG_TYPE_VIDEO_CALL = 0x0008;
    public static final int TYPE_EMOTICON = 1;
    public static final int TYPE_FILE = 2;
    public static final int TYPE_NONE = 3;
    public static final String TYPE_IMAGE = "image/*";
    public static final String TYPE_VIDEO = "video/*";

    final private String TAG = ChatActivity.class.getSimpleName();

    private PopupWindow popupWindow;
    @BindView(R.id.rootView)
    LinearLayout rootChatView;
    @BindView(R.id.recyclerViewChatMsg)
    RecyclerView recyclerViewChatMsg;
    @BindView(R.id.editTextChat)
    MsgEditText editTextChat;
    @BindView(R.id.editTextChatEmoticon)
    ImageView editTextChatEmoticon;
    @BindView(R.id.imageViewChatAdd)
    ImageView imageViewChatAdd;
    @BindView(R.id.textViewSend)
    TextView textViewSend;
    @BindView(R.id.gridViewEmoji)
    GridView gridViewEmoji;
    @BindView(R.id.imageViewAudio)
    ImageView imageViewAudio;
    @BindView(R.id.textViewTalk)
    TextView textViewTalk;
    @BindView(R.id.textViewChatAddPicture)
    TextView textViewChatAddPicture;
    @BindView(R.id.textViewChatAddVideo)
    TextView textViewChatAddVideo;
    @BindView(R.id.gridViewPanel)
    GridLayout gridViewPanel;
    @BindView(R.id.frameLayoutPanel)
    FrameLayout frameLayoutPanel;
    @BindView(R.id.linearLayoutChatAudioTalk)
    LinearLayout linearLayoutChatAudioTalk;
    @BindView(R.id.frameLayoutChatContent)
    FrameLayout frameLayoutChatContent;
    @BindView(R.id.tvTitleBar)
    TextView tvTitleBar;
    private boolean isActive;
    IChatPresenter chatPresenter;
    IServer server;
    private String groupId;
    private String groupName;
    private int groupMemberNum;
    private List<HistoryMsg.DataBean.Row> historyMsgList = new ArrayList<>();
    private List<String> custIdList = new ArrayList<>();
    private List<String> realNameList = new ArrayList<>();
    HistoryMsgRecyclerViewAdapter historyMsgRecyclerViewAdapter;
    private InputMethodManager inputMethodManager;
    private EmojiGridViewAdapter emojiGridViewAdapter;
    private float thumbY;
    private MediaRecorder mediaRecorder;
    private int currentPanelType;
    private File soundFile;
    private long currentTime;
    private String archiveId;
    private String commanderId;
    private String userId = "1";
    private List<String> members = new ArrayList<>();
    private TextView tvShootPicture;
    private TextView tvShootVideo;
    private TextView tvCancel;
    private File fileShootMedia;
    private View chatChooseShootMask;
    private Uri u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        init();
        isActive = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActive = true;
    }

    private void init() {
//        userId = DbManager.getInstance().getDaoInstant(getApplicationContext()).getUserInfoBeanDao().queryBuilder().list().get(0).getUserId() + "";
//        userId = DbManager.getInstance().getDaoInstant(getApplicationContext()).getUserInfoBeanDao().queryBuilder().list().get(0).getUserId() + "";
//        members = getIntent().getStringArrayListExtra("members");
//        groupId = getIntent().getStringExtra("groupId");
//        groupName = getIntent().getStringExtra("groupName");
//        groupMemberNum = getIntent().getIntExtra("groupMemberNum", 0);
        initViews();
        chatPresenter = new ChatPresenter(this);
        Intent intent = new Intent("com.fhzz.njpsp.MainService");
        intent.setPackage("com.fhzz.njpsp");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        chatPresenter.updateChatRecordsState(groupId);
    }

    private void initViews() {

        tvTitleBar.setText(groupName + "(" + groupMemberNum + ")");
        recyclerViewChatMsg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        historyMsgRecyclerViewAdapter = new HistoryMsgRecyclerViewAdapter(historyMsgList, this);
        recyclerViewChatMsg.setAdapter(historyMsgRecyclerViewAdapter);
        recyclerViewChatMsg.addOnLayoutChangeListener(this);
        textViewTalk.setOnTouchListener(this);
        editTextChat.setFilters(new InputFilter[]{(source, start, end, dest, dstart, dend) -> {
            if (source.toString().equalsIgnoreCase("@")
                    || source.toString().equalsIgnoreCase("＠")) {
                goAt();
            }
            return source;
        }});
        emojiGridViewAdapter = new EmojiGridViewAdapter(this);
        gridViewEmoji.setAdapter(emojiGridViewAdapter);
        gridViewEmoji.setOnItemClickListener(this);
        editTextChat.setOnTouchListener(this);
        editTextChat.setMsgEditTextListener(this);
    }

    @Override
    public void onHistoryMsgLoad(List<HistoryMsg.DataBean.Row> rows) {
        historyMsgList.clear();
        textViewSend.setClickable(true);
        Collections.reverse(rows);
        historyMsgList.addAll(rows);
        historyMsgRecyclerViewAdapter.notifyDataSetChanged();
        recyclerViewChatMsg.getLayoutManager().scrollToPosition(historyMsgList.size() - 1);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            server = IServer.Stub.asInterface(service);
            chatPresenter.loadHistoryMsg(groupId);
            textViewSend.setClickable(false);
            chatPresenter.loadGroupDetails(groupId);
            try {
                server.onConnectSuccess(client);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            
            try {
                server.onDisConnectSuccess(client);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    IClient.Stub client = new IClient.Stub() {

        @Override
        public void response(IMMessage imMessage) throws RemoteException {
            if(imMessage.getType()>1099||imMessage.getType()<1000){
                return;
            }
            HistoryMsg.DataBean.Row chatMessage = new Gson().fromJson(imMessage.getContent(), HistoryMsg.DataBean.Row.class);
            if(!chatMessage.getGroupId().equals(groupId)){
                return;
            }
            chatPresenter.serverResponse(groupId, chatMessage, historyMsgList);
        }

        @Override
        public void notifyGps(String gps) throws RemoteException {

        }

        @Override
        public void debug(String debug) throws RemoteException {

        }
    };

    @Override
    public void onServerResponse(List<HistoryMsg.DataBean.Row> historyMsgList) {
        historyMsgRecyclerViewAdapter.notifyDataSetChanged();
        recyclerViewChatMsg.getLayoutManager().scrollToPosition(historyMsgList.size() - 1);
    }

    @Override
    public void onServerResponse(HistoryMsg.DataBean.Row chatMessage) {
        refreshChatList(chatMessage);
        chatPresenter.updateChatRecordsState(groupId);
    }

    @Override
    public void sendMsg(HistoryMsg.DataBean.Row chatMessage) {
        refreshChatList(chatMessage);

        IMMessage imMessage = new IMMessage();
        switch (Integer.parseInt(chatMessage.getMsgType())){
            case MSG_TYPE_TEXT:
                imMessage.setType(1001);
                break;
            case MSG_TYPE_AUDIO:
                imMessage.setType(1002);
                break;
            case MSG_TYPE_IMAGE:
                imMessage.setType(1003);
                break;
            case MSG_TYPE_VIDEO:
                imMessage.setType(1004);
                break;
            case MSG_TYPE_FILE:
                imMessage.setType(1004);
                break;
        }
        imMessage.setReceiver(members);
        imMessage.setSender(userId);
        imMessage.setContent(new Gson().toJson(chatMessage));
        if (server != null) {
            try {
                server.send(imMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void refreshChatList(HistoryMsg.DataBean.Row chatMessage) {
        editTextChat.getText().clear();
        editTextChat.clearCustIds();
        historyMsgList.add(historyMsgList.size(), chatMessage);
        recyclerViewChatMsg.getLayoutManager().scrollToPosition(historyMsgList.size() - 1);
        historyMsgRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setGroupInfo(List<String> custIds, List<String> realNames, List<String> roleNames, GroupDetailData groupInfo) {
        textViewSend.setClickable(true);
        custIdList = custIds;
        realNameList = realNames;
        this.archiveId = groupInfo.getArchivesId();
        groupName = groupInfo.getGROUP_NAME();
        groupMemberNum = custIdList.size() + 1;
        tvTitleBar.setText(groupName + "(" + groupMemberNum + ")");
        int index = new ArrayList<>(Arrays.asList(groupInfo.getCUST_ID().split(","))).indexOf(SharedPreferencesUtils.getInstance().getString("userId", ""));
        if (-1 != index) {
            if (roleNames.get(index).contains("管理员")) {
//                imageViewAssignTask.setVisibility(View.VISIBLE);
            }
        }
//        chatPresenter.getCommander(archiveId);
    }

    @Override
    public void setCommander(String custId) {
        this.commanderId = custId;
        if (commanderId.equals(SharedPreferencesUtils.getInstance().getString("userId", ""))) {
//            imageViewAssignTask.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gridViewEmoji:
                List<Integer> emojiUnicodeList = EmojiGridViewAdapter.getEmojiUnicodeList();
                if (position == emojiUnicodeList.size() - 1) {
                    editTextChat.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                } else {
                    Integer integer = emojiUnicodeList.get(position);
                    int currentPosition = editTextChat.getSelectionStart();
                    String emoji = new String(Character.toChars(integer));
                    StringBuilder stringBuilder = new StringBuilder(editTextChat.getText().toString());
                    stringBuilder.insert(currentPosition, emoji);
                    editTextChat.setText(stringBuilder.toString());
                    editTextChat.setSelection(currentPosition + emoji.length());
                }
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.textViewTalk:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    
                    thumbY = event.getY();
                    linearLayoutChatAudioTalk.setVisibility(View.VISIBLE);
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setOnErrorListener(null);
                    File dir = new File(Environment.getExternalStorageDirectory(), "fhzz/sound");    //指定录音输出文件的文件夹（最后会删除录音文件的）
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    currentTime = System.currentTimeMillis();
                    soundFile = new File(dir, currentTime + ".mp3");
                    if (!soundFile.exists()) {
                        try {
                            soundFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    chatPresenter.startVoiceRecord(mediaRecorder, soundFile);
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    
                } else if (event.getAction() == MotionEvent.ACTION_UP ||event.getAction() == MotionEvent.ACTION_CANCEL) {
                    linearLayoutChatAudioTalk.setVisibility(View.GONE);
                    try {
                        mediaRecorder.stop();
                        mediaRecorder.release();
                    } catch (RuntimeException stopException) {
                        stopException.printStackTrace();
                    }
                    if ((System.currentTimeMillis() - currentTime) / 1000 < 1) {
                        Toast.makeText(this, "取消录音", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    if (thumbY - event.getY() < 100) {
                        uploadFile(soundFile, MSG_TYPE_AUDIO + "", (System.currentTimeMillis() - currentTime) / 1000 + "");
                    } else {
                        Toast.makeText(this, "取消录音", Toast.LENGTH_SHORT).show();
                    }
                    
                }
                break;
            case R.id.editTextChat:
                if (event.getAction() == MotionEvent.ACTION_UP && frameLayoutPanel.isShown()) {
                    lockContentHeight();//显示软件盘时，锁定内容高度，防止跳闪。
                    hidePanel(true);//隐藏表情布局，显示软件盘
                    //软件盘显示后，释放内容高度
                    editTextChat.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            unlockContentHeightDelayed();
                        }
                    }, 100L);
                }
                break;
        }
        return false;
    }

    @OnClick({R.id.recyclerViewChatMsg,
            R.id.ivTitleBack,
            R.id.textViewSend,
            R.id.imageViewChatAdd,
            R.id.editTextChatEmoticon,
            R.id.editTextChat,
            R.id.imageViewAudio,
            R.id.textViewTalk,
            R.id.textViewChatAddPicture,
            R.id.textViewChatAddVideo,
            R.id.textViewChatAddCamera,
            R.id.textViewChatAddVideoCall,
            R.id.textViewChatAddAudioCall})
    public void onViewClicked(View view) {
        IMMessage imMessage = new IMMessage();
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.recyclerViewChatMsg:
                break;
            case R.id.ivTitleBack:
                setResult(2);
                finish();
                break;
            case R.id.textViewSend:
                String msg = editTextChat.getText().toString();
                encapsulateMsg(msg);
                break;
            case R.id.imageViewChatAdd:
                onClickPanel(TYPE_FILE);
                break;
            case R.id.editTextChatEmoticon:
                onClickPanel(TYPE_EMOTICON);
                break;
            case R.id.imageViewAudio:
                if (imageViewAudio.getContentDescription().toString().equals("audio")) {
                    imageViewAudio.setContentDescription("keyboard");
                    imageViewAudio.setImageResource(R.drawable.chat_keyboard);
                    editTextChat.setVisibility(View.GONE);
                    textViewTalk.setVisibility(View.VISIBLE);
                } else if (imageViewAudio.getContentDescription().toString().equals("keyboard")) {
                    imageViewAudio.setContentDescription("audio");
                    imageViewAudio.setImageResource(R.drawable.chat_audio);
                    editTextChat.setVisibility(View.VISIBLE);
                    textViewTalk.setVisibility(View.GONE);
                }
                break;
            case R.id.textViewChatAddPicture:
                openAlbum(TYPE_IMAGE);
                break;
            case R.id.textViewChatAddVideo:
                openAlbum(TYPE_VIDEO);
                break;
            case R.id.textViewChatAddCamera:
                showPopupWindow();
                break;
//            case R.id.textViewChatAddVideoCall:
//                imMessage.setReceiver(members);
//                imMessage.setSender(userId);
//                imMessage.setContent("");
//                imMessage.setType(1121);
//                intent.setClass(this, ConferenceActivity.class);
//                intent.putExtra("IMMessage",new Gson().toJson(imMessage));
//                intent.putExtra("groupId",groupId);
//                startActivity(intent);
//                break;
            case R.id.textViewChatAddAudioCall:
                imMessage.setReceiver(members);
                imMessage.setSender(userId);
                imMessage.setContent("");
                imMessage.setType(1122);
                intent.putExtra("IMMessage",new Gson().toJson(imMessage));
                break;
        }
    }

    private void showPopupWindow() {
        WindowManager manager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels-getStatusBarHeight(this);
        if (popupWindow == null) {
            View rootView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_chat_choose_camera_shoot_type, null);
            popupWindow = new PopupWindow(rootView, width, height, true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            chatChooseShootMask=rootView.findViewById(R.id.chatChooseShootMask);
            tvShootPicture = rootView.findViewById(R.id.tvShootPicture);
            tvShootVideo = rootView.findViewById(R.id.tvShootVideo);
            tvCancel = rootView.findViewById(R.id.tvCancel);
        }


        popupWindow.showAtLocation(rootChatView,Gravity.BOTTOM,0,0);

        chatChooseShootMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        tvShootPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                shoot(MediaStore.ACTION_IMAGE_CAPTURE);
            }
        });

        tvShootVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                shoot(MediaStore.ACTION_VIDEO_CAPTURE);
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    private void shoot(String action) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date(System.currentTimeMillis());
//        String time = simpleDateFormat.format(date);
        String fileName =(action.equals(MediaStore.ACTION_IMAGE_CAPTURE)?"IMG_":"VID_")+ System.currentTimeMillis() +(action.equals(MediaStore.ACTION_IMAGE_CAPTURE)?".jpeg":".mp4");
        File dir= new File(Environment.getExternalStorageDirectory() + "/fhzz/shoot");
        if(!dir.exists()){
            dir.mkdirs();
        }
        fileShootMedia = new File(dir, fileName);
         u = Uri.fromFile(fileShootMedia);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //通过FileProvider创建一个content类型的Uri
            u = FileProvider.getUriForFile(this, "com.example.chat.fileprovider", fileShootMedia);
        }
        Intent intentVideo = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intentVideo.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
//        intentVideo.setAction(MediaStore.ACTION_VIDEO_CAPTURE);

        intentVideo.setAction(action);
        intentVideo.addCategory(Intent.CATEGORY_DEFAULT);

        //将拍照结果保存至photo_file的Uri中
        intentVideo.putExtra(MediaStore.EXTRA_OUTPUT, u);
        startActivityForResult(intentVideo, action.equals(MediaStore.ACTION_IMAGE_CAPTURE)?SHOOT_PICTURE:SHOOT_VIDEO);
    }

    private void openAlbum(String type) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType(type);
        startActivityForResult(intent, type.equals(TYPE_IMAGE) ? PICK_PICTURE : PICK_VIDEO);
    }

    private void encapsulateMsg(String msg) {
        HistoryMsg.DataBean.Row chatMessage = new HistoryMsg.DataBean.Row();
        chatMessage.setCustId(userId);
        chatMessage.setGroupId(groupId);
        chatMessage.setContent(msg);
        chatMessage.setMsgType(MSG_TYPE_TEXT + "");
        chatMessage.setDes(editTextChat.getUserIdString());
        chatPresenter.uploadChatMsg(chatMessage);
    }

    /**
     * 跳转选择要@的成员
     */
    private void goAt() {
        Intent intent = new Intent(this, GroupMemberListActivity.class);
        ArrayList<String> realNames = new ArrayList<>(realNameList);
        intent.putExtra("realNames", realNames);
        intent.setType("at");
        startActivityForResult(intent, PICK_MEMBER);
    }

    /**
     * 长按头像@该成员
     *
     * @param position
     */
    @Override
    public void onMsgIconLongClick(int position) {
        String cust_id = historyMsgList.get(position).getCustId();
        insertAtMember(cust_id, false);
    }

    private void insertAtMember(String custId, boolean hasAt) {
        String realName = realNameList.get(custIdList.indexOf(custId));
        if (hasAt) {
            editTextChat.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        }
        editTextChat.addAtSpan("@", realName, custId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String imagePath = getImagePath(uri);
                    if (imagePath != null) {
                        File file = new File(imagePath);
                        if (file != null) {
                            uploadFile(file, MSG_TYPE_IMAGE + "", "");
                        }
                    }
                }
                break;
            case PICK_VIDEO:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String imagePath = getImagePath(uri);
                    if (imagePath != null) {
                        File file = new File(imagePath);
                        if (file != null) {
                            uploadFile(file, MSG_TYPE_VIDEO + "", "");
                        }
                    }
                }
                break;
            case UPDATE_NAME:
                if (resultCode == 0x0002) {
                    textViewSend.setClickable(false);
                    chatPresenter.loadGroupDetails(groupId);
                }
                break;
            case PICK_MEMBER:
                if (PICK_MEMBER_RESULT == resultCode) {
                    int position = data.getIntExtra("position", -1);
                    insertAtMember(custIdList.get(position), true);
                }
                break;
            case SHOOT_PICTURE:
                try {
                    getBitmapFormUri(u);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                uploadFile(fileShootMedia,MSG_TYPE_IMAGE+"","");
                break;
            case SHOOT_VIDEO:
                uploadFile(fileShootMedia,MSG_TYPE_VIDEO+"","");
                break;
        }
    }


    public Bitmap getBitmapFormUri(Uri uri) throws FileNotFoundException, IOException {
        InputStream input = getContentResolver().openInputStream(uri);

        //这一段代码是不加载文件到内存中也得到bitmap的真是宽高，主要是设置inJustDecodeBounds为true
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;//不加载到内存
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.RGB_565;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;

        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比，由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;
        bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        input = getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }

    public Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
            if (options <= 0)
                break;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    private void uploadFile(File file, String type, String contentLength) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("custId", userId);
        hashMap.put("groupId", groupId);
        hashMap.put("msgType", type);
        hashMap.put("contentLength", contentLength);
        hashMap.put("type", "chatContent");
        hashMap.put("content", file.getName());
        chatPresenter.uploadFile(hashMap, file);
    }

    private String getImagePath(Uri uri) {
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        String imagePath = null;
        if (scheme == null) {
            imagePath = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            imagePath = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        imagePath = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return imagePath;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (bottom < oldBottom) {
            recyclerViewChatMsg.postDelayed(() -> recyclerViewChatMsg.getLayoutManager().scrollToPosition(historyMsgList.size() - 1), 100);
        }
    }

    private void onClickPanel(int type) {
        if (frameLayoutPanel.isShown()) {
            if (type == currentPanelType) {
                lockContentHeight();
                hidePanel(true);
                currentPanelType = TYPE_NONE;
                unlockContentHeightDelayed();
            } else {
                showPanel(type);
            }
        } else {
            if (getSupportSoftInputHeight() != 0) {
                lockContentHeight();
                hideSoftInput();
                showPanel(type);
                unlockContentHeightDelayed();
            } else {
                showPanel(type);
            }
        }
    }


    private void showPanel(int type) {
        int softInputHeight = getSupportSoftInputHeight();
        if (softInputHeight == 0) {
            softInputHeight = getKeyBoardHeight();
        }
        frameLayoutPanel.getLayoutParams().height = softInputHeight;
        frameLayoutPanel.setVisibility(View.VISIBLE);
        switch (type) {
            case TYPE_EMOTICON:
                currentPanelType = TYPE_EMOTICON;
                gridViewEmoji.setVisibility(View.VISIBLE);
                gridViewPanel.setVisibility(View.GONE);
                break;
            case TYPE_FILE:
                currentPanelType = TYPE_FILE;
                gridViewEmoji.setVisibility(View.GONE);
                gridViewPanel.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 锁定内容高度，防止跳闪
     */
    private void lockContentHeight() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) frameLayoutChatContent.getLayoutParams();
        params.height = frameLayoutChatContent.getHeight();
        params.weight = 0.0F;
    }

    /**
     * 释放被锁定的内容高度
     */
    private void unlockContentHeightDelayed() {
        editTextChat.postDelayed(() -> ((LinearLayout.LayoutParams) frameLayoutChatContent.getLayoutParams()).weight = 1.0F, 200L);
    }

    /**
     * 隐藏表情布局
     *
     * @param showSoftInput 是否显示软件盘
     */
    private void hidePanel(boolean showSoftInput) {
        frameLayoutPanel.setVisibility(View.GONE);
        if (showSoftInput) {
            showSoftInput();
        }
    }

    /**
     * 编辑框获取焦点，并显示软件盘
     */
    private void showSoftInput() {
        editTextChat.requestFocus();
        editTextChat.post(new Runnable() {
            @Override
            public void run() {
                inputMethodManager.showSoftInput(editTextChat, 0);
            }
        });
    }

    /**
     * 获取软键盘的高度
     *
     * @return
     */
    private int getSupportSoftInputHeight() {
        Rect r = new Rect();
        /**
         * decorView是window中的最顶层view，可以从window中通过getDecorView获取到decorView。
         * 通过decorView获取到程序显示的区域，包括标题栏，但不包括状态栏。
         */
        getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        //获取屏幕的高度
        int screenHeight = getWindow().getDecorView().getRootView().getHeight();
        //计算软件盘的高度
        int softInputHeight = screenHeight - r.bottom;

        /**
         * 某些Android版本下，没有显示软键盘时减出来的高度总是144，而不是零，
         * 这是因为高度是包括了虚拟按键栏的(例如华为系列)，所以在API Level高于20时，
         * 我们需要减去底部虚拟按键栏的高度（如果有的话）
         */
        if (Build.VERSION.SDK_INT >= 20) {
            softInputHeight = softInputHeight - getSoftButtonsBarHeight();
        }
        //存一份到本地
//        if (softInputHeight > 0) {
//            SharedPreferencesUtils.getInstance().setInt("input_height", softInputHeight);
//        }
        return softInputHeight;
    }

    /**
     * 获取存储的软键盘高度
     *
     * @return
     */
    private int getKeyBoardHeight() {
        return SharedPreferencesUtils.getInstance().getInt("input_height", 0);
    }

    /**
     * 底部虚拟按键栏的高度
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftButtonsBarHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }


    /**
     * 隐藏软件盘
     */
    private void hideSoftInput() {
        inputMethodManager.hideSoftInputFromWindow(editTextChat.getWindowToken(), 0);
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActive = false;
        unbindService(serviceConnection);
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void onTextChanged(CharSequence text) {
        if (text.length() > 0) {
            imageViewChatAdd.setVisibility(View.GONE);
            textViewSend.setVisibility(View.VISIBLE);
        } else {
            imageViewChatAdd.setVisibility(View.VISIBLE);
            textViewSend.setVisibility(View.GONE);
        }
    }

}

