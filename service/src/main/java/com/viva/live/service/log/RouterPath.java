package com.viva.live.service.log;

/**
 * author:mingming.liu
 * description:该类只存储路由路径等常量
 * warn:
 * time: 2019-09-06
 */
public class RouterPath {

    //组的概念只能适用于单个module中包含多个服务，不能在多个module中使用一个组
    public static final String GROUP_SERVICE = "/service";//功能组组件group
    public static final String GROUP_BUSINESS = "/business";//业务组件group
    public static final String GROUP_SERVICE_LOADER = "/serviceLoader";//组件加载group

    //在application启动的时候需要去初始化的组件需要注册在这里，注册在这里的组件需要两个条件
    // 属于GROUP_SERVICE_LOADER组
    // 实现ServiceLoader接口,而不是直接实现业务接口
    public static final String[] PRE_LOAD_SERVICE = new String[]{
            RouterPath.SERVICE_LOGGER,
            RouterPath.SERVICE_SHARED_PREFERENCES,
    };

    //日志服务
    public static final String SERVICE_LOGGER = "/logger" + GROUP_SERVICE_LOADER;
    public static final String SERVICE_SHARED_PREFERENCES = "/sharedPreferences" + GROUP_SERVICE_LOADER;

    public static final String COMPONENT_LIVE = "/live" + GROUP_SERVICE_LOADER;
    public static final String COMPONENT_LIVE_1V1 = "/live1v1" + GROUP_BUSINESS;
}
