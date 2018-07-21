package com.huan.dagger2demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.huan.dagger2demo.base.BaseActivity;
import com.huan.dagger2demo.gankio.GankIoFragment;
import com.huan.dagger2demo.zhihu.ZhiHuHomeFragment;
import com.huan.dagger2demo.wanandroid.WanAndroidFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements OnOpenDrawerLayoutListener {

    @BindView(R.id.dl_root)
    DrawerLayout dlRoot;

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    @BindView(R.id.nav_left)
    NavigationView navLeft;

    @BindView(R.id.nav_bottom)
    BottomNavigationView navBottom;

    private ZhiHuHomeFragment zhiHuHomeFragment;
    private GankIoFragment gankFragment;
    private WanAndroidFragment androidFragment;

    private Fragment mCurrentFragment;
    private Fragment mShowFragment;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.menu_item_home:
                        if (zhiHuHomeFragment == null) {
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            zhiHuHomeFragment = ZhiHuHomeFragment.newInstance();
                            transaction.add(flContainer.getId(), zhiHuHomeFragment, ZhiHuHomeFragment.class.getName());
                            transaction.commitNowAllowingStateLoss();
                        }
                        mShowFragment = zhiHuHomeFragment;
                        break;
                    case R.id.menu_item_gank_io:
                        if (gankFragment == null) {
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            gankFragment = GankIoFragment.newInstance();
                            transaction.add(flContainer.getId(), gankFragment, GankIoFragment.class.getName());
                            transaction.commitNowAllowingStateLoss();
                        }
                        mShowFragment = gankFragment;
                        break;
                    case R.id.menu_item_wan_android:
                        if (androidFragment == null) {
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            androidFragment = WanAndroidFragment.newInstance();
                            transaction.add(flContainer.getId(), androidFragment, WanAndroidFragment.class.getName());
                            transaction.commitNowAllowingStateLoss();
                        }
                        mShowFragment = androidFragment;
                        break;
                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (mCurrentFragment != null) {
                    transaction.hide(mCurrentFragment);
                }
                transaction.show(mShowFragment);
                transaction.commitNowAllowingStateLoss();
                mCurrentFragment = mShowFragment;
                return true;
            }
        });
        navBottom.setSelectedItemId(R.id.menu_item_home);
    }


    @Override
    public void openDrawer() {
        if (!dlRoot.isDrawerOpen(GravityCompat.START)) {
            dlRoot.openDrawer(GravityCompat.START);
        }
    }
}
