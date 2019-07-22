package com.jinlanmeng.mytencentlivebroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.jinlanmeng.mytencentlivebroadcast.model.CurLiveInfo;
import com.jinlanmeng.mytencentlivebroadcast.model.MySelfInfo;
import com.jinlanmeng.mytencentlivebroadcast.presenters.InitBusinessHelper;
import com.jinlanmeng.mytencentlivebroadcast.presenters.LoginHelper;
import com.jinlanmeng.mytencentlivebroadcast.presenters.viewinface.ProfileView;
import com.jinlanmeng.mytencentlivebroadcast.utils.Constants;
import com.jinlanmeng.mytencentlivebroadcast.utils.LogUtil;
import com.jinlanmeng.mytencentlivebroadcast.utils.SxbLog;
import com.jinlanmeng.mytencentlivebroadcast.view.LiveActivity;
import com.tencent.TIMUserProfile;
import com.tencent.ilivesdk.ILiveSDK;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProfileView {

    private static final String TAG = "MainActivity---------------";
    private LoginHelper mLoginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLive();
        findViewById(R.id.tv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.e(TAG,"开始咯");
                Intent intent = new Intent(MainActivity.this, LiveActivity.class);
                MySelfInfo.getInstance().setIdStatus(Constants.HOST);
                MySelfInfo.getInstance().setJoinRoomWay(true);
                CurLiveInfo.setTitle("最帅的直播间");
                CurLiveInfo.setHostID(MySelfInfo.getInstance().getId());
                CurLiveInfo.setRoomNum(MySelfInfo.getInstance().getMyRoomNum());
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化直播的数据
     */
    private void initLive() {

    }

    /**
     * 登录
     */
    @Override
    protected void onStart() {
        SxbLog.i(TAG, "HomeActivity onStart");
        super.onStart();
        if (ILiveSDK.getInstance().getAVContext() == null) {//retry
            InitBusinessHelper.initApp(getApplicationContext());
            SxbLog.i(TAG, "HomeActivity retry login");
            mLoginHelper = new LoginHelper(this);
            mLoginHelper.iLiveLogin(MySelfInfo.getInstance().getId(), MySelfInfo.getInstance().getUserSig());
        }
    }

    @Override
    public void updateProfileInfo(TIMUserProfile profile) {
        SxbLog.i(TAG, "updateProfileInfo");
        if (null != profile) {
            MySelfInfo.getInstance().setAvatar(profile.getFaceUrl());
            MySelfInfo.getInstance().setSign(profile.getSelfSignature());
            if (!TextUtils.isEmpty(profile.getNickName())) {
                MySelfInfo.getInstance().setNickName(profile.getNickName());
            } else {
                MySelfInfo.getInstance().setNickName(profile.getIdentifier());
            }
        }
    }

    @Override
    public void updateUserInfo(int requestCode, List<TIMUserProfile> profiles) {

    }
}
