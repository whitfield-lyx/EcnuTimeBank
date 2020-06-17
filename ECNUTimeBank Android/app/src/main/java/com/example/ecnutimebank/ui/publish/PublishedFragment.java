package com.example.ecnutimebank.ui.publish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
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
import com.example.ecnutimebank.entity.Order;
import com.example.ecnutimebank.entity.Requirement;
import com.example.ecnutimebank.ui.requirements.RequirementAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import java.util.ArrayList;
import java.util.List;

public class PublishedFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener,
        RequirementAdapter.OnItemClickListener, PublishedViewModel.OnRequestDoneListener, Observer<List<Order>> {

    private List<Order> published_requirements = new ArrayList<>();
    private RecyclerView recyclerView;
    private RequirementAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SmartRefreshLayout refreshLayout;
    private AppCompatActivity activity;
    private PublishedViewModel publishedViewModel;

    public static PublishedFragment newInstance() {
        return new PublishedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_published, container, false);
        publishedViewModel = ViewModelProviders.of(this).get(PublishedViewModel.class);
        publishedViewModel.getRequirementsList().observe(getViewLifecycleOwner(), this);
        publishedViewModel.setOnRequestDoneListener(this);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        activity = (AppCompatActivity) getActivity();
        recyclerView = view.findViewById(R.id.published_requirements_recycler_view);
        adapter = new RequirementAdapter(published_requirements, this);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        refreshLayout = view.findViewById(R.id.requirement_refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

//        publishedViewModel.load10MoreRequirements(getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE).getInt("userId", 1));
        publishedViewModel.load10MoreRequirements(getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE).getInt("userId", 1));

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        publishedViewModel.load10MoreRequirements(getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE).getInt("userId", 1));
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        publishedViewModel.refresh(getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE).getInt("userId", 1));
    }

    @Override
    public void onItemClicked(String id) {
        Intent intent = new Intent(activity, PublishMoreDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", "Name");
        intent.putExtra("time", "Tomorrow");
        intent.putExtra("money", "50");
        intent.putExtra("place", "School");
        intent.putExtra("describe", "123456789987654321234567898765432156879531354687653");
        startActivity(intent);
    }

    @Override
    public void onLoadMoreDone() {
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefreshDone() {
        refreshLayout.finishRefresh();
    }

    @Override
    public void onChanged(List<Order> orders) {
        adapter.setData(orders);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
