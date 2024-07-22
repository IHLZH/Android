package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicalassignments.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import entity.Userinfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import utils.HostUtil;
import utils.Result;
import utils.UserUtil;

public class loginActivity extends AppCompatActivity {
    private EditText edt_login_account;
    private EditText edt_login_passward;
    private Button btn_main_login;
    private final String Host = HostUtil.Host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        setListeners();
    }

    private void setListeners() {
        btn_main_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = edt_login_account.getText().toString().trim();
                String passward = edt_login_passward.getText().toString().trim();
                login(account, passward);
            }
        });
    }

    private void login(String account, String passward) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("account", account)
                .add("pwd", passward)
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(Host + "login")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String json = bufferedReader.readLine();
                Gson gson = new Gson();
                Result result = gson.fromJson(json, new TypeToken<Result<Userinfo>>() {}.getType());
                if(result.getCode() != 200){
                    showResultOnUiThread(result.getMsg());
                }else{
                    Userinfo user = (Userinfo) result.getData();
                    UserUtil.user = user;
                    gotoHome();
                    showResultOnUiThread(result.getMsg());
                }
                if(inputStream != null)inputStream.close();
            }
        });
    }

    private void gotoHome() {
        Intent intent = new Intent();
        intent.setClass(this, homeActivity.class);
        startActivity(intent);
        loginActivity.this.finish();
    }

    private void showResultOnUiThread(String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(loginActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViews() {
        edt_login_account = findViewById(R.id.edt_login_account);
        edt_login_passward = findViewById(R.id.edt_login_passward);
        btn_main_login = findViewById(R.id.btn_main_login);
    }
}