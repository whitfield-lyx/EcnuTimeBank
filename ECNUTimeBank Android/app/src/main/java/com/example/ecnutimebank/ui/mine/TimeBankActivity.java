package com.example.ecnutimebank.ui.mine;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.User;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.google.android.material.tabs.TabLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class TimeBankActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView balanceTextView;
    private FragmentPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timebank);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("我的时间券");
        }
        initData();
        initView();
    }

    private void initData() {
        tabs.add("现金");
        tabs.add("历史");
        fragments.add(new ExchangeCashFragment(this,tabs.get(0)));
        fragments.add(new ExchangeCashFragment(this,tabs.get(1)));
        SharedPreferences sp = getSharedPreferences("user_info", Context.MODE_MULTI_PROCESS);
        int curUserId = sp.getInt("userId",0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkGo.<Result<User>>get(AppConst.User.get_user+"/"+curUserId)
                        .tag(this)
                        .execute(new JsonCallBack<Result<User>>() {
                            @Override
                            public void onSuccess(Response<Result<User>> response) {
                                if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                                    User curUser = response.body().getData();
                                    double balance = curUser.getUserBalance();
                                    balanceTextView.setText("$"+balance);
                                }
                            }
                        });
            }
        }).start();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.exchange_tabs);
        viewPager = (ViewPager) findViewById(R.id.exchangeCash_viewPaper);
        balanceTextView = findViewById(R.id.balance_text_view);
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        //关联ViewPager和TabLayout
        tabLayout.setupWithViewPager(viewPager);
    }

    class TabAdapter extends FragmentPagerAdapter{
        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        //显示标签上的文字
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
