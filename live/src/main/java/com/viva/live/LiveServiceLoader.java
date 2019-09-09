package com.viva.live;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.viva.live.service.component.Component;
import com.viva.live.service.component.ServiceLoader;
import com.viva.live.service.log.RouterPath;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-07
 */
@Route(path = RouterPath.COMPONENT_LIVE)
public class LiveServiceLoader extends ServiceLoader<Component> implements IProvider {

    @Override
    public void init(Context context) {
        Log.d("ServiceLoader", "LiveServiceLoader init");
    }

    @Override
    public Component load(String name) {
        return null;
    }

    @Override
    public boolean recycle(String name) {
        return false;
    }
}
