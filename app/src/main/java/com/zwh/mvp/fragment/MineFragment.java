package com.zwh.mvp.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zwh.mvp.R;
import com.zwh.mvp.library.base.activity.BaseMVPFragment;
import com.zwh.mvp.model.MineModel;
import com.zwh.mvp.presenter.MinePresenter;
import com.zwh.mvp.view.MineView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseMVPFragment<MinePresenter> implements MineView {


    @BindView(R.id.tableLayout)
    TabLayout tableLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.markerBtn)
    Button markerBtn;

    private ContentPagerAdapter pagerAdapter = null;

    private String[] tabs = new String[]{"全部", "商品", "钢厂", "原料", "要闻", "期货", "钢坯", "现货"};
    private List<String> tabItems = new ArrayList<>();
    private List<Fragment> tabFragments = new ArrayList<>();

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void viewCreated(Bundle bundle) {
        setTitle("我的");
        isBackIconHidden(true);
        for (String tab : tabs) {
            tabItems.add(tab);
        }

        initView();
    }

    private void initView() {
        for (int i = 0; i < tabItems.size(); i++) {
            tableLayout.addTab(tableLayout.newTab().setCustomView(getTabView(i)));
//            tableLayout.addTab(tableLayout.newTab().setText(tabItems.get(i)));
        }

        tabFragments.clear();
        for (int i = 0; i < tabItems.size(); i++) {
            tabFragments.add(ItemFragment.getInstance(tabItems.get(i)));
        }

        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                changeTabSelect(tab);
//                tab.setCustomView(getSelectTabView(tab.getPosition()));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabNormal(tab);
//                tab.setCustomView(getTabView(tab.getPosition()));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        viewPager.setAdapter(new ContentPagerAdapter(getFragmentManager()));
//        tableLayout.setupWithViewPager(viewPager,true);
        pagerAdapter = new ContentPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
//        tableLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tableLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tableLayout.getTabAt(0).select();
    }

    @OnClick(R.id.markerBtn)
    public void onViewClicked() {
        tableLayout.setupWithViewPager(null);
        viewPager.setAdapter(null);
        tableLayout.removeAllTabs();
        tabItems.add(2,"自定义");
        initView();
    }


    /**
     * 提供TabLayout的View
     * 根据index返回不同的View
     * 主意：默认选中的View要返回选中状态的样式
     */
    private View getTabView(int index) {
        //自定义View布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab_item_view, null);
        TextView title = view.findViewById(R.id.itemTV);
        title.setText(tabItems.get(index));
        if(index == 0){
            title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            title.setTextColor(getResources().getColor(R.color.text_title));
        }else{
            title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            title.setTextColor(getResources().getColor(R.color.text_label));
        }
        return view;
    }

    /**
     * 提供TabLayout的View
     * 根据index返回不同的View
     * 主意：默认选中的View要返回选中状态的样式
     */
    private View getSelectTabView(int index) {
        //自定义View布局
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab_item_view, null);
        TextView title = view.findViewById(R.id.itemTV);
        title.setText(tabItems.get(index));
        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        title.setTextColor(getResources().getColor(R.color.text_title));
        return view;
    }


    /**
     * 改变TabLayout的View到选中状态
     * 使用属性动画改编Tab中View的状态
     */
    private void changeTabSelect(TabLayout.Tab tab) {
        final View view = tab.getCustomView();
        if(view != null){
            TextView textView = view.findViewById(R.id.itemTV);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            textView.setTextColor(getResources().getColor(R.color.text_title));
        }
//        ObjectAnimator anim = ObjectAnimator
//                .ofFloat(view, View.SCALE_X, 1.0F, 1.1F)
//                .setDuration(200);
//        anim.start();
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float cVal = (Float) animation.getAnimatedValue();
//                view.setAlpha(0.5f + (cVal - 1f) * (0.5f / 0.1f));
//                view.setScaleX(cVal);
//                view.setScaleY(cVal);
//            }
//        });
    }

    /**
     * 改变TabLayout的View到未选中状态
     */
    private void changeTabNormal(TabLayout.Tab tab) {
        final View view = tab.getCustomView();
        if(view != null){
            TextView textView = view.findViewById(R.id.itemTV);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            textView.setTextColor(getResources().getColor(R.color.text_label));
        }

//        ObjectAnimator anim = ObjectAnimator
//                .ofFloat(view, View.TRANSLATION_Y, 1.0F, 0.9F)
//                .setDuration(200);
//        anim.start();
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float cVal = (Float) animation.getAnimatedValue();
//                view.setAlpha(1f - (1f - cVal) * (0.5f / 0.1f));
//                view.setScaleX(cVal);
//                view.setScaleY(cVal);
//            }
//        });
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(new MineModel());
    }


    @Override
    public void result(String reslut) {

    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabItems.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabItems.get(position);
        }

    }

}
