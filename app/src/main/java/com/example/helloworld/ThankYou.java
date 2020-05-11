package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.helloworld.R;

public class ThankYou extends AppCompatActivity {

    TextView textView7;
    TextView textView11;
    TextView textView12;

    private static final String TAG = ThankYou.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        textView7 = findViewById(R.id.text_view_7);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        StringBuilder message = new StringBuilder("");
        String name = "";

        if (b != null) {
            if (b.containsKey(Constants.KEY_USERNAME)) {
                name = b.getString(Constants.KEY_USERNAME);
            }
        }

        message.append(name).append("!");
        textView7.setText(message);

        String occupation = "";
        if(b != null) {
            if(b.containsKey(Constants.KEY_OCCUPATION)) {
                occupation = b.getString(Constants.KEY_OCCUPATION);
            }
        }

        String description = "";
        if(b != null) {
            if(b.containsKey(Constants.KEY_SELF_DESCRIPTION)) {
                description = b.getString(Constants.KEY_SELF_DESCRIPTION);
            }
        }
    }
}
