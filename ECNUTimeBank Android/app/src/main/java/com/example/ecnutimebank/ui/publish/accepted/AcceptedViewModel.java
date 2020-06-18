package com.example.ecnutimebank.ui.publish.accepted;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecnutimebank.entity.Order;
import com.example.ecnutimebank.helper.AppConst;
import com.example.ecnutimebank.helper.JsonCallBack;
import com.example.ecnutimebank.helper.Result;
import com.example.ecnutimebank.helper.ResultCode;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

public class AcceptedViewModel extends ViewModel {
    private MutableLiveData<List<Order>> requirementsList;
    private OnRequestDoneListener onRequestDoneListener;

    public AcceptedViewModel() {
        requirementsList = new MutableLiveData<>();
        requirementsList.setValue(new ArrayList<>());
//        Log.i("RequirementViewModel", "RequirementViewModel initialized");
//        todo 加载时间有点长 切出去之后会直接销毁 希望能保留下来 要么不释放内存 要么保存状态下次恢复
    }

    public void load10MoreRequirements(int userId) {
        OkGo.<Result<List<Order>>>get(AppConst.Order.get_10_more_accepted_order + userId + "/offset/" + requirementsList.getValue().size())
                .execute(new JsonCallBack<Result<List<Order>>>() {
                    @Override
                    public void onSuccess(Response<Result<List<Order>>> response) {
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            Log.i("AcceptedViewModel", "success to load 10 more published requirements of user " + userId);
                            List<Order> list = requirementsList.getValue();
                            list.addAll(response.body().getData());
                            requirementsList.postValue(list);
                        } else {
                            Log.e("AcceptedViewModel", "fail to load 10 more requirements");
                        }
                        onRequestDoneListener.onLoadMoreDone();
                    }
                });
    }

    public void refresh(int userId) {
        clearData();
        OkGo.<Result<List<Order>>>get(AppConst.Order.get_10_more_accepted_order + userId + "/offset/" + requirementsList.getValue().size())
                .execute(new JsonCallBack<Result<List<Order>>>() {
                    @Override
                    public void onSuccess(Response<Result<List<Order>>> response) {
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            Log.i("PublishedViewModel", "success to refresh published requirements of user " + userId);
                            List<Order> list = requirementsList.getValue();
                            list.addAll(response.body().getData());
                            requirementsList.postValue(list);
                        } else {
                            Log.e("PublishedViewModel", "fail to refresh requirements");
                        }
                        onRequestDoneListener.onRefreshDone();
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
