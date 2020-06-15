package com.example.ecnutimebank.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Facility;
import com.example.ecnutimebank.entity.Requirement;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.ui.requirements.RequirementAdapter;
import com.example.ecnutimebank.ui.requirements.RequirementDetailActivity;
import com.ryane.banner.AdPageInfo;
import com.ryane.banner.AdPlayBanner;
import com.ryane.banner.transformer.RotateDownTransformer;
import com.ryane.banner.transformer.ZoomOutPageTransformer;
import com.ryane.banner.view.TitleView;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.sunfusheng.marqueeview.MarqueeView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import static com.ryane.banner.AdPlayBanner.ImageLoaderType.FRESCO;
import static com.ryane.banner.AdPlayBanner.ImageLoaderType.GLIDE;
import static com.ryane.banner.AdPlayBanner.ImageLoaderType.PICASSO;
import static com.ryane.banner.AdPlayBanner.IndicatorType.POINT_INDICATOR;
import static com.ryane.banner.view.TitleView.Gravity.PARENT_BOTTOM;

public class HomeFragment extends Fragment implements  OnRefreshListener, OnLoadMoreListener, HomeAdapter.OnItemClickListener {


    private List<Facility> facilityList = new ArrayList<>();
    private HomeViewModel homeViewModel;
    private AdPlayBanner mAdPlayBanner;
    // 滚动文字条 private MarqueeView marqueeView;
    private AppCompatActivity activity;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        activity = (AppCompatActivity) getActivity();

        //观察者模式 读取Facility数据
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getFacilityData().observe(getViewLifecycleOwner(), new Observer<List<Facility>>() {
            @Override
            public void onChanged(@Nullable final List<Facility> newFacilityList) {
                adapter.setFacilityList(newFacilityList);
                adapter.notifyDataSetChanged();
                Log.d("FacilityList", "onChange: "+newFacilityList.toString());
            }
        });

        //设置滚动图片
        mAdPlayBanner = view.findViewById(R.id.place_banner);
        initAdPlayer();
        //初始化瀑布卡片
        recyclerView = view.findViewById(R.id.home_recycler_view);
        adapter = new HomeAdapter(facilityList, this);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);



        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;

/*通知滚动条
        marqueeView = view.findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("11111111111111");
        info.add("22222222222222");
        info.add("33333333333333");
        info.add("44444444444444");
        info.add("55555555555555");
        info.add("66666666666666");
        marqueeView.startWithList(info);
// 在代码里设置自己的动画
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(activity.getApplicationContext(),  textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
*/
    }

    @Override
    public void onDestroy() {
        if (mAdPlayBanner != null) {
            // 结束时需要调用stop释放资源
            mAdPlayBanner.stop();
        }
        super.onDestroy();

    }
    @Override
    public void onStart() {
        super.onStart();
        //marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        //marqueeView.stopFlipping();
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
        Intent intent = new Intent(activity, HomePlaceDetailActivity.class);
        startActivity(intent);
    }

    public void initAdPlayer(){
        List<AdPageInfo> dataTest = new ArrayList<>();
        AdPageInfo info1 = new AdPageInfo("华师大闵行校区", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590330696394&di=d6cc0e9ea89965a3785a91a257ae7410&imgtype=0&src=http%3A%2F%2Fstatic3.loupan.com%2Fnewsimg%2Fimage%2F2019%2F0613%2F1518513473890.jpg%3FimageView2%2F3%2Fw%2F800%2Fh%2F600%2Fq%2F75", "", 2);
        AdPageInfo info2 = new AdPageInfo("华师大中北校区", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590330696394&di=b18bf60119e8f2831ce7f3599004089e&imgtype=0&src=http%3A%2F%2Fimgbdb2.bendibao.com%2Fshanghai%2F20141%2F8%2F20141815944759.jpg", "", 1);
        dataTest.add(info1);
        dataTest.add(info2);

        mAdPlayBanner
                .setImageLoadType(GLIDE)
                .setOnPageClickListener(new AdPlayBanner.OnPageClickListener() {
                    @Override
                    public void onPageClick(AdPageInfo info, int position) {
                        Intent intent = new Intent(activity, HomePlaceDetailActivity.class);
                        intent.putExtra("description", "这是一个设施的详细信息。");
                        startActivity(intent);
                    }
                })
                .setAutoPlay(true)
                .setIndicatorType(POINT_INDICATOR)
                .setNumberViewColor(0xcc00A600, 0xccea0000, 0xffffffff)
                .setInterval(3000)
                .setImageViewScaleType(AdPlayBanner.ScaleType.CENTER_CROP)
                //.addTitleView(new TitleView(getContext()).setPosition(PARENT_BOTTOM).setTitlePadding(5, 5, 5, 5).setTitleMargin(0, 0, 0, 25).setTitleSize(15).setViewBackground(0x55000000).setTitleColor(getResources().getColor(R.color.white)))
                //.addTitleView(TitleView.getDefaultTitleView(getApplicationContext()))
                .setBannerBackground(0xff565656)
                .setPageTransformer(new ZoomOutPageTransformer())
                .setInfoList(dataTest)
                // 设置不可以手动滑动
                .setCanScroll(true)
                .setOnPagerChangeListener(new AdPlayBanner.OnPagerChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                })
                .setUp();
    }
}
