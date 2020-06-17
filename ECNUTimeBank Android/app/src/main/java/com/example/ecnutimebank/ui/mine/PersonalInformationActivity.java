package com.example.ecnutimebank.ui.mine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Facility;
import com.example.ecnutimebank.entity.User;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.example.ecnutimebank.ui.login.LoginActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sp;
    private TextView nickname_text;
    private String newNickname;
    private TextView gender_text;
    private String newGender;
    private String telephone;
    private int curUserId;
    private TextView id_text ;
    private TextView profile_text ;
    private ImageView profile_iv ;
    private ImageView changeProfile;
    private TextView Nickname_text;
    private ImageView changeNickname_iv;
    private TextView Gender_text ;
    private ImageView changeGender_iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initView();
        initData();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("个人信息");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(){
        id_text = findViewById(R.id.MI_id_textView);
        profile_text = findViewById(R.id.MI_profile_textView);
        profile_iv = findViewById(R.id.MI_profile_imageView);
        changeProfile = findViewById(R.id.changeProfile_imageView);
        Nickname_text = findViewById(R.id.MI_Nickname_textView);
        changeNickname_iv = findViewById(R.id.changeNickname_imageView);
        Gender_text = findViewById(R.id.Gender_textView);
        changeGender_iv = findViewById(R.id.changeGender_imageView);
        gender_text = findViewById(R.id.MI_gender_textView);
        nickname_text = findViewById(R.id.MI_nickname_textView);
        Gender_text.setOnClickListener(this);
        gender_text.setOnClickListener(this);
        changeGender_iv.setOnClickListener(this);
        Nickname_text.setOnClickListener(this);
        nickname_text.setOnClickListener(this);
        changeNickname_iv.setOnClickListener(this);
        profile_iv.setOnClickListener(this);
        profile_text.setOnClickListener(this);
        changeProfile.setOnClickListener(this);

    }
    //修改昵称
    private void dialog_change_nickname(){
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("Nickname")
                .setView(et)
                .setPositiveButton("Commit", new DialogInterface.OnClickListener() {
                    @SuppressLint("CommitPrefEdits")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newNickname = et.getText().toString();
                        changeNickName(newNickname);
                    }
                }).setNegativeButton("Cancel",null).show();
    }
    private void changeNickName(String newNickname){
        int curUserId = sp.getInt("userId",0);
        HashMap params = new HashMap<>();
        params.put("userId",curUserId);
        params.put("userName",newNickname);
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<Result<User>>put(AppConst.User.update_user)
                .tag(this)
                .upJson(jsonObject)
                .execute(new JsonCallBack<Result<User>>() {
                    @Override
                    public void onSuccess(Response<Result<User>> response) {
                        Log.d("update", response.body().getMessage());
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            nickname_text.setText(newNickname);
                        }
                        else{
                            Log.d("update", "更改用户名失败");
                        }
                    }
                });
        initData();
    }
    //修改性别
    private void dialog_choose_gender(){
        final String[] items ={"Male","Female"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("Gender"); //设置标题
        builder.setSingleChoiceItems(items,0,new DialogInterface.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newGender = items[which];;
                changeGender(newGender);
            }
        });
//        builder.setPositiveButton("Commit",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
        builder.create().show();
    }
    private void changeGender(String newGender){
        int curUserId = sp.getInt("userId",0);
        HashMap params = new HashMap<>();
        params.put("userId",curUserId);
        params.put("userGender",newGender);
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<Result<User>>put(AppConst.User.update_user)
                .tag(this)
                .upJson(jsonObject)
                .execute(new JsonCallBack<Result<User>>() {
                    @Override
                    public void onSuccess(Response<Result<User>> response) {
                        Log.d("update", response.body().getMessage());
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            Log.d("update", response.body().getMessage());
                            gender_text.setText(newGender);
                        }
                        else{
                            Log.d("update", "更改用户性别失败");
                        }
                    }
                });
        initData();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.MI_profile_imageView:
            case R.id.MI_profile_textView:
            case R.id.changeProfile_imageView:
                Intent intent = new Intent(PersonalInformationActivity.this, ProfileDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.MI_Nickname_textView:
            case R.id.MI_nickname_textView:
            case R.id.changeNickname_imageView:
                dialog_change_nickname();
                break;
            case R.id.Gender_textView:
            case R.id.MI_gender_textView:
            case R.id.changeGender_imageView:
                dialog_choose_gender();
                break;
        }
    }

    private void initData(){
        sp = getSharedPreferences("user_info", Context.MODE_MULTI_PROCESS);
        curUserId = sp.getInt("userId",0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkGo.<Result<User>>get(AppConst.User.get_user+"/"+curUserId)
                        .tag(this)
                        .execute(new JsonCallBack<Result<User>>() {
                            @Override
                            public void onSuccess(Response<Result<User>> response) {
                                if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                                    User curUser = response.body().getData();
                                    newNickname = curUser.getUserName();
                                    newGender = curUser.getUserGender();
                                    telephone = curUser.getUserTelephone();
                                    nickname_text.setText(newNickname);
                                    gender_text.setText(newGender);
                                    id_text.setText(telephone);
                                }
                            }
                        });
            }
        }).start();
    }

}
