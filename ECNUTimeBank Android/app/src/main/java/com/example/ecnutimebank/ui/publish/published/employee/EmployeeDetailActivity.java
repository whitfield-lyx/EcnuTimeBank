/**
 * @author K.makise on 2020/5/23
 */


package com.example.ecnutimebank.ui.publish.published.employee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Order;
import com.example.ecnutimebank.entity.User;
import com.example.ecnutimebank.entity.VolunteerFor;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetailActivity extends AppCompatActivity implements EmployeeDetailAdapter.OnItemClickListener {
    private List<User> volunteers = new ArrayList<>();
    private EmployeeDetailAdapter adapter;
    private RecyclerView recyclerView;
    private Intent intent;
    private Integer curOrderId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_published_detail);
        intent = getIntent();
        initView();
        initData();
    }

    private void initData() {
        curOrderId = intent.getIntExtra("orderId",0);
        Log.d("EmployeeDetailActivity", "initData: "+curOrderId );
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkGo.<Result<List<VolunteerFor>>>get(AppConst.User.get_volunteer+"/"+curOrderId)
                        .tag(this)
                        .execute(new JsonCallBack<Result<List<VolunteerFor>>>() {
                            @Override
                            public void onSuccess(Response<Result<List<VolunteerFor>>> response) {
                                if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                                    List<VolunteerFor> data = response.body().getData();
                                    for (VolunteerFor datum : data) {
//                                        todo 获取用户详细信息 这里暂时只有userId
                                        User user = new User();
                                        user.setUserId(datum.getVolunteerId());
                                        volunteers.add(user);
                                    }
                                    adapter.setVolunteers(volunteers);
                                    adapter.notifyDataSetChanged();
                                    Log.d("Adapter info", adapter.getData().toString());
                                } else {
                                    Log.e("volunteer", "志愿者获取失败");
                                }
                            }
                        });
            }
        }).start();
    }

    private void initView(){
        recyclerView = findViewById(R.id.employee_recycler_view);
        adapter = new EmployeeDetailAdapter(volunteers, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        Toolbar toolbar = findViewById(R.id.toolbar_employee_detail);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void onAccept(Integer id) {
        Log.e("Debug!!!!!", "" + id);
        OkGo.<Result<Order>>put(AppConst.Order.confirm_order)
                .tag(this)
                .params("orderId", curOrderId)
                .params("volunteerId", id)
                .execute(new JsonCallBack<Result<Order>>() {
                    @Override
                    public void onSuccess(Response<Result<Order>> response) {
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            Log.i("AcceptedDetailActivity", "选取志愿者成功");

                        } else {
                            Log.e("AcceptedDetailActivity", "选取志愿者失败");
                        }
                    }
                });
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            finish();
        }
    }

    @Override
    public void onRefuse(Integer id) {
        OkGo.<Result<VolunteerFor>>delete(AppConst.Order.delete_volunteer_for)
                .tag(this)
                .params("orderId", curOrderId)
                .params("volunteerId", id)
                .execute(new JsonCallBack<Result<VolunteerFor>>() {
                    @Override
                    public void onSuccess(Response<Result<VolunteerFor>> response) {
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            Log.i("EmployeeDetailActivity", "拒绝志愿者成功" + response.body().getData());
                        } else {
                            Log.e("EmployeeDetailActivity", "拒绝志愿者失败");
                        }
                    }
                });
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            finish();
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
