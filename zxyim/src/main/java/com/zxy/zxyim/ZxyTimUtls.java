package com.zxy.zxyim;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;

import java.util.List;

public class ZxyTimUtls {
    private String tag = "zxyIM";

    //zxy java单例模式模板，静态内部类
    private ZxyTimUtls() {
    }

    public static ZxyTimUtls getInstance() {
        return SingleInnerHolder.instance;
    }

    private static class SingleInnerHolder {
        private static ZxyTimUtls instance = new ZxyTimUtls();
    }

    /**
     * 新消息回调
     */
    public interface addMessageListener {
        public boolean onNewMessages(List<TIMMessage> msgs);
    }

    /**
     * 初始化TIM
     * @param application
     * @param SDKAPPID
     */
    public void initTIM(Application application, int SDKAPPID) {
        //ARouter
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(application);

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
        TIMSdkConfig config = new TIMSdkConfig(SDKAPPID)
                .enableLogPrint(true)
                .setLogLevel(TIMLogLevel.DEBUG)
                .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/justfortest/");
        TIMManager.getInstance().init(application, config);
        TUIKit.init(application, SDKAPPID, configs);
    }

    /**
     * @param identifier 为用户名
     * @param userSig    为用户登录凭证
     */
    public void login(String identifier, String userSig) {
        String loginUser = TIMManager.getInstance().getLoginUser();
        if (loginUser != null && loginUser.equals(identifier)) {
            Log.e(tag, "已登录，不需要重复登录");
        } else {
            // identifier 为用户名，userSig 为用户登录凭证
            TIMManager.getInstance().login(identifier, userSig, new TIMCallBack() {
                @Override
                public void onError(int code, String desc) {
                    //错误码 code 和错误描述 desc，可用于定位请求失败原因
                    //错误码 code 列表请参见错误码表
                    Log.e(tag, "login failed. code: " + code + " errmsg: " + desc);
                    Log.e(tag, "失败");
                }

                @Override
                public void onSuccess() {
                    Log.d(tag, "login succ");
                    Log.e(tag, "登录成功");
                }

            });
        }
    }

    /**
     * 登出
     */
    public void loginOut() {
        //登出
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Log.d(tag, "logout failed. code: " + code + " errmsg: " + desc);
            }

            @Override
            public void onSuccess() {
                //登出成功
            }
        });
    }


    /**
     * 获取所有会话未读数
     */
    public long getUnreadMessageNum() {
        long sum = 0;
        //获取会话列表
        List<TIMConversation> conversationLists = TIMManager.getInstance().getConversationList();
        for (TIMConversation conversationList : conversationLists
        ) {
            long unreadMessageNum = conversationList.getUnreadMessageNum();
            sum += unreadMessageNum;
        }
        return sum;
    }

    /**
     * 设置消息监听器，收到新消息时，通过此监听器回调
     *
     * @param addMessageListener
     */
    public void setOnMessageListener(final addMessageListener addMessageListener) {
        //设置消息监听器，收到新消息时，通过此监听器回调
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {//消息监听器
            @Override
            public boolean onNewMessages(List<TIMMessage> msgs) {//收到新消息
                addMessageListener.onNewMessages(msgs);
                //消息的内容解析请参考消息收发文档中的消息解析说明
                return false; //返回true将终止回调链，不再调用下一个新消息监听器
            }
        });
    }

    /**
     * 更新本地用户数据
     *
     * @param listid
     */
    public void updataLocationData(List<String> listid) {
        TIMFriendshipManager.getInstance().getUsersProfile(listid, true, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess(List<TIMUserProfile> timUserProfiles) {

            }
        });
    }


}
