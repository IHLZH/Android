package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button btn_getBaidu;
    private TextView tv_result;
    private Button btn_postFrom;
    private Button btn_postJson;
    private Button btn_upImg;
    private Button btn_downImg;
    private ImageView img_img;
    private final String Host = "http://10.7.86.129:8080/PAServer/";
    private final String Host2 = "http://10.7.86.129:8080/PAServer/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        setListeners();
    }

    private void setListeners() {
        //get
        btn_getBaidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        visitBaidu();
                    }
                }).start();
            }
        });
        //post 表单信息
        btn_postFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForm();
            }
        });
        //同步post Json格式信息
        btn_postJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postJson();
                    }
                }).start();
            }
        });
        //文件上传
        btn_upImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        upImg();
                    }
                }).start();
            }
        });
        //文件下载
        btn_downImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downImg();
                    }
                }).start();
            }
        });
    }

    private void downImg() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Host2 + "/servlet03")
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            InputStream inputStream = response.body().byteStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    img_img.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void upImg() {
        String path = getFilesDir().getAbsolutePath() +"/zj.png";
        File file = new File(path);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/octet-stream"),
                file
        );
        Request request = new Request.Builder()
                .post(requestBody)
                .url(Host + "/servlet02")
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String result = response.body().string();
            showResultOnUiThread(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void postJson() {
        OkHttpClient okHttpClient = new OkHttpClient();
        //1.创建json数据
        User user = new User();
        user.setUsername("json");
        user.setPwd("123456");
        String json = new Gson().toJson(user);
        //2.通过RequestBody封装json数据
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json;charset=utf-8"),//设置数据类型
                json//数据
        );
        Request request = new Request.Builder()
                .post(requestBody)
                .url(Host + "/servlet02")
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String result = response.body().string();
            showResultOnUiThread(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getForm() {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("account", "1145")
                .add("pwd", "123456")
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(Host + "register")
                .build();
        Call call = okHttpClient.newCall(request);
        //异步执行网络操作
        call.enqueue(new Callback() {
            //请求本身失败时调用
            @Override
            public void onFailure(Call call, IOException e) {
                showResultOnUiThread("请求为空");
            }
            //请求成功时调用
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                showResultOnUiThread(result);
            }
        });
    }

    private void visitBaidu() {
        //1.创建HttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.获取request对象
        Request.Builder builder = new Request.Builder()
                .url("https://www.bilibili.com/");
        Request request = builder.build();
        //3.获取call对象
        Call call = okHttpClient.newCall(request);
        //4.执行网络操作（同步）
        try {
            //同步执行网络操作
            Response response = call.execute();
            String result = response.body().string();
            showResultOnUiThread(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showResultOnUiThread(String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_result.setText(result);
            }
        });
    }

    private void findViews() {
        btn_getBaidu = findViewById(R.id.btn_getBaidu);
        btn_postFrom = findViewById(R.id.btn_postForm);
        btn_postJson = findViewById(R.id.btn_postJson);
        btn_upImg = findViewById(R.id.btn_upImg);
        btn_downImg = findViewById(R.id.btn_downImg);
        img_img = findViewById(R.id.img_img);
        tv_result = findViewById(R.id.tv_result);
    }
}