package com.viva.live.service.sp;

import android.os.Parcelable;

/**
 * author:mingming.liu
 * description:sharepreferences的服务api
 * warn:
 * time: 2019-09-09
 */
public interface ISharedPreferencesService {

    boolean putString(String fileName, String key, String value);
    boolean putBoolean(String fileName, String key, boolean value);
    boolean putInt(String fileName, String key, int value);
    boolean putLong(String fileName, String key, long value);
    boolean putFloat(String fileName, String key, float value);
    boolean putDouble(String fileName, String key, double value);
    boolean putBytes(String fileName, String key, byte[] value);
    boolean putParcelable(String fileName, String key, Parcelable value);

    String optString(String fileName, String key, String defaultValue);
    boolean optBoolean(String fileName, String key, boolean defaultValue);
    int optInt(String fileName, String key, int defaultValue);
    long optLong(String fileName, String key, long defaultValue);
    float optFloat(String fileName, String key, float defaultValue);
    double optDouble(String fileName, String key, double defaultValue);
    byte[] optBytes(String fileName, String key, byte[] defaultValue);
    Parcelable optParcelable(String fileName, String key, Class defaultValue);

}
