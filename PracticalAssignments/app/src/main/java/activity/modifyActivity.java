package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.practicalassignments.R;

import entity.Userinfo;
import fragment.fragment_mine;

public class modifyActivity extends AppCompatActivity {
    private EditText edt_name;
    private EditText edt_address;
    private EditText edt_pwd;
    private Button btn_over;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        findViews();
        get_Intent();

        setListeners();
    }

    private void setListeners() {
        btn_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString().trim();
                String address = edt_address.getText().toString().trim();
                String pwd = edt_pwd.getText().toString().trim();

                Userinfo userinfo = new Userinfo();
                userinfo.setName(name);
                userinfo.setAddress(address);
                userinfo.setPassward(pwd);

                Intent intent_back = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", userinfo);
                intent_back.putExtra("userData", bundle);

                intent.setClass(modifyActivity.this, fragment_mine.class);
                setResult(1, intent_back);
                modifyActivity.this.finish();
            }
        });
    }

    private void get_Intent() {
        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("userData");
        Userinfo user = bundle.getSerializable("user", Userinfo.class);
        edt_name.setText(user.getName());
        edt_address.setText(user.getAddress());
        edt_pwd.setText(user.getPassward());
    }

    private void findViews() {
        edt_name = findViewById(R.id.edt_name);
        edt_address = findViewById(R.id.edt_address);
        edt_pwd = findViewById(R.id.edt_pwd);
        btn_over = findViewById(R.id.btn_over);
    }
}