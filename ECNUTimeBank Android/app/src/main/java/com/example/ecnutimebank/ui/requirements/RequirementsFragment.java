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
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Requirement;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RequirementsFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener, RequirementAdapter.OnItemClickListener {

    private RequirementsViewModel requirementsViewModel;
    private List<Requirement> requirements = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SmartRefreshLayout refreshLayout;
    private AppCompatActivity activity;
    private Toolbar toolbar;
    private BottomNavigationViewEx navView;
    private MenuItem currentItem;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_requirements, container, false);
        setHasOptionsMenu(true);
        return root;
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
                Log.i("Requirements", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
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
        Intent intent = new Intent(activity, RequirementDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("name", "Name");
        intent.putExtra("time", "Tomorrow");
        intent.putExtra("money", "50");
        intent.putExtra("place", "School");
        intent.putExtra("contact", "Contact");
        intent.putExtra("describe", "123456789987654321234567898765432156879531354687653");
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
        String [] contentArray = {"一","二","三","四","五"};
        MaterialDialog materialDialog = new MaterialDialog.Builder(getContext())
                .items(contentArray)//添加item内容数组
                .title(R.string.filter_dialog_title)
                .positiveText(R.string.filter_dialog_accept)
                .positiveColor(getResources().getColor(R.color.colorPrimaryDark))
                .itemsCallbackMultiChoice(null, (dialog, which, text) -> {
                    Log.w("Dialog", Arrays.toString(which));
                    return true;
                })
                .build();
        materialDialog.show();
    }
}