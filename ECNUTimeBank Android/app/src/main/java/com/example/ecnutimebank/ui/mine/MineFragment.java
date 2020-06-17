package com.example.ecnutimebank.ui.mine;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ecnutimebank.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private MenuItem menuItem;
    private BottomNavigationViewEx bottomNavigationViewEx;
    private AppCompatActivity appCompatActivity;
    private int curUserId ;
    private TextView accountNumberText;
    private TextView nickname;
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
        bottomNavigationViewEx=appCompatActivity.findViewById(R.id.nav_view);
        menuItem = bottomNavigationViewEx.getMenu().getItem(bottomNavigationViewEx.getCurrentItem());
        menuItem.setEnabled(false);
        menuItem.setOnMenuItemClickListener(null);
        initView(view);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        menuItem.setEnabled(true);
        super.onDestroyView();
    }

    private void initView(View view) {
        CircleImageView circle = view.findViewById(R.id.profile_imageView);
        TextView my_bank = view.findViewById(R.id.myBank_textView);
        TextView identity_authentication = view.findViewById(R.id.identityAuthentication_textView);
        ImageView img = view.findViewById(R.id.mine_settings);
        accountNumberText = view.findViewById(R.id.accountNumber_textView);
        nickname=view.findViewById(R.id.nickname_textView);

        my_bank.setOnClickListener(this);
        identity_authentication.setOnClickListener(this);
        circle.setOnClickListener(this);
        img.setOnClickListener(this);
    }
    private void initData(){
        SharedPreferences sp =getActivity().getSharedPreferences("user_info",Context.MODE_PRIVATE);
        curUserId = sp.getInt("userId",0);
        String userName =sp.getString("userName","张三");
        String telephone = sp.getString("telephone","17333333333");
        nickname.setText(userName);
        accountNumberText.setText(telephone);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_imageView:
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
                break;
            case R.id.mine_settings:
                Intent intent5 = new Intent(appCompatActivity,SettingsActivity.class);
                startActivity(intent5);
                break;
        }
    }
}