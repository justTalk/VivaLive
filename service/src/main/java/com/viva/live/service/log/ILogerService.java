package com.viva.live.service.log;

import android.content.Context;

/**
 * author:mingming.liu
 * description:日志接口定义
 * warn:
 * time: 2019-09-06
 */
public interface ILogerService{

    void init(Context context, String filePath);
    void enable(boolean enable);
    void i(String module, String tag, String message);
    void d(String module, String tag, String message);
    void e(String module, String tag, String message);
    void e(String module, String tag, String format, Object... args);
    void e(String module, String msg, Throwable throwable);
    void release();
}
