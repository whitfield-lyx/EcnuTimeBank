package com.example.ecnutimebank.ui.mine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.ui.login.LoginActivity;

public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sp;
    private TextView nickname_text;
    private String newNickname;
    private TextView gender_text;
    private String newGender;
    private String telephone;
    private int curUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initData();
        initView();
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
        TextView profile_text = findViewById(R.id.MI_profile_textView);
        ImageView profile_iv = findViewById(R.id.MI_profile_imageView);
        ImageView changeProfile = findViewById(R.id.changeProfile_imageView);
        TextView Nickname_text = findViewById(R.id.MI_Nickname_textView);
        nickname_text = findViewById(R.id.MI_nickname_textView);
        ImageView changeNickname_iv = findViewById(R.id.changeNickname_imageView);
        TextView Gender_text = findViewById(R.id.Gender_textView);
        gender_text = findViewById(R.id.MI_gender_textView);
        ImageView changeGender_iv = findViewById(R.id.changeGender_imageView);
        Gender_text.setOnClickListener(this);
        gender_text.setOnClickListener(this);
        changeGender_iv.setOnClickListener(this);
        Nickname_text.setOnClickListener(this);
        nickname_text.setOnClickListener(this);
        changeNickname_iv.setOnClickListener(this);
        profile_iv.setOnClickListener(this);
        profile_text.setOnClickListener(this);
        changeProfile.setOnClickListener(this);

        TextView id_text = findViewById(R.id.MI_id_textView);
        id_text.setText(telephone);

        nickname_text.setText(newNickname);
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
                        nickname_text.setText(newNickname);
                        sp.edit().putString("userName", newNickname);
                        sp.edit().apply();
                    }
                }).setNegativeButton("Cancel",null).show();
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
                newGender = items[which];
                gender_text.setText(newGender);
                sp.edit().putString("Gender", newGender);
                sp.edit().apply();
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
        newNickname = sp.getString("userName","张三");
        telephone = sp.getString("telephone","17333333333");
    }

}
