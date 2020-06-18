package com.example.ecnutimebank.ui.publish.published;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.ui.publish.published.employee.EmployeeDetailActivity;

public class PublishMoreDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView name;
    private TextView time;
    private TextView place;
    private TextView money;
    private TextView describe;
    private Button detailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        Intent intent = getIntent();
        name = findViewById(R.id.requirement_name_detail);
        time = findViewById(R.id.requirement_time_detail);
        place = findViewById(R.id.requirement_place_detail);
        money = findViewById(R.id.requirement_money_detail);
        describe = findViewById(R.id.requirement_describe_content);
        detailBtn = findViewById(R.id.requirement_btn);

        detailBtn.setText("查看志愿者");
        detailBtn.setOnClickListener(this);

        name.setText(intent.getStringExtra("name"));
        time.setText(intent.getStringExtra("time"));
        place.setText(intent.getStringExtra("place"));
        money.setText(intent.getIntExtra("money", 0) + "");
        describe.setText(intent.getStringExtra("describe"));
    }

    @Override
    public void onClick(View view) {
        Intent preIntent = getIntent();
        int curOrderId = preIntent.getIntExtra("id",0);
        Intent intent = new Intent(this, EmployeeDetailActivity.class);
        intent.putExtra("orderId",curOrderId);
        startActivity(intent);
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
