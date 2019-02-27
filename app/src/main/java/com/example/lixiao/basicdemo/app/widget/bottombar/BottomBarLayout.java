package com.example.lixiao.basicdemo.app.widget.bottombar;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lixiao.basicdemo.R;
import com.example.lixiao.basicdemo.app.widget.bean.BarItemBean;
import com.example.lixiao.basicdemo.support.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import de.hdodenhof.circleimageview.CircleImageView;

public class BottomBarLayout extends FrameLayout {

    /**
     * 条目总数
     */
    private final int itemCount = 5;

    /**
     * item大小
     */
    private int itemSize;

    /**
     * barbottom阴影大小
     */
    private int barBckShadow;


    /**
     * 中心浮动的item大小
     */
    private int centerSize;

    /**
     * 中心浮动的item距离背景view间隔
     */
    private int centerIntervalSize;

    /**
     * 中心Bottommarggin
     */
    private int centerImageViewBottomMargin;

    /**
     * 中心item和barbacvkview Bottommarggin
     */
    private int centerImageViewWithBarBottomMargin;


    /**
     * 最底层的背景层
     */
    private BarBackView barBackView;
    /**
     * 底层背景颜色
     */
    private int barBckColor;
    /**
     * 底层布局高度
     */
    private int backViewHeight;
    /**
     * 其余4个item布局，图片+文字样式
     */
    private List<FrameLayout> itemLayouts;
    /**
     * 其余4个item内容，图片文字样式
     */
    private List<BarItemBean> barItemBeans;

    /**
     * 中心item资源文件
     */
    private int centerImageViewRes;



    /**
     * 屏幕宽度
     */
    private int screenWidth;


    private int currentSelectItem = 0;

    /**
     * 4个
     * @param barItemBeans
     */
    public void setBarItemBeans(List<BarItemBean> barItemBeans) {
        this.barItemBeans = barItemBeans;
    }

    /**
     * 1次
     * @param centerImageViewRes
     */
    public void setCenterImageViewRes(int centerImageViewRes) {
        this.centerImageViewRes = centerImageViewRes;
    }


    private OnClickListener onClickListeners;

    public void setOnClickListeners(OnClickListener onClickListeners) {
        this.onClickListeners = onClickListeners;
    }

    public BottomBarLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public BottomBarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomBarLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init(){
        setBackgroundColor(getResources().getColor(R.color.colorTransation));
        barBckShadow = Utils.dip2px(getContext(), 5);
        itemSize = Utils.dip2px(getContext(), 45);
        //底部控件高度
        backViewHeight = Utils.dip2px(getContext(), 50) + barBckShadow;
        //中间按钮大小
        centerSize = Utils.dip2px(getContext(), 62);
        //间隔
        centerIntervalSize = Utils.dip2px(getContext(), 3) + barBckShadow;
        //圆形和barviewmarggin
        centerImageViewWithBarBottomMargin = Utils.dip2px(getContext(), 10);
        //圆心位置
        centerImageViewBottomMargin = Math.abs((centerSize / 2) - backViewHeight) + centerImageViewWithBarBottomMargin - barBckShadow ;

        screenWidth = Utils.getScreenWidth(getContext());

        itemLayouts = new ArrayList<>();


        barBckColor = R.color.colorWhite;

        barBackView = new BarBackView(getContext());
        barBackView.setShadowHeight(barBckShadow);
        barBackView.setBarBckColor(barBckColor);
        barBackView.setBackViewHeight(backViewHeight);
        barBackView.setCenterIntervalSize(centerIntervalSize);
        barBackView.setCenterSize(centerSize);
        barBackView.setCenterSizeBottomMargin(centerImageViewWithBarBottomMargin);
        FrameLayout.LayoutParams backParams = new FrameLayout.LayoutParams(screenWidth , backViewHeight);
        backParams.gravity = Gravity.BOTTOM;
        barBackView.setLayoutParams(backParams);
        barBackView.init();
        initDemoData();
        addAllView();
        onSelectOne(0);
    }

    /**
     * 测试用数据
     */
    private void initDemoData(){
        barItemBeans = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            BarItemBean itemBean = new BarItemBean();
            itemBean.setGap(Utils.dip2px(getContext(), 2));
            itemBean.setNomalColor(R.color.colorAccent);
            itemBean.setSelectColor(R.color.colorPrimary);
            itemBean.setNomalRes(R.mipmap.ic_launcher);
            itemBean.setSelectRes(R.mipmap.ic_launcher_round);
            itemBean.setResSize(Utils.dip2px(getContext(), 30));
            itemBean.setTitle("标题");
            itemBean.setTitleSize(10);
            barItemBeans.add(itemBean);
        }

    }

    private void addAllView(){
        addView(barBackView);


        for (BarItemBean bean : barItemBeans) {
            FrameLayout linearLayout = new FrameLayout(getContext());
            int width = screenWidth / 5;
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width , itemSize);
            params.gravity = Gravity.BOTTOM;
            params.bottomMargin = Utils.dip2px(getContext(), 2.5f);
            linearLayout.setLayoutParams(params);

            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(bean.getNomalRes());
            FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(bean.getResSize(),bean.getResSize());
            if (bean.isSingleImage()) {
                params1.gravity = Gravity.CENTER;
            } else {
                params1.gravity = Gravity.CENTER_HORIZONTAL;
            }
            imageView.setLayoutParams(params1);

            linearLayout.addView(imageView);

            TextView textView = new TextView(getContext());
            textView.setText(bean.getTitle());
            textView.setTextSize(bean.getTitleSize());
            textView.setTextColor(getResources().getColor(bean.getNomalColor()));

            FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
            if (bean.isSingleImage()) {
                params2.gravity = Gravity.CENTER;
            } else {
                params2.gravity = Gravity.CENTER_HORIZONTAL;
            }
            params2.topMargin = bean.getResSize() + bean.getGap();
            textView.setLayoutParams(params2);

            linearLayout.addView(textView);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                linearLayout.setElevation(10f);
            }
            itemLayouts.add(linearLayout);
        }



        /**
         * 一份宽度为1/5屏幕
         */

        for (int i = 0; i < itemLayouts.size(); i++) {
            layoutItem(i);
        }

        /**
         * 添加中间的图片
         */
        centerImage();
    }

    private void centerImage(){
        CircleImageView imageView = new CircleImageView(getContext());
        LayoutParams params = new LayoutParams(centerSize,centerSize);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        params.bottomMargin = centerImageViewBottomMargin;
        imageView.setLayoutParams(params);
        imageView.setBorderWidth(4);
        imageView.setBorderColor(getResources().getColor(R.color.colorAccent));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            imageView.setElevation(10f);
//        }

        addView(imageView);
        Glide.with(this)
                .load(R.mipmap.miho)
                .centerCrop()
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectOne(-1);
                onClickListeners.onClick(2);
            }
        });

    }

    private void layoutItem(int index){
        int width = screenWidth / 5;
        FrameLayout linearLayout = itemLayouts.get(index);
        FrameLayout.LayoutParams params = (LayoutParams) linearLayout.getLayoutParams();
        switch (index) {
            case 0:{
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSelectOne(0);
                        onClickListeners.onClick(0);
                    }
                });
            }
                break;
            case 1:{
                params.leftMargin = width;
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSelectOne(1);
                        onClickListeners.onClick(1);
                    }
                });
            }
                break;
            case 2:{
                params.leftMargin = width * 3;
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSelectOne(2);
                        onClickListeners.onClick(3);
                    }
                });
            }
                break;
            case 3:{
                params.leftMargin = width * 4;
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onSelectOne(3);
                        onClickListeners.onClick(4);
                    }
                });
            }
                break;
        }
        linearLayout.setLayoutParams(params);
        addView(linearLayout);
    }

    public void onSelectOne(int index){
        for (int i = 0; i < itemLayouts.size(); i++) {
            BarItemBean bean = barItemBeans.get(i);
            FrameLayout itemLayout = itemLayouts.get(i);
            ImageView imageView = (ImageView) itemLayout.getChildAt(0);
            imageView.setImageResource(bean.getNomalRes());
            if (!bean.isSingleImage()) {
                TextView textView = (TextView) itemLayout.getChildAt(1);
                textView.setTextColor(getResources().getColor(bean.getNomalColor()));
                if (i == index) {
                    textView.setTextColor(getResources().getColor(bean.getSelectColor()));
                }
            }
            if (i == index) {
                imageView.setImageResource(bean.getSelectRes());
            }
        }

    }

    public interface OnClickListener{
        void onClick(int index);
    }

}
