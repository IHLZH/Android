package com.example.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private Button btn_baidu;
    private TextView tv_baidu;
    private Button btn_getimg;
    private ImageView iv_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        setListeners();

        getImg();
    }

    private void getImg() {
        //创建handler
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 114514:
                        Bitmap bitmap = (Bitmap) msg.obj;
                        iv_img.setImageBitmap(bitmap);
                        Log.i("114514", "获取图片成功！");
                        break;
                }
            }
        };
        //设置监听
        btn_getimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("https://profile-avatar.csdnimg.cn/8e4c56733fdd4dda90854384976d4bb0_ih_lzh.jpg!1");
                            InputStream inputStream = url.openStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            Message msg = handler.obtainMessage();
                            //封装bitmap对象和设置对象编号
                            msg.obj = bitmap;
                            msg.what = 114514;
                            //发送给main线程
                            handler.sendMessage(msg);
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        });
    }

    private void setListeners() {
        btn_baidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //获取百度连接
                            URL url = new URL("https://www.baidu.com/");
                            //获取输入流
                            InputStream inputStream = url.openStream();
                            byte[] bytes = new byte[1024];
                            //存储输入的信息
                            StringBuffer buffer = new StringBuffer();
                            while((inputStream.read(bytes)) != -1){
                                String str = new String(bytes, 0, bytes.length);
                                buffer.append(str);
                            }
                            //在子线程中更改Ui界面，使用runOnUiThread
                            Log.i("baidu", buffer.toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_baidu.setText(buffer.toString());
                                }
                            });
                            //关闭流
                            inputStream.close();
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        });
    }

    private void findViews() {
        btn_baidu = findViewById(R.id.btn_baidu);
        tv_baidu = findViewById(R.id.tv_baidu);
        btn_getimg = findViewById(R.id.btn_getimg);
        iv_img = findViewById(R.id.iv_img);
    }
}


