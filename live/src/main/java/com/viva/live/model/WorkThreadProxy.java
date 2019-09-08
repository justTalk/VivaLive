package com.viva.live.model;

import android.content.Context;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-07
 */
public class WorkThreadProxy {
    private WorkerThread mWorkerThread;

    private WorkThreadProxy(){
    }

    private static class WorkThreadHolder{
        static WorkThreadProxy workThreadProxy = new WorkThreadProxy();
    }

    public static WorkThreadProxy getInstance(){
        return WorkThreadHolder.workThreadProxy;
    }

    public synchronized void initWorkerThread(Context context) {
        if (mWorkerThread == null) {
            mWorkerThread = new WorkerThread(context.getApplicationContext());
            mWorkerThread.start();

            mWorkerThread.waitForReady();
        }
    }

    public synchronized WorkerThread getWorkerThread() {
        return mWorkerThread;
    }

    public synchronized void deInitWorkerThread() {
        mWorkerThread.exit();
        try {
            mWorkerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mWorkerThread = null;
    }
}
