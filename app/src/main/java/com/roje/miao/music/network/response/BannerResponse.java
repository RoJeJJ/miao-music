package com.roje.miao.music.network.response;

import com.roje.miao.music.bean.Banner;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BannerResponse {
    private List<Banner> banners;
}
