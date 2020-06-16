package com.example.ecnutimebank.ui.publish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Requirement;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class AcceptedFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener,AcceptedAdapter.OnItemClickListener {

    private PublishedViewModel mViewModel;
    private List<Requirement> accepted_requirements = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SmartRefreshLayout refreshLayout;
    private AppCompatActivity activity;

    public static PublishedFragment newInstance() {
        return new PublishedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_accepted, container, false);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PublishedViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        activity = (AppCompatActivity) getActivity();
        recyclerView = view.findViewById(R.id.accepted_requirements_recycler_view);
        adapter = new AcceptedAdapter(accepted_requirements,this);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        refreshLayout = view.findViewById(R.id.requirement_refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void onItemClicked(String id) {
        Intent intent = new Intent(activity, AcceptedDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", "Name");
        intent.putExtra("time", "Tomorrow");
        intent.putExtra("money", "50");
        intent.putExtra("place", "School");
        intent.putExtra("describe", "123456789987654321234567898765432156879531354687653");
        intent.putExtra("contact", "Contact");
        startActivity(intent);
    }
}