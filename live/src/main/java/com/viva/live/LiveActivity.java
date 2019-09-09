package com.viva.live;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.viva.live.model.AGEventHandler;
import com.viva.live.model.ConstantApp;
import com.viva.live.model.WorkThreadProxy;
import com.viva.live.model.WorkerThread;
import com.viva.live.service.component.ServiceLoader;
import com.viva.live.service.log.ILogerService;
import com.viva.live.service.log.RouterPath;

import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-06
 */
@Route(path = RouterPath.COMPONENT_LIVE_1V1)
public class LiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postGlobalListenerForInitEvent();
        setContentView(R.layout.live_activity_live);
    }

    private void postGlobalListenerForInitEvent(){
        final View layout = findViewById(Window.ID_ANDROID_CONTENT);
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                initLiveEvent();
            }
        });
    }

    private void initLiveEvent(){
        if (!checkAndRequestPermissions()) {
            return;
        }

        addEventHandler();
        doConfigEngine(Constants.CLIENT_ROLE_BROADCASTER);
        createSurfaceViewAndPreView();
        joinRoom();
    }

    private void addEventHandler(){
        WorkThreadProxy.getInstance().getWorkerThread().eventHandler().addEventHandler(new AGEventHandler() {
            @Override
            public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {
                d("uid is" + uid + " width = " + width + " height = " + height);
            }

            @Override
            public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
                d("uid is" + uid + " channel = " + channel + " when onJoinChannelSuccess called ");
            }

            @Override
            public void onUserOffline(int uid, int reason) {
                d("uid is" + uid + " reason = " + reason + " when onUserOffline called ");
            }

            @Override
            public void onUserJoined(int uid, int elapsed) {
                d("uid is" + uid + " when onUserJoined called ");
            }
        });
    }

    private void doConfigEngine(int cRole) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int prefIndex = pref.getInt(ConstantApp.PrefManager.PREF_PROPERTY_PROFILE_IDX, ConstantApp.DEFAULT_PROFILE_IDX);
        if (prefIndex > ConstantApp.VIDEO_DIMENSIONS.length - 1) {
            prefIndex = ConstantApp.DEFAULT_PROFILE_IDX;
        }
        VideoEncoderConfiguration.VideoDimensions dimension = ConstantApp.VIDEO_DIMENSIONS[prefIndex];

        WorkThreadProxy.getInstance().getWorkerThread().configEngine(cRole, dimension);
    }

    private void createSurfaceViewAndPreView(){
        SurfaceView surfaceV = RtcEngine.CreateRendererView(getApplicationContext());
        WorkThreadProxy.getInstance().getWorkerThread().getRtcEngine().setupLocalVideo(new VideoCanvas(surfaceV, VideoCanvas.RENDER_MODE_HIDDEN, 0));
        ViewGroup viewGroup = findViewById(R.id.live_Root);
        viewGroup.addView(surfaceV, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        WorkThreadProxy.getInstance().getWorkerThread().preview(true, surfaceV, 0);
    }

    private void joinRoom(){
        WorkerThread workerThread = WorkThreadProxy.getInstance().getWorkerThread();
        workerThread.joinChannel("qaz", workerThread.getEngineConfig().mUid);
    }

    private void d(String msg){

    }

    private boolean checkAndRequestPermissions() {
        return checkAndRequest(Manifest.permission.RECORD_AUDIO, ConstantApp.PERMISSION_REQ_ID_RECORD_AUDIO) &&
                checkAndRequest(Manifest.permission.CAMERA, ConstantApp.PERMISSION_REQ_ID_CAMERA) &&
                checkAndRequest(Manifest.permission.WRITE_EXTERNAL_STORAGE, ConstantApp.PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE);
    }

    public boolean checkAndRequest(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{permission},
                    requestCode);
            return false;
        }

        if (Manifest.permission.CAMERA.equals(permission)) {
            WorkThreadProxy.getInstance().initWorkerThread(this);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case ConstantApp.PERMISSION_REQ_ID_RECORD_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkAndRequest(Manifest.permission.CAMERA, ConstantApp.PERMISSION_REQ_ID_CAMERA);
                } else {
                    finish();
                }
                break;
            }
            case ConstantApp.PERMISSION_REQ_ID_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkAndRequest(Manifest.permission.WRITE_EXTERNAL_STORAGE, ConstantApp.PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE);
                    WorkThreadProxy.getInstance().initWorkerThread(this);
                } else {
                    finish();
                }
                break;
            }
            case ConstantApp.PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    finish();
                }
                break;
            }
        }
    }
}
