package com.roje.miao.music.network;

import com.roje.miao.music.network.response.BannerResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Request {

    String HOST = "http://192.168.1.103:3000/";

    @GET("banner")
    Observable<BannerResponse> getBanners();

}
