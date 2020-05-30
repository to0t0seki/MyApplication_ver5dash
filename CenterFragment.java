package com.example.myapplication_ver5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

public class CenterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        DataViewModel dataViewModel = new ViewModelProvider(getActivity(),new ViewModelProvider.NewInstanceFactory()).get(DataViewModel.class);
        dataViewModel.liveData.observe(getViewLifecycleOwner(),(map)->{
            linearLayout.removeAllViews();
            TableLayout tableLayout = new TableLayout(getActivity());
            TableRow rowHeader = new TableRow(getActivity());

            TextView NOHeader = new TextView(getActivity());
            NOHeader.setText("NO");
            NOHeader.setBackgroundResource(R.drawable.border);
            NOHeader.setWidth(120);

            TextView BCHeader = new TextView(getActivity());
            BCHeader.setText("BC");
            BCHeader.setBackgroundResource(R.drawable.border);
            BCHeader.setWidth(120);

            TextView BTHeader = new TextView(getActivity());
            BTHeader.setText("BT");
            BTHeader.setBackgroundResource(R.drawable.border);
            BTHeader.setWidth(120);

            TextView HGHeader = new TextView(getActivity());
            HGHeader.setText("HG");
            HGHeader.setBackgroundResource(R.drawable.border);
            HGHeader.setWidth(120);

            TextView HBCHeader = new TextView(getActivity());
            HBCHeader.setText("HBC");
            HBCHeader.setBackgroundResource(R.drawable.border);
            HBCHeader.setWidth(120);

            rowHeader.addView(NOHeader);
            rowHeader.addView(BCHeader);
            rowHeader.addView(BTHeader);
            rowHeader.addView(HGHeader);
            rowHeader.addView(HBCHeader);

            tableLayout.addView(rowHeader);

            for (Object s:map.keySet()){
                String ss;
                TableRow tableRow = new TableRow(getActivity());
                Map<String,Integer>detailmap = (Map<String,Integer>)map.get(s);
                TextView textNO = new TextView(getActivity());
                textNO.setBackgroundResource(R.drawable.border);
                textNO.setText(s.toString());
      

                TextView textBC = new TextView(getActivity());
                textBC.setBackgroundResource(R.drawable.border);
                textBC.setText(String.valueOf(detailmap.get("BC")));


                TextView textBT = new TextView(getActivity());
                textBT.setBackgroundResource(R.drawable.border);
                textBT.setText(String.valueOf(detailmap.get("BT")));


                TextView textHG = new TextView(getActivity());
                textHG.setBackgroundResource(R.drawable.border);
                textHG.setText(String.valueOf(detailmap.get("HG")));


                TextView textHBC = new TextView(getActivity());
                textHBC.setBackgroundResource(R.drawable.border);
                textHBC.setText(String.valueOf(detailmap.get("HBC")));


                tableRow.addView(textNO);
                tableRow.addView(textBC);
                tableRow.addView(textBT);
                tableRow.addView(textHG);
                tableRow.addView(textHBC);

                tableLayout.addView(tableRow);
            }
            linearLayout.addView(tableLayout);
            Toast.makeText(getActivity() , "更新しました。", Toast.LENGTH_LONG).show();
        });

        return linearLayout;
    }
}
