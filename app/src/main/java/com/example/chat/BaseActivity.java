package com.example.chat;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    static LinkedList<BaseActivity> activityTask = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTask.add(this);
    }

    @Override
    protected void onDestroy() {
        activityTask.remove(this);
        super.onDestroy();
    }

    public BaseActivity getCurrentActivity() {
        return activityTask.get(activityTask.size() - 1);
    }

    public void clearTask() {
        for (BaseActivity b : activityTask) {
            b.finish();
        }
        activityTask.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter[] intentFilters = new IntentFilter[]{};

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //最主要的方法，监听到NFC信息
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    /**
     * 判断某个界面是否在前台,返回true，为显示,否则不是
     */
    public boolean isForeground(String className) {
        if (TextUtils.isEmpty(className)) {
            return false;
        }
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
