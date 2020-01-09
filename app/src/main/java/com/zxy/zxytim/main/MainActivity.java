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
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ZxyTimUtls.getInstance().login("202054","eJyrVgrxCdZLrSjILEpVsjIzMLEwMNABi5WlFilZKRnpGShB*MUp2YkFBZkpSlaGJgYGRpbGRuamEJnMlNS8ksy0TIgGAyMDUxOYnsx0oJBLib5xcbF3qGlycYlnSkB6VEa6h2WyfoqjSYWHn6FFaJSjkaVjVWCGmXagLVRjSWYu0DmGpuYWpkA3mZjUAgCqaS9j");
//        ZxyTimUtls.getInstance().login("202164","eJyrVgrxCdZLrSjILEpVsjIzMLEwMNABi5WlFilZKRnpGShB*MUp2YkFBZkpSlaGJgYGRpbGRuamEJnMlNS8ksy0TIgGAyNDMxOYnsx0oFCFk6mni6e7UZWnn0ehT7ZLWqGJr75TkLNHeoiLk1tUpW9RipdXSWR6TpCnLVRjSWYu0DmGpuYWpmaG5mZGtQCoWy-O");
        findViewById(R.id.singnchat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARoutPath.CHAt)
                        .withString("id", "202164")
                        .withString("name", "133")
                        .navigation();
            }
        });

    }


}
