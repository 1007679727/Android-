package com.example.chat.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;

import com.example.chat.util.Constant;
import com.example.chat.msg.HistoryMsg;
import com.example.chat.IChatActivity;
import com.example.chat.MsgPicActivity;
import com.example.chat.R;
import com.example.chat.util.SharedPreferencesUtils;
import com.example.chat.VideoPlayActivity;
import com.example.chat.data.DbManager;
import com.example.chat.data.dao.ContactBeanDao;
import com.example.chat.request.CommonRequest;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.chat.ChatActivity.MSG_TYPE_AUDIO;
import static com.example.chat.ChatActivity.MSG_TYPE_IMAGE;
import static com.example.chat.ChatActivity.MSG_TYPE_TEXT;
import static com.example.chat.ChatActivity.MSG_TYPE_VIDEO;

/**
 * Created by FHZ on 2018/9/5.
 */

public class HistoryMsgRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final private String TAG = HistoryMsgRecyclerViewAdapter.class.getSimpleName();
    private List<HistoryMsg.DataBean.Row> historyMsgList;
    private IChatActivity chatActivity;
    final public static int ITEM_TYPE_RECEIVE_MSG = 0x0001;
    final public static int ITEM_TYPE_SEND_MSG = 0x0002;
    private HashMap<Integer, Bitmap> videoFrameCache = new HashMap<>();
    private RequestOptions requestOptions;
    private Picasso picasso;

    public HistoryMsgRecyclerViewAdapter(List<HistoryMsg.DataBean.Row> historyMsgList, IChatActivity chatActivity) {
        this.historyMsgList = historyMsgList;
        this.chatActivity = chatActivity;
        init();
    }

    private void init() {
        requestOptions = new RequestOptions().placeholder(R.mipmap.none);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Cookie", "JSESSIONID=" + SharedPreferencesUtils.getInstance(chatActivity.getContext()).getString("token", "")).build();
                return chain.proceed(request);
            }
        }).build();
        picasso = new Picasso.Builder(chatActivity.getContext()).downloader(new OkHttp3Downloader(CommonRequest.getInstance().getOkHttpClient())).build();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM_TYPE_SEND_MSG:
                viewHolder = new ChatSendHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_send_msg, parent, false));
                break;
            case ITEM_TYPE_RECEIVE_MSG:
                viewHolder = new ChatReceiveHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_receive_msg, parent, false));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
//        ContactBeanDao contactBeanDao = DbManager.getInstance().getDaoInstant(chatActivity.getContext().getApplicationContext()).getContactBeanDao();
//        String userName = contactBeanDao.queryBuilder().where(ContactBeanDao.Properties.UserId.eq(historyMsgList.get(position).getCustId())).list().get(0).getRealName();
        String userName = "a";
        switch (holder.getItemViewType()) {
            case ITEM_TYPE_RECEIVE_MSG:
                ChatReceiveHolder chatReceiveHolder = (ChatReceiveHolder) holder;
                chatReceiveHolder.textViewName.setText(userName);
                chatReceiveHolder.imageViewChatIcon.setOnLongClickListener(v -> {
                    chatActivity.onMsgIconLongClick(position);
                    return true;
                });
                String msgTypeReceive = historyMsgList.get(position).getMsgType();
                switch (msgTypeReceive) {
                    case MSG_TYPE_TEXT + "":
                        String content = historyMsgList.get(position).getContent();
                        try {
                            content = URLDecoder.decode(content, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        chatReceiveHolder.textViewContent.setText(content);
                        chatReceiveHolder.textViewContent.setVisibility(View.VISIBLE);
                        chatReceiveHolder.imageViewChatContent.setVisibility(View.GONE);
                        chatReceiveHolder.imageViewAudioFlash.setVisibility(View.GONE);
                        chatReceiveHolder.textViewAudioLength.setVisibility(View.GONE);
                        chatReceiveHolder.imageViewVideoPause.setVisibility(View.GONE);
                        break;
                    case MSG_TYPE_AUDIO + "":
                        chatReceiveHolder.textViewContent.setVisibility(View.VISIBLE);
                        chatReceiveHolder.imageViewChatContent.setVisibility(View.GONE);
                        chatReceiveHolder.imageViewAudioFlash.setVisibility(View.VISIBLE);
                        chatReceiveHolder.imageViewVideoPause.setVisibility(View.GONE);
                        if (!TextUtils.isEmpty(historyMsgList.get(position).getContentLength())) {
                            chatReceiveHolder.textViewAudioLength.setVisibility(View.VISIBLE);
                            chatReceiveHolder.textViewAudioLength.setText(historyMsgList.get(position).getContentLength() + "″");
                        } else {
                            chatReceiveHolder.textViewAudioLength.setVisibility(View.GONE);
                            chatReceiveHolder.textViewAudioLength.setText("");
                        }

                        chatReceiveHolder.textViewContent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //下载音频文件,暂时不用，后期引入数据库放开
//                                CommonRequest commonRequest=new CommonRequest();
//                                commonRequest.downloadFile(historyMessageItems.get(position).getURL(), new CommonRequest.OnRequestCompletedListener() {
//                                    @Override
//                                    public void success(Object o) {
//
//                                    }
//
//                                    @Override
//                                    public void failed(Object o) {
//                                        ((Exception) o).printStackTrace();
//                                        Toast.makeText(chatActivity.getContext(), ((Exception) o).getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//                                });
                                playAudio(Constant.IP_IM + historyMsgList.get(position).getUrl(), chatReceiveHolder.imageViewAudioFlash, R.drawable.receive_audio_play_flash);
                            }
                        });
                        break;
                    case MSG_TYPE_IMAGE + "":
                        chatReceiveHolder.imageViewChatContent.setVisibility(View.VISIBLE);
                        chatReceiveHolder.textViewContent.setVisibility(View.GONE);
                        chatReceiveHolder.imageViewAudioFlash.setVisibility(View.GONE);
                        chatReceiveHolder.textViewAudioLength.setVisibility(View.GONE);
                        chatReceiveHolder.imageViewVideoPause.setVisibility(View.GONE);
                        Bitmap bitmap = BitmapFactory.decodeFile(historyMsgList.get(position).getFileUrl());
                        chatReceiveHolder.imageViewChatContent.setImageBitmap(bitmap);
//                        picasso.load(Constant.IP_IM + historyMsgList.get(position).getUrl()).placeholder(R.mipmap.placeholder).transform(new CommonTransformation()).into(chatReceiveHolder.imageViewChatContent);
                        chatReceiveHolder.imageViewChatContent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(chatActivity.getContext(), MsgPicActivity.class);
                                intent.putExtra("url", historyMsgList.get(position).getUrl());
                                chatActivity.getContext().startActivity(intent);
                            }
                        });
                        break;
                    case MSG_TYPE_VIDEO + "":
                        chatReceiveHolder.imageViewChatContent.setVisibility(View.VISIBLE);
                        chatReceiveHolder.textViewContent.setVisibility(View.GONE);
                        chatReceiveHolder.imageViewAudioFlash.setVisibility(View.GONE);
                        chatReceiveHolder.textViewAudioLength.setVisibility(View.GONE);
                        chatReceiveHolder.imageViewVideoPause.setVisibility(View.VISIBLE);
                        if (videoFrameCache.get(position) != null) {
                            chatReceiveHolder.imageViewChatContent.setImageBitmap(videoFrameCache.get(position));
                        } else {
                            inflateVideoFrame(position, chatReceiveHolder.imageViewChatContent, Constant.IP_IM + historyMsgList.get(position).getUrl());
                        }
                        chatReceiveHolder.imageViewChatContent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(chatActivity.getContext(), VideoPlayActivity.class);
                                intent.putExtra("playUrl", Constant.IP_IM + historyMsgList.get(position).getUrl());
                                chatActivity.getContext().startActivity(intent);
                            }
                        });
                        break;
                }
                setMsgTime(chatReceiveHolder.textViewTime, position);
                break;
            case ITEM_TYPE_SEND_MSG:
                ChatSendHolder chatSendHolder = (ChatSendHolder) holder;
                chatSendHolder.textViewName.setText(userName);
                String msgTypeSend = historyMsgList.get(position).getMsgType();
                switch (msgTypeSend) {
                    case MSG_TYPE_TEXT + "":
                        chatSendHolder.textViewContent.setVisibility(View.VISIBLE);
                        chatSendHolder.imageViewChatContent.setVisibility(View.GONE);
                        chatSendHolder.imageViewAudioFlash.setVisibility(View.GONE);
                        chatSendHolder.imageViewVideoPause.setVisibility(View.GONE);
                        chatSendHolder.imageViewVideoPause.setVisibility(View.GONE);
                        String content = historyMsgList.get(position).getContent();
                        try {
                            content = URLDecoder.decode(content, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        chatSendHolder.textViewContent.setText(content);
                        break;
                    case MSG_TYPE_AUDIO + "":
                        chatSendHolder.textViewContent.setVisibility(View.VISIBLE);
                        chatSendHolder.imageViewChatContent.setVisibility(View.GONE);
                        chatSendHolder.imageViewAudioFlash.setVisibility(View.VISIBLE);
                        chatSendHolder.imageViewVideoPause.setVisibility(View.GONE);
                        if (!TextUtils.isEmpty(historyMsgList.get(position).getContentLength())) {
                            chatSendHolder.textViewAudioLength.setVisibility(View.VISIBLE);
                            chatSendHolder.textViewAudioLength.setText(historyMsgList.get(position).getContentLength() + "″");
                        } else {
                            chatSendHolder.textViewAudioLength.setVisibility(View.GONE);
                            chatSendHolder.textViewAudioLength.setText("");
                        }
                        chatSendHolder.imageViewVideoPause.setVisibility(View.GONE);
                        chatSendHolder.textViewContent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                playAudio(Constant.IP_IM + historyMsgList.get(position).getUrl(), chatSendHolder.imageViewAudioFlash, R.drawable.send_audio_play_flash);
                            }
                        });
                        break;
                    case MSG_TYPE_IMAGE + "":
                        chatSendHolder.imageViewChatContent.setVisibility(View.VISIBLE);
                        chatSendHolder.textViewContent.setVisibility(View.GONE);
                        chatSendHolder.imageViewAudioFlash.setVisibility(View.GONE);
                        chatSendHolder.imageViewVideoPause.setVisibility(View.GONE);
                        chatSendHolder.imageViewVideoPause.setVisibility(View.GONE);
//                    GlideUrl glideUrl = new GlideUrl(Constant.reqAddr + historyMessageItems.get(position).getURL(),
//                            new LazyHeaders.Builder().addHeader("Cookie", "JSESSIONID=" + SharedPreferencesUtils.getInstance().getString("token", "")).build());
//                    Glide.with(activity).asBitmap().load(glideUrl).apply(requestOptions).into(chatSendHolder.imageViewChatContent);
//                        picasso.load(Constant.IP_IM + historyMsgList.get(position).getUrl()).placeholder(R.mipmap.placeholder).transform(new CommonTransformation()).into(chatSendHolder.imageViewChatContent);
                        Bitmap bitmap = BitmapFactory.decodeFile(historyMsgList.get(position).getFileUrl());
                        chatSendHolder.imageViewChatContent.setImageBitmap(bitmap);
                        chatSendHolder.imageViewChatContent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(chatActivity.getContext(), MsgPicActivity.class);
                                intent.putExtra("url", historyMsgList.get(position).getFileUrl());
                                chatActivity.getContext().startActivity(intent);
                            }
                        });
                        break;
                    case MSG_TYPE_VIDEO + "":
                        chatSendHolder.imageViewChatContent.setVisibility(View.VISIBLE);
                        chatSendHolder.textViewContent.setVisibility(View.GONE);
                        chatSendHolder.imageViewAudioFlash.setVisibility(View.GONE);
                        chatSendHolder.textViewAudioLength.setVisibility(View.GONE);
                        chatSendHolder.imageViewVideoPause.setVisibility(View.VISIBLE);
                        if (videoFrameCache.get(position) != null) {
                            chatSendHolder.imageViewChatContent.setImageBitmap(videoFrameCache.get(position));
                        } else {
                            inflateVideoFrame(position, chatSendHolder.imageViewChatContent, Constant.IP_IM + historyMsgList.get(position).getUrl());
                        }
                        chatSendHolder.imageViewChatContent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(chatActivity.getContext(), VideoPlayActivity.class);
                                intent.putExtra("playUrl", Constant.IP_IM + historyMsgList.get(position).getUrl());
                                chatActivity.getContext().startActivity(intent);
                            }
                        });
                        break;
                }
                setMsgTime(chatSendHolder.textViewTime, position);
                break;
        }
    }

    private void setMsgTime(TextView textViewTime, int position) {
        String createTime1 = historyMsgList.get(position).getCreateTime();
        textViewTime.setText(createTime1);
        if (position == 0) {
            textViewTime.setVisibility(View.VISIBLE);
        } else {
            String lastTime = historyMsgList.get(position - 1).getCreateTime();
            if (compareTime(createTime1, lastTime)) {
                textViewTime.setVisibility(View.VISIBLE);
            } else {
                textViewTime.setVisibility(View.GONE);
            }
        }
    }

    private void inflateVideoFrame(int position, ImageView imageView, String url) {
//        Toast.makeText(chatActivity.getContext(),url,Toast.LENGTH_LONG).show();
        GetFrameHandler handler = new GetFrameHandler(imageView);
        Thread thread = new Thread() {
            @Override
            public void run() {
                MediaMetadataRetriever media = new MediaMetadataRetriever();
                try {
                    HttpsURLConnection.setDefaultSSLSocketFactory(VideoPlayActivity.SSlUtiles.createSSLSocketFactory());
                    HttpsURLConnection.setDefaultHostnameVerifier(new VideoPlayActivity.SSlUtiles.TrustAllHostnameVerifier());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                media.setDataSource(url, new HashMap<String, String>());
                videoFrameCache.put(position, media.getFrameAtTime());
                handler.obtainMessage(1, media.getFrameAtTime()).sendToTarget();
                media.release();
            }
        };
        imageView.setImageResource(R.mipmap.placeholder);
        thread.start();
    }

    @SuppressLint("HandlerLeak")
    class GetFrameHandler extends Handler {
        ImageView imageView;

        public GetFrameHandler(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                imageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    }

    int i=0,j=0;

    private void playAudio(String url, ImageView imageView, int animResource) {
        MediaPlayer mediaPlayer = new MediaPlayer();
//        File file=new File(url);
        try {
//            FileInputStream fileInputStream=new FileInputStream(file);
//            mediaPlayer.setDataSource(fileInputStream.getFD()); // 设置数据源
            try {
                HttpsURLConnection.setDefaultSSLSocketFactory(VideoPlayActivity.SSlUtiles.createSSLSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new VideoPlayActivity.SSlUtiles.TrustAllHostnameVerifier());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mediaPlayer.setDataSource(url); // 设置数据源为网络文件
            mediaPlayer.prepareAsync(); // 准备(InputStream), 异步
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    
                    // 准备完成后, 开始播放音频文件
                    mp.start();
                    audioFlashStart(imageView, animResource);
                    i++;
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    
                    audioFlashStop(imageView, animResource);
                    mp.stop();
                    mp.release();
                    j++;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void audioFlashStart(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }

    private void audioFlashStop(ImageView imageView, int resource) {
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.stop();
        imageView.setImageResource(resource);
    }

//    private void loadImage(String url,ImageView view){
//        picasso.load(url).placeholder(R.mipmap.placeholder).resize()
//    }

    @Override
    public int getItemCount() {
        return historyMsgList.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (historyMsgList.get(position).getCustId().equals(DbManager.getInstance().getDaoInstant(chatActivity.getContext().getApplicationContext()).getUserInfoBeanDao().queryBuilder().list().get(0).getUserId() + "")) {
            return ITEM_TYPE_SEND_MSG;
//        } else {
//            return ITEM_TYPE_RECEIVE_MSG;
//        }
    }

    class ChatReceiveHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewTime)
        TextView textViewTime;
        @BindView(R.id.imageViewChatIcon)
        ImageView imageViewChatIcon;
        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.textViewContent)
        TextView textViewContent;
        @BindView(R.id.imageViewChatContent)
        ImageView imageViewChatContent;
        @BindView(R.id.imageViewAudioFlash)
        ImageView imageViewAudioFlash;
        @BindView(R.id.textViewAudioLength)
        TextView textViewAudioLength;
        @BindView(R.id.imageViewVideoPause)
        ImageView imageViewVideoPause;

        ChatReceiveHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ChatSendHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewTime)
        TextView textViewTime;
        @BindView(R.id.imageViewChatIcon)
        ImageView imageViewChatIcon;
        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.textViewContent)
        TextView textViewContent;
        @BindView(R.id.imageViewChatContent)
        ImageView imageViewChatContent;
        @BindView(R.id.imageViewAudioFlash)
        ImageView imageViewAudioFlash;
        @BindView(R.id.textViewAudioLength)
        TextView textViewAudioLength;
        @BindView(R.id.imageViewVideoPause)
        ImageView imageViewVideoPause;

        ChatSendHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private boolean compareTime(String createTime, String lastTime) {
        String[] createSplit = createTime.split(" ");
        int createHour = cutTime(createSplit[1], 0);
        int createMin = cutTime(createSplit[1], 1);
        String[] lastSplit = lastTime.split(" ");
        int lastHour = cutTime(lastSplit[1], 0);
        int lastMin = cutTime(lastSplit[1], 1);
        if (lastHour == createHour) {
            return (createMin - lastMin) > 5;
        } else if (createHour - lastHour == 1) {
            return (lastMin + (60 - createMin)) > 5;
        } else {
            return true;
        }
    }

    private int cutTime(String time, int index) {
        String[] strings = time.split(":");
        return Integer.valueOf(strings[index]);
    }

    class CommonTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int targetWidth = 600;

            int targetHeight = 800;

            if (source.getWidth() == 0 || source.getHeight() == 0) {
                return source;
            }

            if (source.getWidth() > source.getHeight()) {//横向长图
                if (source.getHeight() < targetHeight && source.getWidth() <= targetWidth) {
                    return source;
                } else {
                    //如果图片大小大于等于设置的高度，则按照设置的高度比例来缩放
                    double aspectRatio = (double) source.getWidth() / (double) source.getHeight();
                    int width = (int) (targetHeight * aspectRatio);
                    if (width > targetWidth) { //对横向长图的宽度 进行二次限制
                        width = targetWidth;
                        targetHeight = (int) (width / aspectRatio);// 根据二次限制的宽度，计算最终高度
                    }
                    if (width != 0 && targetHeight != 0) {
                        Bitmap result = Bitmap.createScaledBitmap(source, width, targetHeight, false);
                        if (result != source) {
                            // Same bitmap is returned if sizes are the same
                            source.recycle();
                        }
                        return result;
                    } else {
                        return source;
                    }
                }
            } else {//竖向长图
                //如果图片小于设置的宽度，则返回原图
                if (source.getWidth() < targetWidth && source.getHeight() <= targetHeight) {
                    return source;
                } else {
                    //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
                    double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                    int height = (int) (targetWidth * aspectRatio);
                    if (height > targetHeight) {//对横向长图的高度进行二次限制
                        height = targetHeight;
                        targetWidth = (int) (height / aspectRatio);//根据二次限制的高度，计算最终宽度
                    }
                    if (height != 0 && targetWidth != 0) {
                        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, height, false);
                        if (result != source) {
                            // Same bitmap is returned if sizes are the same
                            source.recycle();
                        }
                        return result;
                    } else {
                        return source;
                    }
                }
            }
        }

        @Override
        public String key() {
            return "desiredWidth" + " desiredHeight";
        }
    }
}
