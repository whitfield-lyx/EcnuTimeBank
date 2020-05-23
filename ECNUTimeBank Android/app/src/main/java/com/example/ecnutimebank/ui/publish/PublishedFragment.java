package com.example.ecnutimebank.ui.publish;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.ui.requirements.RequirementDetailActivity;

public class PublishedFragment extends Fragment {

    private PublishedViewModel mViewModel;

    public static PublishedFragment newInstance() {
        return new PublishedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("Published","create");
        return inflater.inflate(R.layout.published_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PublishedViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button button = view.findViewById(R.id.btn_go_to_publish_detail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PublishMoreDetailActivity.class);
                intent.putExtra("id", "123");
                intent.putExtra("name", "Name");
                intent.putExtra("time", "Tomorrow");
                intent.putExtra("money", "50");
                intent.putExtra("place", "School");
                intent.putExtra("describe", "123456789987654321234567898765432156879531354687653");
                startActivity(intent);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
