package com.zxy.zxyim;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;

import java.util.List;

/**
 * Created by zxy on 2020/1/11 0011 15:53
 * ******************************************
 * * 消息列表
 * ******************************************
 */
@Route(path = ARoutPath.MSGLIST)
public class MessageListActivity extends AppCompatActivity {
    private ConversationLayout mConversationLayout;
    private ConversationListLayout conversationListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        StatusUtils.setStatusIconCollor(this, true);
        initView();
        initConfig();



    }

    private void initView() {
        // 从布局文件中获取会话列表面板
        mConversationLayout = findViewById(R.id.conversation_layout);
        // 初始化聊天面板
        mConversationLayout.initDefault();
    }

    private void initConfig() {
        // 获取 TitleBarLayout
        com.tencent.qcloud.tim.uikit.component.TitleBarLayout titleBarLayout = mConversationLayout.findViewById(R.id.conversation_title);
        // 设置标题
        titleBarLayout.setTitle(getResources().getString(R.string.messageListName), TitleBarLayout.POSITION.MIDDLE);
        // 隐藏左侧 Group
        titleBarLayout.getLeftGroup().setVisibility(View.VISIBLE);
        titleBarLayout.getLeftIcon().setVisibility(View.VISIBLE);
        titleBarLayout.getLeftIcon().setImageResource(R.mipmap.back_black);
        // 设置右侧的菜单图标
        titleBarLayout.setRightIcon(R.drawable.lucency);

        titleBarLayout.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });

        //zxy 进入聊天
        conversationListLayout = mConversationLayout.findViewById(R.id.conversation_list);
        conversationListLayout.setOnItemClickListener(new ConversationListLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ConversationInfo messageInfo) {
                //单聊
                ARouter.getInstance().build(ARoutPath.CHAt)
                        .withString("id", messageInfo.getId())
                        .withString("name", messageInfo.getTitle())
                        .navigation();
            }
        });

    }
}
