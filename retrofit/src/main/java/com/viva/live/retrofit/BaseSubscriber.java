package com.viva.live.retrofit;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * author:mingming.liu
 * description:
 * warn:
 * time: 2019-09-06
 */
public abstract class BaseSubscriber<T> extends ResourceSubscriber<DataWrapper<T>> {

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public final void onComplete() {
        if (!isDisposed()) {
            dispose();
        }
    }

    @Override
    public final void onError(Throwable e) {
        onException(e);
        if (!isDisposed()) {
            dispose();
        }
    }

    @Override
    public final void onNext(DataWrapper<T> t) {
        if (t.isResult()) {
            onSuccess(t.getData());
        } else {
            onError(t.getCode(), t.getMessage());
        }
    }

    public abstract void onError(int errorCode, String errorMessage);

    public abstract void onSuccess(T t);

    public abstract void onException(Throwable e);
}
