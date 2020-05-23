package com.example.ecnutimebank.ui.requirements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecnutimebank.R;

public class RequirementDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requirement_detail);

        Intent intent = getIntent();

    }
}
