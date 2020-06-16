package com.example.ecnutimebank.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Facility;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

public class HomePlaceDetailActivity extends AppCompatActivity {

    private TextView facility_id;
    private TextView facility_name;
    private TextView facility_description;
    private Facility curFacility;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_place_detail);
        Intent intent = getIntent();
        id = intent.getIntExtra("facility_id",0);
        initCurFacility();
        initView();
    }
    private void initCurFacility() {
        curFacility = new Facility();
        OkGo.<Result<Facility>>get(AppConst.Facility.get_facility+"/"+id)
                .tag(this)
                .execute(new JsonCallBack<Result<Facility>>() {
                    @Override
                    public void onSuccess(Response<Result<Facility>> response) {
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            curFacility = response.body().getData();
                            Log.d("Facility", "单个设施获取成功!"+curFacility.toString());
                            setViewData(curFacility);
                        } else {
                            Log.d("Facility", "单个设施获取失败!");
                        }
                    }
                });
    }
    public void setViewData(Facility curFacility){
        facility_id.setText(Integer.toString(id));
        facility_name.setText(curFacility.getFacilityName());
        facility_description.setText(curFacility.getFacilityDescription());
    }
    public void initView(){
        facility_id = findViewById(R.id.home_place_id);
        facility_name= findViewById(R.id.home_place_name);
        facility_description= findViewById(R.id.home_place_description);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
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
}
