package com.viva.live.base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * @ProjectName: qyxx
 * @Package: com.viva.up.now.live.helper
 * @ClassName: MetaDataHelper
 * @Description: mate data 数据相关帮助类
 * @Author: Liu
 * @CreateDate: 2018/11/28 上午10:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/11/28 上午10:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MetaDataHelper {
    public static final String META_NAME_CHANNEL = "CHANNEL";
    public static final String DEFAULTCHANNEL = "1-1";
    private MetaDataHelper(){

    }

    /**
     * @param context
     * @param metaName 配置的名字
     * @return may be null
     **/
    public static String getMetaData(Context context, String metaName){
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return DEFAULTCHANNEL;
        }
        if (appInfo.metaData.getString(metaName) == null || "".equals(appInfo.metaData.getString(metaName)))
            return DEFAULTCHANNEL;
        return appInfo.metaData.getString(metaName);
    }

    /**
     * @param context
     * @param metaName 配置的名字
     * @return may be null
     **/
    public static int getIntMetaData(Context context, String metaName){
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
        return appInfo.metaData.getInt(metaName);
    }
}
