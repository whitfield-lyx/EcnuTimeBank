package com.example.ecnutimebank.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.ui.requirements.RequirementAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private MenuItem menuItem;
    private BottomNavigationViewEx bottomNavigationViewEx;
    private AppCompatActivity appCompatActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        appCompatActivity = (AppCompatActivity) getActivity();
        Toolbar toolbar = appCompatActivity.findViewById(R.id.toolbar_mine);
        bottomNavigationViewEx=appCompatActivity.findViewById(R.id.nav_view);
        menuItem = bottomNavigationViewEx.getMenu().getItem(bottomNavigationViewEx.getCurrentItem());
        menuItem.setEnabled(false);
        menuItem.setOnMenuItemClickListener(null);
        appCompatActivity.setSupportActionBar(toolbar);
        toolbar.setTitle("我的个人信息");
        initView(view);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        menuItem.setEnabled(true);
        super.onDestroyView();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.mine_toolbar_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(appCompatActivity,SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(View view) {
        ImageView profile = view.findViewById(R.id.profile_imageView);
        ImageView next = view.findViewById(R.id.next_imageView);
        TextView nickname = view.findViewById(R.id.nickname_textView);
        TextView account_number = view.findViewById(R.id.accountNumber_textView);
        TextView my_bank = view.findViewById(R.id.myBank_textView);
        TextView identity_authentication = view.findViewById(R.id.identityAuthentication_textView);
        nickname.setOnClickListener(this);
        account_number.setOnClickListener(this);
        my_bank.setOnClickListener(this);
        identity_authentication.setOnClickListener(this);
        profile.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_imageView:
                Intent intent1 = new Intent(getActivity().getApplicationContext(),ProfileDetailActivity.class);
                startActivity(intent1);
                break;
            case R.id.nickname_textView:
            case R.id.accountNumber_textView:
            case R.id.next_imageView:
                Intent intent2 = new Intent(getActivity().getApplicationContext(), PersonalInformationActivity.class);
                startActivity(intent2);
                break;
            case R.id.identityAuthentication_textView:
                Intent intent3 = new Intent(getActivity().getApplicationContext(), IdentityAuthenticationActivity.class);
                startActivity(intent3);
                break;
            case R.id.myBank_textView:
                Intent intent4 = new Intent(getActivity().getApplicationContext(), TimeBankActivity.class);
                startActivity(intent4);
        }
    }
}
