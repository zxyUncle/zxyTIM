package com.zxy.zxytim.main;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.zxy.zxyim.ARoutPath;
import com.zxy.zxyim.MessageListActivity;
import com.zxy.zxyim.ZxyTimUtls;
import com.zxy.zxytim.R;

import java.util.List;


@Route(path = ARoutPath.MAIN)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ZxyTimUtls.getInstance().login("207026", "eJyrVgrxCdZLrSjILEpVsjIzMLEwMNABi5WlFilZKRnpGShB*MUp2YkFBZkpSlaGJgYGRpbGRuamEJnMlNS8ksy0TIgGA3MDIzOYnsx0oJCfj5NToWFAbqalpXtyYVFSeIGlp3lJblBUoX9ZYlWmS6BpQElKuLGfs3O*LVRjSWYu0DmGpuYWlhaGBhYWtQDLVzBv");
//        ZxyTimUtls.getInstance().login("2233039", "eJyrVgrxCdZLrSjILEpVsjIzMLEwMNABi5WlFilZKRnpGShB*MUp2YkFBZkpSlaGJgYGRpbGRuamEJnMlNS8ksy0TIgGA3MDIzOYnsx0oFB6WUBeQFFKfmBksHmAU7FveVBGZnBUuleutmmJS3qeZbqnk6*jtmWESX6yLVRjSWYu0DmGpuYWFuZm5qbmtQDb8jBr");
        findViewById(R.id.singnchat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String mIconUrl = "https://goss.veer.com/creative/vcg/veer/800water/veer-134671947.jpg";
//                ARouter.getInstance().build(ARoutPath.CHAt)
//                        .withString("id", "201977")
////                        .withString("img", mIconUrl)
//                        .withString("name", "带亚洲")
//                        .navigation();

                ARouter.getInstance().build(ARoutPath.MSGLIST)
                        .navigation();
            }
        });
       ZxyTimUtls.getInstance().setOnMessageListener(new ZxyTimUtls.addMessageListener() {
           @Override
           public boolean onNewMessages(List<TIMMessage> msgs) {
               Toast.makeText(MainActivity.this, "新消息来了", Toast.LENGTH_SHORT).show();
               return false;
           }
       });
    }


}
