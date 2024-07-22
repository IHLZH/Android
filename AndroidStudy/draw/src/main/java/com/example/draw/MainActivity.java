package com.example.draw;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btn_draw;
    private ImageView img_draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        btn_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //drawCle();
                //drawPath();
                drawCaptcha(); //绘制验证码
            }
        });
    }

    private void drawCaptcha() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.BLACK);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(100, 100, 400, 200, paint);

        char[] number = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
        Random random = new Random();
        char[] randomNum = new char[4];
        int index = 0;
        while(index < 4){
            randomNum[index++] = number[random.nextInt(number.length)];
        }

        paint.setColor(Color.GREEN);
        paint.setTextSize(50);
        paint.setLetterSpacing(1);
        canvas.drawText(randomNum, 0, 4, 100, 160, paint);

        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        for(int i = 0; i < 30; i++){
            int color = Color.argb(60,
                    55 + random.nextInt(200),
                    55 + random.nextInt(200),
                    55 + random.nextInt(200));
            paint.setColor(color);

            path.moveTo(100, 100 + random.nextInt(100));

            int begin_x = 100 + random.nextInt(100);
            int begin_y = 100 + random.nextInt(100);

            int end_x = 400;
            int end_y = 100 + random.nextInt(100);
            path.quadTo(begin_x, begin_y, end_x, end_y);
            canvas.drawPath(path, paint);
        }

        img_draw.setImageBitmap(bitmap);
    }

    private void drawPath() {
        Bitmap bitmap = Bitmap.createBitmap(
                800,
                800,
                Bitmap.Config.ARGB_8888
        );
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        //设置Path
        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(200, 200);
        path.lineTo(300, 100);
        path.close();
        canvas.drawPath(path, paint);
        canvas.drawTextOnPath("fnasdujfvbasiofubnaiwusfcbniasufnc", path, 20, 20, paint);
        img_draw.setImageBitmap(bitmap);
    }

    private void drawCle() {
        //创建画纸（bitmap）
        Bitmap bitmap = Bitmap.createBitmap(
                800,
                800,
                Bitmap.Config.ARGB_8888     //表示每个颜色用四个通道来显示
        );
        //创建canvas（画架以及画手的功能）
        Canvas canvas = new Canvas(bitmap);
        //画笔  并设置属性
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE); //不填充（默认填充）
        //绘制一个圆 圆心加半径 传入画笔
        canvas.drawCircle(100, 100,100, paint);
        img_draw.setImageBitmap(bitmap);
    }

    private void findViews() {
        btn_draw = findViewById(R.id.btn_draw);
        img_draw = findViewById(R.id.img_draw);
    }
}