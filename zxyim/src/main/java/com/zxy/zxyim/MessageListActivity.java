package com.zxy.zxyim;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;

/**
 * Created by zxy on 2020/1/11 0011 15:53
 * ******************************************
 * * 消息列表
 * ******************************************
 */
@Route(path = ARoutPath.MSGLIST)
public class MessageListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        StatusUtils.setStatusIconCollor(this, true);
        initView();
    }

    private void initView() {
        // 从布局文件中获取会话列表面板
        ConversationLayout conversationLayout = findViewById(R.id.conversation_layout);
        // 初始化聊天面板
        conversationLayout.initDefault();
    }
}
