package com.example.ecnutimebank.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecnutimebank.R;

public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        ImageView backToAboutMe = findViewById(R.id.Se_backToAboutMe_imageView);
        backToAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView textSizeSet = findViewById(R.id.sizeSetting_textView);
        textSizeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
