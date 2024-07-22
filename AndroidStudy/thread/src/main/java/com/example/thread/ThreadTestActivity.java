package com.example.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ThreadTestActivity extends AppCompatActivity {
    private Button btn_getbaidu;
    private Button btn_img;
    private ImageView iv_img;
    private TextView tv_getbaidu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);

        findViews();

        getbaidu();

        getimg();
    }

    private void getimg() {
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 114514:
                        Bitmap bitmap = (Bitmap) msg.obj;
                        iv_img.setImageBitmap(bitmap);
                        break;
                }
            }
        };
        btn_img.setOnClickListener(new View.OnClickListener() {
            URL url = null;
            InputStream inputStream = null;
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            url = new URL("https://profile-avatar.csdnimg.cn/8e4c56733fdd4dda90854384976d4bb0_ih_lzh.jpg!1");
                            inputStream = url.openStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            Message message = handler.obtainMessage();
                            message.obj = bitmap;
                            message.what = 114514;
                            handler.sendMessage(message);
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } finally {
                            if(null != inputStream){
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }).start();
            }
        });
    }

    private void getbaidu() {
        btn_getbaidu.setOnClickListener(new View.OnClickListener() {
            URL url = null;
            InputStream inputStream = null;
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //访问网络需要获取系统权限
                            url = new URL("https://www.baidu.com/");
                            //获取流输入
                            inputStream = url.openStream();
                            StringBuffer stringBuffer = new StringBuffer();
                            byte[] bytes = new byte[1024];
                            while(inputStream.read(bytes) != -1){
                                String str = new String(bytes, 0, bytes.length);
                                stringBuffer.append(str);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_getbaidu.setText(stringBuffer.toString());
                                }
                            });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }finally {
                            if(null != inputStream){
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }).start();
            }
        });
    }

    private void findViews() {
        btn_getbaidu = findViewById(R.id.btn_getbaidu);
        btn_img = findViewById(R.id.btn_img);
        iv_img = findViewById(R.id.iv_img);
        tv_getbaidu = findViewById(R.id.tv_getbaidu);
    }
}