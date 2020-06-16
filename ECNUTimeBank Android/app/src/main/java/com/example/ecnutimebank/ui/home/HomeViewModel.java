package com.example.ecnutimebank.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecnutimebank.entity.Facility;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

//LiveData遵循观察者模式
public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Facility>> facilityData;

    public HomeViewModel() {
        initFacilityData();
    }

 // 单例模式
    public MutableLiveData<List<Facility>> getFacilityData() {
        if (facilityData == null) {
            facilityData = new MutableLiveData<>();
        }
        return facilityData;
    }

    public void setFacilityData(MutableLiveData<List<Facility>> facilityData) {
        this.facilityData = facilityData;
    }

    public void initFacilityData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getFacilityData();
                OkGo.<Result<List<Facility>>>get(AppConst.Facility.get_all_facility)
                        .tag(this)
                        .execute(new JsonCallBack<Result<List<Facility>>>() {
                            @Override
                            public void onSuccess(Response<Result<List<Facility>>> response) {
                                if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                                    facilityData.postValue(response.body().getData());
                                } else {
                                    Log.d("Facility", "设施获取失败!");
                                }
                            }
                        });
            }
        }).start();
    }

    //清理数据
    @Override
    protected void onCleared()
    {
        super.onCleared();
    }
}