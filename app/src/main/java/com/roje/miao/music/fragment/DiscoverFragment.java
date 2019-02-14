package com.roje.miao.music.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roje.miao.music.R;
import com.roje.miao.music.fragment.discover.FriendsFragment;
import com.roje.miao.music.fragment.discover.RadioFragment;
import com.roje.miao.music.fragment.discover.RecommendFragment;
import com.roje.miao.music.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DiscoverFragment extends Fragment {

    @BindView(R.id.discover_tab_layout)
    TabLayout discoverTabLayout;

    @BindView(R.id.discover_view_pager)
    ViewPager discoverViewPager;

    private List<Fragment> discoverFragments;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this,rootView);
        initTab();
        return rootView;
    }

    private void initTab() {
        discoverFragments = new ArrayList<>();
        discoverFragments.add(RecommendFragment.newInstance());
        discoverFragments.add(FriendsFragment.newInstance("", ""));
        discoverFragments.add(RadioFragment.newInstance("", ""));
        final int[] pageTitlesRes = new int[]{R.string.recommend_title, R.string.friends_title, R.string.radio_title};
        discoverViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return discoverFragments.get(i);
            }

            @Override
            public int getCount() {
                return discoverFragments.size();
            }

            @NonNull
            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageTitlesRes[position]);
            }
        });
        discoverTabLayout.setupWithViewPager(discoverViewPager);
    }

    @Override
    public void onStart() {
        Log.i("tag","onStart");
        super.onStart();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
