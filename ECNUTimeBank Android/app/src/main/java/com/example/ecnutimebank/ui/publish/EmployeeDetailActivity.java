/**
 * @author K.makise on 2020/5/23
 */


package com.example.ecnutimebank.ui.publish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetailActivity extends AppCompatActivity implements EmployeeDetailAdapter.OnItemClickListener {
    private List<Employee> employees = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_published_detail);

        RecyclerView recyclerView = findViewById(R.id.employee_recycler_view);
        EmployeeDetailAdapter adapter = new EmployeeDetailAdapter(employees, this);
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
    public void onAccept(String id) {
        Toast.makeText(this, "accepted" + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefuse(String id) {
        Toast.makeText(this, "refused" + id, Toast.LENGTH_SHORT).show();
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
