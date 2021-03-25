package com.example.chat;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;


import com.example.chat.request.CommonRequest;
import com.example.chat.util.Constant;
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.github.chrisbanes.photoview.PhotoView;


import java.io.File;
import java.io.FileOutputStream;

public class MsgPicActivity extends BaseActivity {

    private PhotoView msgPicIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_pic);
        msgPicIV =  findViewById(R.id.msg_pic_IV);
        init();
    }

    private void init() {
//        String url = new StringBuilder(Constant.IP_IM).append(getIntent().getStringExtra("url")).toString();
        String url = getIntent().getStringExtra("url");
//        String imageName="ORIGIN_"+getIntent().getStringExtra("imageName");
//        File image = new File(new File(Environment.getExternalStorageDirectory() + "/fhzz/shoot"), imageName);
//        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(CommonRequest.getInstance().getOkHttpClient())).build();
//        if (image.exists()) {
//            picasso.load(image).into(msgPicIV);
//            return;
//        }

//        picasso.load(url).into(msgPicIV, new Callback() {
//            @Override
//            public void onSuccess() {
//                Bitmap bitmap = ((BitmapDrawable) msgPicIV.getDrawable()).getBitmap();
//
//                File dir = new File(Environment.getExternalStorageDirectory() + "/fhzz/shoot");
//                if (!dir.exists()) {
//                    dir.mkdir();
//                }
//                File file = new File(dir, imageName);
//                FileOutputStream ostream = null;
//                try {
//                    ostream = new FileOutputStream(file);
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
//                    ostream.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
        msgPicIV.setImageBitmap(BitmapFactory.decodeFile(url));

        msgPicIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
