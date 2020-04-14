package com.zwh.mvp.acitivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.ToastUtils;
import com.zwh.mvp.R;
import com.zwh.mvp.fragment.HomeFragment;
import com.zwh.mvp.fragment.MineFragment;
import com.zwh.mvp.library.helper.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AppActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.bottomNavigationBar)
    BottomNavigationBar bottomNavigationBar;

    private List<Fragment> mFragments;

    private HomeFragment homeFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        initBottomBar();
        initFragment();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        //设置初始化页数
        viewPager.setOffscreenPageLimit(5);
        viewPager.setScroll(false);//是否可与左右滑动
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        homeFragment = new HomeFragment();
//        messageFragment = new MessageFragment();
//
        mineFragment = new MineFragment();
//        demoListFragment = new DemoListFragment();
        mFragments.add(homeFragment);
//        mFragments.add(demoListFragment);
//        mFragments.add(messageFragment);
        mFragments.add(mineFragment);


    }

    private void initBottomBar() {

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);

        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

//        TextBadgeItem numberBadgeItem2 = new TextBadgeItem();
//        ShapeBadgeItem shapeBadgeItem = new ShapeBadgeItem();
//        shapeBadgeItem.setShape(ShapeBadgeItem.SHAPE_HEART);
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.nav_map, "地图").setActiveColorResource(R.color.main_color).setBadgeItem(numberBadgeItem));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.nav_map, "首页").setActiveColorResource(R.color.main_colorPrimary));
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.nav_message, "美图").setActiveColorResource(R.color.main_colorPrimary));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.nav_mine, "我的").setActiveColorResource(R.color.main_colorPrimary));


        bottomNavigationBar.setFirstSelectedPosition(0).initialise();

        bottomNavigationBar.setTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showShort("再按一次退出程序", Toast.LENGTH_SHORT);
                exitTime = System.currentTimeMillis();
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
                finish();
            }
        }
        return false;
    }
}
