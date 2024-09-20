package com.example.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyView extends View {

    //控件仅在代码中实例化
    public MyView(Context context) {
        super(context);
    }

    //控件需要应用在layout布局文件中， attrs就是我们在布局文件中配置的各个属性
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //View需要随着主题而改变属性值
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
    }
}
