package com.viva.live.logger;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.viva.live.service.component.ServiceLoader;
import com.viva.live.service.log.ILogerService;
import com.viva.live.service.log.RouterPath;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-06
 */
@Route(path = RouterPath.SERVICE_LOGGER, group = RouterPath.GROUP_SERVICE)
public class LoggerServiceProvider extends ServiceLoader<ILogerService> implements IProvider {

    ILogerService serviceImpl;

    @Override
    public void init(Context context) {
    }

    @Override
    public ILogerService load(String name) {
        if (serviceImpl == null) {
            serviceImpl = new LoggerServiceImpl();
        }
        return serviceImpl;
    }

    @Override
    public boolean recycle(String name) {
        serviceImpl.release();
        serviceImpl = null;
        return true;
    }
}
