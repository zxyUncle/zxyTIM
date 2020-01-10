package com.zxy.zxytim.main;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zxy.zxyim.ARoutPath;
import com.zxy.zxyim.ZxyTimUtls;
import com.zxy.zxytim.R;


@Route(path = ARoutPath.MAIN)
public class MainActivity extends AppCompatActivity   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ZxyTimUtls.getInstance().login("2233039", "eJyrVgrxCdZLrSjILEpVsjIzMLEwMNABi5WlFilZKRnpGShB*MUp2YkFBZkpSlaGJgYGRpbGRuamEJnMlNS8ksy0TIgGI2NjA2NLmKbMdKBYaKZrYbhLhXlxpUm2eViRQZazRZGnV2VBckRksmNuVVmEeUqyX3KoYXmppy1UY0lmLtA9hqbmFmbGlpZGxrUAEGsxNw__");
        findViewById(R.id.singnchat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mIconUrl = "https://goss.veer.com/creative/vcg/veer/800water/veer-134671947.jpg";
                ARouter.getInstance().build(ARoutPath.CHAt)
                        .withString("id", "201977")
                        .withString("img", mIconUrl)
                        .withString("name", "带亚洲")
                        .navigation();
            }
        });
    }


}
