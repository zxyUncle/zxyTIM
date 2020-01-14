package com.zxy.zxytim.main;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMGroupEventListener;
import com.tencent.imsdk.TIMGroupTipsElem;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMRefreshListener;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import com.zxy.zxyim.ZxyTimUtls;

import java.util.List;

public class MyApplication extends Application {
    public static final int SDKAPPID = 1400293275; // 您的 SDKAppID

    @Override
    public void onCreate() {
        super.onCreate();
        ZxyTimUtls.getInstance().initTIM(this,SDKAPPID);
    }
}
