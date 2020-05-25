package com.example.ecnutimebank.ui.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

import com.example.ecnutimebank.R;


public class Profile_detail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_profile);
        System.out.println("1");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.change_profile,menu);
        return true;
    }

    /*Menu功能待实现*/

}

