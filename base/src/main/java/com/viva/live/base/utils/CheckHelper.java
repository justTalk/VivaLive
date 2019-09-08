package com.viva.live.base.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: qyxx
 * @Package: com.viva.up.now.live.helper
 * @ClassName: CheckHelper
 * @Description: java类作用描述
 * @Author: Liu
 * @CreateDate: 2019/1/5 上午11:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/5 上午11:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CheckHelper {

    public static  boolean checkValid(List list, int index){
        return list != null && index >= 0 && index < list.size();
    }

    public static boolean isEmpty(List list){
        return list != null ? list.isEmpty() : true;
    }

    public static boolean isEmpty(Map map){
        return map != null ? map.isEmpty() : true;
    }

    /**
     * @param dialog not null
     */
    public static boolean canDismiss(Dialog dialog){
        if (dialog.isShowing()) {
            Context context = dialog.getContext();
            if (context != null && context instanceof ContextWrapper && ((ContextWrapper) context).getBaseContext() instanceof Activity) {
                return checkActivityValid((Activity) ((ContextWrapper) context).getBaseContext());
            }
            return true;
        }
        return false;
    }

    public static boolean checkActivityValid(Activity activity){
        return activity != null
                && !activity.isFinishing()
                && !activity.isDestroyed();
    }
}
