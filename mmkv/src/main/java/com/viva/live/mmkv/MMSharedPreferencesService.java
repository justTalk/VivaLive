package com.viva.live.mmkv;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.getkeepsafe.relinker.ReLinker;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;
import com.viva.live.service.component.ServiceLoader;
import com.viva.live.service.log.RouterPath;
import com.viva.live.service.sp.ISharedPreferencesService;

/**
 * author:mingming.liu
 * description:mmkv服务提供者
 * warn:
 * time: 2019-09-09
 */
@Route(path = RouterPath.SERVICE_SHARED_PREFERENCES)
public class MMSharedPreferencesService extends ServiceLoader<ISharedPreferencesService> implements IProvider {

    private M mMMKV;

    @Override
    public void init(Context context) {
        Log.d("ServiceLoader", "MMSharedPreferencesService init");
        MMKV.initialize(getFilePath(context),new MMKV.LibLoader() {
            @Override
            public void loadLibrary(String libName) {
                ReLinker.loadLibrary(context, libName);
            }
        });
        MMKV.setLogLevel(BuildConfig.DEBUG ? MMKVLogLevel.LevelDebug : MMKVLogLevel.LevelNone);
    }

    private String getFilePath(Context context){
        return context.getFilesDir().getAbsolutePath() + "/mmkv";
    }

    @Override
    public ISharedPreferencesService load(String name) {
        if (mMMKV == null) {
            mMMKV = new M();
        }
        return mMMKV;
    }

    @Override
    public boolean recycle(String name) {
        return false;
    }

    private static class M implements ISharedPreferencesService{

        @Override
        public boolean putString(String fileName, String key, String value) {
            return MMKV.mmkvWithID(fileName).encode(key, value);
        }

        @Override
        public boolean putBoolean(String fileName, String key, boolean value) {
            return MMKV.mmkvWithID(fileName).encode(key, value);
        }

        @Override
        public boolean putInt(String fileName, String key, int value) {
            return MMKV.mmkvWithID(fileName).encode(key, value);
        }

        @Override
        public boolean putLong(String fileName, String key, long value) {
            return MMKV.mmkvWithID(fileName).encode(key, value);
        }

        @Override
        public boolean putFloat(String fileName, String key, float value) {
            return MMKV.mmkvWithID(fileName).encode(key, value);
        }

        @Override
        public boolean putDouble(String fileName, String key, double value) {
            return MMKV.mmkvWithID(fileName).encode(key, value);
        }

        @Override
        public boolean putBytes(String fileName, String key, byte[] value) {
            return MMKV.mmkvWithID(fileName).encode(key, value);
        }

        @Override
        public boolean putParcelable(String fileName, String key, Parcelable value) {
            return MMKV.mmkvWithID(fileName).encode(key, value);
        }

        @Override
        public String optString(String fileName, String key, String defaultValue) {
            return MMKV.mmkvWithID(fileName).decodeString(key, defaultValue);
        }

        @Override
        public boolean optBoolean(String fileName, String key, boolean defaultValue) {
            return MMKV.mmkvWithID(fileName).decodeBool(key,defaultValue);
        }

        @Override
        public int optInt(String fileName, String key, int defaultValue) {
            return MMKV.mmkvWithID(fileName).decodeInt(key, defaultValue);
        }

        @Override
        public long optLong(String fileName, String key, long defaultValue) {
            return MMKV.mmkvWithID(fileName).decodeLong(key, defaultValue);
        }

        @Override
        public float optFloat(String fileName, String key, float defaultValue) {
            return MMKV.mmkvWithID(fileName).decodeFloat(key, defaultValue);
        }

        @Override
        public double optDouble(String fileName, String key, double defaultValue) {
            return MMKV.mmkvWithID(fileName).decodeDouble(key, defaultValue);
        }

        @Override
        public byte[] optBytes(String fileName, String key, byte[] defaultValue) {
            return MMKV.mmkvWithID(fileName).decodeBytes(key, defaultValue);
        }

        @Override
        public Parcelable optParcelable(String fileName, String key, Class defaultValue) {
            return MMKV.mmkvWithID(fileName).decodeParcelable(key, defaultValue);
        }
    }
}
