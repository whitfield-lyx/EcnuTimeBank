package com.example.ecnutimebank.ui.publish;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Order;
import com.example.ecnutimebank.ui.requirements.RequirementAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class AcceptedFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener, RequirementAdapter.OnItemClickListener,
        AcceptedViewModel.OnRequestDoneListener, Observer<List<Order>> {

    private AcceptedViewModel acceptedViewModel;
    private List<Order> accepted_requirements = new ArrayList<>();
    private RecyclerView recyclerView;
    private RequirementAdapter adapter;
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
        acceptedViewModel = ViewModelProviders.of(this).get(AcceptedViewModel.class);
        acceptedViewModel.setOnRequestDoneListener(this);
        acceptedViewModel.getRequirementsList().observe(getViewLifecycleOwner(), this);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        activity = (AppCompatActivity) getActivity();
        recyclerView = view.findViewById(R.id.accepted_requirements_recycler_view);
        adapter = new RequirementAdapter(accepted_requirements,this);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        refreshLayout = view.findViewById(R.id.requirement_refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

        acceptedViewModel.load10MoreRequirements(getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE).getInt("userId", 1));

        super.onViewCreated(view, savedInstanceState);
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