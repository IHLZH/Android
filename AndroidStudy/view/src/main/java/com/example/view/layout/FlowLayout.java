package com.example.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/*
 自定义ViewGroup，FlowLayout流式布局
 */

public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";
    private List<List<View>> allLineViews; //表示所有行所有的View
    private List<Integer> lineHeights; //表示每行的高度
    //private int ;

    //private int mHorizontalSpacing = dp

    //实现带有1，2，3个参数的构造方法
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void clearMeasureParams(){
        allLineViews.clear();
        lineHeights.clear();
    }

    //onMeasure方法，用于度量
    //参数MearSure存放了View的宽高信息和度量规则
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        clearMeasureParams(); //onMeasure方法不一定只会被调用一次

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        //ViewGroup解析的父亲给的宽高,可以理解为当前ViewGroup的最大容纳值
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        //存储一行中所有的View
        List<View> lineView = new ArrayList<>();
        int lineWidthUsed = 0; //一行中用过的宽度
        int lineHeight = 0; //一行的高度，即一行中高度最高View的高度

        //根据子View的宽高，计算出的父ViewGroup的宽高，初始值为0
        int parentNeededWidth = 0;
        int parentNeededHeight = 0;

        //先度量孩子
        //度量宽高 ->解析xml文件中的layout_width和layout_height
        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++){
            View childView = getChildAt(i);

            LayoutParams childLp = getLayoutParams();

            //将layoutParams转换为MeasureSpec
            //获取子View的度量宽高
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                    paddingLeft + paddingRight,
                    childLp.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                    paddingTop + paddingBottom,
                    childLp.height);
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            //再度量完之后，获取子View度量完之后的宽高
            int childMeasuredWidth = childView.getMeasuredWidth();
            int childMeasuredHeight = childView.getMeasuredHeight();

            //父容器一行中剩余的空间不足以容纳这个子View，需要换行
            if(childMeasuredWidth + lineWidthUsed > selfWidth){
                parentNeededHeight += lineHeight;
                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed);

                //清空记录的该行的数据，准备记录下一行
                allLineViews.add(lineView);
                lineHeights.add(lineHeight);
                lineView = new ArrayList<>();
                lineHeight = 0;
                lineWidthUsed = 0;
            }else{
                lineView.add(childView);
                lineHeight = Math.max(lineHeight, childMeasuredHeight);
                lineWidthUsed += childMeasuredWidth;
            }

            //处理最后一行的特殊情况
            if(i == childCount - 1){
                allLineViews.add(lineView);
                lineHeights.add(lineHeight);
                parentNeededHeight += lineHeight;
                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed);
            }
        }

        //再度量自己，并保存宽高，
        //保存后父类View可通过get方法获取到自己的宽高
        //保存自己的实际所需宽高
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = (widthMode == MeasureSpec.EXACTLY)?selfWidth:parentNeededWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY)?selfHeight:parentNeededHeight;

        //resolveSize() 根据测量模式确定最终的宽高值

        setMeasuredDimension(realWidth, realHeight);
    }

    //实现onLayout方法,获取布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = allLineViews.size(); //一共有多少行

        //初始的原点位置
        int curL = getPaddingLeft();
        int curT = getPaddingTop();

        for(int i = 0; i < count; i++){
            List<View> lineView = allLineViews.get(i);
            for (View view : lineView) {
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                view.layout(curL, curT, curL + measuredWidth, curT + measuredHeight);
                curL += measuredWidth;
                curT += measuredHeight;
            }
            curL = getPaddingLeft();
            curT += lineHeights.get(i);
        }
    }
}
