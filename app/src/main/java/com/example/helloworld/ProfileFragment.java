package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private TextView textView7;
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;

    private static final String TAG = ThankYou.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        textView7 = view.findViewById(R.id.text_view_7);
        textView11 = view.findViewById(R.id.text_view_11);
        textView12 = view.findViewById(R.id.text_view_12);
        textView13 = view.findViewById(R.id.text_view_13);

        // textView12.setWidth(findViewById(R.id.image_view_1).getWidth());

        Intent intent = getActivity().getIntent();
        Bundle b = intent.getExtras();

        StringBuilder message = new StringBuilder("");
        String name = "";

        if (b != null) {
            if (b.containsKey(Constants.KEY_USERNAME)) {
                name = b.getString(Constants.KEY_USERNAME);
            }
        }

        message.append(name);
        textView7.setText(message);

        String age = "";
        if(b != null) {
            if(b.containsKey(Constants.KEY_AGE)) {
                age = b.getString(Constants.KEY_AGE);
            }
        }
        textView13.setText(age);

        String occupation = "";
        if(b != null) {
            if(b.containsKey(Constants.KEY_OCCUPATION)) {
                occupation = b.getString(Constants.KEY_OCCUPATION);
            }
        }
        textView11.setText(occupation);

        String description = "";
        if(b != null) {
            if(b.containsKey(Constants.KEY_SELF_DESCRIPTION)) {
                description = b.getString(Constants.KEY_SELF_DESCRIPTION);
            }
        }
        textView12.setText(description);

        return view;
    }
}
