package com.example.ecnutimebank.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ecnutimebank.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ImageView settings = view.findViewById(R.id.settings_imageView);
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
        settings.setOnClickListener(this);
        profile.setOnClickListener(this);
        next.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_imageView:
                Intent intent1 = new Intent(getActivity().getApplicationContext(), SettingsActivity.class);
                startActivity(intent1);
                break;
            case R.id.profile_imageView:
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
