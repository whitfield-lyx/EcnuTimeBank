package com.example.ecnutimebank.ui.requirements;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.ecnutimebank.App;
import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Order;
import com.example.ecnutimebank.helper.AppConst;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RequirementsFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener,
        RequirementAdapter.OnItemClickListener, Observer<List<Order>>, RequirementsViewModel.OnRequestDoneListener {

    private RequirementsViewModel requirementsViewModel;
    private List<Order> requirements = new ArrayList<>();
    private RecyclerView recyclerView;
    private RequirementAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SmartRefreshLayout refreshLayout;
    private AppCompatActivity activity;
    private Toolbar toolbar;
    private String name;
    private BottomNavigationViewEx navView;
    private MenuItem currentItem;
    private int selectedItem = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_requirements, container, false);
        setHasOptionsMenu(true);
        requirementsViewModel = ViewModelProviders.of(this).get(RequirementsViewModel.class);
        requirementsViewModel.getRequirementsList().observe(getViewLifecycleOwner(), this);
        requirementsViewModel.setOnRequestDoneListener(this);
        return root;
    }

    @Override
    public void onResume() {
        requirementsViewModel.refresh();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        currentItem.setEnabled(true);
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        activity = (AppCompatActivity) getActivity();
        toolbar = activity.findViewById(R.id.toolbar_requirements);
        navView = activity.findViewById(R.id.nav_view);
        currentItem = navView.getMenu().getItem(navView.getCurrentItem());
        currentItem.setEnabled(false);
        currentItem.setOnMenuItemClickListener(null);
        toolbar.setTitle("需求列表");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorSecondaryText));
        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        recyclerView = view.findViewById(R.id.requirements_recycler_view);
        adapter = new RequirementAdapter(requirements, this);
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
        inflater.inflate(R.menu.requirement_toolbar_menu, menu);
        MenuItem search = menu.findItem(R.id.requirement_search);
        SearchView mSearchView = (SearchView) search.getActionView();

        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                requirementsViewModel.refreshBySearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setOnSearchClickListener(new View.OnClickListener() { // 点击搜索的时候清空数据 禁止刷新和加载更多
            @Override
            public void onClick(View v) {
                requirementsViewModel.clearData();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                refreshLayout.setEnableRefresh(false);
                refreshLayout.setEnableLoadMore(false);
            }
        });
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (selectedItem <= 0) {
                    requirementsViewModel.refresh();
                } else {
                    requirementsViewModel.refreshByFilter(selectedItem);
                }
                refreshLayout.setEnableLoadMore(true);
                refreshLayout.setEnableRefresh(true);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (selectedItem <= 0) {
            requirementsViewModel.load10MoreRequirements();
        } else {
            requirementsViewModel.load10MoreRequirementsByFilter(selectedItem);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (selectedItem <= 0) {
            requirementsViewModel.refresh();
        } else {
            requirementsViewModel.refreshByFilter(selectedItem);
        }
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(activity, RequirementDetailActivity.class);
        Order order = adapter.getData().get(position);
        intent.putExtra("id", order.getOrderId());
        intent.putExtra("name", order.getOrderTitle());
        intent.putExtra("time", order.getOrderTime());
        intent.putExtra("money", order.getOrderBonus());
        intent.putExtra("place", order.getOrderAddress());
        intent.putExtra("contact", order.getOrderTelephone());
        intent.putExtra("describe", order.getOrderDescription());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.requirement_filter:
                showFilterDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("CheckResult")
    private void showFilterDialog() {
        String[] contentArray = AppConst.Order.type_name;
        MaterialDialog materialDialog = new MaterialDialog.Builder(getContext())
                .items(contentArray)//添加item内容数组
                .title(R.string.filter_dialog_title)
                .positiveText(R.string.filter_dialog_accept)
                .positiveColor(getResources().getColor(R.color.colorPrimaryDark))
                .itemsCallbackSingleChoice(selectedItem, (dialog, itemView, which, text) -> {
                    selectedItem = which;
                    requirementsViewModel.clearData();
                    requirementsViewModel.load10MoreRequirementsByFilter(selectedItem);
                    return true;
                })
                .build();
        materialDialog.show();
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
    public void onLoadMoreDone() {
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefreshDone() {
        refreshLayout.finishRefresh();
    }

    @Override
    public void onFilterDone() {

    }
}