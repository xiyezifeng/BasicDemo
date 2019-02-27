package com.example.lixiao.basicdemo.app.widget.bottombar;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.LinearGradient;
import android.graphics.MaskFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.example.lixiao.basicdemo.R;
import com.example.lixiao.basicdemo.support.utils.Utils;

import androidx.annotation.Nullable;

public class BarBackView extends View {

    /**
     * 背景颜色
     */
    private int barBckColor;
    /**
     * 布局高度
     */
    private int backViewHeight;

    /**
     * 中心背景view间隔
     */
    private int centerIntervalSize;

    /**
     * 中心浮动的item大小
     */
    private int centerSize;

    /**
     * 屏幕宽度
     */
    private int screenWidth;

    private int centerImageViewBottomMargin;

    private int shadowHeight ;

    /**
     * 圆弧圆心
     */
    private int arcCenter;

    private Paint backPaint;
    private Paint cirPaint;
    private Paint linePaint;

    private boolean isInit;


    private Paint shadow;

    public BarBackView(Context context) {
        super(context);
    }

    public BarBackView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BarBackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBarBckColor(int barBckColor) {
        this.barBckColor = barBckColor;
    }

    public void setBackViewHeight(int backViewHeight) {
        this.backViewHeight = backViewHeight;
    }

    public void setCenterIntervalSize(int centerIntervalSize) {
        this.centerIntervalSize = centerIntervalSize;
    }

    public void setShadowHeight(int shadowHeight) {
        this.shadowHeight = shadowHeight;
    }

    public void setCenterSize(int centerSize) {
        this.centerSize = centerSize;
    }

    public void setCenterSizeBottomMargin(int centerImageViewBottomMargin) {
        this.centerImageViewBottomMargin = centerImageViewBottomMargin;
    }

    public void init(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }


        backPaint = new Paint();
        backPaint.setColor(getResources().getColor(barBckColor));
        backPaint.setAntiAlias(true);
        backPaint.setDither(true);
        backPaint.setStyle(Paint.Style.FILL);

        cirPaint = new Paint();
        cirPaint.setColor(getResources().getColor(barBckColor));
        cirPaint.setAntiAlias(true);
        cirPaint.setDither(true);
        cirPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.colorBottomBar));
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(3);


        // 设置光源的方向
        float[] direction = new float[]{ 1, 1, 1 };
        //设置环境光亮度
        float light = 0.4f;
        // 选择要应用的反射等级
        float specular = 6;
        // 向mask应用一定级别的模糊
        float blur = shadowHeight;
        BlurMaskFilter emboss=new BlurMaskFilter(blur ,BlurMaskFilter.Blur.NORMAL);

        shadow = new Paint();
        shadow.setColor(getResources().getColor(R.color.colorShadow_1));
        shadow.setAntiAlias(true);
        shadow.setDither(true);
        shadow.setStyle(Paint.Style.FILL);
        shadow.setStrokeWidth(1);
        shadow.setMaskFilter(emboss);

        screenWidth = Utils.getScreenWidth(getContext());
        isInit = true;
        //计算圆心

        arcCenter = centerImageViewBottomMargin - shadowHeight;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制
        if (isInit) {
            //绘制左半边
            int leftWidth = ((screenWidth - centerSize) / 2) - centerIntervalSize;
            //绘制右半边
            int rightWidth = leftWidth + centerSize + centerIntervalSize * 2;

            int controllY = -arcCenter;
            int bian = (rightWidth - leftWidth) / 2;
            double radius = Math.sqrt(controllY * controllY + bian * bian);
            int[] mMinColors = {Color.parseColor("#000000") , Color.parseColor("#f1f1f1")  };
            float [] positions = {0.5f,0.5f};
            //先创建一个渲染器
            LinearGradient mSweepGradient = new LinearGradient(0, shadowHeight , 0 , 0 ,mMinColors , null, Shader.TileMode.CLAMP);
            shadow.setShader(mSweepGradient);
            //画阴影
            Path shadowcirpath = new Path();
            shadowcirpath.addCircle(screenWidth / 2, controllY, (float) radius - shadowHeight, Path.Direction.CW);
            Path shadowpath = new Path();
            shadowpath.moveTo(0, 0);
            shadowpath.lineTo(screenWidth, 0);
            shadowpath.lineTo(screenWidth, backViewHeight);
            shadowpath.lineTo(0, backViewHeight);
            shadowpath.op(shadowcirpath, Path.Op.DIFFERENCE);
            canvas.drawPath(shadowpath, shadow);


            //画背景
            Path cirpath = new Path();
            cirpath.addCircle(screenWidth / 2, controllY, (float) radius, Path.Direction.CW);

            Path path = new Path();
            path.moveTo(0, shadowHeight);
            path.lineTo(screenWidth, shadowHeight);
            path.lineTo(screenWidth, backViewHeight);
            path.lineTo(0, backViewHeight);
            path.op(cirpath, Path.Op.DIFFERENCE);

            canvas.drawPath(path, cirPaint);

        }
    }


}
