package com.jinlanmeng.mytencentlivebroadcast;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;


import com.jinlanmeng.mytencentlivebroadcast.presenters.InitBusinessHelper;
import com.jinlanmeng.mytencentlivebroadcast.utils.SxbLogImpl;

import java.util.List;


/**
 * 全局Application
 */
public class QavsdkApplication extends Application {

    private static QavsdkApplication app;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        context = getApplicationContext();

        if (shouldInit()) {
            SxbLogImpl.init(getApplicationContext());

            //初始化APP
            InitBusinessHelper.initApp(context);
        }


//        LeakCanary.install(this);

        //创建AVSDK 控制器类
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();

        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static Context getContext() {
        return context;
    }

    public static QavsdkApplication getInstance() {
        return app;
    }
}
