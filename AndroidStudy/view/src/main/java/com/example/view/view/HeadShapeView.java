package com.example.view.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class HeadShapeView extends View {

    private Paint paint;

    public HeadShapeView(Context context) {
        this(context, null);
    }

    public HeadShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();

        float headRadius = Math.min(width, height) / 4;

        // Draw the head (circle)
        canvas.drawCircle(width / 2, height / 3, headRadius, paint);

        // Draw the neck (a small rectangle below the head)
        canvas.drawRect(width / 2 - headRadius / 4, height / 3 + headRadius,
                width / 2 + headRadius / 4, height / 3 + headRadius * 1.5f, paint);

        // Draw the shoulders (curved lines)
        Path path = new Path();
        path.moveTo(width / 2 - headRadius, height / 3 + headRadius * 1.5f);
        path.quadTo(width / 2, height / 3 + headRadius * 2,
                width / 2 + headRadius, height / 3 + headRadius * 1.5f);
        canvas.drawPath(path, paint);
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8f);
        paint.setAntiAlias(true);
    }
}
