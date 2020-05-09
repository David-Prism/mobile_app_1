package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.helloworld.R;

public class ThankYou extends AppCompatActivity {

    TextView textView;

    private static final String TAG = ThankYou.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        textView = findViewById(R.id.text_view_7);

        StringBuilder message = new StringBuilder("Thanks for signing up, ");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String name = "";

        if (b != null) {
            if (b.containsKey(Constants.KEY_USERNAME)) {
                name = b.getString(Constants.KEY_USERNAME);
            }
        }

        message.append(name).append("!");

        textView.setText(message);
    }
}
