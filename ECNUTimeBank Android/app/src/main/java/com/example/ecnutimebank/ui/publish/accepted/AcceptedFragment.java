package com.example.ecnutimebank.ui.publish.accepted;

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
import com.example.ecnutimebank.ui.publish.published.PublishedFragment;
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

    @Override
    public void onResume() {
        acceptedViewModel.refresh(getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE).getInt("userId", 1));
        super.onResume();
    }

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

        super.onViewCreated(view, savedInstanceState);
    }



    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(activity, AcceptedDetailActivity.class);
        Order order = adapter.getData().get(position);
        intent.putExtra("id", order.getOrderId());
        intent.putExtra("name", order.getOrderTitle());
        intent.putExtra("time", order.getOrderTime());
        intent.putExtra("money", order.getOrderBonus());
        intent.putExtra("place", order.getOrderAddress());
        intent.putExtra("describe", order.getOrderDescription());
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

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        acceptedViewModel.load10MoreRequirements(getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE).getInt("userId", 1));
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//        todo 这个userId获取应该抽离出去
        acceptedViewModel.refresh(getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE).getInt("userId", 1));
    }
}