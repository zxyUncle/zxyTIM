package com.zxy.zxyim;

import android.util.Log;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;

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
     * @param identifier 为用户名
     * @param userSig    为用户登录凭证
     */
    public void login(String identifier, String userSig) {
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

}
