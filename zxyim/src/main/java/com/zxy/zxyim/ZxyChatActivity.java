package com.zxy.zxyim;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.input.InputLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
@Route(path = ARoutPath.CHAt)
public class ZxyChatActivity extends AppCompatActivity {
    private ChatLayout chatLayout;
    //id
    @Autowired(name = "id")
    String id = "";

    //名字
    @Autowired(name = "name")
    String name = "";

//    //头像
//    @Autowired(name = "img")
//    String mIconUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxy_chat);
        ARouter.getInstance().inject(this);
        chatLayout = findViewById(R.id.chat_layout);
        StatusUtils.setStatusIconCollor(this, true);
        ZxyChatActivityPermissionsDispatcher.onNeedPermissionWithPermissionCheck(this);
        initChat();
        initConfig();
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
        titleBar.setLeftIcon(R.mipmap.back_black);

        //zxy 内容消息区
        MessageLayout messageLayout = chatLayout.getMessageLayout();
        //设置聊天背景
//        messageLayout.setBackground(new ColorDrawable(0xB0AA6D96));
        // 设置默认头像，默认与朋友与自己的头像相同
        messageLayout.setAvatar(R.drawable.ic_login_logo);
        // 设置头像圆角，不设置则默认不做圆角处理
//        messageLayout.setAvatarRadius(50);
        // 设置头像大小
//        messageLayout.setAvatarSize(new int[]{48, 48});

//        messageLayout.setRightNameVisibility(View.VISIBLE);
//        messageLayout.setLeftNameVisibility(View.VISIBLE);

//        //设置头像
//        HashMap<String, Object> hashMap = new HashMap<>();
//        // 头像，mIconUrl 就是您上传头像后的 URL，可以参考 Demo 中的随机头像作为示例
//        if (!TextUtils.isEmpty(mIconUrl)) {
//            hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_FACEURL, mIconUrl);
//        }
//        TIMFriendshipManager.getInstance().modifySelfProfile(hashMap, new TIMCallBack() {
//            @Override
//            public void onError(int i, String s) {
//                Log.e("zxyIM", "modifySelfProfile err code = " + i + ", desc = " + s);
//                ToastUtil.toastShortMessage("Error code = " + i + ", desc = " + s);
//            }
//
//            @Override
//            public void onSuccess() {
//                Log.e("zxyIM", "modifySelfProfile success");
//            }
//        });

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

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO})
    void onNeedPermission() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ZxyChatActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 提示
     * @param request
     */
    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO})
    void onShowPermission(final PermissionRequest request) {
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO})
    void onDeniedPermission() {
        Toast.makeText(this, "onDeniedPermission", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO})
    void onNeverAskPermission() {
    }
}
