package com.example.ecnutimebank.ui.requirements;

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

public class RequirementsViewModel extends ViewModel {

    private MutableLiveData<List<Order>> requirementsList;
    private OnRequestDoneListener onRequestDoneListener;

    public RequirementsViewModel() {
        requirementsList = new MutableLiveData<>();
        requirementsList.setValue(new ArrayList<>());
//        Log.i("RequirementViewModel", "RequirementViewModel initialized");
//        todo 加载时间有点长 切出去之后会直接销毁 希望能保留下来 要么不释放内存 要么保存状态下次恢复
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
                        } else {
                            Log.e("RequirementsViewModel", "fail to load 10 more requirements");
                        }
                        onRequestDoneListener.onLoadMoreDone();
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
                        } else {
                            Log.e("RequirementsViewModel", "fail to refresh requirements");
                        }
                        onRequestDoneListener.onRefreshDone();
                    }
                });
    }

    public void load10MoreRequirementsByFilter(int type) {
        if (type <= 0) {
            load10MoreRequirements();
        } else {
            OkGo.<Result<List<Order>>>get(AppConst.Order.get_10_more_order_by_type + AppConst.Order.type_name[type] + "/offset/" + requirementsList.getValue().size())
                    .execute(new JsonCallBack<Result<List<Order>>>() {
                        @Override
                        public void onSuccess(Response<Result<List<Order>>> response) {
                            if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                                Log.i("RequirementsViewModel", "success to filter requirements by type " + type);
                                List<Order> list = requirementsList.getValue();
                                list.addAll(response.body().getData());
                                requirementsList.postValue(list);
                            } else {
                                Log.e("RequirementsViewModel", "fail to filter requirements");
                            }
                            onRequestDoneListener.onLoadMoreDone();
                        }
                    });
        }
    }

    public void refreshByFilter(int type) {
        if (type <= 0) {
            refresh();
        } else {
            clearData();
            OkGo.<Result<List<Order>>>get(AppConst.Order.get_10_more_order_by_type + AppConst.Order.type_name[type] + "/offset/" + requirementsList.getValue().size())
                    .execute(new JsonCallBack<Result<List<Order>>>() {
                        @Override
                        public void onSuccess(Response<Result<List<Order>>> response) {
                            if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                                Log.i("RequirementsViewModel", "success to filter requirements by type " + type);
                                List<Order> list = requirementsList.getValue();
                                list.addAll(response.body().getData());
                                requirementsList.postValue(list);
                            } else {
                                Log.e("RequirementsViewModel", "fail to filter requirements");
                            }
                            onRequestDoneListener.onRefreshDone();
                        }
                    });
        }
    }

    public void refreshBySearch(String keyword) {
        clearData();
        OkGo.<Result<List<Order>>>get(AppConst.Order.get_order_by_search + keyword)
                .execute(new JsonCallBack<Result<List<Order>>>() {
                    @Override
                    public void onSuccess(Response<Result<List<Order>>> response) {
                        if (response.body().getCode() == ResultCode.SUCCESS.getCode()) {
                            List<Order> list = requirementsList.getValue();
                            list.addAll(response.body().getData());
                            requirementsList.postValue(list);
                        } else {
                            Log.e("RequirementsViewModel", "fail to search requirements");
                        }
                    }
                });
    }

    public void clearData() {
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

        void onFilterDone();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
//        Log.i("RequirementViewModel", "onCleared() executed");
    }
}