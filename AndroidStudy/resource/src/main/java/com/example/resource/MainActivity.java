package com.example.resource;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Button btn_raw;
    private Button btn_assets;
    private ImageView img_raw;
    private ImageView img_assets;
    private ImageView iv_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        btn_raw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRawImage();
            }
        });

        btn_assets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAssetsImage();
            }
        });

        iv_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getAssetsImage() {
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("pic2.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            img_assets.setImageBitmap(bitmap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getRawImage() {
        Resources resources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.raw.pic1);
//        img_raw.setImageBitmap(bitmap);
        InputStream inputStream = resources.openRawResource(R.raw.pic1);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        img_raw.setImageBitmap(bitmap);
    }

    private void findViews() {
        btn_raw = findViewById(R.id.btn_raw);
        btn_assets = findViewById(R.id.btn_assets);
        img_raw = findViewById(R.id.img_raw);
        img_assets = findViewById(R.id.img_assets);
        iv_state = findViewById(R.id.iv_state);
    }
}