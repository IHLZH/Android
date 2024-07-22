package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import entity.Shop;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopService {
    private static final String Host = HostUtil.Host;
    public static List<Shop> shopList;
    public void getShop(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                askShop();
            }
        });
        thread.start();
    }

    public void askShop(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder()
                .url(Host + "shop");
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String json = "";
                String str = null;
                while((str = bufferedReader.readLine()) != null){
                    json += str;
                }
                Gson gson = new Gson();
                List<Shop> shopList1 = gson.fromJson(json, new TypeToken<List<Shop>>() {}.getType());
                shopList = shopList1;
            }
        });
    }
}
