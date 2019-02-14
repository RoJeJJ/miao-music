package com.roje.miao.music.activity;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.roje.miao.music.R;
import com.roje.miao.music.fragment.DiscoverFragment;
import com.roje.miao.music.fragment.MusicFragment;
import com.roje.miao.music.fragment.VideoFragment;
import com.roje.miao.music.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mainDrawer)
    DrawerLayout mainDrawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.mainTab)
    TabLayout mainTab;

    @BindView(R.id.mainViewPager)
    ViewPager mainViewPager;

    private List<Fragment> fragments;

    private ActionBarDrawerToggle drawerToggle;

    private int[] iconRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.transparentStatusBar(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initDrawer();
        initTab();
    }

    @SuppressLint("ResourceType")
    private void initTab() {
        fragments = new ArrayList<>();
        fragments.add(MusicFragment.newInstance("",""));
        fragments.add(DiscoverFragment.newInstance());
        fragments.add(VideoFragment.newInstance("",""));
        iconRes = new int[]{R.drawable.music_tab_selector,
                R.drawable.discover_tab_selector,R.drawable.video_tab_selector};
        mainViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mainTab.setupWithViewPager(mainViewPager);
        for (int i=0;i<mainTab.getTabCount();i++) {
            TabLayout.Tab tab = mainTab.getTabAt(i);
            if (tab != null) {
                ImageView view = new ImageView(this);
                view.setImageResource(iconRes[i]);
                tab.setCustomView(view);
            }
        }
        mainViewPager.setCurrentItem(1);
    }


    private void initDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this,mainDrawer,toolbar,
                R.string.drawer_open,R.string.drawer_close);
        drawerToggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.w3);
        }
        mainDrawer.addDrawerListener(drawerToggle);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
