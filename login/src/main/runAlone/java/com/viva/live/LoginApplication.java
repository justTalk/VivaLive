package com.viva.live;

import com.alibaba.android.arouter.launcher.ARouter;
import com.viva.live.base.BaseApplication;
import com.viva.live.login.BuildConfig;
import com.viva.live.service.log.RouterPath;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-06
 */
public class LoginApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 ARouter
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        initServiceLoader();
    }

    public void initServiceLoader(){
        for (String router:
                RouterPath.PRE_LOAD_SERVICE) {
            ARouter.getInstance().build(router).navigation();
        }
    }
}
