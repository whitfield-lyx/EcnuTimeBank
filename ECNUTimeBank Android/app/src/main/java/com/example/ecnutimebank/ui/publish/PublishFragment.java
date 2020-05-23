package com.example.ecnutimebank.ui.publish;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.ecnutimebank.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PublishFragment extends Fragment {

    private TabLayout my_tablayout;
    private ViewPager my_viewpager;
    private int mode = TabLayout.MODE_FIXED;
    public static PublishFragment publishFragment;
    private PublishViewModel publishViewModel;

    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        publishViewModel =
                ViewModelProviders.of(this).get(PublishViewModel.class);
        View root = inflater.inflate(R.layout.fragment_publish, container, false);

        publishViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        initConentView(root);
        initData();
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton btn_send = getActivity().findViewById(R.id.publish_button);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick","click");
                Intent intent = new Intent(getActivity(),PublishDetailActivity.class);
                startActivity(intent);
            }
        });
    }


    public void initConentView(View viewContent) {
        this.my_tablayout = viewContent.findViewById(R.id.requirement_tabs);
        this.my_viewpager = viewContent.findViewById(R.id.requirement_content);
    }

    public void initData() {
        //创建一个viewpager的adapter
        PublishTabFragmentAdapter adapter = new PublishTabFragmentAdapter(getFragmentManager());
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new AcceptedFragment());
        fragments.add(new PublishedFragment());
        String[] titlesArr = {"已接收", "已发布"};
        adapter.setTitlesArr(titlesArr);
        adapter.setFragments(fragments);

        this.my_viewpager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        this.my_tablayout.setupWithViewPager(this.my_viewpager);
        my_tablayout.setTabMode(mode);
    }

}