package com.viva.live;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.viva.live.base.BaseApplication;
import com.viva.live.service.component.ServiceLoader;
import com.viva.live.service.log.ILogerService;
import com.viva.live.service.log.RouterPath;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-06
 */
public class LiveApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 ARouter
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        ServiceLoader<ILogerService> logImp = (ServiceLoader<ILogerService>) ARouter.getInstance().build(RouterPath.SERVICE_LOGGER, RouterPath.GROUP_SERVICE).navigation();
        if (logImp != null) {
            logImp.load(null).init(this, null);
        }
    }
}
