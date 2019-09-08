package com.viva.live.login;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.viva.live.R;
import com.viva.live.base.component.BaseFragmentActivity;
import com.viva.live.service.log.RouterPath;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-07
 */
public class LoginActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        findViewById(R.id.login_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterPath.COMPONENT_LIVE_1V1, RouterPath.GROUP_BUSINESS).navigation();
            }
        });
    }
}
