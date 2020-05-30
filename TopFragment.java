package com.example.myapplication_ver5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class TopFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getActivity());

        Button button = new Button(getActivity());
        button.setText("絆データ");
        DataViewModel dataViewModel = new ViewModelProvider(getActivity(),new ViewModelProvider.NewInstanceFactory()).get(DataViewModel.class);
        button.setOnClickListener((v)->{
            new Kizuna().setCallbackInstance((resultMap)->{
                dataViewModel.liveData.postValue(resultMap);
            }).start();
        });
        linearLayout.addView(button);
        return linearLayout;
    }
}
