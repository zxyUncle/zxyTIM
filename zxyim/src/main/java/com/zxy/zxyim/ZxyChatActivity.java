package com.zxy.zxyim;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.input.InputLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;

@Route(path = ARoutPath.CHAt)
public class ZxyChatActivity extends AppCompatActivity {
    private ChatLayout chatLayout;
    //id
    @Autowired(name = "id")
    String id = "";

    //名字
    @Autowired(name = "name")
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxy_chat);
        ARouter.getInstance().inject(this);
        chatLayout = findViewById(R.id.chat_layout);
        setStatusIconCollor(this,true);
        initChat();
        initConfig();
    }

    /**
     * 设置状态栏图标的颜色，true=黑色，false=白色
     *
     * @param setDark
     */
    public static void setStatusIconCollor(Activity activity, boolean setDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = activity.getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (setDark) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

    private void initChat() {
        chatLayout.initDefault();
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(TIMConversationType.C2C);
        chatInfo.setId(id);
        chatInfo.setChatName(name);
        chatLayout.setChatInfo(chatInfo);
    }

    private void initConfig() {
        //zxy 标题区
        TitleBarLayout titleBar = chatLayout.getTitleBar();
        titleBar.setRightIcon(R.drawable.lucency);

        //zxy 内容消息区
        MessageLayout messageLayout = chatLayout.getMessageLayout();
        //设置聊天背景
//        messageLayout.setBackground(new ColorDrawable(0xB0AA6D96));
        // 设置默认头像，默认与朋友与自己的头像相同
        messageLayout.setAvatar(R.drawable.ic_launcher_background);
        // 设置头像圆角，不设置则默认不做圆角处理
        messageLayout.setAvatarRadius(50);
        // 设置头像大小
        messageLayout.setAvatarSize(new int[]{48, 48});

        messageLayout.setRightNameVisibility(View.VISIBLE);


        // 聊天界面设置头像和昵称
//        TIMUserProfile profile = TIMFriendshipManager.getInstance().queryUserProfile(msg.getFromUser());
//        if (profile == null) {
//            usernameText.setText(msg.getFromUser());
//        } else {
//            usernameText.setText(!TextUtils.isEmpty(profile.getNickName()) ? profile.getNickName() : msg.getFromUser());
//            if (!TextUtils.isEmpty(profile.getFaceUrl()) && !msg.isSelf()) {
//                List<String> urllist = new ArrayList<>();
//                urllist.add(profile.getFaceUrl());
//                leftUserIcon.setIconUrls(urllist);
//                urllist.clear();
//            }
//        }
//        TIMUserProfile selfInfo = TIMFriendshipManager.getInstance().queryUserProfile(TIMManager.getInstance().getLoginUser());
//        if (profile != null && msg.isSelf()) {
//            if (!TextUtils.isEmpty(selfInfo.getFaceUrl())) {
//                List<String> urllist = new ArrayList<>();
//                urllist.add(profile.getFaceUrl());
//                rightUserIcon.setIconUrls(urllist);
//                urllist.clear();
//            }
//        }

        // zxy 输入区域
        // 从 ChatLayout 里获取 InputLayout
        InputLayout inputLayout = chatLayout.getInputLayout();
        // 隐藏拍照并发送
        inputLayout.disableCaptureAction(false);
        // 隐藏发送文件
        inputLayout.disableSendFileAction(false);
        // 隐藏发送图片
        inputLayout.disableSendPhotoAction(false);
        // 隐藏摄像并发送
        inputLayout.disableVideoRecordAction(false);

    }

}
