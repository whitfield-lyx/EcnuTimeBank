package com.example.ecnutimebank.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecnutimebank.entity.Facility;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Facility>> facilityData;

 //to do LiveData

    public MutableLiveData<List<Facility>> getFacilityData() {
        if (facilityData == null) {
            facilityData = new MutableLiveData<>();
        }
        return facilityData;
    }

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }


    public LiveData<String> getText() {
        return mText;
    }

    @Override
    protected void onCleared()
    {
        super.onCleared();
    }
}