package com.example.lixiao.basicdemo.app.widget.TradeLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lixiao.basicdemo.R;
import com.example.lixiao.basicdemo.app.widget.bean.TradeItemBean;
import com.example.lixiao.basicdemo.support.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TradeLayout extends FrameLayout {
    /**
     * 屏幕宽度
     */
    private int screenWidth;
    /**
     * 商品之间的间距
     */
    private int tradeMarggin = 15;
    /**
     * 间距总数 （3个商品4个间隔）
     */
    private int tradeMargginCount = 4;
    /**
     * 一屏显示的个数
     */
    private int tradeCount = 3;
    /**
     * 最右侧缩放比例
     */
    private float rightTradeScale = 1.0f;

    /**
     * 缩放比例衰减率
     */
    private float scaleaTtenuation = 0.1f;

    /**
     * trade宽高比
     */
    private float aspectratio = 1.35f;

    private int tradeUseCount = tradeCount + 2;
    /**
     * 存放缩放比例，+2是左右两侧还有两个预加载的。
     */
    private float[] scaleList = new float[tradeUseCount];
    /**
     * 存放宽高尺寸的数组
     */
    private float[] widthList = new float[tradeUseCount];
    private float[] heightList = new float[tradeUseCount];
    private float[] marginList = new float[tradeUseCount];

    /**
     * 当前最右侧item 0开始
     */
    private int currentTrade = 2;
    /**
     * 商品列表
     */
    private List<TradeItemBean> tradeItemBeans;
    private List<ImageView> tradeViewList;

    /**
     * 动画耗时 毫秒
     */
    private int animeTime = 500;
    /**
     * 切换时间间隔
     */
    private int timeGap = 3000;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    goNext();
                    handler.sendEmptyMessageDelayed(100, timeGap);
                    break;
                case 200:
                    if (animator != null) {
                        animator.cancel();
                    }
                    animator = null;
                    handler.removeMessages(100);
                    handler.removeMessages(200);
                    isStart = false;
                    break;
            }
        }
    };

    private void init(){

        screenWidth = Utils.getScreenWidth(getContext());
        //全部可布局的宽度
        float layoutWidth = screenWidth - tradeMarggin * tradeMargginCount;

        //计算像素密度差
        scaleList[0] = rightTradeScale - ((tradeCount ) * scaleaTtenuation);
        scaleList[tradeUseCount - 1] = rightTradeScale + scaleaTtenuation;
        float tempCount = 0;
        for (int i = 0; i < tradeCount; i++) {
            tempCount += rightTradeScale - (i * scaleaTtenuation);
            scaleList[i + 1] = scaleList[i] + scaleaTtenuation;
        }


        //百分比尺寸
        float pxSize = layoutWidth / tempCount;

        for (int i = 0; i <= tradeUseCount - 1; i++) {
            widthList[i] = pxSize * scaleList[i];
            heightList[i] = widthList[i] * aspectratio;
        }
        setMinimumHeight((int) heightList[tradeUseCount - 1]);
        tradeViewList = new ArrayList<>();
        tempData();
        layoutItem();

    }

    private boolean isStart = false;
    public void start(){
        if (isStart) {
            return;
        }
        isStart = true;
        handler.sendEmptyMessageDelayed(100, timeGap);
    }

    public void stop(){
        handler.sendEmptyMessage(200);
    }


    private void tempData(){
        tradeItemBeans = new ArrayList<>();
        tradeItemBeans.add(new TradeItemBean(R.mipmap.aaaaaa));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.bbbbbbb));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.cccccc));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.ddddd));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.eeeee));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.ffffff));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.aaaaaa));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.bbbbbbb));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.cccccc));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.ddddd));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.eeeee));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.ffffff));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.aaaaaa));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.bbbbbbb));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.cccccc));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.ddddd));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.eeeee));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.ffffff));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.aaaaaa));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.bbbbbbb));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.cccccc));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.ddddd));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.eeeee));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.ffffff));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.aaaaaa));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.bbbbbbb));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.cccccc));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.ddddd));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.eeeee));
        tradeItemBeans.add(new TradeItemBean(R.mipmap.ffffff));

    }

    private void layoutItem(){
        for (int i = 0; i <= tradeUseCount - 1; i++) {
            if (i == tradeUseCount - 1) {
                //右侧不在屏幕中
                ImageView imageView = new ImageView(getContext());
                LayoutParams params = new LayoutParams((int)widthList[tradeUseCount - 1] , (int)heightList[tradeUseCount - 1]);
                params.leftMargin = screenWidth;
                params.gravity = Gravity.BOTTOM;
                imageView.setLayoutParams(params);
                addView(imageView);
                tradeViewList.add(imageView);
                marginList[i] = screenWidth;
                if (i <= tradeItemBeans.size()) {
                    Glide.with(getContext())
                            .load(tradeItemBeans.get(i - 1).getImageRes())
                            .centerCrop()
                            .into(imageView);
                    currentTrade = i - 1;
                }

            } else if (i == 0) {
                //左侧不在屏幕中 第一次绘制不管
                marginList[i] = -widthList[i];
            } else {
                //屏幕中
                ImageView imageView = new ImageView(getContext());
                LayoutParams params = new LayoutParams((int)widthList[i] , (int)heightList[i]);
                int left = tradeMarggin * (i);
                for (int j = 1; j < tradeUseCount - 1; j++) {
                    if (i > j) {
                        left += widthList[j];
                    }else{
                        break;
                    }
                }
                params.leftMargin =  left;
                marginList[i] = left;
                params.gravity = Gravity.BOTTOM;
                imageView.setLayoutParams(params);
                addView(imageView);
                tradeViewList.add(imageView);
                if (i < tradeItemBeans.size()) {
                    Glide.with(getContext())
                            .load(tradeItemBeans.get(i - 1).getImageRes())
                            .centerCrop()
                            .into(imageView);
                }
            }
        }
    }



    private ValueAnimator animator;
    /**
     * 滚动一次
     */
    private void goNext(){
        if (currentTrade >= tradeItemBeans.size()) {
            handler.sendEmptyMessageDelayed(200, 1000);
            return;
        }
        //滚动
        animator = ValueAnimator.ofFloat(1f, 0);
        animator.setStartDelay(animeTime);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //标记路程的百分比
                float value = (float) animation.getAnimatedValue();
                for (int i = 0; i < tradeUseCount - 1  ; i++) {
                    //总计移动的距离
                    float gap = marginList[i + 1] - marginList[i];
                    //计算出当前margin
                    float margin = marginList[i] + value * gap ;
                    if (i <= tradeViewList.size()) {
                        ImageView view = tradeViewList.get(i);
                        LayoutParams p = (LayoutParams) view.getLayoutParams();
                        p.leftMargin = (int) margin;


                        float startHeight = heightList[i + 1];
                        float endHeight = heightList[i];
                        float offsetY = startHeight - endHeight;
                        float currentY = endHeight + offsetY * value;

                        float startWidth = widthList[i + 1];
                        float endWidth = widthList[i];
                        float offsetX = startWidth - endWidth;
                        float currentX = endWidth + offsetX * value;

                        p.height = (int) currentY;
                        p.width = (int) currentX;
                        //动画设置大小

                        view.setLayoutParams(p);
                    }
                }
            }
        });
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                Log.e("TradeLayout", "onAnimationCancel");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.e("TradeLayout", "onAnimationEnd");
                currentTrade++;
                removeView(tradeViewList.get(0));
                tradeViewList.remove(0);

                ImageView imageView = new ImageView(getContext());
                LayoutParams params = new LayoutParams((int)widthList[tradeUseCount - 1] , (int)heightList[tradeUseCount - 1]);
                params.leftMargin = screenWidth;
                params.gravity = Gravity.BOTTOM;
                imageView.setLayoutParams(params);
                addView(imageView);
                tradeViewList.add(imageView);
                if (currentTrade < tradeItemBeans.size()) {
                    Glide.with(getContext())
                            .load(tradeItemBeans.get(currentTrade).getImageRes())
                            .centerCrop()
                            .into(imageView);
                }

            }
        });
    }


    public TradeLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public TradeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TradeLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


}
