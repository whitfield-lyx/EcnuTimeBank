package com.example.ecnutimebank.ui.requirements;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.User;
import com.example.ecnutimebank.entity.VolunteerFor;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import java.util.HashMap;

public class RequirementDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView name;
    private TextView time;
    private TextView place;
    private TextView money;
    private TextView describe;
    private Button acceptBtn;
    private TextView contact;

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
        contact = findViewById(R.id.requirement_contact_detail);
        acceptBtn = findViewById(R.id.requirement_btn);

        name.setText(intent.getStringExtra("name"));
        time.setText(intent.getStringExtra("time"));
        place.setText(intent.getStringExtra("place"));
        money.setText(intent.getStringExtra("money"));
        contact.setText(intent.getStringExtra("contact"));
        describe.setText(intent.getStringExtra("describe"));
        acceptBtn.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = getIntent();
        int cueOrderId = intent.getIntExtra("id",0);
        SharedPreferences sp = getSharedPreferences("user_info",MODE_PRIVATE);
        int curUserId = sp.getInt("userId",0);
        Toast.makeText(this, "Requirement Accepted", Toast.LENGTH_SHORT).show();
        HashMap params = new HashMap<>();
            params.put("orderId",cueOrderId);
            params.put("volunteerId",curUserId);
            JSONObject jsonObject = new JSONObject(params);
            OkGo.<Result<VolunteerFor>>post(AppConst.User.register)
                    .tag(this)
                    .upJson(jsonObject)
                    .execute(new JsonCallBack<Result<VolunteerFor>>() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onSuccess(Response<Result<VolunteerFor>> response) {
                            Log.d("register", response.body().getMessage());
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
