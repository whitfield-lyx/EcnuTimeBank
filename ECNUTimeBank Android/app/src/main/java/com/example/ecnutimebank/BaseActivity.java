package com.example.ecnutimebank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ecnutimebank.ui.publish.PublishDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.example.ecnutimebank.components.*;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //BottomNavigationView navView = findViewById(R.id.nav_view);
        CustomBottomNavigationViewEx navView = findViewById(R.id.nav_view);
        navView.enableAnimation(false);
                // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_requirements, R.id.navigation_publish, R.id.navigation_mine)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        FloatingActionButton published_btn = findViewById(R.id.publish_button);
        published_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(BaseActivity.this, PublishDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
