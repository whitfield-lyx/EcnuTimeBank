package com.example.ecnutimebank.ui.publish;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecnutimebank.R;

public class AcceptedFragment extends Fragment {

    private AcceptedViewModel mViewModel;

    public static AcceptedFragment newInstance() {
        return new AcceptedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("Accepted","create");
        return inflater.inflate(R.layout.accepted_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AcceptedViewModel.class);
        // TODO: Use the ViewModel
    }

}
