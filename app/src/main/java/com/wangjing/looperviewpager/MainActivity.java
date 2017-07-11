package com.wangjing.looperviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.wangjing.loopviewpager.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<View> guideImages;
    private ViewPagerAdapter adapter;
    private LoopViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager= (LoopViewPager) findViewById(R.id.viewpager);
        guideImages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.mipmap.ic_launcher);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            guideImages.add(imageView);
        }

        adapter = new ViewPagerAdapter(guideImages);
        viewpager.setAdapter(adapter);
        viewpager.setShowTime(1000);
        viewpager.setDirection(LoopViewPager.Direction.RIGHT);
        viewpager.start();
    }
}
