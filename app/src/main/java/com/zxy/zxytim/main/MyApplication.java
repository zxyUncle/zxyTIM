package com.zxy.zxytim.main;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;

public class MyApplication extends Application {
    public static final int SDKAPPID = 1400293275; // 您的 SDKAppID

    @Override
    public void onCreate() {
        super.onCreate();
        //ARouter
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

        // 配置 Config，请按需配置
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new TIMSdkConfig(SDKAPPID));
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());

        /**
         * TUIKit 的初始化函数
         *
         * @param context  应用的上下文，一般为对应应用的 ApplicationContext
         * @param sdkAppID 您在腾讯云注册应用时分配的 SDKAppID
         * @param configs  TUIKit 的相关配置项，一般使用默认即可，需特殊配置参考 API 文档
         */
        TUIKit.init(this, SDKAPPID, configs);
    }
}
