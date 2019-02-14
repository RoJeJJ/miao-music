package com.roje.miao.music.block.impl;

import com.roje.miao.music.block.Mode;
import com.roje.miao.music.network.NetWorkManager;
import com.roje.miao.music.network.response.BannerResponse;

import io.reactivex.Observable;

public class ModelImpl implements Mode {
    @Override
    public Observable<BannerResponse> getBanners() {
        return NetWorkManager.INSTANCE.getRequest().getBanners();
    }
}
