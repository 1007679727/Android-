package com.example.exception;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;


import com.example.chat.data.DbManager;
import com.example.chat.data.bean.UserInfoBean;
import com.example.chat.request.CommonRequest;
import com.example.chat.request.RequestListener;

import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".log";

    private String fileName;
    private String serverUrl = "http://20.46.2.92:10052/sumbmit";

    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;
    private UserInfoBean userInfoBean;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context;
    }


    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        userInfoBean = DbManager.getInstance().getDaoInstant(mContext).getUserInfoBeanDao().queryBuilder().list().get(0);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        printWriter.close();
        String unCaughtException = stringWriter.toString();// crash log details
        
        boolean isAvailable = sdCardIsAvailable(); // check your directory
        
        dumpExceptionToSdCard(ex); //write crash log to local device
    }

    private void dumpExceptionToSdCard(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        dumpPhoneInfo(printWriter);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            String filePath = "crash-test/";
            String time = new SimpleDateFormat("MMddHHmmss").format(new Date(System.currentTimeMillis()));
            fileName = "crash(" + userInfoBean.getRealName() + "-" + userInfoBean.getUserName() + "-" + time + ").txt";
            File file = new File(Environment.getExternalStorageDirectory() + "/fhzz", filePath + fileName);
            if (!file.exists()) {
                // file.mkdirs();
                file.getParentFile().mkdirs();
            }
            String content = sb.toString() + "\r\n";
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(content.getBytes());
            raf.close();
            uploadException(file);
//            uploadExceptionToServer(file.getAbsolutePath());
        } catch (Exception e) {
            
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) {
        pw.print("Model: ");
        pw.print(Build.MODEL);
        pw.print("APU ABI: ");
        pw.println(Build.CPU_ABI);
    }

    private void uploadException(File file) {
        CommonRequest.getInstance().upload(serverUrl, new HashMap<>(), file, "file", "uploadException", null, new RequestListener() {
            @Override
            public void onSuccess(Object o) {
            }

            @Override
            public void onError() {

            }
        });
        ActivityManager am = (ActivityManager) mContext.getSystemService(mContext.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTaskList = am.getAppTasks();
        for (ActivityManager.AppTask at : appTaskList) {
            at.finishAndRemoveTask();
        }
    }

    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            
            return sd.canWrite();
        } else {
            return false;
        }
    }
}