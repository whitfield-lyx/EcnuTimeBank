package com.example.ecnutimebank.ui.publish.accepted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Order;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class AcceptedDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView name;
    private TextView time;
    private TextView place;
    private TextView money;
    private TextView describe;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement_detail);

        Intent intent = getIntent();
        name = findViewById(R.id.requirement_name_detail);
        time = findViewById(R.id.requirement_time_detail);
        place = findViewById(R.id.requirement_place_detail);
        money = findViewById(R.id.requirement_money_detail);
        describe = findViewById(R.id.requirement_describe_content);

        cancelBtn = findViewById(R.id.requirement_btn);

        name.setText(intent.getStringExtra("name"));
        time.setText(intent.getStringExtra("time"));
        place.setText(intent.getStringExtra("place"));
        money.setText(intent.getStringExtra("money"));
        describe.setText(intent.getStringExtra("describe"));

        cancelBtn.setOnClickListener(this);
        cancelBtn.setText("取消订单");
        cancelBtn.setBackground(getResources().getDrawable(R.drawable.btn_refuse));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = getIntent();
        int curOrderId = intent.getIntExtra("id",0);
        HashMap params = new HashMap<>();
        params.put("orderId",curOrderId);
        params.put("orderAccpetersId",0);
        JSONObject jsonObject = new JSONObject(params);
        OkGo.<Result<Order>>put(AppConst.Order.update_order)
                .tag(this)
                .upJson(jsonObject)
                .execute(new JsonCallBack<Result<Order>>() {
                    @Override
                    public void onSuccess(Response<Result<Order>> response) {
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            Log.i("AcceptedDetailActivity", "取消订单成功");
                        } else {
                            Log.e("AcceptedDetailActivity", "取消订单失败");
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
