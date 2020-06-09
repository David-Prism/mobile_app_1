package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
    private Button saveSettings;
    private String email;

    private SettingsViewModel settingsViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        dailyRemind = view.findViewById(R.id.input_daily_reminder);
        maxDist = view.findViewById(R.id.input_max_dist);
        genderPref = view.findViewById(R.id.input_gender);
        accountVis = view.findViewById(R.id.input_acct_vis);
        ageRangePref = view.findViewById(R.id.input_age_range_desired);
        saveSettings = view.findViewById(R.id.save_settings_button);

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        Intent intent = getActivity().getIntent();
        Bundle b = intent.getExtras();

        final Observer<List<SettingsEntity>> getSettingsObserver = newSettings -> {
            if (newSettings == null || newSettings.size() <= 0) {
                return;
            }
            SettingsEntity setting = newSettings.get(0);

            if (setting == null) {
                return;
            }

            dailyRemind.setText(setting.getTime());
            maxDist.setText(setting.getMaxDist());
            genderPref.setText(setting.getGender());
            accountVis.setText(setting.getAcct_vis());
            ageRangePref.setText(setting.getAgeRange());
        };

        // test code ------------------------------------

        String[] emails = {""};
        if(b != null) {
            if(b.containsKey(Constants.KEY_EMAIL)) {
                emails[0] = b.getString(Constants.KEY_EMAIL);
            }
        }


        saveSettings.setOnClickListener(v -> {

            Log.i(TAG, "click");

            SettingsEntity settingsEntity = new SettingsEntity();

            settingsEntity.setEmail(emails[0]);

            if(dailyRemind.getText().toString().equals("")) {
                dailyRemind.setText(R.string.none);
            }
            settingsEntity.setTime(dailyRemind.getText().toString());

            if(maxDist.getText().toString().equals("")) {
                maxDist.setText(R.string.none);
            }
            settingsEntity.setMaxDist(maxDist.getText().toString());

            if(genderPref.getText().toString().equals("")) {
                genderPref.setText(R.string.none);
            }
            settingsEntity.setGender(genderPref.getText().toString());

            if(accountVis.getText().toString().equals("")) {
                accountVis.setText(R.string.none);
            }
            settingsEntity.setAcct_vis(accountVis.getText().toString());

            if(ageRangePref.getText().toString().equals("")) {
                ageRangePref.setText(R.string.none);
            }
            settingsEntity.setAgeRange(ageRangePref.getText().toString());

            settingsViewModel.updateSettings(getContext(), settingsEntity);
        });

        // end test code --------------------------------


        settingsViewModel.loadAllByIds(getContext(), emails).observe(getViewLifecycleOwner(), getSettingsObserver);

        return view;
    }

//    public void updateDatabase(View view) {
//
//        SettingsEntity fakeSetting = new SettingsEntity();
//
//        if(email.equals("")) {
//            fakeSetting.setTime("");
//            fakeSetting.setMaxDist("");
//            fakeSetting.setGender("");
//            fakeSetting.setAcct_vis("");
//            fakeSetting.setAgeRange("");
//        }
//
//        settingsViewModel.updateSettings(getContext(), fakeSetting);
//
//        dailyRemind.setText(fakeSetting.getTime());
//        maxDist.setText(fakeSetting.getMaxDist());
//        genderPref.setText(fakeSetting.getGender());
//        accountVis.setText(fakeSetting.getAcct_vis());
//        ageRangePref.setText(fakeSetting.getAgeRange());
//    }

    public void deleteSetting(View view) {

        SettingsEntity currentSetting = new SettingsEntity();

        currentSetting.setTime(this.dailyRemind.getText().toString());
        currentSetting.setMaxDist(this.maxDist.getText().toString());
        currentSetting.setGender(this.genderPref.getText().toString());
        currentSetting.setAcct_vis(this.accountVis.getText().toString());
        currentSetting.setAgeRange(this.ageRangePref.getText().toString());

        settingsViewModel.deleteSettings(getContext(), currentSetting);

        dailyRemind.setText("");
        maxDist.setText("");
        genderPref.setText("");
        accountVis.setText("");
        ageRangePref.setText("");
    }

}

