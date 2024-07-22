package com.example.practice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyButton extends androidx.appcompat.widget.AppCompatButton {

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("btn", "btn1 onTouch");
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                Log.i("btn", "bnt1 Up");
                break;
            case MotionEvent.ACTION_DOWN:
                Log.i("btn", "bnt1 Down");
                break;
        }
        //返回是否成功解决事件
        return true;
    }

}
