package com.roje.miao.music.network.scheduler.impl;

import android.support.annotation.NonNull;

import com.roje.miao.music.network.scheduler.SchedulerProvider;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AndroidSchedulerProvider implements SchedulerProvider {

    private static SchedulerProvider instance;

    public static SchedulerProvider getInstance() {
        if (instance == null) {
            synchronized (AndroidSchedulerProvider.class) {
                if (instance == null) {
                    instance = new AndroidSchedulerProvider();
                }
            }
        }
        return instance;
    }

    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull
    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(io())
                .observeOn(ui());
    }
}
