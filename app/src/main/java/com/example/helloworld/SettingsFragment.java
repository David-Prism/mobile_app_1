package com.example.helloworld;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import androidx.lifecycle.Observer;

public class SettingsFragment extends Fragment {

    private static final String TAG = SettingsFragment.class.getSimpleName();

    private EditText dailyRemind;
    private EditText maxDist;
    private EditText genderPref;
    private EditText accountVis;
    private EditText ageRangePref;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        dailyRemind = view.findViewById(R.id.input_daily_reminder);
        maxDist = view.findViewById(R.id.input_max_dist);
        genderPref = view.findViewById(R.id.input_gender);
        accountVis = view.findViewById(R.id.input_acct_vis);
        ageRangePref = view.findViewById(R.id.input_age_range_desired);

        return view;
    }

//    final Observer<List<SettingsEntity>> getSettingsObserver
}

