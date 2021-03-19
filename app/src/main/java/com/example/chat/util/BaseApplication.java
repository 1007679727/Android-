/**
 * Copyright (C) 2013 AVIT, All rights reserved
 *
 * @fileName: com.avit.ott.common.base.BaseApplication.java
 *
 * @author: daishulun@avit.com.cn
 *
 * Modification History:
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2013-8-22     daishulun      v1.0.0        create
 *
 */
package com.example.chat.util;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.chat.data.DbManager;
import com.example.exception.CrashHandler;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mmkv.MMKV;


/**
 * @Description:
 */
public class BaseApplication extends Application {

	protected String TAG = "BaseApplication";
	private static Context APPLICATION;
	@Override
	public void onCreate() {
		TAG = this.getClass().getSimpleName();
		APPLICATION=getApplicationContext();
		
		super.onCreate();
		DbManager.getInstance().init(APPLICATION);
		MMKV.initialize(APPLICATION);
		Fresco.initialize(APPLICATION);
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
	}

	@Override
	public void onTerminate() {
		
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
		super.onTerminate();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		
		super.onLowMemory();
	}

	public static Context getAppInstance() {
		return APPLICATION;
	}
}
