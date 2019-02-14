package com.roje.miao.music.block.impl;

import com.roje.miao.music.block.ContractView;
import com.roje.miao.music.block.Mode;
import com.roje.miao.music.block.Presenter;
import com.roje.miao.music.network.scheduler.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class PresenterImpl implements Presenter {

    private Mode mMode;

    private ContractView mView;

    private SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mDisposable;

    public PresenterImpl(Mode mode, ContractView view, SchedulerProvider schedulerProvider) {
        this.mMode = mode;
        this.mView = view;
        this.mSchedulerProvider = schedulerProvider;
        mDisposable = new CompositeDisposable();
    }

    public void dispose() {
        mDisposable.dispose();
    }
    @SuppressWarnings("unchecked")
    @Override
    public void getBanners() {
        Disposable disposable = mMode.getBanners()
                .compose(mSchedulerProvider.applySchedulers())
                .subscribe(bannerResponse -> mView.success(bannerResponse), throwable -> mView.fail(throwable));
        mDisposable.add(disposable);
    }
}
