package com.example.ecnutimebank.ui.publish;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ecnutimebank.BaseActivity;
import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Order;
import com.example.ecnutimebank.entity.User;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.example.ecnutimebank.ui.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PublishDetailActivity extends AppCompatActivity {

    private MaterialEditText etTitle;
    private MaterialEditText etName;
    private MaterialEditText etTime;
    private MaterialEditText etTel;
    private MaterialEditText etAddress;
    private MaterialEditText etDescription;
    private MaterialSpinner type_spinner;
    private Button bt_publish;
    private String type_string="陪聊";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }
    private void initView() {

        setContentView(R.layout.activity_publish_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        etTitle=findViewById(R.id.et_title);
        etName= findViewById(R.id.et_name);
        etAddress= findViewById(R.id.et_address);
        etDescription = findViewById(R.id.et_description);
        etTel = findViewById(R.id.et_tel);
        etTime = findViewById(R.id.et_time);

        type_spinner = findViewById(R.id.type_spinner);
        type_spinner.setItems("陪聊", "散步", "代买", "打扫", "其他");
        type_spinner.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> type_string = item);
    }

    private void setListener(){
        bt_publish =findViewById(R.id.publish_finish_btn);
        bt_publish.setOnClickListener(view -> {
            String title =etTitle.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String time = etTime.getText().toString().trim();
            String telephone = etTel.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String type = type_string.trim();
            publish(title,name,time,telephone,address,description,type);

            Intent intent = new Intent(PublishDetailActivity.this, BaseActivity.class);
            startActivity(intent);
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void publish(String title,String name,String time,String telephone,String address,String description,String type){

        SharedPreferences userinfo = getSharedPreferences("user_info",MODE_MULTI_PROCESS);
        int cueUserId=userinfo.getInt("userId",0);
        HashMap params = new HashMap<>();
        params.put("orderPublisherId",cueUserId);
        params.put("orderTitle",title);
        params.put("orderTime",time);
        params.put("orderType",type);
        params.put("orderAddress",address);
        params.put("orderTelephone",telephone);
        params.put("orderDescription",description);
        JSONObject jsonObject = new JSONObject(params);

        OkGo.<Result<Order>>post(AppConst.Order.publish_new_order)
                .tag(this)
                .upJson(jsonObject)
                .execute(new JsonCallBack<Result<Order>>() {
                    @Override
                    public void onSuccess(Response<Result<Order>> response) {
                        Log.d("publish", response.body().getMessage());
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            Log.d("publish", "发布需求成功!");
                        }
                        else{
                            Log.d("publish", "发布需求失败!");
                        }
                    }
                });
    }

}
