package com.example.ecnutimebank.ui.publish;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class AcceptedFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    private PublishedViewModel mViewModel;
    private List<Requirement> accepted_requirements = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SmartRefreshLayout refreshLayout;

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

        recyclerView = view.findViewById(R.id.published_requirements_recycler_view);
        adapter = new PublishedAdapter(accepted_requirements);
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
        MenuItem search = menu.findItem(R.id.anime_search);
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
}
