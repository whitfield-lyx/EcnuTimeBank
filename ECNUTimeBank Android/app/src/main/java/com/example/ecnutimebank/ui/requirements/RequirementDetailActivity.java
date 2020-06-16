package com.example.ecnutimebank.ui.requirements;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecnutimebank.R;

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

        name.setText("Name: " + intent.getStringExtra("name"));
        time.setText("Time: " + intent.getStringExtra("time"));
        place.setText("Place" + intent.getStringExtra("place"));
        money.setText("Money" + intent.getStringExtra("money"));
        contact.setText("Contact" + intent.getStringExtra("contact"));
        describe.setText("Describe" + intent.getStringExtra("describe"));
        acceptBtn.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Requirement Accepted", Toast.LENGTH_SHORT).show();
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
