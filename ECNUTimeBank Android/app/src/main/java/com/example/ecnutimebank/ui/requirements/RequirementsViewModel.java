package com.example.ecnutimebank.ui.requirements;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecnutimebank.entity.Order;
import com.example.ecnutimebank.entity.Requirement;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class RequirementsViewModel extends ViewModel {

    private MutableLiveData<List<Order>> requirementsList;
    private OnRequestDoneListener onRequestDoneListener;

    public RequirementsViewModel() {
        requirementsList = new MutableLiveData<>();
        requirementsList.setValue(new ArrayList<>());
//        todo 加载时间有点长 切出去之后会直接销毁 希望能保留下来 要么不释放内存 要么保存状态下次恢复
        load10MoreRequirements();
    }

    public void load10MoreRequirements() {
        OkGo.<Result<List<Order>>>get(AppConst.Order.get_10_more_order + "/offset/" + requirementsList.getValue().size())
                .execute(new JsonCallBack<Result<List<Order>>>() {
                    @Override
                    public void onSuccess(Response<Result<List<Order>>> response) {
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            Log.i("RequirementsViewModel", "success to load 10 more requirements");
                            List<Order> list = requirementsList.getValue();
                            list.addAll(response.body().getData());
                            requirementsList.postValue(list);
                            onRequestDoneListener.onLoadMoreDone();
                        } else {
                            Log.e("RequirementsViewModel", "fail to load 10 more requirements");
                        }
                    }
                });
    }

    public void refresh() {
        clearData();
        OkGo.<Result<List<Order>>>get(AppConst.Order.get_10_more_order + "/offset/0")
                .execute(new JsonCallBack<Result<List<Order>>>() {
                    @Override
                    public void onSuccess(Response<Result<List<Order>>> response) {
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            Log.i("RequirementsViewModel", "success to refresh requirements");
                            List<Order> list = requirementsList.getValue();
                            list.addAll(response.body().getData());
                            requirementsList.postValue(list);
                            onRequestDoneListener.onRefreshDone();
                        } else {
                            Log.e("RequirementsViewModel", "fail to refresh requirements");
                        }
                    }
                });
    }

    private void clearData() {
        requirementsList.getValue().clear();
    }

    public MutableLiveData<List<Order>> getRequirementsList() {
        return requirementsList;
    }

    public void setRequirementsList(MutableLiveData<List<Order>> requirementsList) {
        this.requirementsList = requirementsList;
    }

    public void setOnRequestDoneListener(OnRequestDoneListener onRequestDoneListener) {
        this.onRequestDoneListener = onRequestDoneListener;
    }

    interface OnRequestDoneListener {
        void onLoadMoreDone();
        void onRefreshDone();
    }
}