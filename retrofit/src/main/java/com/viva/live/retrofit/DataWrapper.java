package com.viva.live.retrofit;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-06
 */
public abstract class DataWrapper<T> implements Serializable {

    /**
    * @description:
    * @author:mingming.liu
    * @Date:14:41
    * @Param:
    */
    protected abstract boolean isResult();

    /**
    * @description:
    * @author:mingming.liu
    * @Date:14:42
    * @Param:
    */
    protected abstract String getMessage();

    /**
    * @description:
    * @author:mingming.liu
    * @Date:14:42
    * @Param:
    */
    protected abstract int getCode();

    /**
    * @description:
    * @author:mingming.liu
    * @Date:14:43
    * @Param:
    */
    protected abstract T getData();
}
