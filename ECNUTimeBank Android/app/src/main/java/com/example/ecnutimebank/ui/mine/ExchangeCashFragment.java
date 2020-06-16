package com.example.ecnutimebank.ui.mine;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecnutimebank.R;

public class ExchangeCashFragment extends Fragment {
    private Context context;
    private String content; //Fragment的显示内容
    public ExchangeCashFragment() {

    }

    public ExchangeCashFragment(Context context,String content){
        this.context = context;
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(context);
        textView.setText(content);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
