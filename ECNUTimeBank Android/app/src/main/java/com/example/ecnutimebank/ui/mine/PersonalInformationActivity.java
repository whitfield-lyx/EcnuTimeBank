package com.example.ecnutimebank.ui.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initView();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("个人信息");
        }
    }

    private void dialog_choose_gender(){
        final String[] items ={"Male","Female"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("Gender"); //设置标题
        builder.setSingleChoiceItems(items,0,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialog.dismiss();
                /* 待实现*/
            }
        });
        builder.setPositiveButton("Commit",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                /* 待实现*/
            }
        });
        builder.create().show();
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
        TextView nickname_text = findViewById(R.id.MI_nickname_textView);
        ImageView changeNickname_iv = findViewById(R.id.changeNickname_imageView);
        TextView Gender_text = findViewById(R.id.Gender_textView);
        TextView gender_text = findViewById(R.id.MI_gender_textView);
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
    }

    private void dialog_change_nickname(){
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("Nickname")
                .setView(et)
                .setPositiveButton("Commit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        /*  待实现  */
                    }
                }).setNegativeButton("Cancel",null).show();
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

}
