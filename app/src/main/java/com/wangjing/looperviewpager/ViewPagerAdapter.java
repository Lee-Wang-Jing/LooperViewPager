package com.wangjing.looperviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2015/8/16.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> guideImages;


    public ViewPagerAdapter(List<View> views) {
        this.guideImages = views;
    }

    @Override
    public int getCount() {
        return guideImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 添加一个Item
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(guideImages.get(position), 0);
        return guideImages.get(position);
    }

    /***
     * 删除一个Item
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(guideImages.get(position));
    }

}
