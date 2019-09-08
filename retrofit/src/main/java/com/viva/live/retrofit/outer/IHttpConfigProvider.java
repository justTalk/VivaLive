package com.viva.live.retrofit.outer;

import androidx.annotation.NonNull;

import okhttp3.Interceptor;

/**
 * author:mingming.liu
 * description:向retrofit基础组件输出自身业务的配置信息
 * warn:
 * time: 2019-09-06
 */
public interface IHttpConfigProvider {

    /**
    * @description:返回接口申明的class
    * @author:mingming.liu
    * @Date:15:35
    * @Param:
    */
    public @NonNull Class getHttpService();

    /**
    * @description:返回自身业务的拦截器
    * @author:mingming.liu
    * @Date:15:40
    * @Param:
    */
    public Interceptor getInterceptor();

    /**
    * @description:
    * @author:mingming.liu
    * @Date:15:46
    * @Param:
    */
    public @NonNull HttpParam getHttpParam();

    /**
    * @description:告知是否需要刷新token，如果返回true，则会重新创建Retrofit
    * @author:mingming.liu
    * @Date:16:07
    * @Param:
    */
    public boolean refreshToken();
}
