package com.example.ecnutimebank.ui.requirements;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RequirementsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RequirementsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is requirements fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}