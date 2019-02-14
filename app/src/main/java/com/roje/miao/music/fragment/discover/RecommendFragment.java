package com.roje.miao.music.fragment.discover;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.roje.miao.music.R;
import com.roje.miao.music.block.ContractView;
import com.roje.miao.music.block.Presenter;
import com.roje.miao.music.block.impl.ModelImpl;
import com.roje.miao.music.block.impl.PresenterImpl;
import com.roje.miao.music.network.response.BannerResponse;
import com.roje.miao.music.network.scheduler.impl.AndroidSchedulerProvider;
import com.roje.miao.music.view.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendFragment extends Fragment {

    @BindView(R.id.recommend_banner_view_pager)
    ViewPager bannerViewPager;

    private final Presenter presenter;

    private RoundImageView[] images;

    private class BannerView implements ContractView<BannerResponse> {

        @Override
        public void success(BannerResponse data) {
            Log.i("banner","获取成功");
        }

        @Override
        public void fail(Throwable e) {
            e.printStackTrace();
        }
    }

    public RecommendFragment() {
        // Required empty public constructor
        presenter = new PresenterImpl(new ModelImpl(),new BannerView(), AndroidSchedulerProvider.getInstance());
    }

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_recommend,container,false);
        ButterKnife.bind(this,root);
        initBanner();
        return root;
    }

    private void initBanner() {
        images = new RoundImageView[9];
        for (int i=0;i<images.length;i++) {
            images[i] = new RoundImageView(getContext());
            images[i].setImageResource(R.drawable.avi);
            images[i].setScaleType(ImageView.ScaleType.FIT_XY);
            images[i].setImageMode(RoundImageView.MODE_CIRCLE);
            images[i].setPadding(4,0,4,0);
        }
        bannerViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                container.addView(images[position],params);
                return images[position];
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(images[position]);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        presenter.getBanners();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
