package com.viva.live.service.component;

import android.content.Context;

import androidx.lifecycle.LifecycleObserver;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-07
 */
public interface Component {
    void init(Context context, CParam cParam);
    LifecycleObserver getLifecycleObserver();
    void release();
}
