package com.example.ecnutimebank.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecnutimebank.R;

public class Personal_Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info_layout);
        ImageView backToAboutMe = findViewById(R.id.MI_backToAboutMe_imageView);
        backToAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
