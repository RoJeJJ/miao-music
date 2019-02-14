package com.roje.miao.music.block;

import com.roje.miao.music.network.response.BannerResponse;

import io.reactivex.Observable;

public interface Mode {
    Observable<BannerResponse> getBanners();
}
