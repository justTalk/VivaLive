package com.viva.live.service.component;

/**
 * author:mingming.liu
 * description:组件加载器，具备加载和卸载单个组件的能力
 * warn:
 * time: 2019-09-07
 */
public abstract class ServiceLoader<T> {

    /**
    * @description:加载单个组件
    * @author:mingming.liu
    * @Date:11:18
    * @Param:组件名字
    */
    public abstract T load(String name);

    /**
    * @description:卸载单个组件
    * @author:mingming.liu
    * @Date:11:18
    * @Param:组件名字
    */
    public abstract boolean recycle(String name);
}
