package com.example.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {
    private Button btn_getNetImg;
    private ImageView img_netImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        btn_getNetImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNetImg();
            }
        });
    }

    private void getNetImg() {
        Glide.with(this)
                .load("https://i0.hdslb.com/bfs/article/38aad4e7c4ca2048b95aa6659fb1fcaf43d0f597.gif")
                .placeholder(R.mipmap.loading)//设置图片加载完成之前的状态
                .error(R.mipmap.error)//设置图片加载失败的状态
                .fallback(R.mipmap.nullpic)//设置图片为空的状态
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_netImg);
    }

    private void findViews() {
        btn_getNetImg = findViewById(R.id.btn_getNetImg);
        img_netImg = findViewById(R.id.img_netImg);
    }
}