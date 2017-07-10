package com.wangjing.loopviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 作者：Created by WangJing on 2017/7/10.
 * 邮箱：wangjinggm@gmail.com
 * 描述：TODO
 * 最近修改：2017/7/10 15:29 by WangJing
 */

public class LoopViewPager extends ViewPager {

    public LoopViewPager(Context context) {
        this(context, null);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 播放时间
     */
    private int showTime = 3 * 1000;

    /**
     * 滚动方向
     */
    private Direction direction = Direction.LEFT;

    /**
     * 设置播放时间，默认3秒
     *
     * @param showTimeMillis 毫秒
     */
    public void setShowTime(int showTimeMillis) {
        this.showTime = showTimeMillis;
    }

    /**
     * 设置滚动方向，默认向左滚动
     *
     * @param direction 方向
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * 开始
     */
    public void start() {
        stop();
        postDelayed(player, showTime);
    }

    /**
     * 停止
     */
    public void stop() {
        removeCallbacks(player);
    }

    /**
     * 播放上一个
     */
    public void previous() {
        // TODO 待实现播放上一个
    }

    /**
     * 播放下一个
     */
    public void next() {
        // TODO 待实现播放下一个
    }

    public enum Direction {
        /**
         * 向左行动，播放的应该是右边的
         */
        LEFT,

        /**
         * 向右行动，播放的应该是左边的
         */
        RIGHT
    }

    /**
     * 播放器
     */
    private Runnable player = new Runnable() {
        @Override
        public void run() {
            play(direction);
        }
    };

    /**
     * 执行播放
     *
     * @param direction 播放方向
     */
    private synchronized void play(Direction direction) {
        // 拿到ViewPager的适配器
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {// 判空
            // Item数量
            int count = pagerAdapter.getCount();
            // ViewPager现在显示的第几个？
            int currentItem = getCurrentItem();
            switch (direction) {
                case LEFT:// 如是向左滚动的，currentItem+1播放下一个
                    currentItem++;

                    // 如果+到最后一个了，就到第一个
                    if (currentItem >= count)
                        currentItem = 0;
                    break;
                case RIGHT:// 如是向右滚动的，currentItem-1播放上一个
                    currentItem--;

                    // 如果-到低一个了，就到最后一个
                    if (currentItem < 0)
                        currentItem = count - 1;
                    break;
            }
            setCurrentItem(currentItem);// 播放根据方向计算出来的position的item
        }

        // 这就是当可以循环播放的重点，每次播放完成后，再次开启一个定时任务
        start();
    }

    /**
     * 当我们用手指刚滑完一张，紧接着第二张又出来了，不卖关子了，原因就是我们手指滑动的时候private Runnable player这个任务没有停止，所以我们在手指滑动时停止player，手指松开的时候再次开启player
     */
    @Override
    protected void onFinishInflate() {
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == SCROLL_STATE_IDLE) {
                    start();
                } else if (state == SCROLL_STATE_DRAGGING) {
                    stop();
                }
            }
        });
        super.onFinishInflate();
    }

    /**
     * 这里post了一个Runnable，它持有当前Activity的实例，所以在AutoPlayViewPager所在的当前Activity销毁时可能会发生内存泄漏，我们在View销毁的时候移除Runnable
     */
    @Override
    protected void onDetachedFromWindow() {
        removeCallbacks(player);
        super.onDetachedFromWindow();
    }
}
