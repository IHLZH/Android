package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.practicalassignments.R;

import activity.modifyActivity;
import entity.Userinfo;
import utils.UserUtil;

public class fragment_mine extends Fragment {
    private TextView tv_name;
    private TextView tv_address;
    private ImageView iv_avater;
    private Button btn_modify;
    private ActivityResultLauncher<Intent> launcher;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_mine, null);
        iv_avater = view.findViewById(R.id.iv_avater);
        tv_name = view.findViewById(R.id.tv_name);
        tv_address = view.findViewById(R.id.tv_address);
        btn_modify = view.findViewById(R.id.btn_modify);

        Userinfo userinfo = UserUtil.user;
        tv_name.setText(userinfo.getName());
        tv_address.setText(userinfo.getAddress());
        Glide.with(this)
                .load(userinfo.getAvater())
                .circleCrop()
                .into(iv_avater);
        getLauncher();
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToModify(userinfo);
            }
        });
        return view;
    }

    private void getLauncher() {
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //获取跳转Activity返回的数据
                        switch (result.getResultCode()) { //resultCode在跳转Activity设置
                            case 1:
                                Intent intent = result.getData();
                                Bundle bundle = intent.getBundleExtra("userData");
                                Userinfo user = bundle.getSerializable("user", Userinfo.class);
                                set_Text(user);
                                modifyUser(user);
                                break;
                        }
                    }
                }
        );
    }

    private void goToModify(Userinfo userinfo) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", userinfo);
        intent.putExtra("userData", bundle);
        intent.setClass(fragment_mine.this.getContext(), modifyActivity.class);
        launcher.launch(intent);
    }

    private void modifyUser(Userinfo user) {
        UserUtil.user.setName(user.getName());
        UserUtil.user.setAddress(user.getAddress());
        UserUtil.user.setPassward(user.getPassward());
        UserUtil.modifyUserDb();
    }

    private void set_Text(Userinfo user) {
        tv_name.setText(user.getName());
        tv_address.setText(user.getAddress());
    }

}
