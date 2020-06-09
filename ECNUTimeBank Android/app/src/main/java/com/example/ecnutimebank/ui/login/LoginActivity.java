package com.example.ecnutimebank.ui.login;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;

import com.example.ecnutimebank.BaseActivity;
import com.example.ecnutimebank.entity.User;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.R;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btGo;
    private CardView cv;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setListener();
    }
    private void initView() {

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btGo = findViewById(R.id.bt_go);
        cv = findViewById(R.id.cv);
        fab = findViewById(R.id.fab);
    }

    private void setListener() {
        btGo.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                final String telephone = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                OkGo.<Result<User>>post(AppConst.User.login)
                        .tag(this)
                        .params("telephone", telephone)
                        .params("password", password)
                        .execute(new JsonCallBack<Result<User>>() {
                            @Override
                            public void onSuccess(Response<Result<User>> response) {
                                Log.d("login", response.body().getMessage());
                                if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                                    Explode explode = new Explode();
                                    explode.setDuration(500);
                                    getWindow().setExitTransition(explode);
                                    getWindow().setEnterTransition(explode);
                                    ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
                                    Intent i2 = new Intent(LoginActivity.this, BaseActivity.class);
                                    startActivity(i2, oc2.toBundle());
                                }
                            }
                        });
                fab.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View view) {
                        getWindow().setExitTransition(null);
                        getWindow().setEnterTransition(null);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, fab, fab.getTransitionName());
                        startActivity(new Intent(LoginActivity.this, RegisterActivity.class), options.toBundle());
                    }
                });
            }
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        fab.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fab.setVisibility(View.VISIBLE);
    }
}

