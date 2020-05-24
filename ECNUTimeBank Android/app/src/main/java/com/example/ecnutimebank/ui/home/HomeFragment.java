package com.example.ecnutimebank.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.ryane.banner.AdPageInfo;
import com.ryane.banner.AdPlayBanner;
import com.ryane.banner.transformer.RotateDownTransformer;
import com.ryane.banner.view.TitleView;

import java.util.ArrayList;
import java.util.List;

import static com.ryane.banner.AdPlayBanner.ImageLoaderType.FRESCO;
import static com.ryane.banner.AdPlayBanner.ImageLoaderType.GLIDE;
import static com.ryane.banner.AdPlayBanner.ImageLoaderType.PICASSO;
import static com.ryane.banner.AdPlayBanner.IndicatorType.POINT_INDICATOR;
import static com.ryane.banner.view.TitleView.Gravity.PARENT_BOTTOM;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private AdPlayBanner mAdPlayBanner;
    private AppCompatActivity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        activity = (AppCompatActivity) getActivity();
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        List<AdPageInfo> dataTest = new ArrayList<>();
        AdPageInfo info1 = new AdPageInfo("华师大闵行校区", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590330696394&di=d6cc0e9ea89965a3785a91a257ae7410&imgtype=0&src=http%3A%2F%2Fstatic3.loupan.com%2Fnewsimg%2Fimage%2F2019%2F0613%2F1518513473890.jpg%3FimageView2%2F3%2Fw%2F800%2Fh%2F600%2Fq%2F75", "", 2);
        AdPageInfo info2 = new AdPageInfo("华师大中北校区", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590330696394&di=b18bf60119e8f2831ce7f3599004089e&imgtype=0&src=http%3A%2F%2Fimgbdb2.bendibao.com%2Fshanghai%2F20141%2F8%2F20141815944759.jpg", "", 1);
        dataTest.add(info1);
        dataTest.add(info2);

        mAdPlayBanner = view.findViewById(R.id.place_banner);

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
                .setImageViewScaleType(AdPlayBanner.ScaleType.FIT_CENTER)
                .addTitleView(new TitleView(getContext()).setPosition(PARENT_BOTTOM).setTitlePadding(5, 5, 5, 5).setTitleMargin(0, 0, 0, 25).setTitleSize(15).setViewBackground(0x55000000).setTitleColor(getResources().getColor(R.color.white)))
                //.addTitleView(TitleView.getDefaultTitleView(getApplicationContext()))
                .setBannerBackground(0xff565656)
                .setPageTransformer(new RotateDownTransformer())
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
        return view;
    }

    @Override
    public void onDestroy() {
        if (mAdPlayBanner != null) {
            // 结束时需要调用stop释放资源
            mAdPlayBanner.stop();
        }
        super.onDestroy();

    }
}