package utils;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import entity.Userinfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserUtil {
    public static Userinfo user;
    public static void setUser(Userinfo user1){
        user = user1;
    }
    public static void modifyUserDb(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                modify();
            }
        }).start();
    }

    private static void modify() {
        OkHttpClient okHttpClient = new OkHttpClient();
        String json = new Gson().toJson(user);
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json;charset=utf-8"),
                json
        );
        Request request = new Request.Builder()
                .post(requestBody)
                .url(HostUtil.Host + "/modify")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("modify", response.body().string());
            }
        });
    }
}
